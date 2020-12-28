package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PayList {
	@ApiModelProperty(value = "交易类型")
	private String fd_trade_kind;
	@ApiModelProperty(value = "接收条件")
	private String fd_p_receipt;
	@ApiModelProperty(value = "计划收款时间")
	private String fd_plan_ptime;
	@ApiModelProperty(value = "计划收款金额")
	private String fd_plan_psum;
}
