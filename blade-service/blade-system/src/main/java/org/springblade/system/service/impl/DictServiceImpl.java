/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.common.constant.CommonConstant;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.ServiceCode;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.system.entity.Dict;
import org.springblade.system.mapper.DictMapper;
import org.springblade.system.service.IDictService;
import org.springblade.system.vo.DictVO;
import org.springblade.system.wrapper.DictWrapper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springblade.core.cache.constant.CacheConstant.DICT_CACHE;


/**
 * 服务实现类
 *
 * @author Chill
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

	@Override
	public IPage<DictVO> selectDictPage(IPage<DictVO> page, DictVO dict) {
		return page.setRecords(baseMapper.selectDictPage(page, dict));
	}

	@Override
	public List<DictVO> tree() {
		return ForestNodeMerger.merge(baseMapper.tree());
	}

	@Override
	public List<DictVO> parentTree() {
		return ForestNodeMerger.merge(baseMapper.parentTree());
	}

	@Override
	public String getValue(String code, String dictKey) {
		return Func.toStr(baseMapper.getValue(code, dictKey), StringPool.EMPTY);
	}

	@Override
	public List<Dict> getList(String code) {
		return baseMapper.getList(code);
	}

	@Override
	public boolean submit(Dict dict) {
		LambdaQueryWrapper<Dict> lqw = Wrappers.<Dict>query().lambda().eq(Dict::getCode, dict.getCode()).eq(Dict::getDictKey, dict.getDictKey());
		Integer cnt = baseMapper.selectCount((Func.isEmpty(dict.getId())) ? lqw : lqw.notIn(Dict::getId, dict.getId()));
		if (cnt > 0) {
			throw new ServiceException("当前字典键值已存在!");
		}
		if (Func.isEmpty(dict.getParentId())) {
			dict.setParentId(BladeConstant.TOP_PARENT_ID);
		}
		dict.setIsDeleted(BladeConstant.DB_NOT_DELETED);
		CacheUtil.clear(DICT_CACHE);
		try {
			dict.setDictKey(URLDecoder.decode(dict.getDictKey(), "UTF-8"));
		}catch (UnsupportedEncodingException e){
			log.error("【错误码{}】：系统字典保存或修改，字符转码发生错误", ServiceCode.CHARACTER_DECODE_FAIL.getCode());
			throw new ServiceException(ServiceCode.CHARACTER_DECODE_FAIL);
		}
		return saveOrUpdate(dict);
	}

	@Override
	public boolean removeDict(String ids) {
		Integer cnt = baseMapper.selectCount(Wrappers.<Dict>query().lambda().in(Dict::getParentId, Func.toLongList(ids)));
		if (cnt > 0) {
			throw new ServiceException("请先删除子节点!");
		}
		return removeByIds(Func.toLongList(ids));
	}

	@Override
	public IPage<DictVO> parentList(Map<String, Object> dict, Query query) {
		IPage<Dict> page = this.page(Condition.getPage(query), Condition.getQueryWrapper(dict, Dict.class).lambda().eq(Dict::getParentId, CommonConstant.TOP_PARENT_ID).orderByAsc(Dict::getSort));
		return DictWrapper.build().pageVO(page);
	}

	@Override
	public IPage<DictVO> childList(Map<String, Object> dict, Long parentId, Query query) {
		dict.remove(CommonConstant.PARENT_ID);
		IPage<Dict> page = this.page(Condition.getPage(query), Condition.getQueryWrapper(dict, Dict.class).lambda().eq(Dict::getParentId, parentId).orderByAsc(Dict::getSort));
		return DictWrapper.build().pageVO(page);
	}

	@Override
	public List<DictVO> lazyList(Long parentId, Map<String, Object> param) {
		if (Func.isEmpty(Func.toStr(param.get(CommonConstant.PARENT_ID)))) {
			parentId = null;
		}
		return baseMapper.lazyList(parentId, param);
	}

	List<Long> result =new ArrayList<>();
	/**
	 * @Description:  根据ids 递归查询子节点
	 * @Param:  ids
	 * @return:void
	 * @Author: lm
	 * @Date:  2020年6月19日
	 */
	public void selectChild(List<Long> ids) {
		List<Long> temp = new ArrayList<>();
		ids.forEach(id -> {
			QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>();
			queryWrapper.eq("parent_id", id);
			List<Dict> dictList = this.baseMapper.selectList(queryWrapper);
			result.add(id);
			dictList.forEach(dict -> {
				temp.add(dict.getId());
				result.add(dict.getId());
				if (temp.size() != 0 && temp != null) {
					selectChild(temp);
				};
			});
		});
	}

	/**
	* @Description: 重新定义删除方法
	* @Param:ids
	* @return:boolean
	* @Author: lm
	* @Date:  2020年6月19日
	*/
	@Override
	public Boolean deleteIds(String ids){
		List<Long> idList =Func.toLongList(ids);
		//递归查询
		result =new ArrayList<>();
		this.selectChild(idList);
		return removeByIds(result);

	}

	@Override
	public Map<String,List<Dict>> dictionaryByCodes(List<String> codes) {
		List<Dict> dictList = baseMapper.dictionaryByCodes(codes);
		if (dictList.isEmpty()){
			return null;
		}
		Map<String, List<Dict>> collect = dictList.stream().collect(Collectors.groupingBy(Dict::getCode));
		return collect;
	}

	@Override
	public IPage<Dict> pageList(IPage<Dict> page, Dict dict) {
		return baseMapper.pageList(page, dict);
	}
}
