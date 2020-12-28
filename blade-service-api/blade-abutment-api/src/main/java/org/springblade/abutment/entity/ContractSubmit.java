package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ekp审核通过的数据")
public class ContractSubmit {
	@ApiModelProperty(value = "合同id")
	private String contractId;
	@ApiModelProperty(value = "审批状态")
	private String submitStatus;
	/*@ApiModelProperty(value = "签章位置信息,除骑缝签章外必填")
	private String EKPId;*/
}
