package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "收款明细列表")
public class CollectionMulti {
	@ApiModelProperty(value = "唯一标识ID")
	private String fd_multi_id;
	@ApiModelProperty(value = "单据内容:付款信息-总期数")
	private String fd_period_num_multi;
	@ApiModelProperty(value = "单据内容:付款信息-第几期")
	private String fd_period_idx_multi;
	@ApiModelProperty(value = "单据内容:【付款信息-条件+帐期天数】 合同签定生效后（≥3）天||a1 料到启动施工后（≥3）天||a2 备货完成后（≥3）天||a3 "+
		"货到票未到（≥3）天||a4 其他（手填描述）(计算日期结果-申请日期≥3天)||a5 约定日期||a6 货到票到（≥10）天||b1 "+
		"货到票到固定日期||b2 合同签定生效后（≥3）天||c1 料到启动施工后（≥3）天||c2 完成程度（手工填写）%（≥3）天||c3"+
		"质保期满（≥3）天||c4 备货完成后（≥3）天||c5 其他（手填描述）(计算日期结果-申请日期≥3天)||c6 约定日期||c7")
	private String fd_payee_condition_multi;
	@ApiModelProperty(value = "单据内容:付款信息-是否开票")
	private String fd_is_receipt_multi;
	@ApiModelProperty(value = "单据内容:付款信息-比例")
	private String fd_per_multi;
	@ApiModelProperty(value = "单据内容:付款信息-金额（未税）")
	private String fd_pay_amount_multi;
	@ApiModelProperty(value = "单据内容:收款明细  需要填写")
	private String fd_pay_days_multi;
}
