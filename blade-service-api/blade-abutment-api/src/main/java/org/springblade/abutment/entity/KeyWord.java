package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "签章关键字")
public class KeyWord {

	@ApiModelProperty(value = "签章方身份信息")
	private String fd_keyword;
	@ApiModelProperty(value = "签章角色")
	private String fd_roles;
}
