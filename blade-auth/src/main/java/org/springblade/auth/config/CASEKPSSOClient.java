package org.springblade.auth.config;

import com.landray.sso.client.EKPSSOChain;
import com.landray.sso.client.EKPSSOContext;
import com.landray.sso.client.EKPSSOUserData;
import com.landray.sso.client.filter.AbstractFilter;
import com.landray.sso.client.filter.CASURLFilter;
import com.landray.sso.client.util.Logger;
import com.landray.sso.client.util.StringUtil;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.util.XmlUtils;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.Cas10TicketValidator;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.springblade.system.cache.SysCache;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
public  class CASEKPSSOClient extends AbstractFilter {

	private Map sessionMap;
	private String casServerUrlPrefix;
	private String ticketParameterName;

	@Override
	public void init(Properties prop) throws ServletException {
		this.sessionMap = new HashMap();
		this.casServerUrlPrefix = this.getConfigProperty(prop, "CASURLFilter.cas.server");
		this.ticketParameterName = this.getConfigProperty(prop, "CASURLFilter.cas.ticket");
	}

	@Override
	public void destroy() {
		this.sessionMap = null;
		this.casServerUrlPrefix = null;
		this.ticketParameterName = null;
	}

	@Override
	public void doFilter(EKPSSOContext context, EKPSSOChain chain) throws IOException, ServletException {
		if ("POST".equals(context.getRequest().getMethod())) {
			this.doLogout(context, chain);
		} else {
			this.doLogin(context, chain);
		}

	}

	private void doLogout(EKPSSOContext context, EKPSSOChain chain) throws IOException, ServletException {
		String logoutRequest = CommonUtils.safeGetParameter(context.getRequest(), "logoutRequest");
		if (CommonUtils.isNotBlank(logoutRequest)) {
			String sessionIdentifier = XmlUtils.getTextForElement(logoutRequest, "SessionIndex");
			if (CommonUtils.isNotBlank(sessionIdentifier)) {
				HttpSession session = (HttpSession)this.sessionMap.remove(sessionIdentifier);
				if (session != null) {
					try {
						session.invalidate();
					} catch (IllegalStateException var7) {
					}
				}
			}
		}

		chain.doNextFilter();
	}

	private void doLogin(EKPSSOContext context, EKPSSOChain chain) throws IOException, ServletException {
		HttpServletRequest request = context.getRequest();
		HttpServletResponse response = context.getResponse();
		String ticket = request.getParameter(this.ticketParameterName);
		if (!StringUtil.isNull(ticket) && !this.sessionMap.containsKey(ticket)) {
			String uri = request.getRequestURI();
			String localServerName = request.getRequestURL().toString();
			if (StringUtil.isNotNull(uri)) {
				localServerName = localServerName.substring(0, localServerName.length() - uri.length());
			}

			Logger.debug("从URL中获取到的" + this.ticketParameterName + "参数为：" + ticket);
			//String serviceURL = CommonUtils.constructServiceUrl(request, response, (String)null, localServerName, this.ticketParameterName, true);
			//serviceURL="http://cms.pec.com.cn/api/blade-auth/auth/login";
			String serviceURL="http://upht.pec.com.cn/api/blade-auth/auth/login";
			try {
				String username = null;
				TicketValidator validator = null;
				validator = new Cas10TicketValidator(this.casServerUrlPrefix);
				Assertion assertion = validator.validate(ticket, serviceURL);
				username = assertion.getPrincipal().getName();
				System.out.println("sso的username是"+username);
				Logger.debug("经过验证票据，得到用户名：" + username);
				if (StringUtil.isNotNull(username)) {
					context.setCurrentUsername(username);
					System.out.println("sso的CurrentUsername1:"+context.getCurrentUsername());
				} else {
					ticket = null;
				}
			} catch (TicketValidationException var12) {
				response.setStatus(403);
				throw new ServletException(var12);
			}

			chain.doNextFilter();
			System.out.println("sso的CurrentUsername2:"+context.getCurrentUsername());
			EKPSSOUserData userData = EKPSSOUserData.getInstance();
			String username = userData.getCurrentUsername();
			System.out.println("sso的username3:"+username);
			if (chain.isFinish() && ticket != null) {
				this.sessionMap.put(ticket, request.getSession());
				System.out.println("sso的request.getSession()4:"+request.getSession().getServletContext());
			}

		} else {
			chain.doNextFilter();
		}
	}

}
