package org.springblade.auth.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class OAuthExRequest implements Serializable {

	private String departId;

	private String grant_type;

	private String refresh_token;

	private String scope;

	private String tenantId;

	private String token;

	private String username;

	private String password;

	private String type;

}
