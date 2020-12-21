package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "EKP单据交互入参")
public class EkpEntity implements Serializable {
	@ApiModelProperty(value = "员工编号(流程发起人的员工编号,必填)")
	private String emplno;
	@ApiModelProperty(value = "单据主题(必填)")
	private String docSubject;
	@ApiModelProperty(value = "单据内容:依据文档id(必填,ekp跳转到合同平台传递的依据文档id)")
	private String fd_parent_id;
	@ApiModelProperty(value = "单据内容:PDF文件id(必填,合同平台上传合同到E签宝返回的文件id)")
	private String fd_file_id;
	@ApiModelProperty(value = "单据内容:对方名称(必填)")
	private String fd_name;
	@ApiModelProperty(value = "单据内容:合同份数(必填)")
	private String fd_totle;
	@ApiModelProperty(value = "单据内容:合同期限(必填,xs:≤三年 ds:>三年 wx:未附终止期限)")
	private String fd_cont_scop;
	@ApiModelProperty(value = "单据内容:付款期限(必填,1:票期<10天 2:10天≤票期<45天 3:45天≤票期 4:先款后货 5:我司收款 6:合同无价款，我司不需要对外付款，亦不需要对外收款。)")
	private String fd_paydate;
	@ApiModelProperty(value = "单据内容:合同开始时间(yyyy-MM-dd文本格式)")
	private String fd_starttime;
	@ApiModelProperty(value = "单据内容:合同截止时间(yyyy-MM-dd文本格式)")
	private String fd_lasttime;
	@ApiModelProperty(value = "单据内容:合同金额")
	private String fd_dollar;
	@ApiModelProperty(value = "单据内容:合同标的")
	private String fd_biaodi;
	@ApiModelProperty(value = "单据内容:付款条款(1:一次性付款 2:分期付款 3:预付货款 4:账扣 5:票折 6:补货)")
	private String fd_tiaokuan;
	@ApiModelProperty(value = "单据内容:一次性付款天数（一次性付款时必填）")
	private String fd_days;
	@ApiModelProperty(value = "单据内容:完成进度1(分期付款时填选，例句： 1) 乙方完成   ( 百分比) 且经过甲方验收合格后并拿到乙方增值税发票   天内，支付全部款项的   ( 百分比)； )")
	private String fd_percent1;
	@ApiModelProperty(value = "单据内容:发票天数1（分期付款时填选）")
	private String fd_billday1;
	@ApiModelProperty(value = "单据内容:支付百分比1（分期付款时填选）")
	private String fd_percent2;
	@ApiModelProperty(value = "单据内容:完成进度2（分期付款时填选）")
	private String fd_percent3;
	@ApiModelProperty(value = "单据内容:发票天数2（分期付款时填选）")
	private String fd_billday2;
	@ApiModelProperty(value = "单据内容:支付百分比2（分期付款时填选）")
	private String fd_percent4;
	@ApiModelProperty(value = "单据内容:完成进度3（分期付款时填选）")
	private String fd_percent5;
	@ApiModelProperty(value = "单据内容:发票天数3（分期付款时填选）")
	private String fd_billday3;
	@ApiModelProperty(value = "单据内容:支付百分比3（分期付款时填选）")
	private String fd_percent6;
	@ApiModelProperty(value = "单据内容:发票天数4（分期付款时填选）")
	private String fd_billday4;
	@ApiModelProperty(value = "单据内容:款项百分比4（分期付款时填选）")
	private String fd_percent7;
	@ApiModelProperty(value = "单据内容:发票天数5（分期付款时填选）")
	private String fd_billday5;
	@ApiModelProperty(value = "单据内容:款项百分比5（分期付款时填选）")
	private String fd_percent8;
	@ApiModelProperty(value = "单据内容:发票天数6（预付货款时填选）")
	private String fd_billday6;
	@ApiModelProperty(value = "单据内容:预付货款（预付货款时填选）")
	private String fd_pay;
	@ApiModelProperty(value = "单据内容:收款条款(我司收款时填选，款到发货|KDFH 赊销|SX 合同生效后|SHHK)")
	private String fd_shouktk;
	@ApiModelProperty(value = "单据内容:收款天数（我司收款时填选）")
	private String fd_shoukts;
	@ApiModelProperty(value = "单据内容:违约责任")
	private String fd_duty;
}
