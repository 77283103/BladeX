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
package org.springblade.auth.endpoint;

import com.wf.captcha.SpecCaptcha;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springblade.common.cache.CacheNames;
import org.springblade.core.cache.constant.CacheConstant;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.redis.cache.BladeRedis;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.*;

/**
 * BladeEndPoint
 *
 * @author feng
 */
@Slf4j
@RestController
@AllArgsConstructor
public class BladeTokenEndPoint {

	private BladeRedis bladeRedis;
	private AuthorizationServerEndpointsConfiguration endpoints;
	private ClientDetailsService clientDetailsService;
	private TokenStore tokenStore;
	private PasswordEncoder passwordEncoder;


	@GetMapping("/oauth/user-info")
	public R<Authentication> currentUser(Authentication authentication) {
		return R.data(authentication);
	}

	@GetMapping("/oauth/captcha")
	public Kv captcha() {
		SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
		String verCode = specCaptcha.text().toLowerCase();
		String key = StringUtil.randomUUID();
		// 存入redis并设置过期时间为30分钟
		bladeRedis.setEx(CacheNames.CAPTCHA_KEY + key, verCode, Duration.ofMinutes(30));
		// 将key和base64返回给前端
		return Kv.create().set("key", key).set("image", specCaptcha.toBase64());
	}

	@GetMapping("/oauth/logout")
	public Kv logout() {
		BladeUser user = AuthUtil.getUser();
		return Kv.create().set("success", "true").set("account", user.getAccount()).set("msg", "success");
	}

	/**
	 * 身份选择
	 * @return
	 */
	@GetMapping("/oauth/select-depart")
	public R<OAuth2AccessToken> selectDepart(String departId, String tenantId, String refreshToken) throws Exception {
		String userName = "admin";
		String password = "admin";
		String clientId = "saber";
		String clientSecret = "saber_secret";
		// 使用oauth2密码模式登录.
		Map<String, String> postParameters = new HashMap<>();
		postParameters.put("tenant_id", tenantId);
		postParameters.put("refresh_token", refreshToken);
		postParameters.put("grant_type", "refresh_token");

		// 生成token
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(clientId, clientSecret, Collections.emptyList());
		ResponseEntity<OAuth2AccessToken> responseEntity = endpoints.tokenEndpoint().postAccessToken(authRequest, postParameters);
		OAuth2AccessToken body = responseEntity.getBody();
		return R.data(body);
	}

	@GetMapping("/oauth/clear-cache")
	public Kv clearCache() {
		CacheUtil.clear(CacheConstant.BIZ_CACHE);
		CacheUtil.clear(CacheConstant.MENU_CACHE);
		CacheUtil.clear(CacheConstant.USER_CACHE);
		CacheUtil.clear(CacheConstant.DICT_CACHE);
		CacheUtil.clear(CacheConstant.FLOW_CACHE);
		CacheUtil.clear(CacheConstant.SYS_CACHE);
		CacheUtil.clear(CacheConstant.RESOURCE_CACHE);
		CacheUtil.clear(CacheConstant.PARAM_CACHE);
		return Kv.create().set("success", "true").set("msg", "success");
	}

}
