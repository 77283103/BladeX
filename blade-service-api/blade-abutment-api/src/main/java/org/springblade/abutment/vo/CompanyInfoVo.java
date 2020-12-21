package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询企业信息返回数据")
public class CompanyInfoVo {
	@ApiModelProperty(value = "社会信用代码")
	private String organCode;
	@ApiModelProperty(value = "企业名")
	private String name;
	@ApiModelProperty(value = "申请电子印章是否成功（1：成功，0：失败）")
	private String available;
}
