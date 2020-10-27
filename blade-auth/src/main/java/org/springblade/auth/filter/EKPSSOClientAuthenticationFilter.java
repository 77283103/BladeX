package org.springblade.auth.filter;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.landray.sso.client.EKPSSOUserData;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author battle
 */ //该文件不用修改
public class EKPSSOClientAuthenticationFilter implements Filter {

	private static final Log logger = LogFactory
			.getLog(EKPSSOClientAuthenticationFilter.class);
	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
						 final FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		System.out.println("this is MyFilter,url :"+request.getRequestURI());
		EKPSSOUserData userData = EKPSSOUserData.getInstance();
		if (userData.isUserChanged()) {
			HttpServletResponse response = (HttpServletResponse) res;
			String username = userData.getCurrentUsername();
			System.out.println(username);
			userData.acceptUserChange();
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) {
		System.out.println("this is MyFilter");
	}

}
