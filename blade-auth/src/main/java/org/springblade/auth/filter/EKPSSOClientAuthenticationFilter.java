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

	public EKPSSOClientAuthenticationFilter() {
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
						 final FilterChain chain) throws IOException, ServletException {
		try{
			EKPSSOUserData userData = EKPSSOUserData.getInstance();
			if (userData.isUserChanged()) {
				HttpServletResponse response = (HttpServletResponse) res;
				String username = userData.getCurrentUsername();
				System.out.println(username);
				userData.acceptUserChange();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		System.out.println(123456);
		}
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) {
		System.out.println("this is MyFilter");
	}

}
