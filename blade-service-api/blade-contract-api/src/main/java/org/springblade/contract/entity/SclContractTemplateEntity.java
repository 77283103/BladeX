package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalTime;


/**
 * 生产类：下脚品买卖合同模版 实体类
 *
 * @author 张文武
 * @date : 2021-01-04 15:17:26
 */
@Getter
@Setter
@TableName("scl_contract_template")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclContractTemplate对象", description = "生产类：下脚品买卖合同模版")
public class SclContractTemplateEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方（卖方）
	 */
    @ApiModelProperty(value="甲方（卖方）")
	private String sclPartyA;
	/**
	 * 甲方（卖方）地址：【？】
	 */
    @ApiModelProperty(value="甲方（卖方）地址：【？】")
	private String sclPartyB;
	/**
	 * 乙方（买方）：【？】
	 */
    @ApiModelProperty(value="乙方（买方）：【？】")
	private String sclPatyB;
	/**
	 * 乙方（买方）地址：【？】
	 */
    @ApiModelProperty(value="乙方（买方）地址：【？】")
	private String sclMonth;
	/**
	 * 经甲乙双方协商一致，本着平等互利的原则，就甲方向乙方出售甲方之【？】类（一般类、费面类或费油类）下脚品事宜，签订本合同，以资双方信守履行
	 */
    @ApiModelProperty(value="经甲乙双方协商一致，本着平等互利的原则，就甲方向乙方出售甲方之【？】类（一般类、费面类或费油类）下脚品事宜，签订本合同，以资双方信守履行")
	private String sclDay;
	/**
	 * 甲方将生产过程中产生的【？】类下脚品外卖给乙方，由乙方至甲方厂区内自行提货并向甲方支付费用
	 */
    @ApiModelProperty(value="甲方将生产过程中产生的【？】类下脚品外卖给乙方，由乙方至甲方厂区内自行提货并向甲方支付费用")
	private String sclSite;
	/**
	 * 乙方可在每周【？】
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方可在每周【？】")
	private Integer sclStorage;
	/**
	 * 至周【？】
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="至周【？】")
	private Integer sclArea;
	/**
	 * 【？】时
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="【？】时")
	private Integer sclNo;
	/**
	 * 【？】分
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="【？】分")
	private Integer sclStorageee;
	/**
	 * 【？】时
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="【？】时")
	private Integer sclConditionsa;
	/**
	 * 【？】分,进行分拣、装车等作业
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="【？】分,进行分拣、装车等作业")
	private Integer sclNumber;
	/**
	 * 分拣作业地点：【？】
	 */
    @ApiModelProperty(value="分拣作业地点：【？】")
	private String sclServices;
	/**
	 * 按上述方法计算的当次赔偿金额小于【？】元或无法查明具体偷盗、偷食产品的价值
	 */
    @ApiModelProperty(value="按上述方法计算的当次赔偿金额小于【？】元或无法查明具体偷盗、偷食产品的价值")
	private String sclFood;
	/**
	 * 则以【？】元/次计算赔偿金
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="则以【？】元/次计算赔偿金")
	private Integer sclDrinks;
	/**
	 * 乙方工作人员应达到的其他标准：【？】
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方工作人员应达到的其他标准：【？】")
	private Integer sclDairy;
	/**
	 * 装车：乙方应在指定地点(【？】)
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="装车：乙方应在指定地点(【？】)")
	private Integer sclWater;
	/**
	 * 履约保证金：乙方应于本合同签署前，向甲方支付履约保证金人民币【？】万元
	 */
    @ApiModelProperty(value="履约保证金：乙方应于本合同签署前，向甲方支付履约保证金人民币【？】万元")
	private BigDecimal sclRequirementsp;
	/**
	 * （大写：【？】）
	 */
    @ApiModelProperty(value="（大写：【？】）")
	private String sclRange;
	/**
	 * 履约保证金被扣除后，乙方应在【？】日内将履约保证金补足
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="履约保证金被扣除后，乙方应在【？】日内将履约保证金补足")
	private Integer sclAreae;
	/**
	 * 超过【？】日仍未补足的，甲方有权在乙方的预付款内扣除
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="超过【？】日仍未补足的，甲方有权在乙方的预付款内扣除")
	private Integer sclRequirementse;
	/**
	 * 甲方账户信息：户名【？】
	 */
    @ApiModelProperty(value="甲方账户信息：户名【？】")
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
	 * 乙方账户信息：户名
	 */
    @ApiModelProperty(value="乙方账户信息：户名")
	private String sclProvide;
	/**
	 * 账号：【？】
	 */
    @ApiModelProperty(value="账号：【？】")
	private String sclHours;
	/**
	 * 开户行：【？】
	 */
    @ApiModelProperty(value="开户行：【？】")
	private String sclMorning;
	/**
	 * 结算完成且双方确认无误后【？】日内甲方向乙方开具发票
	 */
    @ApiModelProperty(value="结算完成且双方确认无误后【？】日内甲方向乙方开具发票")
	private BigDecimal sclAfternoon;
	/**
	 * 甲方指定邮箱：【？】
	 */
    @ApiModelProperty(value="甲方指定邮箱：【？】")
	private String sclAdvance;
	/**
	 * 乙方指定邮箱：【？】
	 */
    @ApiModelProperty(value="乙方指定邮箱：【？】")
	private String sclSeason;
	/**
	 * 还须同时支付违约金【？】万元（可自履约保证金中扣除）
	 */
    @ApiModelProperty(value="还须同时支付违约金【？】万元（可自履约保证金中扣除）")
	private BigDecimal sclReturn;
	/**
	 * 甲方有权解除本合同并要求乙方支付违约金（第六.6项违约金）：【 】万元
	 */
    @ApiModelProperty(value="甲方有权解除本合同并要求乙方支付违约金（第六.6项违约金）：【 】万元")
	private BigDecimal sclLiquidatedDamages;
	/**
	 * 支付（第六.7项违约金）：【 】元作为解约补偿金
	 */
    @ApiModelProperty(value="支付（第六.7项违约金）：【 】元作为解约补偿金")
	private BigDecimal sclSenDamages;
	/**
	 * 乙方还应向甲方支付（第六.8项违约金）：【    】元的违约金
	 */
    @ApiModelProperty(value="乙方还应向甲方支付（第六.8项违约金）：【    】元的违约金")
	private BigDecimal sclEngDamages;
	/**
	 * 本合同一式【？】份
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="本合同一式【？】份")
	private Integer sclRequesta;
	/**
	 * 甲乙双方各执【？】份
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="甲乙双方各执【？】份")
	private Integer sclItems;
	/**
	 * 本合同有附件【？】份
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="本合同有附件【？】份")
	private Integer sclDate;
	/**
	 * 本合同有效期限开始时间:
	 */
    @ApiModelProperty(value="本合同有效期限开始时间:")
	private LocalTime sclStartTimes;
	/**
	 * 本合同有效期限结束时间:
	 */
    @ApiModelProperty(value="本合同有效期限结束时间:")
	private LocalTime sclEndTimes;

}
