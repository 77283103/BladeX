package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class EkpSyncDeptInfoVo implements Serializable {

	@ApiModelProperty("ekp数据标识，是否有效（1：有效 0：无效）")
	private String available;

	@ApiModelProperty("ekp部门名称")
	private String orgName;

	@ApiModelProperty("ekp部门标识")
	private String orgId;

	@ApiModelProperty("ekp部门父级标识")
	private String parentId;
}
