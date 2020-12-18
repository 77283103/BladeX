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
 * 生产类：物流服务合同（二段配送） 实体类
 *
 * @author kx
 * @date : 2020-12-18 17:17:38
 */
@Getter
@Setter
@TableName("scl_logistics_service")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclLogisticsService对象", description = "生产类：物流服务合同（二段配送）")
public class SclLogisticsServiceEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 立合同人甲方
	 */
    @ApiModelProperty(value="立合同人甲方")
	private String sclPartyA;
	/**
	 * 立合同人乙方
	 */
    @ApiModelProperty(value="立合同人乙方")
	private String sclPartyB;
	/**
	 * 签订日期：年
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="签订日期：年")
	private Integer sclYear;
	/**
	 * 月
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="月")
	private Integer sclMonth;
	/**
	 * 日
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="日")
	private Integer sclDay;
	/**
	 * 签订地点
	 */
    @ApiModelProperty(value="签订地点")
	private String sclSite;
	/**
	 * （第一项时间节点）：【？】年
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="（第一项时间节点）：【？】年")
	private Integer sclStorage;
	/**
	 * 中标的物流线路为
	 */
    @ApiModelProperty(value="中标的物流线路为")
	private String sclArea;
	/**
	 * 正常单
	 */
    @ApiModelProperty(value="正常单")
	private String sclNo;
	/**
	 * 加单
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="加单")
	private Integer sclStorageee;
	/**
	 * 加急单
	 */
    @ApiModelProperty(value="加急单")
	private String sclConditionsa;
	/**
	 * （第三.3.（7）项时间要求）：【？】
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="（第三.3.（7）项时间要求）：【？】")
	private Integer sclNumber;
	/**
	 * （第三.3.（8）项时间要求）：【？】
	 */
    @ApiModelProperty(value="（第三.3.（8）项时间要求）：【？】")
	private String sclServices;
	/**
	 * 常规负责甲方货物配送车辆不得少于    台【？】
	 */
    @ApiModelProperty(value="常规负责甲方货物配送车辆不得少于    台【？】")
	private String sclFood;
	/**
	 * 其他要求：【？】
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="其他要求：【？】")
	private Integer sclDrinks;
	/**
	 * 有权要求乙方优先安排 【？】客户的配送
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="有权要求乙方优先安排 【？】客户的配送")
	private Integer sclDairy;
	/**
	 * （第六.2.（7）项配送量要求）：【？】
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="（第六.2.（7）项配送量要求）：【？】")
	private Integer sclWater;
	/**
	 * （第六.2.（7）项时间要求）：【？】
	 */
    @ApiModelProperty(value="（第六.2.（7）项时间要求）：【？】")
	private String sclRequirementsp;
	/**
	 * 保证每日配送运力在下列幅度之内：【？】万件
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="保证每日配送运力在下列幅度之内：【？】万件")
	private Integer sclRange;
	/**
	 * （9）运费由乙方承担、且乙方须按【？】元/单/天向甲方承担违约责任
	 */
    @ApiModelProperty(value="（9）运费由乙方承担、且乙方须按【？】元/单/天向甲方承担违约责任")
	private String sclAreae;
	/**
	 * 退仓运费按相同地点正常运价【？】%计算
	 */
    @ApiModelProperty(value="退仓运费按相同地点正常运价【？】%计算")
	private Double sclRequirementse;
	/**
	 * 当日配送货物如需退货，乙方最迟于次日【？】时前（节假日顺延）将所退货物送到甲方仓库办理退仓手续
	 */
    @ApiModelProperty(value="当日配送货物如需退货，乙方最迟于次日【？】时前（节假日顺延）将所退货物送到甲方仓库办理退仓手续")
	private String sclContractd;
	/**
	 * 履约保证金：【？】万元整
	 */
    @ApiModelProperty(value="履约保证金：【？】万元整")
	private BigDecimal sclSecond;
	/**
	 * （大写：         【？】）
	 */
    @ApiModelProperty(value="（大写：         【？】）")
	private String sclBreach;
	/**
	 * 乙方缴纳的履约保证金因赔偿甲方损失或本合同中约定的其他扣款原因导致不足【？】万元人民币时, 乙方应在7日内补足
	 */
    @ApiModelProperty(value="乙方缴纳的履约保证金因赔偿甲方损失或本合同中约定的其他扣款原因导致不足【？】万元人民币时, 乙方应在7日内补足")
	private BigDecimal sclProvide;
	/**
	 * 双方配送、装卸费用结算时间段为：【？】
	 */
    @ApiModelProperty(value="双方配送、装卸费用结算时间段为：【？】")
	private String sclHours;
	/**
	 * 乙方于结算截止日之后【？】日内，将运费清单递至甲方
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方于结算截止日之后【？】日内，将运费清单递至甲方")
	private Integer sclMorning;
	/**
	 * 对于账目不清之处，乙方有责任与甲方物流部核算员共同核对，并限于【？】内达成共识
	 */
    @ApiModelProperty(value="对于账目不清之处，乙方有责任与甲方物流部核算员共同核对，并限于【？】内达成共识")
	private String sclAfternoon;
	/**
	 * 退仓费用按相同地点正常运价【？】%计算
	 */
    @ApiModelProperty(value="退仓费用按相同地点正常运价【？】%计算")
	private Double sclAdvance;
	/**
	 * 因甲方或甲方客户原因，导致乙方配送的车辆未能及时卸货而在当地住宿的，甲方须
	 */
    @ApiModelProperty(value="因甲方或甲方客户原因，导致乙方配送的车辆未能及时卸货而在当地住宿的，甲方须")
	private String sclSeason;
	/**
	 * （第九.2.（4）项次数要求）：【？】
	 */
    @ApiModelProperty(value="（第九.2.（4）项次数要求）：【？】")
	private String sclReturn;
	/**
	 * （含【？】次）
	 */
    @ApiModelProperty(value="（含【？】次）")
	private String sclRequesta;
	/**
	 * 视为乙方履行延迟，乙方应按【？】元/单/天承担违约责任
	 */
    @ApiModelProperty(value="视为乙方履行延迟，乙方应按【？】元/单/天承担违约责任")
	private BigDecimal sclItems;
	/**
	 * （第九.2.（9）项倍数要求）：【？】
	 */
    @ApiModelProperty(value="（第九.2.（9）项倍数要求）：【？】")
	private String sclDate;
	/**
	 * （第九.2.（9）项次数要求）：【？
	 */
    @ApiModelProperty(value="（第九.2.（9）项次数要求）：【？")
	private String sclRequirementsddd;
	/**
	 * （含【？】次）
	 */
    @ApiModelProperty(value="（含【？】次）")
	private String sclThird;
	/**
	 * 日配达率（当日已配达传票张数/当日总传票张数*100%）（不得低于【？】%
	 */
    @ApiModelProperty(value="日配达率（当日已配达传票张数/当日总传票张数*100%）（不得低于【？】%")
	private Double sclRequirementsf;
	/**
	 * （第九.2.（12）项违约金）：【？】
	 */
    @ApiModelProperty(value="（第九.2.（12）项违约金）：【？】")
	private String sclTransfer;
	/**
	 * 合同有效期：开始时间
	 */
    @ApiModelProperty(value="合同有效期：开始时间")
	private LocalTime sclDateOfs;
	/**
	 * 合同有效期：开始时间
	 */
    @ApiModelProperty(value="合同有效期：开始时间")
	private LocalTime sclRequirementsss;
	/**
	 * 除本合同另有约定外，非经双方协商一致，任一方均不得擅自解除本合同，否则应向对方支付违约金计人民币【？】万元整
	 */
    @ApiModelProperty(value="除本合同另有约定外，非经双方协商一致，任一方均不得擅自解除本合同，否则应向对方支付违约金计人民币【？】万元整")
	private BigDecimal sclStandard;
	/**
	 * 特别约定：【？】
	 */
    @ApiModelProperty(value="特别约定：【？】")
	private String sclAread;
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;

}
