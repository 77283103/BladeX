package org.springblade.contract.dto.middleground;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * (统一中台) 结算区
 */
@Data
public class PayCondition implements Serializable {

	@ApiModelProperty(value = "结算方式")
	private String pay_method;

	@ApiModelProperty(value = "总期数")
	private String period_num;

	@ApiModelProperty(value = "结算明细")
	private PayDetail detail;
}
