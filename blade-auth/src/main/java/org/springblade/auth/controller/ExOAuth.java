//package org.springblade.auth.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springblade.auth.entity.OAuthExRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 扩展oauth2 token获取控制器
 * 修改为json格式接收请求参数,返回格式及处理未作修改
 * 注：此控制器影响所有原参数形式调用。需将所有调用/oauth/token的程序或前端改为发送json格式
 */
//@Slf4j
//@RestController
//@RequestMapping("/oauth")
//public class ExOAuth{
//
//	@Autowired
//	private TokenEndpoint tokenEndpoint;
//
//	@RequestMapping(
//		value = {"/token"},
//		method = {RequestMethod.POST}
//	)
//	public ResponseEntity<OAuth2AccessToken> getAccessToken(Principal principal, @RequestBody OAuthExRequest oAuthExRequest) throws HttpRequestMethodNotSupportedException {
//		Map<String,String> parameters = JSONObject.parseObject(JSONObject.toJSONString(oAuthExRequest), HashMap.class);
//		ResponseEntity<OAuth2AccessToken>responseEntity = tokenEndpoint.postAccessToken(principal,parameters);
//		return responseEntity;
//	}
//}
