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
package org.springblade.system.feign;

import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.*;
import org.springblade.system.vo.DataSealAuthorityResponseVO;
import org.springblade.system.vo.DeptVO;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Feign失败配置
 *
 * @author Chill
 */
@Component
public class ISysClientFallback implements ISysClient {

	@Override
	public R<Menu> getMenu(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<DeptVO>> getDeptTree(String tenantId, BladeUser bladeUser) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Dept> getDept(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<Dept>> getDeptAll() {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> getDeptIds(String tenantId, String deptNames) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Long> getDeptNewId(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> getDeptName(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<String>> getDeptNames(String deptIds) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<Dept>> getDeptChild(Long deptId) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Post> getPost(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> getPostIds(String tenantId, String postNames) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> getPostName(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<String>> getPostNames(String postIds) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Role> getRole(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Role> getRoleByName(String roleName) {
		return return R.fail("获取数据失败");
	}

	@Override
	public R<String> getRoleIds(String tenantId, String roleNames) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> getRoleName(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> getRoleAlias(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<String>> getRoleNames(String roleIds) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<List<String>> getRoleAliases(String roleIds) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Tenant> getTenant(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Tenant> getTenant(String tenantId) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Param> getParam(Long id) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> getParamValue(String paramKey) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Boolean> saveOrUpdateBatchDept(List<Dept> dept) {
		return null;
	}

	@Override
	public R<Boolean> saveOrUpdateBatchPost(List<Post> post) {
		return null;
	}
	@Override
	public R<Boolean> saveOrUpdateBatchUserDepart(List<UserDepartEntity> userDepart) {
		return  R.fail("保存數據失敗");
	}

	@Override
	public R<Boolean> saveDept(Dept dept) {
		return R.fail("保存數據失敗");
	}

	@Override
	public R<Boolean> savePost(Post post) {
		return R.fail("保存數據失敗");
	}

	@Override
	public R<Long> getDeptIdByAssociationId(String associationId) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Long> getUserDepartByAssociationId(Long associationId) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<UserDepartEntity> getUserDepart(Long associationId) {
		return R.fail("根据用户ID未查询到该用户关联部门和岗位和角色信息");
	}

	@Override
	public R<List<UserDepartEntity>> getUserDepartAll() {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> getAncestors(Long ancestorIds) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<Long> getPostIdByAssociationId(String associationId) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<DataSealAuthorityResponseVO> getByIdData(String userId,String roleId) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<String> adminInfo(String sealVale) {
		return R.fail("获取数据失败");
	}

	@Override
	public R<TemplateEntity> getTemplateByCode(String templateCode) {
		return R.fail("获取数据失败");
	}
}
