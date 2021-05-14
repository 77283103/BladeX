package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "多方收付款信息列表")
public class MultiPay {
	@ApiModelProperty(value = "单据内容:相对方名称（）")
	private String fd_linkman;
	@ApiModelProperty(value = "单据内容:收付款(必填,付款|1 收款|2 合同无价款|3)")
	private String fd_payment;
	@ApiModelProperty(value = "单据内容:收款条件(必填,全部预付货款|1 部分预付货款|2 帐扣|3 票折|4 补货|5 10天≤票期<45天|6 票期≥45天|7)")
	private String fd_condition;
	@ApiModelProperty(value = "单据内容:付款条件(必填,款到发货|1 赊销|2 签约后收款|3)")
	private String fd_payee_condition;
	@ApiModelProperty(value = "单据内容:收付款天数(“收付款”非“收款”可不传值)")
	private String fd_payee_days;
	@ApiModelProperty(value = "单据内容:合同未税金额(yyyy-MM-dd文本格式)")
	private String fd_taxed_price;
	@ApiModelProperty(value = "单据内容:税率")
	private String fd_tax_rate;
	@ApiModelProperty(value = "单据内容:含税金额")
	private String fd_tax_include;
}
