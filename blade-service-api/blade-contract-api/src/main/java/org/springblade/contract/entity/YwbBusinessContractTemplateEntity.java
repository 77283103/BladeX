package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 业务类：15.房屋租赁合同模板 实体类
 *
 * @author 王策
 * @date : 2021-01-12 17:30:26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("ywb_business_contract_template")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "YwbBusinessContractTemplate对象", description = "业务类：15.房屋租赁合同模板")
public class YwbBusinessContractTemplateEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 出租方
	 */
	@ApiModelProperty(value = "出租方")
	private String ywbLessors;
	/**
	 * 出租方证件类型及编号
	 */
	@ApiModelProperty(value = "出租方证件类型及编号")
	private Integer ywbCertificate;
	/**
	 * 出租房住所地
	 */
	@ApiModelProperty(value = "出租房住所地")
	private String ywbAddress;
	/**
	 * 承租方
	 */
	@ApiModelProperty(value = "承租方")
	private String ywbTenantry;
	/**
	 * 承租方证件类型及编号
	 */
	@ApiModelProperty(value = "承租方证件类型及编号")
	private String ywbCertificateB;
	/**
	 * 承租方住所地
	 */
	@ApiModelProperty(value = "承租方住所地")
	private String ywbResidence;
	/**
	 * 甲方同意将位于
	 */
	@ApiModelProperty(value = "甲方同意将位于")
	private String ywbAgrees;
	/**
	 * 建筑面积(平方米)
	 */
	@ApiModelProperty(value = "建筑面积(平方米)")
	private String ywbBuiltupArea;
	/**
	 * 共多少间
	 */
	@ApiModelProperty(value = "共多少间")
	private String ywbBetweenA;
	/**
	 * 给乙方作为什么用房
	 */
	@ApiModelProperty(value = "给乙方作为什么用房")
	private String ywbPartyRoom;
	/**
	 * 租赁期限
	 */
	@ApiModelProperty(value = "租赁期限")
	private Date ywbTerm;
	/**
	 * 甲方应于
	 */
	@ApiModelProperty(value = "甲方应于")
	private Date ywbShall;
	/**
	 * 第二.2项时间要求
	 */
	@ApiModelProperty(value = "第二.2项时间要求")
	private String ywbRequirement;
	/**
	 * 租金标准
	 */
	@ApiModelProperty(value = "租金标准")
	private Double ywbStandard;
	/**
	 * 租金支付时间（□月/ □季/ □半年/ □年）
	 */
	@ApiModelProperty(value = "租金支付时间（□月/ □季/ □半年/ □年）")
	private String ywbPayment;
	/**
	 * 租金总计含税
	 */
	@ApiModelProperty(value = "租金总计含税")
	private BigDecimal ywbTotalrent;
	/**
	 * 乙方以银行汇款的方式按（□月/ □季/ □半年/ □年）支付
	 */
	@ApiModelProperty(value = "乙方以银行汇款的方式按（□月/ □季/ □半年/ □年）支付")
	private String ywbBankRemittance;
	/**
	 * 乙方须在合同签订后多少日
	 */
	@ApiModelProperty(value = "乙方须在合同签订后多少日")
	private String ywbMdmbsContract;
	/**
	 * 上一期租金期间到期日前的多少日
	 */
	@ApiModelProperty(value = "上一期租金期间到期日前的多少日")
	private String ywbHmdbfPrevious;
	/**
	 * 大写押金：甲、乙双方约定，甲方交付该房屋时，乙方向甲方支付房屋押金人民币
	 */
	@ApiModelProperty(value = "大写押金：甲、乙双方约定，甲方交付该房屋时，乙方向甲方支付房屋押金人民币")
	private String ywbDepositA;
	/**
	 * 押金：甲、乙双方约定，甲方交付该房屋时，乙方向甲方支付房屋押金人民币
	 */
	@ApiModelProperty(value = "押金：甲、乙双方约定，甲方交付该房屋时，乙方向甲方支付房屋押金人民币")
	private BigDecimal ywbDeposit;
	/**
	 * 违约金
	 */
	@ApiModelProperty(value = "违约金")
	private Double ywbDamagesA;
	/**
	 * 租赁期内甲方承担的费用含
	 */
	@ApiModelProperty(value = "租赁期内甲方承担的费用含")
	private BigDecimal ywbTerminclude;
	/**
	 * 租赁期内乙方承担的费用含
	 */
	@ApiModelProperty(value = "租赁期内乙方承担的费用含")
	private BigDecimal ywbPeriod;
	/**
	 * 第五.2项时间要求
	 */
	@ApiModelProperty(value = "第五.2项时间要求")
	private Date ywbRequirementA;
	/**
	 * 百分比作为解约补偿金
	 */
	@ApiModelProperty(value = "百分比作为解约补偿金")
	private Double ywbCompensationA;
	/**
	 * 第九.4项时间要求
	 */
	@ApiModelProperty(value = "第九.4项时间要求")
	private Date ywbTimeRequirement;
	/**
	 * 其他约定事项
	 */
	@ApiModelProperty(value = "其他约定事项")
	private String ywbAgreements;
	/**
	 * 本合同（及附件）一式几份
	 */
	@ApiModelProperty(value = "本合同（及附件）一式几份")
	private String ywbHmcopies;
	/**
	 * 其中甲方执几 份
	 */
	@ApiModelProperty(value = "其中甲方执几 份")
	private String ywbHmcopiesA;
	/**
	 * 其中乙方执几 份
	 */
	@ApiModelProperty(value = "其中乙方执几 份")
	private String ywbHmcopiesB;
	/**
	 * 本合同及附件
	 */
	@ApiModelProperty(value = "本合同及附件")
	private String ywbAttachments;

}
