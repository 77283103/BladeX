package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 生产类：加工承揽合同（代工合同） 实体类
 *
 * @author 生产类：加工承揽合同（代工合同）
 * @date : 2021-01-08 14:24:53
 */
@Getter
@Setter
@TableName("scl_construction_project")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclConstructionProject对象", description = "生产类：加工承揽合同（代工合同）")
public class SclConstructionProjectEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 定作方
	 */
	@ApiModelProperty(value="定作方")
	private String sclOrderingParty;
	/**
	 * 定作方地址
	 */
	@ApiModelProperty(value="定作方地址")
	private String sclPartyAddress;
	/**
	 * 承揽方
	 */
	@ApiModelProperty(value="承揽方")
	private String sclContractor;
	/**
	 * 承揽方地址
	 */
	@ApiModelProperty(value="承揽方地址")
	private String sclAddressOfContractor;
	/**
	 * 加工开始时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="加工开始时间")
	private Date sclStartTime;
	/**
	 * 加工完成时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="加工完成时间")
	private Date sclCompletionTime;
	/**
	 * 加工方式：甲乙双方同意采取以下【？】条方式合作
	 */
	@ApiModelProperty(value="加工方式：甲乙双方同意采取以下【？】条方式合作")
	private String sclProcessingWay;
	/**
	 * 运输及费用：代工产品之运输（包括但不限于叫车、装卸货、运输防护等）由【？】方负责
	 */
	@ApiModelProperty(value="运输及费用：代工产品之运输（包括但不限于叫车、装卸货、运输防护等）由【？】方负责")
	private String sclExpenseBearingParty;
	/**
	 * 费用由【？】方承担
	 */
	@ApiModelProperty(value="费用由【？】方承担")
	private String sclExpenseBearingParty1;
	/**
	 * 代工产品交付前的毁损、灭失风险由【？】方承担
	 */
	@ApiModelProperty(value="代工产品交付前的毁损、灭失风险由【？】方承担")
	private String sclRiskOfLoss;
	/**
	 * 支付时间
	 */
	@ApiModelProperty(value="支付时间")
	private String sclPaymentTime;
	/**
	 * (第四.4.1项日期要求)
	 */
	@ApiModelProperty(value="(第四.4.1项日期要求)")
	private String sclDateOfRequest;
	/**
	 * 开户行名称
	 */
	@ApiModelProperty(value="开户行名称")
	private String sclNameOfBank;
	/**
	 * 账户名称
	 */
	@ApiModelProperty(value="账户名称")
	private String sclAccountName;
	/**
	 * 银行账号
	 */
	@ApiModelProperty(value="银行账号")
	private String sclBankNumber;
	/**
	 * 定金：定作方自本合同签订之日起【？】日内
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value="定金：定作方自本合同签订之日起【？】日内")
	private Integer sclDeposit;
	/**
	 * 向承揽方支付【？】万元定金
	 */
	@ApiModelProperty(value="向承揽方支付【？】万元定金")
	private BigDecimal sclDeposits;
	/**
	 * 若制程异常承揽方未主动通报定作方，定作方有权对该批产品拒收，同时处罚承揽方【？】元
	 */
	@ApiModelProperty(value="若制程异常承揽方未主动通报定作方，定作方有权对该批产品拒收，同时处罚承揽方【？】元")
	private BigDecimal sclPenalties;
	/**
	 * 如承揽方故意隐瞒品质异常，承揽方需向定作方支付【？】元/次的违约金
	 */
	@ApiModelProperty(value="如承揽方故意隐瞒品质异常，承揽方需向定作方支付【？】元/次的违约金")
	private BigDecimal sclBreachOfContract;
	/**
	 * （第6.1项日期要求）
	 */
	@ApiModelProperty(value="（第6.1项日期要求）")
	private String sclDatatime;
	/**
	 * 扣减当月加工费
	 */
	@ApiModelProperty(value="扣减当月加工费")
	private BigDecimal sclProcessingFee1;
	/**
	 * （第7.8项违约金）
	 */
	@ApiModelProperty(value="（第7.8项违约金）")
	private String sclBreachOfContract78;
	/**
	 * （第7.11项违约金
	 */
	@ApiModelProperty(value="（第7.11项违约金")
	private String sclBreachOfContract711;
	/**
	 * （第7.12项违约金
	 */
	@ApiModelProperty(value="（第7.12项违约金")
	private String sclBreachOfContract712;
	/**
	 * （第7.15.1项违约金）
	 */
	@ApiModelProperty(value="（第7.15.1项违约金）")
	private String sclBreachOfContract7151;
	/**
	 * （第7.15.2项违约金）
	 */
	@ApiModelProperty(value="（第7.15.2项违约金）")
	private String sclBreachOfContract7152;
	/**
	 * （第8.1项赔付金）
	 */
	@ApiModelProperty(value="（第8.1项赔付金）")
	private String sclPaid;
	/**
	 * 拼接附件
	 */
	@ApiModelProperty(value = "拼接附件")
	private String annex;
	/**
	 * 加工承揽合同（代工合同）关联表1
	 */
	@ApiModelProperty(value="（加工承揽合同（代工合同）关联表1）")
	@TableField(exist = false)
	private List<SclConstructionProject1Entity> sclConstructionProject1EntityList;
	/**
	 * 加工承揽合同（代工合同）关联表2
	 */
	@ApiModelProperty(value="（加工承揽合同（代工合同）关联表2）")
	@TableField(exist = false)
	private List<SclConstructionProject2Entity> sclConstructionProject2EntityList;
	/**
	 * 加工承揽合同（代工合同）关联表3
	 */
	@ApiModelProperty(value="（加工承揽合同（代工合同）关联表3）")
	@TableField(exist = false)
	private List<SclConstructionProject3Entity> sclConstructionProject3EntityList;

}
