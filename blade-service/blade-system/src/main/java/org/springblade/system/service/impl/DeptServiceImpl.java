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

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springblade.common.utils.ChinessToPinyin;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.node.ForestNodeMerger;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.system.entity.Dept;
import org.springblade.system.mapper.DeptMapper;
import org.springblade.system.service.IDeptService;
import org.springblade.system.vo.DeptVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {

	@Override
	public List<DeptVO> lazyList(String tenantId, Long parentId, Map<String, Object> param) {
		if (AuthUtil.isAdministrator()) {
			tenantId = StringPool.EMPTY;
		}
		String paramTenantId = Func.toStr(param.get("tenantId"));
		if (Func.isNotEmpty(paramTenantId) && AuthUtil.isAdministrator()) {
			tenantId = paramTenantId;
		}
		Object paramParentId = param.get("parentId");
		if (Func.isEmpty(paramParentId)) {
			parentId = null;
		}
		return baseMapper.lazyList(tenantId, parentId, param);
	}


	@Override
	public List<DeptVO> tree(String tenantId) {
		return ForestNodeMerger.merge(baseMapper.tree(tenantId));
	}

	@Override
	public List<DeptVO> lazyTree(String tenantId, Long parentId) {
		if (AuthUtil.isAdministrator()) {
			tenantId = StringPool.EMPTY;
		}
		return ForestNodeMerger.merge(baseMapper.lazyTree(tenantId, parentId));
	}

	@Override
	public String getDeptIds(String tenantId, String deptNames) {
		List<Dept> deptList = baseMapper.selectList(Wrappers.<Dept>query().lambda().eq(Dept::getTenantId, tenantId).in(Dept::getDeptName, Func.toStrList(deptNames)));
		if (deptList != null && deptList.size() > 0) {
			return deptList.stream().map(dept -> Func.toStr(dept.getId())).distinct().collect(Collectors.joining(","));
		}
		return null;
	}

	@Override
	public List<String> getDeptNames(String deptIds) {
		return baseMapper.getDeptNames(Func.toLongArray(deptIds));
	}

	@Override
	public List<Dept> getDeptChild(Long deptId) {
		/* 2020-6-19 14:45:40 tah，增加过滤条件，查询未删除的的，即isDeleted=0 */
		return baseMapper.selectList(Wrappers.<Dept>query().lambda().like(Dept::getAncestors, deptId).eq(Dept::getIsDeleted, 0));
	}

	@Override
	public boolean removeDept(String ids) {
		Integer cnt = baseMapper.selectCount(Wrappers.<Dept>query().lambda().in(Dept::getParentId, Func.toLongList(ids)));
		if (cnt > 0) {
			throw new ServiceException("请先删除子节点!");
		}
		return removeByIds(Func.toLongList(ids));
	}

	@Override
	public boolean removeDeptIgnoreChild(String ids){
		return removeByIds(Func.toLongList(ids));
	}

	@Override
	public boolean submit(Dept dept) {
		if (Func.isEmpty(dept.getParentId())) {
			dept.setTenantId(AuthUtil.getTenantId());
			dept.setParentId(BladeConstant.TOP_PARENT_ID);
			dept.setAncestors(String.valueOf(BladeConstant.TOP_PARENT_ID));
		}
		if (dept.getParentId() > 0) {
			Dept parent = getById(dept.getParentId());
			if (Func.toLong(dept.getParentId()) == Func.toLong(dept.getId())) {
				throw new ServiceException("父节点不可选择自身!");
			}
			dept.setTenantId(parent.getTenantId());
			String ancestors = parent.getAncestors() + StringPool.COMMA + dept.getParentId();
			dept.setAncestors(ancestors);
		}
		/* 根据id是否为空判断做新增还是修改 */
		if(null == dept.getId()){
			dept.setIsDeleted(BladeConstant.DB_NOT_DELETED);
			/* 保存拼音名称 */
			dept.setPinyinName(ChinessToPinyin.deptToPinyin(dept.getDeptName(),dept.getFullName()));
			return save(dept);
		}else{
			boolean flag = false;
			/* 获取被修改机构的id */
			Long oldId = dept.getId();
			/* 将被修改机构制作成副本保存一份 */
			dept.setUpdateDeptId(oldId);
			dept.setId(null);
			dept.setIsDeleted(BladeConstant.DB_NOT_DELETED);
			dept.setPinyinName(ChinessToPinyin.deptToPinyin(dept.getDeptName(),dept.getFullName()));
			save(dept);
			/* 获取机构修改后的id，这个id与oldId不同 */
			Long newId = dept.getId();
			/* 逻辑删除被修改记录留存 */
			flag = removeDeptIgnoreChild(oldId+"");
			/* 获取修改机构的子级单位集合 */
			List<Dept> childDepts = getDeptChild(oldId);
			/* 获取修改后的机构对象 */
			Dept deptNew = getById(newId);
			/* 遍历子单位集合修改子单位的父ID和祖籍列表 */
			for (Dept dept1: childDepts) {
				dept1.setParentId(newId);
				dept1.setAncestors(deptNew.getAncestors() + StringPool.COMMA + dept1.getParentId());
				flag = saveOrUpdate(dept1);
			}
			return flag;
		}
	}

	@Override
	public boolean updateDeptStatus(String deptId, Integer isEnable) {
		Dept dept = baseMapper.selectById(deptId);
		dept.setIsEnable(isEnable);
		baseMapper.updateById(dept);
		return true;
	}
}
