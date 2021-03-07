package org.springblade.auth.config;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 *
 * 密码验证，用于登录及安全认证平台免密登录
 * @author battle
 */
@Component
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

	public LoginAuthenticationProvider(UserDetailsService loginService, PasswordEncoder passwordEncoder) {
		super();
		setUserDetailsService(loginService);
		setPasswordEncoder(passwordEncoder);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)   {
		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException(
				messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
		boolean verificationResults = false;
		String presentedPassword = authentication.getCredentials().toString();
		//免验证
		//if(laissezPasserConstant.inspectLaissezPasser(userDetails.getPassword())){
			verificationResults = true;
		//}else{
		//	verificationResults = this.getPasswordEncoder().matches(presentedPassword, userDetails.getPassword());
		//}
		if (!verificationResults) {
			logger.debug("Authentication failed: password does not match stored value");
			throw new BadCredentialsException(messages.getMessage(
				"AbstractUserDetailsAuthenticationProvider.badCredentials",
				"Bad credentials"));
		}
		super.additionalAuthenticationChecks(userDetails, authentication);
	}
}
