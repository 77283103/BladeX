package org.springblade.auth.config;

import com.landray.sso.client.EKPSSOClient;
import com.landray.sso.client.EKPSSOFilter;
import com.landray.sso.client.util.FileUtil;
import com.landray.sso.client.util.Logger;
import com.landray.sso.client.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

public class MyEKPSSOClient extends EKPSSOClient {

	@Override
	public void init(String filterConfigFile) throws ServletException {
		if (StringUtil.isNull(filterConfigFile)) {
			String errorMsg = "未正确配置filterConfigFile初始参数。";
			Logger.error(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		} else {
			Properties prop = new Properties();
			prop.setProperty("log.level","WARN");
			prop.setProperty("filter.chain","CASURLFilter");
			prop.setProperty("CASURLFilter.cas.server","http://sso.pec.com.cn/sso");
			prop.setProperty("CASURLFilter.cas.ticket","ticket");
			String logLevel;
			String filterChain;
			logLevel = prop.getProperty("log.level");
			if (StringUtil.isNotNull(logLevel)) {
				Logger.setLevel(logLevel);
			}
			filterChain = prop.getProperty("filter.chain");
			if (filterChain != null && filterChain.length() > 0) {
				String[] filterClassFullNames = filterChain.split(";");
				processingFilters = new EKPSSOFilter[filterClassFullNames.length];
				String packageName = EKPSSOClient.class.getPackage().getName() + ".filter.";
				for(int i = 0; i < filterClassFullNames.length; ++i) {
					String clzName = filterClassFullNames[i];
					if (clzName.indexOf(".") == -1) {
						clzName = packageName + clzName;
					}
					try {
						Class c = Class.forName(clzName);
						EKPSSOFilter filter = (EKPSSOFilter)c.newInstance();
						processingFilters[i] = filter;
						filter.init(prop);
					} catch (Exception var11) {
						Logger.error("加载过滤器 " + clzName + " 发生错误！", var11);
						throw new ServletException(var11);
					}
				}
			}

		}
	}
}
