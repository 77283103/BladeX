package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

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
	@ApiModelProperty(value="出租方")
	private String ywbLessors;
	/**
	 * 出租方证件类型及编号
	 */
	@ApiModelProperty(value="出租方证件类型及编号")
	private String ywbCertificate;
	/**
	 * 出租房住所地
	 */
	@ApiModelProperty(value="出租房住所地")
	private String ywbAddress;
	/**
	 * 承租方
	 */
	@ApiModelProperty(value="承租方")
	private String ywbTenantry;
	/**
	 * 承租方证件类型及编号
	 */
	@ApiModelProperty(value="承租方证件类型及编号")
	private String ywbCertificateB;
	/**
	 * 承租方住所地
	 */
	@ApiModelProperty(value="承租方住所地")
	private String ywbResidence;
	/**
	 * 甲方同意将位于
	 */
	@ApiModelProperty(value="甲方同意将位于")
	private String ywbAgrees;
	/**
	 * 建筑面积(平方米)
	 */
	@ApiModelProperty(value="建筑面积(平方米)")
	private String ywbBuiltupArea;
	/**
	 * 共多少间
	 */
	@ApiModelProperty(value="共多少间")
	private String ywbBetweena;
	/**
	 * 给乙方作为什么用房
	 */
	@ApiModelProperty(value="给乙方作为什么用房")
	private String ywbPartyRoom;
	/**
	 * 租赁期限起始时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="租赁期限起始时间")
	private Date ywbTermStart;
	/**
	 * 甲方应于
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="甲方应于")
	private Date ywbShall;
	/**
	 * 第二.2项时间要求
	 */
	@ApiModelProperty(value="第二.2项时间要求")
	private String ywbRequirement;
	/**
	 * 租金标准
	 */
	@ApiModelProperty(value="租金标准")
	private Double ywbStandard;
	/**
	 * 租金支付时间（□月/ □季/ □半年/ □年）
	 */
	@ApiModelProperty(value="租金支付时间（□月/ □季/ □半年/ □年）")
	private String ywbPayment;
	/**
	 * 租金总计含税
	 */
	@ApiModelProperty(value="租金总计含税")
	private BigDecimal ywbTotalrent;
	/**
	 * 乙方以银行汇款的方式按（□月/ □季/ □半年/ □年）支付
	 */
	@ApiModelProperty(value="乙方以银行汇款的方式按（□月/ □季/ □半年/ □年）支付")
	private String ywbBankRemittance;
	/**
	 * 乙方须在合同签订后多少日
	 */
	@ApiModelProperty(value="乙方须在合同签订后多少日")
	private String ywbMdmbsContract;
	/**
	 * 上一期租金期间到期日前的多少日
	 */
	@ApiModelProperty(value="上一期租金期间到期日前的多少日")
	private String ywbHmdbfPrevious;
	/**
	 * 大写乙方向甲方支付房屋押金人民币
	 */
	@ApiModelProperty(value="大写乙方向甲方支付房屋押金人民币")
	private String ywbShallCapitali;
	/**
	 * 乙方向甲方支付房屋押金人民币
	 */
	@ApiModelProperty(value="乙方向甲方支付房屋押金人民币")
	private BigDecimal ywbShallRmba;
	/**
	 * 违约金比例
	 */
	@ApiModelProperty(value="违约金比例")
	private Double ywbDamagesA;
	/**
	 * 租赁期内甲方承担的费用含
	 */
	@ApiModelProperty(value="租赁期内甲方承担的费用含")
	private String ywbTerminclude;
	/**
	 * 租赁期内乙方承担的费用含
	 */
	@ApiModelProperty(value="租赁期内乙方承担的费用含")
	private String ywbPeriod;
	/**
	 * 第五.2项时间要求
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value="第五.2项时间要求")
	private Integer ywbRequirementA;
	/**
	 * 百分比作为解约补偿金
	 */
	@ApiModelProperty(value="百分比作为解约补偿金")
	private Double ywbCompensationA;
	/**
	 * 第九.4项时间要求
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value="第九.4项时间要求")
	private Integer ywbTimeRequirement;
	/**
	 * 其他约定事项
	 */
	@ApiModelProperty(value="其他约定事项")
	private String ywbAgreements;
	/**
	 * 本合同（及附件）一式几份
	 */
	@ApiModelProperty(value="本合同（及附件）一式几份")
	private String ywbHmcopies;
	/**
	 * 其中甲方执几 份
	 */
	@ApiModelProperty(value="其中甲方执几 份")
	private String ywbHmcopiesA;
	/**
	 * 其中乙方执几 份
	 */
	@ApiModelProperty(value="其中乙方执几 份")
	private String ywbHmcopiesB;
	/**
	 * 本合同及附件
	 */
	@ApiModelProperty(value="本合同及附件")
	private String ywbAttachments;
	/**
	 * 租赁期限结束时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="租赁期限结束时间")
	private Date ywbTermEnd;
	/**
	 * 交付完成其他条件
	 */
	@ApiModelProperty(value="交付完成其他条件")
	private String ywbJiaofu;
	/**
	 * 租金税率
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value="租金税率")
	private BigDecimal ywbShuilv;
	/**
	 * 租金金额
	 */
	@ApiModelProperty(value="租金金额")
	private String ywbZujinjine;
	/**
	 * 租金金额大写
	 */
	@ApiModelProperty(value="租金金额大写")
	private String ywbZujinjined;
	/**
	 * 甲方法定代表签字
	 */
	@ApiModelProperty(value="甲方法定代表签字")
	private String ywbQianmingjia;
	/**
	 * 乙方法定代表签字
	 */
	@ApiModelProperty(value="乙方法定代表签字")
	private String ywbQianmingyi;
	/**
	 * 甲方代理人签字
	 */
	@ApiModelProperty(value="甲方代理人签字")
	private String ywbQianmingdai;
	/**
	 * 乙方代理人签字
	 */
	@ApiModelProperty(value="乙方代理人签字")
	private String ywbQianmingdaia;
	/**
	 * 甲方开户行名称
	 */
	@ApiModelProperty(value="甲方开户行名称")
	private String ywbKaihuhangjia;
	/**
	 * 乙方开户行名称
	 */
	@ApiModelProperty(value="乙方开户行名称")
	private String ywbKaihuhangjiaa;
	/**
	 * 甲方银行账号
	 */
	@ApiModelProperty(value="甲方银行账号")
	private String ywbZhanghaojia;
	/**
	 * 乙方银行账号
	 */
	@ApiModelProperty(value="乙方银行账号")
	private String ywbZhanghaoyi;
	/**
	 * 甲方联系电话
	 */
	@ApiModelProperty(value="甲方联系电话")
	private String ywbDianhuajia;
	/**
	 * 乙方联系电话
	 */
	@ApiModelProperty(value="乙方联系电话")
	private String ywbDianhuayi;
	/**
	 * 合同签约地点
	 */
	@ApiModelProperty(value="合同签约地点")
	private String ywbAdreesqian;

}
