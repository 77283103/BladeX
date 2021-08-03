package org.springblade.contract.dto.middleground;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * (统一中台) 结算明细
 */
@Data
public class PayDetail {

	@ApiModelProperty(value = "第几期")
	private String period_idx;

	@ApiModelProperty(value = "条件")
	private String pay_condition;

	@ApiModelProperty(value = "是否要发票")
	private String is_receipt;

	@ApiModelProperty(value = "占比")
	private Double per;

	@ApiModelProperty(value = "金额（未税）")
	private BigDecimal pay_amount;

	@ApiModelProperty(value = "帐期")
	private Integer account_period;

	@ApiModelProperty(value = "固定付款日期")
	private Date pay_date;

}
