package org.springblade.contract.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springblade.core.tool.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 生产类：作业外包协议 实体类
 *
 * @author kx
 * @date : 2020-12-18 16:08:24
 */
@Getter
@Setter
@TableName("scl_outsourcing_agreement")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclOutsourcingAgreement对象", description = "生产类：作业外包协议")
public class SclOutsourcingAgreementEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 发包方
	 */
    @ApiModelProperty(value="发包方")
	private String sclPartyA;
	/**
	 * 发包方住所
	 */
    @ApiModelProperty(value="发包方住所")
	private String sclPartyB;
	/**
	 * 承包方
	 */
    @ApiModelProperty(value="承包方")
	private String sclYear;
	/**
	 * 承包方住所
	 */
    @ApiModelProperty(value="承包方住所")
	private String sclMonth;
	/**
	 * 承包内容：甲方将【？】作业发包给乙方
	 */
    @ApiModelProperty(value="承包内容：甲方将【？】作业发包给乙方")
	private String sclDay;
	/**
	 * 承包开始时间
	 */
    @ApiModelProperty(value="承包开始时间")
	private LocalTime sclSite;
	/**
	 * 承包开始时间
	 */
    @ApiModelProperty(value="承包开始时间")
	private LocalTime sclStorage;
	/**
	 * 上述方法计算的赔偿金额小于【？】元或无法查明具体偷盗、偷食产品的价值
	 */
    @ApiModelProperty(value="上述方法计算的赔偿金额小于【？】元或无法查明具体偷盗、偷食产品的价值")
	private BigDecimal sclArea;
	/**
	 * 则以【？】元为赔偿金
	 */
    @ApiModelProperty(value="则以【？】元为赔偿金")
	private BigDecimal sclNo;
	/**
	 * 乙方应为其工作人员缴纳每人不低于【？】万元保险金额的人身意外保险
	 */
    @ApiModelProperty(value="乙方应为其工作人员缴纳每人不低于【？】万元保险金额的人身意外保险")
	private BigDecimal sclStorageee;
	/**
	 * 乙方联系方式：【？】
	 */
    @ApiModelProperty(value="乙方联系方式：【？】")
	private String sclConditionsa;
	/**
	 * 乙方工作人员应达到的其他标准：【？】
	 */
    @ApiModelProperty(value="乙方工作人员应达到的其他标准：【？】")
	private String sclNumber;
	/**
	 * 乙方应于本协议签署之日起【？】日内
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方应于本协议签署之日起【？】日内")
	private Integer sclServices;
	/**
	 * 向甲方支付履约保证金【？】万元
	 */
    @ApiModelProperty(value="向甲方支付履约保证金【？】万元")
	private BigDecimal sclFood;
	/**
	 * （大写：【？】）
	 */
    @ApiModelProperty(value="（大写：【？】）")
	private String sclDrinks;
	/**
	 * 乙方应在【？】日内将履约保证金补足
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方应在【？】日内将履约保证金补足")
	private Integer sclDairy;
	/**
	 * 超过【？】日仍未补足的，甲方有权解除合同
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="超过【？】日仍未补足的，甲方有权解除合同")
	private Integer sclWater;
	/**
	 * 乙方根据甲、乙双方共同确认的费用资料开具正式合法增值税【？】发票
	 */
    @ApiModelProperty(value="乙方根据甲、乙双方共同确认的费用资料开具正式合法增值税【？】发票")
	private String sclRequirementsp;
	/**
	 * 并于每月【？】日前交由甲方
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="并于每月【？】日前交由甲方")
	private Integer sclRange;
	/**
	 * 甲方收到发票后【？】日内
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="甲方收到发票后【？】日内")
	private Integer sclAreae;
	/**
	 * 以【？】形式支付上月承包作业费用
	 */
    @ApiModelProperty(value="以【？】形式支付上月承包作业费用")
	private String sclRequirementse;
	/**
	 * 乙方账户信息：户名：【？】
	 */
    @ApiModelProperty(value="乙方账户信息：户名：【？】")
	private String sclContractd;
	/**
	 * 账号：【？】
	 */
    @ApiModelProperty(value="账号：【？】")
	private String sclSecond;
	/**
	 * 开户行：【？】
	 */
    @ApiModelProperty(value="开户行：【？】")
	private String sclBreach;
	/**
	 * 解约金：【？】万元
	 */
    @ApiModelProperty(value="解约金：【？】万元")
	private BigDecimal sclProvide;
	/**
	 * 解约补偿金：【？】元
	 */
    @ApiModelProperty(value="解约补偿金：【？】元")
	private BigDecimal sclHours;
	/**
	 * （第七.4项违约金）：【？】
	 */
    @ApiModelProperty(value="（第七.4项违约金）：【？】")
	private String sclMorning;
	/**
	 * （第七.5项违约金）：【？】
	 */
    @ApiModelProperty(value="（第七.5项违约金）：【？】")
	private String sclAfternoon;
	/**
	 * 其他约定：【？】
	 */
    @ApiModelProperty(value="其他约定：【？】")
	private String sclAdvance;

}
