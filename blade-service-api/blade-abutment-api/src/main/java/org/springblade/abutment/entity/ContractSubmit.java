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
	@ApiModelProperty(value = "ekp单号")
	private String ekp_number;
}
