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

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.*;
import org.springblade.system.service.*;
import org.springblade.system.vo.DataSealAuthorityResponseVO;
import org.springblade.system.vo.DeptVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springblade.core.cache.constant.CacheConstant.SYS_CACHE;

/**
 * 系统服务Feign实现类
 *
 * @author Chill
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class SysClient implements ISysClient {

	private IDeptService deptService;

	private IUserDepartService userDepartService;

	private IPostService postService;

	private IRoleService roleService;

	private IMenuService menuService;

	private ITenantService tenantService;

	private IParamService paramService;

	private IDataSealAuthorityService dataSealAuthorityService;

	private IDictBizClient iDictBizClient;

	@Override
	@GetMapping(MENU)
	public R<Menu> getMenu(Long id) {
		return R.data(menuService.getById(id));
	}

	@Override
	public R<List<DeptVO>> getDeptTree(String tenantId, BladeUser bladeUser) {
		List<DeptVO> tree = deptService.tree(Func.toStrWithEmpty(tenantId, bladeUser.getTenantId()));
		return R.data(tree);
	}

	@Override
	@GetMapping(DEPT)
	public R<Dept> getDept(Long id) {
		return R.data(deptService.getById(id));
	}

	@Override
	public R<String> getDeptIds(String tenantId, String deptNames) {
		return R.data(deptService.getDeptIds(tenantId, deptNames));
	}

	@Override
	@GetMapping(DEPT_NAME)
	public R<String> getDeptName(Long id) {
		if(Func.isEmpty(deptService.getById(id))){
			return R.data(200,"","未查到所属部门信息");
		}else{
			return R.data(deptService.getById(id).getDeptName());
		}
	}

	@Override
	@GetMapping(DEPT_NAMES)
	public R<List<String>> getDeptNames(String deptIds) {
		return R.data(deptService.getDeptNames(deptIds));
	}

	@Override
	@GetMapping(DEPT_CHILD)
	public R<List<Dept>> getDeptChild(Long deptId) {
		return R.data(deptService.getDeptChild(deptId));
	}

	@Override
	public R<Post> getPost(Long id) {
		return R.data(postService.getById(id));
	}

	@Override
	public R<String> getPostIds(String tenantId, String postNames) {
		return R.data(postService.getPostIds(tenantId, postNames));
	}

	@Override
	public R<String> getPostName(Long id) {
		return R.data(postService.getById(id).getPostName());
	}

	@Override
	public R<List<String>> getPostNames(String postIds) {
		return R.data(postService.getPostNames(postIds));
	}

	@Override
	@GetMapping(ROLE)
	public R<Role> getRole(Long id) {
		return R.data(roleService.getById(id));
	}

	@Override
	public R<String> getRoleIds(String tenantId, String roleNames) {
		return R.data(roleService.getRoleIds(tenantId, roleNames));
	}

	@Override
	@GetMapping(ROLE_NAME)
	public R<String> getRoleName(Long id) {
		return R.data(roleService.getById(id).getRoleName());
	}

	@Override
	@GetMapping(ROLE_ALIAS)
	public R<String> getRoleAlias(Long id) {
		return R.data(roleService.getById(id).getRoleAlias());
	}

	@Override
	@GetMapping(ROLE_NAMES)
	public R<List<String>> getRoleNames(String roleIds) {
		return R.data(roleService.getRoleNames(roleIds));
	}

	@Override
	@GetMapping(ROLE_ALIASES)
	public R<List<String>> getRoleAliases(String roleIds) {
		return R.data(roleService.getRoleAliases(roleIds));
	}

	@Override
	@GetMapping(TENANT)
	public R<Tenant> getTenant(Long id) {
		return R.data(tenantService.getById(id));
	}

	@Override
	@GetMapping(TENANT_ID)
	public R<Tenant> getTenant(String tenantId) {
		return R.data(tenantService.getByTenantId(tenantId));
	}

	@Override
	@GetMapping(PARAM)
	public R<Param> getParam(Long id) {
		return R.data(paramService.getById(id));
	}

	@Override
	@GetMapping(PARAM_VALUE)
	public R<String> getParamValue(String paramKey) {
		return R.data(paramService.getValue(paramKey));
	}

	@Override
	@PostMapping(SAVE_DEPT_API)
	public R<Boolean> saveOrUpdateBatchDept(List<Dept> dept) {
		CacheUtil.clear(SYS_CACHE);
		return R.data(deptService.saveBatchDept(dept));
	}

	@Override
	@PostMapping(SAVE_POST_API)
	public R<Boolean> saveOrUpdateBatchPost(List<Post> post) {
		CacheUtil.clear(SYS_CACHE);
		return R.data(postService.saveBatchPost(post));
	}
	@Override
	@PostMapping(SAVE_USER_DEPART_API)
	public R<Boolean> saveOrUpdateBatchUserDepart(List<UserDepartEntity> userDepart) {
		CacheUtil.clear(SYS_CACHE);
		return R.data(userDepartService.saveOrUpdateBatch(userDepart));
	}

	@Override
	@PostMapping(SUBMIT_DEPT_API)
	public R<Boolean> saveDept(Dept dept) {
		return R.data(deptService.saveOrUpdate(dept));
	}

	@Override
	@PostMapping(SUBMIT_POST_API)
	public R<Boolean> savePost(Post post) {
		return R.data(postService.saveOrUpdate(post));
	}
	@Override
	@GetMapping(DEPT_NEW_ID)
	public R<Long> getDeptNewId(Long id) {
		return R.data(deptService.getDeptNewId(id));
	}

	@Override
	@GetMapping(GET_DEPT_ID_BY_LUNID)
	public R<Long> getDeptIdByAssociationId(String associationId) {
		return R.data(deptService.getDeptIdByAssociationId(associationId));
	}


	@Override
	@GetMapping(GET_USER_DEPART_ID_BY_LUNID)
	public R<Long> getUserDepartByAssociationId(Long associationId) {
		associationId= Long.parseLong(JSON.parseObject(String.valueOf(associationId)).get("associationId").toString());
		return R.data(userDepartService.getUserDepartIdByAssociationId(associationId));
	}

	@Override
	@GetMapping(GET_ANCESTOR_IDS)
	public R<String> getAncestors(Long ancestorIds) {
		return R.data(deptService.getAncestors(ancestorIds));
	}

	@Override
	@GetMapping(GET_POST_ID_BY_LUNID)
	public R<Long> getPostIdByAssociationId(String associationId) {
		return R.data(postService.getPostIdByAssociationId(associationId));
	}

	@Override
	@GetMapping(GET_DATA_SEAL_AUTHORITY)
	public R<DataSealAuthorityResponseVO> getByIdData(String userId,String roleId) {
		if (dataSealAuthorityService.getUserId(userId,roleId).getCode()== HttpStatus.OK.value()){
			return R.data(HttpStatus.OK.value(), dataSealAuthorityService.getUserId(userId,roleId).getData(),"OK");
		}else {
			return R.data(HttpStatus.NO_CONTENT.value(), null,"NO_CONTENT");
		}
	}

	@Override
	@GetMapping(GET_DATA_SEAL_ADMIN_INFO)
	public R<String> adminInfo(String sealVale) {
		List<String> adIf=new ArrayList<>();
		String sealV=iDictBizClient.getKey("application_seal",sealVale).getData();
		List<Map<String, String>> lmp=dataSealAuthorityService.sealMap();
		lmp.forEach(lm->{
			if (lm.getOrDefault("seal", "").contains(sealV)) {
				adIf.add(lm.getOrDefault("admin_info", ""));
			}
		});
		return R.data(adIf.get(0));
	}

}
