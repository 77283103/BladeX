package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;


/**
 * 韩素娟劳务派遣合同模板(甲方有拼接附件） 实体类
 *
 * @author wd
 * @date : 2021-01-22 15:16:07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("labor_dispatch")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "LaborDispatch对象", description = "韩素娟劳务派遣合同模板(甲方有拼接附件）")
public class LaborDispatchEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String ywbFirstParty;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String ywbAddressA;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String ywbPartyB;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String ywbAddressB;
	/**
	 * 第二条  保证金：本合同保证金为？元
	 */
	@ApiModelProperty(value = "第二条  保证金：本合同保证金为？元")
	private String principal;
	/**
	 * 则需向甲方支付违约金？元
	 */
	@ApiModelProperty(value = "则需向甲方支付违约金？元")
	private String liquidatedDamages;
	/**
	 * 本合同履行完毕后？日
	 */
	@ApiModelProperty(value = "本合同履行完毕后？日")
	private String date;
	/**
	 * 工作人员到甲方单位从事？工作
	 */
	@ApiModelProperty(value = "工作人员到甲方单位从事？工作")
	private String job;
	/**
	 * 限不应低于？天
	 */
	@ApiModelProperty(value = "限不应低于？天")
	private String days;
	/**
	 * 乙方所提供的人员年龄不得低于18周岁且不得超过?周岁
	 */
	@ApiModelProperty(value = "乙方所提供的人员年龄不得低于18周岁且不得超过?周岁")
	private String age;
	/**
	 * 甲方每月?日
	 */
	@ApiModelProperty(value = "甲方每月?日")
	private String assessmentDay;
	/**
	 * 确认无误后?个工作日
	 */
	@ApiModelProperty(value = "确认无误后?个工作日")
	private String accountingDate;
	/**
	 * 发票后的？日
	 */
	@ApiModelProperty(value = "发票后的？日")
	private String invoiceDate;
	/**
	 * 开户名
	 */
	@ApiModelProperty(value = "开户名")
	private String accountName;
	/**
	 * 开户行
	 */
	@ApiModelProperty(value = "开户行")
	private String bank;
	/**
	 * 账号
	 */
	@ApiModelProperty(value = "账号")
	private String accountNumber;
	/**
	 * 考核期为？天
	 */
	@ApiModelProperty(value = "考核期为？天")
	private String assessmentPeriod;
	/**
	 * 并于?个工作日后将该人员退回乙方
	 */
	@ApiModelProperty(value = "并于?个工作日后将该人员退回乙方")
	private String returnm;
	/**
	 * 乙方在?日内重新派遣
	 */
	@ApiModelProperty(value = "乙方在?日内重新派遣")
	private String dispatch;
	/**
	 * 要求乙方在?日内重新派
	 */
	@ApiModelProperty(value = "要求乙方在?日内重新派")
	private String reassignment;
	/**
	 * 申请之日起？日内重新派遣
	 */
	@ApiModelProperty(value = "申请之日起？日内重新派遣")
	private String applicationForAssignment;
	/**
	 * 姓名?
	 */
	@ApiModelProperty(value = "姓名?")
	private String name;
	/**
	 * 联系方式?
	 */
	@ApiModelProperty(value = "联系方式?")
	private String phone;
	/**
	 * 通知?天内按合同要求付费
	 */
	@ApiModelProperty(value = "通知?天内按合同要求付费")
	private String paymentPeriod;
	/**
	 * 迟到扣款人民币?元人次
	 */
	@ApiModelProperty(value = "迟到扣款人民币?元人次")
	private String late;
	/**
	 * 旷职扣款人民币?元人天
	 */
	@ApiModelProperty(value = "旷职扣款人民币?元人天")
	private String absenteeism;
	/**
	 * 每逾期一日按逾期金额的?％承担违约责任
	 */
	@ApiModelProperty(value = "每逾期一日按逾期金额的?％承担违约责任")
	private String overdue;
	/**
	 * 按缺少人数处违约金人民币?元人次
	 */
	@ApiModelProperty(value = "按缺少人数处违约金人民币?元人次")
	private String lack;
	/**
	 * 要求乙方支付违约金人民币? 万元
	 */
	@ApiModelProperty(value = "要求乙方支付违约金人民币? 万元")
	private String iiquidatedDamages;
	/**
	 * 5？
	 */
	@ApiModelProperty(value = "5？")
	private String supplementFive;
	/**
	 * 6?
	 */
	@ApiModelProperty(value = "6?")
	private String supplementSix;
	/**
	 * 乙方应向甲方支付违约金?元
	 */
	@ApiModelProperty(value = "乙方应向甲方支付违约金?元")
	private String payment;
	/**
	 * 乙方应向甲方支付延迟支付部分的?%作为违约金
	 */
	@ApiModelProperty(value = "乙方应向甲方支付延迟支付部分的?%作为违约金")
	private String delayedPayment;
	/**
	 * 违约金金额为人民币? 元
	 */
	@ApiModelProperty(value = "违约金金额为人民币? 元")
	private String relieve;
	/**
	 * 自?年?月?日起
	 */
	@ApiModelProperty(value = "自?年?月?日起")
	private Date specificDateStart;
	/**
	 * ?年?月?日起
	 */
	@ApiModelProperty(value = "?年?月?日起")
	private String specificDateEnd;
	/**
	 * 合同期满前?日
	 */
	@ApiModelProperty(value = "合同期满前?日")
	private String expirationOfContract;
	/**
	 * 甲方电话
	 */
	@ApiModelProperty(value = "甲方电话")
	private String telephoneA;
	/**
	 * 乙方电话
	 */
	@ApiModelProperty(value = "乙方电话")
	private String telephoneB;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String addressA;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String addressB;
	/**
	 * 附件一
	 */
	@ApiModelProperty(value = "附件一")
	private String file1;
	/**
	 * 附件二
	 */
	@ApiModelProperty(value = "附件二")
	private String file2;
	/**
	 * 附件三
	 */
	@ApiModelProperty(value = "附件三")
	private String file3;
	/**
	 * 附件四
	 */
	@ApiModelProperty(value = "附件四")
	private String file4;
	/**
	 * 附件五
	 */
	@ApiModelProperty(value = "附件五")
	private String file5;
	/**
	 * 拼接附件
	 */
	@ApiModelProperty(value = "拼接附件")
	private String annex;

}
