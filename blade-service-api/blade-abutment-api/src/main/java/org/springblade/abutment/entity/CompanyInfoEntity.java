package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@Data
@ApiModel(value = "获取企业信息的入参")
public class CompanyInfoEntity implements Serializable {
	@ApiModelProperty(value = "查询方式 1社会信用代码查询 2系统ID+系统类别查询(必填)")
	private String queryType;
	@ApiModelProperty(value = "企业社会信用代码(查询方式为1时必填)")
	private String organCode;
	@ApiModelProperty(value = "系统UID(查询方式为2时必填)")
	private String sysUid;
	@ApiModelProperty(value = "系统类别(查询方式为2时必填)")
	private String sysType;
}
