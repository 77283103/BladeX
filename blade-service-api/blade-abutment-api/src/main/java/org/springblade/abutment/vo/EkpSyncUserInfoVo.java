package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class EkpSyncUserInfoVo implements Serializable {

	@ApiModelProperty("ekp数据标识，是否有效（1：有效 0：无效）")
	private String available;

	@ApiModelProperty("ekp员工编号")
	private String emplno;

	@ApiModelProperty("ekp用户名称")
	private String userName;

	@ApiModelProperty("ekp用户id")
	private String userId;

	@ApiModelProperty("部门标识")
	private String parentId;
}
