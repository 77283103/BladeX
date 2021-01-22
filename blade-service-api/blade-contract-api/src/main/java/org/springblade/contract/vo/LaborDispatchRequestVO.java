package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;

/**
 * 韩素娟劳务派遣合同模板(甲方有拼接附件） 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author wd
 * @date : 2021-01-22 15:16:11
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "韩素娟劳务派遣合同模板(甲方有拼接附件）请求对象")
public class LaborDispatchRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String ywbFirstParty;
	
    @ApiModelProperty(value="甲方地址")
	private String ywbAddressA;
	
    @ApiModelProperty(value="乙方")
	private String ywbPartyB;
	
    @ApiModelProperty(value="乙方地址")
	private String ywbAddressB;
	
    @ApiModelProperty(value="第二条  保证金：本合同保证金为？元")
	private String principal;
	
    @ApiModelProperty(value="则需向甲方支付违约金？元")
	private String liquidatedDamages;
	
    @ApiModelProperty(value="本合同履行完毕后？日")
	private String date;
	
    @ApiModelProperty(value="工作人员到甲方单位从事？工作")
	private String job;
	
    @ApiModelProperty(value="限不应低于？天")
	private String days;
	
    @ApiModelProperty(value="乙方所提供的人员年龄不得低于18周岁且不得超过?周岁")
	private String age;
	
    @ApiModelProperty(value="甲方每月?日")
	private String assessmentDay;
	
    @ApiModelProperty(value="确认无误后?个工作日")
	private String accountingDate;
	
    @ApiModelProperty(value="发票后的？日")
	private String invoiceDate;
	
    @ApiModelProperty(value="开户名")
	private String accountName;
	
    @ApiModelProperty(value="开户行")
	private String bank;
	
    @ApiModelProperty(value="账号")
	private String accountNumber;
	
    @ApiModelProperty(value="考核期为？天")
	private String assessmentPeriod;
	
    @ApiModelProperty(value="并于?个工作日后将该人员退回乙方")
	private String returnm;
	
    @ApiModelProperty(value="乙方在?日内重新派遣")
	private String dispatch;
	
    @ApiModelProperty(value="要求乙方在?日内重新派")
	private String reassignment;
	
    @ApiModelProperty(value="申请之日起？日内重新派遣")
	private String applicationForAssignment;
	
    @ApiModelProperty(value="姓名?")
	private String name;
	
    @ApiModelProperty(value="联系方式?")
	private String phone;
	
    @ApiModelProperty(value="通知?天内按合同要求付费")
	private String paymentPeriod;
	
    @ApiModelProperty(value="迟到扣款人民币?元人次")
	private String late;
	
    @ApiModelProperty(value="旷职扣款人民币?元人天")
	private String absenteeism;
	
    @ApiModelProperty(value="每逾期一日按逾期金额的?％承担违约责任")
	private String overdue;
	
    @ApiModelProperty(value="按缺少人数处违约金人民币?元人次")
	private String lack;
	
    @ApiModelProperty(value="要求乙方支付违约金人民币? 万元")
	private String iiquidatedDamages;
	
    @ApiModelProperty(value="5？")
	private String supplementFive;
	
    @ApiModelProperty(value="6?")
	private String supplementSix;
	
    @ApiModelProperty(value="乙方应向甲方支付违约金?元")
	private String payment;
	
    @ApiModelProperty(value="乙方应向甲方支付延迟支付部分的?%作为违约金")
	private String delayedPayment;
	
    @ApiModelProperty(value="违约金金额为人民币? 元")
	private String relieve;
	
    @ApiModelProperty(value="自?年?月?日起")
	private Date specificDateStart;
	
    @ApiModelProperty(value="?年?月?日起")
	private String specificDateEnd;
	
    @ApiModelProperty(value="合同期满前?日")
	private String expirationOfContract;
	
    @ApiModelProperty(value="甲方电话")
	private String telephoneA;
	
    @ApiModelProperty(value="乙方电话")
	private String telephoneB;
	
    @ApiModelProperty(value="甲方地址")
	private String addressA;
	
    @ApiModelProperty(value="乙方地址")
	private String addressB;
	
}
