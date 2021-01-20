package org.springblade.contract.vo;

import lombok.*;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 作 业 外 包 协 议 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 王策
 * @date : 2021-01-20 13:42:14
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "作 业 外 包 协 议请求对象")
public class OutsourcingAgreementRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="发包方")
	private String employer;

    @ApiModelProperty(value="发包方住所")
	private String employersAddress;

    @ApiModelProperty(value="承包方")
	private String contractor;

    @ApiModelProperty(value="承包方住所")
	private String addressContractor;

    @ApiModelProperty(value="甲方将作业发包给乙方")
	private String operationA;

    @ApiModelProperty(value="承包期限初试日期")
	private Date contractPeriodA;

    @ApiModelProperty(value="承包期限结束日期")
	private Date contractPeriodB;

    @ApiModelProperty(value="赔偿金额小于多少元")
	private BigDecimal compensationMuch;

    @ApiModelProperty(value="以多少元为赔偿金")
	private BigDecimal damages;

    @ApiModelProperty(value="不低于多少万元保险金额的人身意外保险")
	private BigDecimal accidentInsurance;

    @ApiModelProperty(value="乙方联系人")
	private String contactPersonb;

    @ApiModelProperty(value="联系方式")
	private String contactInformation;

    @ApiModelProperty(value="乙方工作人员应达到的其他标准")
	private String otherStandards;

    @ApiModelProperty(value="乙方应于本协议签署之日起几日内")
	private String thisAgreement;

    @ApiModelProperty(value="向甲方支付履约保证金小写")
	private BigDecimal performance;

    @ApiModelProperty(value="向甲方支付履约保证金大写")
	private BigDecimal performanceA;

    @ApiModelProperty(value="乙方应在几日内将履约保证金补足")
	private String fewDays;

    @ApiModelProperty(value="超过期限日仍未补足")
	private String exceedterm;

    @ApiModelProperty(value="增值税发票")
	private String vatinvoice;

    @ApiModelProperty(value="并于每月日前交由甲方")
	private String deliveredMonth;

    @ApiModelProperty(value="甲方收到发票后多少日内")
	private String receivesInvoice;

    @ApiModelProperty(value="以形式支付上月承包作业费用")
	private String contractOperation;

    @ApiModelProperty(value="乙方户名")
	private String accountName;

    @ApiModelProperty(value="乙方帐号")
	private String accountNumber;

    @ApiModelProperty(value="开户行")
	private String bankDeposit;

    @ApiModelProperty(value="支付解约金")
	private BigDecimal terminationMoney;

    @ApiModelProperty(value="解约补偿金")
	private BigDecimal compensation;

    @ApiModelProperty(value="甲方有权解除本协议并要求乙方支付违约金")
	private BigDecimal liquidatedDamages;

    @ApiModelProperty(value="甲方有权解除本协议并要求乙方支付违约金2")
	private BigDecimal liquidatedDamagesa;

    @ApiModelProperty(value="其他约定")
	private String otherAgreements;

    @ApiModelProperty(value="委托代理人（甲方）")
	private String agentA;

    @ApiModelProperty(value="委托代理人（乙方）")
	private String agentB;

}
