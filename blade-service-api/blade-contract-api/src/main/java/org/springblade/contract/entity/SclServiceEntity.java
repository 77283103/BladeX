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
 * 生产类：物流服务合同（二段仓储+配送） 实体类
 *
 * @author kx
 * @date : 2020-12-18 17:07:58
 */
@Getter
@Setter
@TableName("scl_service")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclService对象", description = "生产类：物流服务合同（二段仓储+配送）")
public class SclServiceEntity extends BaseEntity {

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
	 * 仓储地点【？】市
	 */
    @ApiModelProperty(value="仓储地点【？】市")
	private String sclStorage;
	/**
	 * 【？】区
	 */
    @ApiModelProperty(value="【？】区")
	private String sclArea;
	/**
	 * 【？】号
	 */
    @ApiModelProperty(value="【？】号")
	private String sclNo;
	/**
	 * 仓储面积：【？】平方米
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="仓储面积：【？】平方米")
	private Integer sclStorageee;
	/**
	 * 其他条件：【？】
	 */
    @ApiModelProperty(value="其他条件：【？】")
	private String sclConditionsa;
	/**
	 * 甲方应将库存数量控制在【？】箱以内
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="甲方应将库存数量控制在【？】箱以内")
	private Integer sclNumber;
	/**
	 * 乙方必须协助部分特殊出货的增值服务：【？】
	 */
    @ApiModelProperty(value="乙方必须协助部分特殊出货的增值服务：【？】")
	private String sclServices;
	/**
	 * 剩余保质期：食品少于【？】天
	 */
    @ApiModelProperty(value="剩余保质期：食品少于【？】天")
	private String sclFood;
	/**
	 * 饮料（茶、果汁、咖啡、奶茶）少于【？】天
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="饮料（茶、果汁、咖啡、奶茶）少于【？】天")
	private Integer sclDrinks;
	/**
	 * 乳品少于【？】天
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乳品少于【？】天")
	private Integer sclDairy;
	/**
	 * 水少于【？】天的
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="水少于【？】天的")
	private Integer sclWater;
	/**
	 * 其他要求
	 */
    @ApiModelProperty(value="其他要求")
	private String sclRequirementsp;
	/**
	 * 乙方保证每日配送运力在下列幅度之内：【？】万件
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方保证每日配送运力在下列幅度之内：【？】万件")
	private Integer sclRange;
	/**
	 * 乙方为甲方提供配送服务的区域为：【？】
	 */
    @ApiModelProperty(value="乙方为甲方提供配送服务的区域为：【？】")
	private String sclAreae;
	/**
	 * （第二.3.（2）项压单量要求）：【？】
	 */
    @ApiModelProperty(value="（第二.3.（2）项压单量要求）：【？】")
	private String sclRequirementse;
	/**
	 * （第二.3.（2）.1项违约金）：【？】
	 */
    @ApiModelProperty(value="（第二.3.（2）.1项违约金）：【？】")
	private String sclContractd;
	/**
	 * （第二.3.（2）.1项违约金）：【？】
	 */
    @ApiModelProperty(value="（第二.3.（2）.1项违约金）：【？】")
	private String sclSecond;
	/**
	 * （第二.3.（2）.3项违约金）： 【？】
	 */
    @ApiModelProperty(value="（第二.3.（2）.3项违约金）： 【？】")
	private String sclBreach;
	/**
	 * 乙方向甲方提供一年【？】天
	 */
    @ApiModelProperty(value="乙方向甲方提供一年【？】天")
	private String sclProvide;
	/**
	 * 每天【？】小时的仓储服务和不间断的运输服务
	 */
    @ApiModelProperty(value="每天【？】小时的仓储服务和不间断的运输服务")
	private String sclHours;
	/**
	 * 甲方进货时间应尽量安排在正常工作日内（周一至周六）的上午【？】
	 */
    @ApiModelProperty(value="甲方进货时间应尽量安排在正常工作日内（周一至周六）的上午【？】")
	private String sclMorning;
	/**
	 * 至下午【？】
	 */
    @ApiModelProperty(value="至下午【？】")
	private String sclAfternoon;
	/**
	 * 因特殊情况，在每天工作时间以外的时间，甲方应提前【？】小时通知乙方，以便乙方安排作业
	 */
    @ApiModelProperty(value="因特殊情况，在每天工作时间以外的时间，甲方应提前【？】小时通知乙方，以便乙方安排作业")
	private String sclAdvance;
	/**
	 * 节假日进货的甲方应提前【？】个小时通知乙方
	 */
    @ApiModelProperty(value="节假日进货的甲方应提前【？】个小时通知乙方")
	private String sclSeason;
	/**
	 * 退货作业：等待【？】分钟内如无法解决可收下退货
	 */
    @ApiModelProperty(value="退货作业：等待【？】分钟内如无法解决可收下退货")
	private String sclReturn;
	/**
	 * （第三.1.1项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第三.1.1项日期要求）：【？】")
	private String sclRequesta;
	/**
	 * （第三.1.2项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第三.1.2项日期要求）：【？】")
	private String sclItems;
	/**
	 * （第三.2项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第三.2项日期要求）：【？】")
	private String sclDate;
	/**
	 * （第三.3.1项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第三.3.1项日期要求）：【？】")
	private String sclRequirementsddd;
	/**
	 * （第三.3.2项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第三.3.2项日期要求）：【？】")
	private String sclThird;
	/**
	 * （第四.1项时间要求）：【？】
	 */
    @ApiModelProperty(value="（第四.1项时间要求）：【？】")
	private String sclRequirementsf;
	/**
	 * 数据传送：【？】（乙方对应数据接口）
	 */
    @ApiModelProperty(value="数据传送：【？】（乙方对应数据接口）")
	private String sclTransfer;
	/**
	 * （第四.3项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第四.3项日期要求）：【？】")
	private String sclDateOfs;
	/**
	 * （第四.4项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第四.4项日期要求）：【？】")
	private String sclRequirementsss;
	/**
	 * 产成品仓储费用（请选择（1）、（2）两种收费标准中的一种）：【？】
	 */
    @ApiModelProperty(value="产成品仓储费用（请选择（1）、（2）两种收费标准中的一种）：【？】")
	private String sclStandard;
	/**
	 * 乙方提供给甲方之仓库面积为【？】平米
	 */
    @ApiModelProperty(value="乙方提供给甲方之仓库面积为【？】平米")
	private String sclAread;
	/**
	 * 行销品仓储费用：乙方提供给甲方之行销品仓库面积为【？】平米
	 */
    @ApiModelProperty(value="行销品仓储费用：乙方提供给甲方之行销品仓库面积为【？】平米")
	private String sclWarehouse;
	/**
	 * 依订单按单箱计价：饮料、乳品、方便面等商品按【？】元/箱计算
	 */
    @ApiModelProperty(value="依订单按单箱计价：饮料、乳品、方便面等商品按【？】元/箱计算")
	private String sclGoods;
	/**
	 * （第六.1项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第六.1项日期要求）：【？】")
	private String sclRequest;
	/**
	 * （第六.2项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第六.2项日期要求）：【？】")
	private String sclDates;
	/**
	 * （第六.3项日期要求）：【？】
	 */
    @ApiModelProperty(value="（第六.3项日期要求）：【？】")
	private String sclItem;
	/**
	 * （第六.4项时间要求）：【？】
	 */
    @ApiModelProperty(value="（第六.4项时间要求）：【？】")
	private String sclTime;
	/**
	 * 履约保证金人民币【？】
	 */
    @ApiModelProperty(value="履约保证金人民币【？】")
	private BigDecimal sclYuan;
	/**
	 * 整（大写：【？】）
	 */
    @ApiModelProperty(value="整（大写：【？】）")
	private String sclWhole;
	/**
	 * 乙方缴纳的履约保证金因赔偿甲方损失或本合同中约定的其他扣款原因导致不足【？】万元人民币时，乙方应在7日内补足
	 */
    @ApiModelProperty(value="乙方缴纳的履约保证金因赔偿甲方损失或本合同中约定的其他扣款原因导致不足【？】万元人民币时，乙方应在7日内补足")
	private String sclLead;
	/**
	 * （第九.1项次数要求）：【？】次（含
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="（第九.1项次数要求）：【？】次（含")
	private Integer sclRequirementsd;
	/**
	 * 【？】次）
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="【？】次）")
	private Integer sclTimes;
	/**
	 * （第九.3项违约金）：【？】
	 */
    @ApiModelProperty(value="（第九.3项违约金）：【？】")
	private String sclContract;
	/**
	 * （第九.3项时间要求）：【？】
	 */
    @ApiModelProperty(value="（第九.3项时间要求）：【？】")
	private String sclRequirements;
	/**
	 * （第九.5项次数要求）：【？】
	 */
    @ApiModelProperty(value="（第九.5项次数要求）：【？】")
	private String sclNumberOf;
	/**
	 * （第九.7项倍数要求）：【？】
	 */
    @ApiModelProperty(value="（第九.7项倍数要求）：【？】")
	private String sclMultiple;
	/**
	 * （第九.7项次数要求）：【？】次
	 */
    @ApiModelProperty(value="（第九.7项次数要求）：【？】次")
	private String sclRequirementsa;
	/**
	 * （含【？】次）
	 */
    @ApiModelProperty(value="（含【？】次）")
	private String sclContaining;
	/**
	 * （第九.8项次数要求）：【？】
	 */
    @ApiModelProperty(value="（第九.8项次数要求）：【？】")
	private String sclNinth;
	/**
	 * 甲方驻仓人员在乙方的办公场所费用为人民币【？】元/月
	 */
    @ApiModelProperty(value="甲方驻仓人员在乙方的办公场所费用为人民币【？】元/月")
	private BigDecimal sclCost;
	/**
	 * 甲方于每月【？】日通过银行汇款方式向乙方支付上月费用
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="甲方于每月【？】日通过银行汇款方式向乙方支付上月费用")
	private Integer sclBank;
	/**
	 * 乙方应提供甲方驻仓人员以下办公等条件：【？】
	 */
    @ApiModelProperty(value="乙方应提供甲方驻仓人员以下办公等条件：【？】")
	private String sclConditions;
	/**
	 * 第十一.3项日期区间要求）：【？】
	 */
    @ApiModelProperty(value="第十一.3项日期区间要求）：【？】")
	private String sclDateOf;
	/**
	 * （第十一.4项时间要求）：【？】
	 */
    @ApiModelProperty(value="（第十一.4项时间要求）：【？】")
	private String sclRequirementsaaadd;
	/**
	 * 配达率：（不得低于【？】%）
	 */
    @ApiModelProperty(value="配达率：（不得低于【？】%）")
	private Double sclRatio;
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
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;

}
