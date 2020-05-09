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
package org.springblade.auth.utils;

import lombok.SneakyThrows;
import org.springblade.core.launch.constant.TokenConstant;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.*;
import org.springblade.system.entity.Tenant;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;

import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * 认证工具类
 *
 * @author Chill
 */
public class TokenUtil {

	public final static String AVATAR = TokenConstant.AVATAR;
	public final static String ACCOUNT = TokenConstant.ACCOUNT;
	public final static String USER_NAME = TokenConstant.USER_NAME;
	public final static String NICK_NAME = TokenConstant.NICK_NAME;
	public final static String REAL_NAME = TokenConstant.REAL_NAME;
	public final static String USER_ID = TokenConstant.USER_ID;
	public final static String DEPT_ID = TokenConstant.DEPT_ID;
	public final static String POST_ID = TokenConstant.POST_ID;
	public final static String ROLE_ID = TokenConstant.ROLE_ID;
	public final static String ROLE_NAME = TokenConstant.ROLE_NAME;
	public final static String TENANT_ID = TokenConstant.TENANT_ID;
	public final static String CLIENT_ID = TokenConstant.CLIENT_ID;
	public final static String LICENSE = TokenConstant.LICENSE;
	public final static String LICENSE_NAME = TokenConstant.LICENSE_NAME;

	public final static String CAPTCHA_HEADER_KEY = "Captcha-Key";
	public final static String CAPTCHA_HEADER_CODE = "Captcha-Code";
	public final static String CAPTCHA_NOT_CORRECT = "验证码不正确";
	public final static String TENANT_HEADER_KEY = "Tenant-Id";
	public final static String TENANT_PARAM_KEY = "tenant_id";
	public final static String DEFAULT_TENANT_ID = "000000";
	public final static String TENANT_NOT_FOUND = "租户ID未找到";
	public final static String USER_TYPE_HEADER_KEY = "User-Type";
	public final static String DEFAULT_USER_TYPE = "web";
	public final static String USER_NOT_FOUND = "用户名或密码错误";
	public final static String USER_HAS_NO_ROLE = "未获得用户的角色信息";
	public final static String USER_HAS_NO_TENANT = "未获得用户的租户信息";
	public final static String USER_HAS_NO_TENANT_PERMISSION = "租户授权已过期,请联系管理员";
	public final static String HEADER_KEY = "Authorization";
	public final static String HEADER_PREFIX = "Basic ";
	public final static String DEFAULT_AVATAR = "";

	/**
	 * 解码
	 */
	@SneakyThrows
	public static String[] extractAndDecodeHeader() {
		String header = WebUtil.getRequest().getHeader(TokenUtil.HEADER_KEY);
		if (header == null || !header.startsWith(TokenUtil.HEADER_PREFIX)) {
			throw new UnapprovedClientAuthenticationException("请求头中无client信息");
		}

		byte[] base64Token = header.substring(6).getBytes(Charsets.UTF_8_NAME);

		byte[] decoded;
		try {
			decoded = Base64.getDecoder().decode(base64Token);
		} catch (IllegalArgumentException var7) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}

		String token = new String(decoded, Charsets.UTF_8_NAME);
		int index = token.indexOf(StringPool.COLON);
		if (index == -1) {
			throw new BadCredentialsException("Invalid basic authentication token");
		} else {
			return new String[]{token.substring(0, index), token.substring(index + 1)};
		}
	}

	/**
	 * 获取请求头中的客户端id
	 */
	public static String getClientIdFromHeader() {
		String[] tokens = extractAndDecodeHeader();
		return tokens[0];
	}

	/**
	 * 获取token过期时间(次日凌晨3点)
	 *
	 * @return expire
	 */
	public static int getTokenValiditySecond() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 3);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
	}

	/**
	 * 获取refreshToken过期时间
	 *
	 * @return expire
	 */
	public static int getRefreshTokenValiditySeconds() {
		return 60 * 60 * 24 * 15;
	}

	/**
	 * 判断租户权限
	 *
	 * @param tenant 租户信息
	 * @return boolean
	 */
	public static boolean judgeTenant(Tenant tenant) {
		if (tenant == null) {
			throw new UserDeniedAuthorizationException(TokenUtil.USER_HAS_NO_TENANT);
		}
		if (StringUtil.equalsIgnoreCase(tenant.getTenantId(), BladeConstant.ADMIN_TENANT_ID)) {
			return false;
		}
		Date expireTime = tenant.getExpireTime();
		if (expireTime != null && expireTime.before(DateUtil.now())) {
			throw new UserDeniedAuthorizationException(TokenUtil.USER_HAS_NO_TENANT_PERMISSION);
		}
		return false;
	}

}
