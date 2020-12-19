package org.springblade.contract.entity;

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


/**
 * 生产类：物流服务合同（一段+调拨运输） 实体类
 *
 * @author kx
 * @date : 2020-12-18 17:18:02
 */
@Getter
@Setter
@TableName("scl_production_category")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclProductionCategory对象", description = "生产类：物流服务合同（一段+调拨运输）")
public class SclProductionCategoryEntity extends BaseEntity {

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
	 * 乙方须按甲方要求保证每日配送运力不低于：食品【？】万件
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方须按甲方要求保证每日配送运力不低于：食品【？】万件")
	private Integer sclStorage;
	/**
	 * 乳饮【？】万件
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乳饮【？】万件")
	private Integer sclArea;
	/**
	 * 履约保证金人民币【？】万元整
	 */
    @ApiModelProperty(value="履约保证金人民币【？】万元整")
	private BigDecimal sclNo;
	/**
	 * （大写：【？】）
	 */
    @ApiModelProperty(value="（大写：【？】）")
	private String sclStorageee;
	/**
	 * 乙方每月【？】日前就上一个月的出货传票客户签收联与甲方核对
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方每月【？】日前就上一个月的出货传票客户签收联与甲方核对")
	private Integer sclConditionsa;
	/**
	 * 并以【？】方式向甲方申请结算运
	 */
    @ApiModelProperty(value="并以【？】方式向甲方申请结算运")
	private String sclNumber;
	/**
	 * 支付条件：每月【？】日前，乙方提供上月运送明细及运费金额由甲方核对
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="支付条件：每月【？】日前，乙方提供上月运送明细及运费金额由甲方核对")
	private Integer sclServices;
	/**
	 * 甲方应在当月【？】日前确认完毕
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="甲方应在当月【？】日前确认完毕")
	private Integer sclFood;
	/**
	 * 油价标准：【？】（中石化或中石油）
	 */
    @ApiModelProperty(value="油价标准：【？】（中石化或中石油）")
	private String sclDrinks;
	/**
	 * 有权要求乙方优先安排 【？】客户的配送
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="有权要求乙方优先安排 【？】客户的配送")
	private Integer sclDairy;
	/**
	 * 合同有效期：开始时间
	 */
    @ApiModelProperty(value="合同有效期：开始时间")
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date sclDateOfs;
	/**
	 * 合同有效期：开始时间
	 */
    @ApiModelProperty(value="合同有效期：开始时间")
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date sclRequirementsss;

}
