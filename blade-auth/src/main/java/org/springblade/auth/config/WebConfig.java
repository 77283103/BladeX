package org.springblade.auth.config;

import com.landray.sso.client.EKPSSOClient;
import org.springblade.auth.filter.EKPSSOClientAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author battle
 */
@Configuration
public class WebConfig {

    /*@Bean
    public FilterRegistrationBean<Filter> EKPAuthFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean();
        registration.setFilter(new EKPSSOClientAuthenticationFilter());
        registration.addUrlPatterns("/*");
        registration.setName("EKPSSOClientAuthenticationFilter");
        registration.setOrder(1);
        return registration;
    }*/

    @Bean
    public FilterRegistrationBean<Filter> EKPClientFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        //创建上面的自定义的WebFilter对象
        registration.setFilter(new MyEKPSSOClient());
        registration.addUrlPatterns("/*");
        //相当于web.xml中的<param-name>、<param-value>。可以添加n个
        registration.addInitParameter("filterConfigFile", "/sso-config.properties");
        //该Filter的名字，自己随便定义
        registration.setName("EKPSSOClient");
        //启动时候的优先级
        registration.setOrder(20);
        return registration;
    }

	@Bean
	public FilterRegistrationBean<Filter> CASEKPSSOClient() {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
		//创建上面的自定义的WebFilter对象
		registration.setFilter(new MyEKPSSOClient());
		registration.addUrlPatterns("/*");
		//相当于web.xml中的<param-name>、<param-value>。可以添加n个
		registration.addInitParameter("filterConfigFile", "/sso-config.properties");
		//该Filter的名字，自己随便定义
		registration.setName("CASEKPSSOClient");
		//启动时候的优先级
		registration.setOrder(22);
		return registration;
	}

    /*@Bean
    public EKPSSOClientAuthenticationFilter EKPSSOClientAuthenticationFilter() {
        return new EKPSSOClientAuthenticationFilter();
    }*/
}
