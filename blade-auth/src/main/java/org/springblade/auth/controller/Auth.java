package org.springblade.auth.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.landray.sso.client.EKPSSOUserData;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springblade.core.tool.api.R;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class Auth {
	private IUserClient userClient;
	@GetMapping("/login")
	@ApiOperation(value = "单点", notes = "传入ticket")
	public R eipLogin( HttpServletResponse response) throws IOException {
		EKPSSOUserData userData = EKPSSOUserData.getInstance();
		String username = userData.getCurrentUsername();
		if (StringUtils.isBlank(username)) {
			//跳转到SSO登录
			response.sendRedirect("http://sso.pec.com.cn/sso/login?service=http://upht.pec.com.cn:8100/auth/login");
			return R.success("false");
		}
		//R<UserInfo> userDTO=userClient.userInfo("000000","admin");
		//String password=userDTO.getData().getUser().getPassword();
		userData.changeCurrentUser(username);
		response.sendRedirect("http://upht.pec.com.cn:1888/#/login?username="+username+"&type=1");
		return R.success("");
	}

}
