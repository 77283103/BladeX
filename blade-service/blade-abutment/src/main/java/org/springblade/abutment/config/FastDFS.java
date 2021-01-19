package org.springblade.abutment.config;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
/**
 * @author battle
 */
@Configuration
public class FastDFS {
	/**
	 * fastDFS文件上传的配置
	 */
		private final Logger log = LoggerFactory.getLogger(this.getClass());

		@Value("classpath:fastdfs.properties")
		private Resource ccs;

		@Bean
		public TrackerClient initClient() {
			try {
				ClientGlobal.init(ccs.getFilename());
				return new TrackerClient();
			} catch (Exception e) {
				log.info("FastDFS创建客户端失败");
				return null;
			}
		}
}
