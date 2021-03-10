package org.springblade.auth.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.landray.sso.client.EKPSSOUserData;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas10TicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springblade.auth.feign.SSOClient;
import org.springblade.core.tool.api.R;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class Auth {
	private final  RedisTemplate redisTemplate;
	private final  IUserClient userClient;
	private final  SSOClient iSSOClient;
	@Value("${api.ssoUrl}")
	private String ssoUrl;
	@Value("${api.serviceURL}")
	private String serviceURL;
	@Value("${api.tokenUrl}")
	private String tokenUrl;


	@GetMapping("/loginTest/{ticket}")
	@ApiOperation(value = "单点", notes = "传入ticket")
	public void eipLogin(@PathVariable("ticket") String ticket, HttpServletRequest request) {
		String urlNginx = request.getHeaders("x-forwarded-for").toString();
		StringBuffer url=request.getRequestURL();
		String serviceURL = "http://upht.pec.com.cn:8100/auth/login";
		String casServerUrlPrefix = "http://sso.pec.com.cn/sso";
		String username = null;
		TicketValidator validator = null;
		validator = new Cas10TicketValidator(casServerUrlPrefix);
		Assertion assertion = null;
		try {
			assertion = validator.validate(ticket, serviceURL);
		} catch (TicketValidationException e) {
			e.printStackTrace();
		}
		username = assertion.getPrincipal().getName();
		System.out.println(username);

	}

	@GetMapping("/login")
	@ApiOperation(value = "单点", notes = "传入ticket")
	public R eipLoginCokie(HttpServletResponse response,
						   @RequestParam(value ="ticket",required=false) String ticket,
						   @RequestParam(value ="id",required=false) String id,
						   @RequestParam(value ="name",required=false) String name,
						   @RequestParam(value ="accType",required=false) String accType,
						   @RequestParam(value ="sourceOfContract",required=false) String sourceOfContract,
						   @RequestParam(value ="code",required=false) String code) throws IOException {
		String username="";
		//id为空为单点登录
		if (StringUtils.isBlank(id)) {
			if (StringUtils.isBlank(ticket)) {
				//跳转到SSO登录
				response.sendRedirect("http://sso.pec.com.cn/sso/login?service="+ssoUrl+"/api/blade-auth/auth/login");
				return R.success("false");
			} else {
				String validator = iSSOClient.validate(ticket,serviceURL);
				System.out.println(validator);
				if(validator.contains("yes")){
					username =validator.replaceAll("yes","").trim();
					System.out.println(username);
				}
				setToken(username,response);
				response.sendRedirect(ssoUrl+"/#/singleLogin");
			}
		}else {//id不为空为跳转起草页面
			//查看缓存里是否有依据信息
			String j = (String) redisTemplate.opsForValue().get(id);
			//没有的情况下去缓存依据信息
			if (StringUtils.isBlank(j)) {
				com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
				object.put("type", "1");
				object.put("draftType", "1");
				object.put("fileId", id);
				object.put("accordingName", name);
				object.put("documentType", accType);
				object.put("sourceOfContract", sourceOfContract);
				object.put("code", code);
				String json = object.toString();
				redisTemplate.opsForValue().set(id, json);
				response.sendRedirect("http://sso.pec.com.cn/sso/login?service="+ssoUrl+"/api/blade-auth/auth/login?id=" + id);
			} else {
				if (StringUtils.isBlank(ticket)) {
					redisTemplate.delete(id);
					com.alibaba.fastjson.JSONObject object = new com.alibaba.fastjson.JSONObject();
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
					response.sendRedirect("http://sso.pec.com.cn/sso/login?service="+ssoUrl+"/api/blade-auth/auth/login?id=" + id);
				}else{
					//有缓存的话说明已经是通过单点之后进来的
					String validator = iSSOClient.validate(ticket,serviceURL+"?id=" + id);
					System.out.println("validator是："+validator);
					if(validator.contains("yes")){
						username =validator.replaceAll("yes","").trim();
					}
					setToken(username,response);
					com.alibaba.fastjson.JSONObject jsonObject1 =com.alibaba.fastjson.JSONObject.parseObject(j);
					String source= (String) jsonObject1.get("sourceOfContract");
					response.sendRedirect(ssoUrl+"/#/singleLogin?draftType=1&source="+source+"&fileId=" + id);
				}
			}
			return R.success("false");
		}
		return R.success("");
	}

	public void setToken(String username,HttpServletResponse response) throws UnsupportedEncodingException {
		//有缓存的话说明已经是通过单点之后进来的
		//删除名称为saber-access-token的Cookie
		Cookie newCookie=new Cookie("saber-access-token",null);
		//让cookie生命周期为空
		newCookie.setMaxAge(0);
		//项目所有目录均有效，这句很关键，否则不敢保证删除
		newCookie.setPath("/");
		//重新写入，将覆盖之前的
		response.addCookie(newCookie);
		//假如要删除名称为username的Cookie
		Cookie cookie=new Cookie("saber-refresh-token",null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		//重新写入，将覆盖之前的
		response.addCookie(cookie);
		//后台模拟登陆
		JSONObject param= JSONUtil.createObj();
		param.set("tenantId","000000");
		param.set("username",username);
		R<UserInfo> userDTO=userClient.userInfo("000000",username);
		String password=userDTO.getData().getUser().getPassword();
		param.set("password",password);
		param.set("grant_type","captcha");
		param.set("scope","all");
		param.set("type","account");
		JSONObject docInfoJson = JSONUtil.parseObj(HttpUtil.createPost(this.tokenUrl)
			.header("Tenant-Id","000000")
			.header("Authorization","Basic c2FiZXI6c2FiZXJfc2VjcmV0")
			.form(param)
			.execute()
			.body());
		//保存信息到access_token中
		Cookie access_token=new Cookie("access_token",docInfoJson.getStr("access_token"));
		access_token.setMaxAge(60*60*24);
		access_token.setPath("/");
		response.addCookie(access_token);
		Cookie refresh_token=new Cookie("refresh_token",docInfoJson.getStr("refresh_token"));
		refresh_token.setMaxAge(60*60*24);
		refresh_token.setPath("/");
		response.addCookie(refresh_token);
		Cookie userDetails=new Cookie("userDetails", URLEncoder.encode(docInfoJson.toString(),"UTF-8"));
		userDetails.setMaxAge(60*60*24);
		userDetails.setPath("/");
		response.addCookie(userDetails);
	}
}
