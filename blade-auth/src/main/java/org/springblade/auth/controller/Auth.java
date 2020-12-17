package org.springblade.auth.controller;

import com.alibaba.fastjson.JSONObject;
import com.landray.sso.client.EKPSSOUserData;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.tool.api.R;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class Auth {
	private RedisTemplate redisTemplate;

	@GetMapping("/login")
	@ApiOperation(value = "单点", notes = "传入ticket")
	public R eipLogin(HttpServletResponse response,String id, String name, String accType, String sourceOfContract, String code) throws IOException {
		EKPSSOUserData userData = EKPSSOUserData.getInstance();
		String username = userData.getCurrentUsername();
		//id为空为单点登录
		if (StringUtils.isBlank(id)) {
			if (StringUtils.isBlank(username)) {
				//跳转到SSO登录
				response.sendRedirect("http://sso.pec.com.cn/sso/login?service=http://upht.pec.com.cn:8100/auth/login");
				return R.success("false");
			} else {
				response.sendRedirect("http://upht.pec.com.cn/#/login?username=" + username + "&type=1");
			}
		}else {//id不为空为单点登录后跳转起草页面
			//查看缓存里是否有依据信息
			String j = (String) redisTemplate.opsForValue().get(id);
			//没有的情况下去缓存依据信息
			if (StringUtils.isBlank(j)) {
				JSONObject object = new JSONObject();
				object.put("type", "1");
				object.put("draftType", "1");
				object.put("fileId", id);
				object.put("accordingName", name);
				object.put("documentType", accType);
				object.put("sourceOfContract", sourceOfContract);
				object.put("code", code);
				String json = object.toString();
				redisTemplate.opsForValue().set(id, json);
				response.sendRedirect("http://sso.pec.com.cn/sso/login?service=http://upht.pec.com.cn:8100/auth/login?id=" + id);
			} else {
				if (StringUtils.isBlank(username)) {
					redisTemplate.delete(id);
					JSONObject object = new JSONObject();
					object.put("type", "1");
					object.put("draftType", "1");
					object.put("fileId", id);
					object.put("accordingName", name);
					object.put("documentType", accType);
					object.put("sourceOfContract", sourceOfContract);
					object.put("code", code);
					String json = object.toString();
					redisTemplate.opsForValue().set(id, json);
					//跳转到SSO登录
					response.sendRedirect("http://sso.pec.com.cn/sso/login?service=http://upht.pec.com.cn:8100/auth/login?id=" + id);
				}else{
					//有缓存的话说明已经是通过单点之后进来的
					Cookie newCookie=new Cookie("saber-access-token",null); //假如要删除名称为username的Cookie
					newCookie.setMaxAge(0); //让cookie生命周期为空
					newCookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除
					response.addCookie(newCookie); //重新写入，将覆盖之前的
					Cookie cookie=new Cookie("saber-refresh-token",null); //假如要删除名称为username的Cookie
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie); //重新写入，将覆盖之前的
					response.sendRedirect("http://upht.pec.com.cn/#/login?username=" + username + "&type=1&draftType=1&fileId=" + id);
				}
			}
			return R.success("false");
		}
		//R<UserInfo> userDTO=userClient.userInfo("000000","admin");
		//String password=userDTO.getData().getUser().getPassword();
		return R.success("");
	}
}
