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
	@ApiModelProperty(value = "单据内容:子公司名称（）")
	private String fd_pay_seal;
	@ApiModelProperty(value = "单据内容:相对方名称（）")
	private String fd_pay_name;
	@ApiModelProperty(value = "单据内容:收付款(必填,付款|1 收款|2 合同无价款|3)")
	private String fd_pay_type;
	@ApiModelProperty(value = "单据内容:收款条件(必填,全部预付货款|1 部分预付货款|2 帐扣|3 票折|4 补货|5 10天≤票期<45天|6 票期≥45天|7)")
	private String fd_receive_condition;
	@ApiModelProperty(value = "单据内容:付款条件(必填,款到发货|1 赊销|2 签约后收款|3)")
	private String fd_pay_condition;
	@ApiModelProperty(value = "单据内容:收付款天数(“收付款”非“收款”可不传值)")
	private String fd_pay_days;
	@ApiModelProperty(value = "单据内容:合同未税金额(yyyy-MM-dd文本格式)")
	private String fd_pay_untaxed;
	@ApiModelProperty(value = "单据内容:税率")
	private String fd_pay_rate;
	@ApiModelProperty(value = "单据内容:含税金额")
	private String fd_pay_include;
	@ApiModelProperty(value = "单据内容:付款信息-总期数")
	private String fd_period_num;
	@ApiModelProperty(value = "单据内容:付款信息-第几期")
	private String fd_period_idx;
	@ApiModelProperty(value = "单据内容:付款信息-条件+帐期天数")
	private String fd_payee_condition;
	@ApiModelProperty(value = "单据内容:付款信息-是否开票")
	private String fd_is_receipt;
	@ApiModelProperty(value = "单据内容:付款信息-比例")
	private String fd_per;
	@ApiModelProperty(value = "单据内容:付款信息-金额（未税）")
	private String fd_pay_amount;
}
