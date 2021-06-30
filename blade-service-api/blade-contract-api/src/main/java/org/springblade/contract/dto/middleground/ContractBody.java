package org.springblade.contract.dto.middleground;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * (统一中台) 合同体
 */
@Data
public class ContractBody {

	@ApiModelProperty(value = "料号/系列号")
	private String mtrl_no;

	@ApiModelProperty(value = "名称")
	private String mtrl_name;

	@ApiModelProperty(value = "规格")
	private String mtrl_spec;

	@ApiModelProperty(value = "未税价格")
	private Integer uprice;

	@ApiModelProperty(value = "税率")
	private Double taxrate;

	@ApiModelProperty(value = "单位")
	private String unit_m;

	@ApiModelProperty(value = "帐期")
	private Integer account_period_detail;

	@ApiModelProperty(value = "价格有效期起")
	private Date price_b_date;

	@ApiModelProperty(value = "价格有效期止")
	private Date price_e_date;

	@ApiModelProperty(value = "数量")
	private Integer qty;

	@ApiModelProperty(value = "起订量")
	private Integer qty_begin;

	@ApiModelProperty(value = "交付地点")
	private String deliver_point;

	@ApiModelProperty(value = "付款条件")
	private String condition;

}
