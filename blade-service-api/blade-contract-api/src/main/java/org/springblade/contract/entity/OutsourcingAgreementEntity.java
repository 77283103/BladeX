package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 作 业 外 包 协 议 实体类
 *
 * @author 王策
 * @date : 2021-01-20 13:42:12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("outsourcing_agreement")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "OutsourcingAgreement对象", description = "作 业 外 包 协 议")
public class OutsourcingAgreementEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 发包方
	 */
	@ApiModelProperty(value = "发包方")
	private String employer;
	/**
	 * 发包方住所
	 */
	@ApiModelProperty(value = "发包方住所")
	private String employersAddress;
	/**
	 * 承包方
	 */
	@ApiModelProperty(value = "承包方")
	private String contractor;
	/**
	 * 承包方住所
	 */
	@ApiModelProperty(value = "承包方住所")
	private String addressContractor;
	/**
	 * 甲方将作业发包给乙方
	 */
	@ApiModelProperty(value = "甲方将作业发包给乙方")
	private String operationA;
	/**
	 * 承包期限初试日期
	 */
	@ApiModelProperty(value = "承包期限初试日期")
	private Date contractPeriodA;
	/**
	 * 承包期限结束日期
	 */
	@ApiModelProperty(value = "承包期限结束日期")
	private Date contractPeriodB;
	/**
	 * 赔偿金额小于多少元
	 */
	@ApiModelProperty(value = "赔偿金额小于多少元")
	private BigDecimal compensationMuch;
	/**
	 * 以多少元为赔偿金
	 */
	@ApiModelProperty(value = "以多少元为赔偿金")
	private BigDecimal damages;
	/**
	 * 不低于多少万元保险金额的人身意外保险
	 */
	@ApiModelProperty(value = "不低于多少万元保险金额的人身意外保险")
	private BigDecimal accidentInsurance;
	/**
	 * 乙方联系人
	 */
	@ApiModelProperty(value = "乙方联系人")
	private String contactPersonb;
	/**
	 * 联系方式
	 */
	@ApiModelProperty(value = "联系方式")
	private String contactInformation;
	/**
	 * 乙方工作人员应达到的其他标准
	 */
	@ApiModelProperty(value = "乙方工作人员应达到的其他标准")
	private String otherStandards;
	/**
	 * 乙方应于本协议签署之日起几日内
	 */
	@ApiModelProperty(value = "乙方应于本协议签署之日起几日内")
	private String thisAgreement;
	/**
	 * 向甲方支付履约保证金小写
	 */
	@ApiModelProperty(value = "向甲方支付履约保证金小写")
	private BigDecimal performance;
	/**
	 * 向甲方支付履约保证金大写
	 */
	@ApiModelProperty(value = "向甲方支付履约保证金大写")
	private BigDecimal performanceA;
	/**
	 * 乙方应在几日内将履约保证金补足
	 */
	@ApiModelProperty(value = "乙方应在几日内将履约保证金补足")
	private String fewDays;
	/**
	 * 超过期限日仍未补足
	 */
	@ApiModelProperty(value = "超过期限日仍未补足")
	private String exceedterm;
	/**
	 * 增值税发票
	 */
	@ApiModelProperty(value = "增值税发票")
	private String vatinvoice;
	/**
	 * 并于每月日前交由甲方
	 */
	@ApiModelProperty(value = "并于每月日前交由甲方")
	private String deliveredMonth;
	/**
	 * 甲方收到发票后多少日内
	 */
	@ApiModelProperty(value = "甲方收到发票后多少日内")
	private String receivesInvoice;
	/**
	 * 以形式支付上月承包作业费用
	 */
	@ApiModelProperty(value = "以形式支付上月承包作业费用")
	private String contractOperation;
	/**
	 * 乙方户名
	 */
	@ApiModelProperty(value = "乙方户名")
	private String accountName;
	/**
	 * 乙方帐号
	 */
	@ApiModelProperty(value = "乙方帐号")
	private String accountNumber;
	/**
	 * 开户行
	 */
	@ApiModelProperty(value = "开户行")
	private String bankDeposit;
	/**
	 * 支付解约金
	 */
	@ApiModelProperty(value = "支付解约金")
	private BigDecimal terminationMoney;
	/**
	 * 解约补偿金
	 */
	@ApiModelProperty(value = "解约补偿金")
	private BigDecimal compensation;
	/**
	 * 甲方有权解除本协议并要求乙方支付违约金
	 */
	@ApiModelProperty(value = "甲方有权解除本协议并要求乙方支付违约金")
	private BigDecimal liquidatedDamages;
	/**
	 * 甲方有权解除本协议并要求乙方支付违约金2
	 */
	@ApiModelProperty(value = "甲方有权解除本协议并要求乙方支付违约金2")
	private BigDecimal liquidatedDamagesa;
	/**
	 * 其他约定
	 */
	@ApiModelProperty(value = "其他约定")
	private String otherAgreements;
	/**
	 * 委托代理人（甲方）
	 */
	@ApiModelProperty(value = "委托代理人（甲方）")
	private String agentA;
	/**
	 * 委托代理人（乙方）
	 */
	@ApiModelProperty(value = "委托代理人（乙方）")
	private String agentB;

	/**
	 * 拼接附件
	 */
	@ApiModelProperty(value = "拼接附件")
	private String annex;

}
