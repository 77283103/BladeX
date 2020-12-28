package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KeepList {
	@ApiModelProperty(value = "交易类型")
	private String fd_trade_type;
	@ApiModelProperty(value = "接收条件")
	private String fd_receipt;
	@ApiModelProperty(value = "计划完成时间")
	private String fd_plan_time;
	@ApiModelProperty(value = "计划内容完成时间")
	private String fd_plan_content;
}
