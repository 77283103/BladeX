package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 业务类：15.房屋租赁合同模板 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 王策
 * @date : 2021-01-12 17:30:27
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "业务类：15.房屋租赁合同模板请求对象")
public class YwbBusinessContractTemplateRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="出租方")
	private String ywbLessors;
	
    @ApiModelProperty(value="出租方证件类型及编号")
	private Integer ywbCertificate;
	
    @ApiModelProperty(value="出租房住所地")
	private String ywbAddress;
	
    @ApiModelProperty(value="承租方")
	private String ywbTenantry;
	
    @ApiModelProperty(value="承租方证件类型及编号")
	private String ywbCertificateB;
	
    @ApiModelProperty(value="承租方住所地")
	private String ywbResidence;
	
    @ApiModelProperty(value="甲方同意将位于")
	private String ywbAgrees;
	
    @ApiModelProperty(value="建筑面积(平方米)")
	private String ywbBuiltupArea;
	
    @ApiModelProperty(value="共多少间")
	private String ywbBetweenA;
	
    @ApiModelProperty(value="给乙方作为什么用房")
	private String ywbPartyRoom;
	
    @ApiModelProperty(value="租赁期限")
	private Date ywbTerm;
	
    @ApiModelProperty(value="甲方应于")
	private Date ywbShall;
	
    @ApiModelProperty(value="第二.2项时间要求")
	private String ywbRequirement;
	
    @ApiModelProperty(value="租金标准")
	private Double ywbStandard;
	
    @ApiModelProperty(value="租金支付时间（□月/ □季/ □半年/ □年）")
	private String ywbPayment;
	
    @ApiModelProperty(value="租金总计含税")
	private BigDecimal ywbTotalrent;
	
    @ApiModelProperty(value="乙方以银行汇款的方式按（□月/ □季/ □半年/ □年）支付")
	private String ywbBankRemittance;
	
    @ApiModelProperty(value="乙方须在合同签订后多少日")
	private String ywbMdmbsContract;
	
    @ApiModelProperty(value="上一期租金期间到期日前的多少日")
	private String ywbHmdbfPrevious;
	
    @ApiModelProperty(value="大写押金：甲、乙双方约定，甲方交付该房屋时，乙方向甲方支付房屋押金人民币")
	private String ywbDepositA;
	
    @ApiModelProperty(value="押金：甲、乙双方约定，甲方交付该房屋时，乙方向甲方支付房屋押金人民币")
	private BigDecimal ywbDeposit;
	
    @ApiModelProperty(value="违约金")
	private Double ywbDamagesA;
	
    @ApiModelProperty(value="租赁期内甲方承担的费用含")
	private BigDecimal ywbTerminclude;
	
    @ApiModelProperty(value="租赁期内乙方承担的费用含")
	private BigDecimal ywbPeriod;
	
    @ApiModelProperty(value="第五.2项时间要求")
	private Date ywbRequirementA;
	
    @ApiModelProperty(value="百分比作为解约补偿金")
	private Double ywbCompensationA;

	@ApiModelProperty(value = "第九.3项解决补偿金")
	private Date ywbSettlementCompensation;

    @ApiModelProperty(value="第九.4项时间要求")
	private Date ywbTimeRequirement;
	
    @ApiModelProperty(value="其他约定事项")
	private String ywbAgreements;
	
    @ApiModelProperty(value="本合同（及附件）一式几份")
	private String ywbHmcopies;
	
    @ApiModelProperty(value="其中甲方执几 份")
	private String ywbHmcopiesA;
	
    @ApiModelProperty(value="其中乙方执几 份")
	private String ywbHmcopiesB;
	
    @ApiModelProperty(value="本合同及附件")
	private String ywbAttachments;
	
}
