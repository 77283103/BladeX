package org.springblade.abutment.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("api.transferstation")
public class TransferStationProperties {

	private String push_url;
	private String token_url;
	private String token_username;
	private String token_password;

}
