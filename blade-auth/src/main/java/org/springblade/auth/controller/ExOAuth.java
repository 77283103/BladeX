//package org.springblade.auth.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.springblade.auth.entity.OAuthExRequest;
//
//import org.springblade.core.tool.jackson.JsonUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AccountStatusException;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 扩展oauth2 token获取控制器
// * 修改为json格式接收请求参数,返回格式及处理未作修改
// * 注：此控制器影响所有原参数形式调用。需将所有调用/oauth/token的程序或前端改为发送json格式
// */
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
//	public ResponseEntity getAccessToken(Principal principal, @RequestBody OAuthExRequest oAuthExRequest) {
//		Map<String,String>parameters = JSONObject.parseObject(JSONObject.toJSONString(oAuthExRequest), HashMap.class);
//		try{
//			return tokenEndpoint.postAccessToken(principal,parameters);
//		}catch (Exception e) {
//			Map<String,Object> res = new HashMap<>();
//			res.put("error_description",e.getMessage());
//			return new ResponseEntity(res,HttpStatus.BAD_REQUEST);
//		}
//	}
//}
