package org.springblade.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.landray.sso.client.EKPSSOUserData;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class Auth {
	private RedisTemplate redisTemplate;
	private IUserClient userClient;

	@GetMapping("/login")
	@ApiOperation(value = "单点", notes = "传入ticket")
	public R eipLogin(HttpServletResponse response, String id, String name, String accType, String templateType, String code) throws IOException {
		EKPSSOUserData userData = EKPSSOUserData.getInstance();
		String username = userData.getCurrentUsername();
		//通过id和名称来判断是否为单点登录
		if (StringUtils.isBlank(id)) {
			if (StringUtils.isBlank(username)) {
				//跳转到SSO登录
				response.sendRedirect("http://sso.pec.com.cn/sso/login?service=http://upht.pec.com.cn:8100/auth/login");
				return R.success("false");
			} else {
				userData.changeCurrentUser(username);
				response.sendRedirect("http://upht.pec.com.cn/#/login?username=" + username + "&type=1");
			}
		} else {
			//查看缓存里是否有依据信息
			String j = (String) redisTemplate.opsForValue().get(id);
			//没有的情况下去缓存
			if (StringUtils.isBlank(j)) {
				JSONObject object = new JSONObject();
				object.put("type", "1");
				object.put("draftType", "1");
				object.put("fileId", id);
				object.put("accordingName", name);
				object.put("documentType", accType);
				object.put("templateType", templateType);
				object.put("code", code);
				String json = object.toString();
				redisTemplate.opsForValue().set(id, json);
				if (StringUtils.isBlank(username)) {
					//跳转到SSO登录
					response.sendRedirect("http://sso.pec.com.cn/sso/login?service=http://upht.pec.com.cn:8100/auth/login?id=" + id);
					return R.success("false");
				}else{
					//有缓存的话说明已经是通过单点之后进来的
					response.sendRedirect("http://upht.pec.com.cn/#/login?username=" + username + "&type=1&draftType=1&fileId=" + id);
				}
			} else {
				if (StringUtils.isBlank(username)) {
					//跳转到SSO登录
					redisTemplate.delete(id);
					response.sendRedirect("http://sso.pec.com.cn/sso/login?service=http://upht.pec.com.cn:8100/auth/login");
					return R.success("false");
				}else{
					//有缓存的话说明已经是通过单点之后进来的
					response.sendRedirect("http://upht.pec.com.cn/#/login?username=" + username + "&type=1&draftType=1&fileId=" + id);
				}
			}
		}
		//R<UserInfo> userDTO=userClient.userInfo("000000","admin");
		//String password=userDTO.getData().getUser().getPassword();
		return R.success("");
	}

	/*@GetMapping("/according")
	@ApiOperation(value = "根据依据类型跳转", notes = "传入ticket")
	public R according(HttpServletResponse response, String id, String name, String accType, String templateType, String code) throws IOException {
		EKPSSOUserData userData = EKPSSOUserData.getInstance();
		String username = userData.getCurrentUsername();
		//R<UserInfo> userDTO=userClient.userInfo("000000","admin");
		//String password=userDTO.getData().getUser().getPassword();
		userData.changeCurrentUser(username);
		BladeUser user = AuthUtil.getUser();
		if (null != user) {
			Kv.create().set("success", "true").set("account", user.getAccount()).set("msg", "success");
		}
		JSONObject object = new JSONObject();
		object.put("type", "1");
		object.put("draftType", "1");
		object.put("fileId", id);
		object.put("accordingName", name);
		object.put("documentType", accType);
		object.put("templateType", templateType);
		object.put("code", code);
		String json = object.toString();
		redisTemplate.opsForValue().set(id, json);
		if (StringUtils.isBlank(username)) {
			//跳转到SSO登录
			response.sendRedirect("http://sso.pec.com.cn/sso/login?service=http://upht.pec.com.cn:8100/auth/login");
			return R.success("false");
		}
		response.sendRedirect("http://upht.pec.com.cn/#/login?username=" + username + "&type=1&draftType=1&fileId=" + id);
		return R.success("");
	}*/
}
