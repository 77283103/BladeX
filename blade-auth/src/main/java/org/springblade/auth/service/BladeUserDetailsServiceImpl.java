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
package org.springblade.auth.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springblade.auth.constant.AuthConstant;
import org.springblade.auth.enums.BladeUserEnum;
import org.springblade.auth.utils.TokenUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.core.tool.utils.WebUtil;
import org.springblade.system.dto.UserDepartDTO;
import org.springblade.system.entity.Tenant;
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * @author Chill
 */
@Service
@AllArgsConstructor
public class BladeUserDetailsServiceImpl implements UserDetailsService {

	private IUserClient userClient;
	private ISysClient sysClient;

	@Override
	@SneakyThrows
	public BladeUserDetails loadUserByUsername(String username) {
		HttpServletRequest request = WebUtil.getRequest();
		// 获取租户ID
		String headerTenant = request.getHeader(TokenUtil.TENANT_HEADER_KEY);
		String paramTenant = request.getParameter(TokenUtil.TENANT_PARAM_KEY);
		// 获取当前身份信息
		String departId = request.getHeader(TokenUtil.DEPART_ID);
		if (StringUtil.isAllBlank(headerTenant, paramTenant)) {
			throw new UserDeniedAuthorizationException(TokenUtil.TENANT_NOT_FOUND);
		}
		String tenantId = StringUtils.isBlank(headerTenant) ? paramTenant : headerTenant;

		// 获取租户信息
		R<Tenant> tenant = sysClient.getTenant(tenantId);
		if (tenant.isSuccess()) {
			if (TokenUtil.judgeTenant(tenant.getData())) {
				throw new UserDeniedAuthorizationException(TokenUtil.USER_HAS_NO_TENANT_PERMISSION);
			}
		} else {
			throw new UserDeniedAuthorizationException(TokenUtil.USER_HAS_NO_TENANT);
		}

		// 获取用户类型
		String userType = Func.toStr(request.getHeader(TokenUtil.USER_TYPE_HEADER_KEY), TokenUtil.DEFAULT_USER_TYPE);

		// 远程调用返回数据
		R<UserInfo> result;
		// 根据不同用户类型调用对应的接口返回数据，用户可自行拓展
		if (userType.equals(BladeUserEnum.WEB.getName())) {
			result = userClient.userInfo(tenantId, username);
		} else if (userType.equals(BladeUserEnum.APP.getName())) {
			result = userClient.userInfo(tenantId, username);
		} else {
			result = userClient.userInfo(tenantId, username);
		}

		// 判断返回信息
		if (result.isSuccess()) {
			UserInfo userInfo = result.getData();
			UserDTO user = userInfo.getUser();
			if (user == null || user.getId() == null) {
				throw new UsernameNotFoundException(TokenUtil.USER_NOT_FOUND);
			}

			String roleName = "";
			List<UserDepartDTO> userDepartList = user.getUserDepartList();
			if(StringUtils.isEmpty(departId)){
				List<String> roleAliasList = userDepartList.stream().map(UserDepartDTO::getRoleAlias).collect(Collectors.toList());
				Set<Long> deptIdsSet = userDepartList.stream().map(UserDepartDTO::getDeptId).collect(Collectors.toSet());
				Set<Long> roleIdsSet = userDepartList.stream().map(UserDepartDTO::getRoleId).collect(Collectors.toSet());
				Set<Long> postIdsSet = userDepartList.stream().map(UserDepartDTO::getPostId).collect(Collectors.toSet());
				//
				user.setDeptId(Func.join(deptIdsSet, StringPool.COMMA));
				user.setRoleId(Func.join(roleIdsSet,StringPool.COMMA));
				user.setPostId(Func.join(postIdsSet,StringPool.COMMA));
				roleName = Func.join(roleAliasList);
			} else{
				Map<Long, UserDepartDTO> userDepartMap = userDepartList.stream().collect(Collectors.toMap(UserDepartDTO::getId, d -> d));
				UserDepartDTO userDepart = userDepartMap.get(Func.toLong(departId));
				user.setDeptId(Func.toStr(userDepart.getDeptId()));
				user.setPostId(Func.toStr(userDepart.getPostId()));
				user.setRoleId(Func.toStr(userDepart.getRoleId()));
				roleName = userDepart.getRoleAlias();
			}
			if (Func.isEmpty(roleName)) {
				throw new UserDeniedAuthorizationException(TokenUtil.USER_HAS_NO_ROLE);
			}

			return new BladeUserDetails(
				user.getId(),
				user.getTenantId(),
				user.getRealName(),
				user.getDeptId(),
				user.getPostId(),
				user.getRoleId(),
				roleName,
				Func.toStr(user.getAvatar(), TokenUtil.DEFAULT_AVATAR),
				username,
				AuthConstant.ENCRYPT + user.getPassword(),
				true,
				true,
				true,
				true,
				AuthorityUtils.commaSeparatedStringToAuthorityList(roleName),
				user.getUserDepartList());
		} else {
			throw new UsernameNotFoundException(result.getMsg());
		}
	}

}
