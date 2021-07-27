package org.springblade.abutment.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties("api.synekp")
public class EkpProperties {

	private String url;

	private String password;

	private String token_account;

	private String token_password;

	private String token_url;
}
