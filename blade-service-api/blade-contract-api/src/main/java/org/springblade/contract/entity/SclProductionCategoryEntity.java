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
 * 生产类：物流服务合同（一段+调拨运输 实体类
 *
 * @author kx
 * @date : 2021-01-16 18:10:58
 */
@Getter
@Setter
@TableName("scl_production_category")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclProductionCategory对象", description = "生产类：物流服务合同（一段+调拨运输")
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
	 * 签订日期：
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="签订日期：")
	private Date sclDateOfSigning;
	/**
	 * 签订地点
	 */
    @ApiModelProperty(value="签订地点")
	private String sclSite;
	/**
	 * 招标年份
	 */
    @ApiModelProperty(value="招标年份")
	private String sclTimes;
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
	 * 乙方自愿在合同签订时向甲方缴付保证金人民币 【？】万元整
	 */
    @ApiModelProperty(value="乙方自愿在合同签订时向甲方缴付保证金人民币 【？】万元整")
	private BigDecimal sclBail;
	/**
	 * （大写：【？】）
	 */
    @ApiModelProperty(value="（大写：【？】）")
	private String sclStorageee1;
	/**
	 * 因乙方与甲方关联企业：【？】
	 */
    @ApiModelProperty(value="因乙方与甲方关联企业：【？】")
	private String sclAffiliatedEnterprise;
	/**
	 * （合同编号：【？】）
	 */
    @ApiModelProperty(value="（合同编号：【？】）")
	private String  sclContract;
	/**
	 * 且已向其缴纳保证金 【？】万元整
	 */
    @ApiModelProperty(value="且已向其缴纳保证金 【？】万元整")
	private BigDecimal sclDeposit;
	/**
	 * （大写：【？】）
	 */
    @ApiModelProperty(value="（大写：【？】）")
	private String sclStorageee2;
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
	 * 合同有效期：开始时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="合同有效期：开始时间")
	private Date sclDateOfs;
	/**
	 * 合同有效期：结束时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="合同有效期：结束时间")
	private Date sclRequirementsss;
	/**
	 * 特别约定
	 */
    @ApiModelProperty(value="特别约定")
	private String sclConvention;
	/**
	 * 甲方地址
	 */
    @ApiModelProperty(value="甲方地址")
	private String sclJfAddress;
	/**
	 * 甲方电话
	 */
    @ApiModelProperty(value="甲方电话")
	private String sclJfPhone;
	/**
	 * 甲方联系人
	 */
    @ApiModelProperty(value="甲方联系人")
	private String sclJfContact;
	/**
	 * 甲方委托代理人
	 */
    @ApiModelProperty(value="甲方委托代理人")
	private String sclJfEntrusted;
	/**
	 * 甲方时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="甲方时间")
	private Date sclJfTime;
	/**
	 * 乙方地址
	 */
    @ApiModelProperty(value="乙方地址")
	private String sclYfAddress;
	/**
	 * 乙方电话
	 */
    @ApiModelProperty(value="乙方电话")
	private String sclYfPhone;
	/**
	 * 乙方联系人
	 */
    @ApiModelProperty(value="乙方联系人")
	private String sclYfContact;
	/**
	 * 乙方委托代理人
	 */
    @ApiModelProperty(value="乙方委托代理人")
	private String sclYfEntrusted;
	/**
	 * 乙方时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="乙方时间")
	private Date sclYfTime;
	/**
	 * 公司名称
	 */
    @ApiModelProperty(value="公司名称")
	private String sclCompany;
	/**
	 * 保证金缴纳方案一
	 */
	@ApiModelProperty(value="保证金缴纳方案一")
	private String bondChooseOne;
	/**
	 * 保证金缴纳方案二
	 */
	@ApiModelProperty(value="保证金缴纳方案二")
	private String bondChooseTow;
	/**
	 * 保证金缴纳方案三
	 */
	@ApiModelProperty(value="保证金缴纳方案三")
	private String bondChooseThree;
}
