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
package org.springblade.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.vo.DictBizVO;

import java.util.List;
import java.util.Map;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface DictBizMapper extends BaseMapper<DictBiz> {

	/**
	 * 获取字典表对应中文
	 *
	 * @param code    字典编号
	 * @param dictKey 字典序号
	 * @return
	 */
	String getValue(String code, String dictKey);
	/**
	 * 获取字典表对应键值
	 *
	 * @param code    字典编号
	 * @param dictValue 字典序号
	 * @return
	 */
	String getKey(String code, String dictValue);
	/**
	 * 获取字典表对应中文
	 *
	 * @param code    字典编号
	 * @param id 父主键
	 * @return
	 */
	String getValues(String code, Long id);
	/**
	 * 获取字典表
	 *
	 * @param code 字典编号
	 * @return
	 */
	List<DictBiz> getList(String code);

	/**
	 * 获取树形节点
	 *
	 * @return
	 */
	List<DictBizVO> tree(String code);


	/**
	 * 获取树形节点map
	 *
	 * @return
	 */
	List<DictBiz> treeMap(@Param("codes") List<String> codes);

	/**
	 * 获取树形节点
	 *
	 * @return
	 */
	List<DictBizVO> parentTree();

	/**
	 * 懒加载列表
	 *
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<DictBizVO> lazyList(Long parentId, Map<String, Object> param);


	/**
	 * 根据codes获取字典(code以“，”分隔)
	 * @param codes
	 * @return
	 */
	List<DictBiz> dictionaryByCodes(@Param("codes") List<String> codes);
	/**
	 * 分页查询
	 * @param page
	 * @param dict
	 * @return
	 */
	IPage<DictBiz> pageList(@Param("page") IPage<DictBiz> page, @Param("dict") DictBiz dict);
}
