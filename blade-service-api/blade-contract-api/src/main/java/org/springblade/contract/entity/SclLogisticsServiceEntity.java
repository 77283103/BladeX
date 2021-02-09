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
 * 生产类：物流服务合同（二段配送） 实体类
 *
 * @author 张文武
 * @date : 2021-01-23 16:50:39
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
	private String partya;
	/**
	 * 立合同人乙方
	 */
    @ApiModelProperty(value="立合同人乙方")
	private String partyb;
	/**
	 * 签订日期：
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="签订日期：")
	private Date date;
	/**
	 * 签订地点
	 */
    @ApiModelProperty(value="签订地点")
	private String site;
	/**
	 * （第一项时间节点）：【？】年
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="（第一项时间节点）：【？】年")
	private Integer storage;
	/**
	 * 中标的物流线路为
	 */
    @ApiModelProperty(value="中标的物流线路为")
	private String area;
	/**
	 * 日
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="日")
	private Integer day;
	/**
	 * 正常单
	 */
    @ApiModelProperty(value="正常单")
	private String no;
	/**
	 * 加单
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="加单")
	private String storageee;
	/**
	 * 加急单
	 */
    @ApiModelProperty(value="加急单")
	private String conditionsa;
	/**
	 * 日
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="日")
	private Integer day1;
	/**
	 * 台
	 */
    @ApiModelProperty(value="台")
	private String services;
	/**
	 * 其他要求：【？】
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="其他要求：【？】")
	private String drinks;
	/**
	 * 配送
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="配送")
	private String dairy;
	/**
	 * 日常配送量
	 */
    @ApiModelProperty(value="日常配送量")
	private Double dairy1;
	/**
	 * 交接手续
	 */
    @ApiModelProperty(value="交接手续")
	private String requirementsp;
	/**
	 * 万件
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="万件")
	private Integer ranges;
	/**
	 * 元/单/天
	 */
    @ApiModelProperty(value="元/单/天")
	private String areae;
	/**
	 * %计算
	 */
    @ApiModelProperty(value="%计算")
	private Double requirementse;
	/**
	 * 次日【？】时
	 */
    @ApiModelProperty(value="次日【？】时")
	private String contractd;
	/**
	 * 【？】万元整
	 */
    @ApiModelProperty(value="【？】万元整")
	private BigDecimal second;
	/**
	 * （大写：         【？】）
	 */
    @ApiModelProperty(value="（大写：         【？】）")
	private String breach;
	/**
	 * 不足【？】万元人民币
	 */
    @ApiModelProperty(value="不足【？】万元人民币")
	private BigDecimal provide;
	/**
	 * 双方配送、装卸费用结算时间段为：【？】
	 */
    @ApiModelProperty(value="双方配送、装卸费用结算时间段为：【？】")
	private String hours;
	/**
	 * 乙方于结算截止日之后【？】日内，将运费清单递至甲方
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方于结算截止日之后【？】日内，将运费清单递至甲方")
	private Integer morning;
	/**
	 * 将运费清单递至甲方
	 */
    @ApiModelProperty(value="将运费清单递至甲方           ")
	private String manifest;
	/**
	 * 对于账目不清之处，乙方有责任与甲方物流部核算员共同核对，并限于【？】内达成共识
	 */
    @ApiModelProperty(value="对于账目不清之处，乙方有责任与甲方物流部核算员共同核对，并限于【？】内达成共识")
	private String afternoon;
	/**
	 * 退仓费用按相同地点正常运价【？】%计算
	 */
    @ApiModelProperty(value="退仓费用按相同地点正常运价【？】%计算")
	private Double advancess;
	/**
	 * 因甲方或甲方客户原因，导致乙方配送的车辆未能及时卸货而在当地住宿的，甲方须
	 */
    @ApiModelProperty(value="因甲方或甲方客户原因，导致乙方配送的车辆未能及时卸货而在当地住宿的，甲方须")
	private String season;
	/**
	 * 次
	 */
    @ApiModelProperty(value="次")
	private String times;
	/**
	 * 元/单/天
	 */
    @ApiModelProperty(value="元/单/天")
	private BigDecimal items;
	/**
	 * 违约金
	 */
    @ApiModelProperty(value="违约金")
	private String date1;
	/**
	 * 次
	 */
    @ApiModelProperty(value="次")
	private String requirementsddd;
	/**
	 * 日配达率（当日已配达传票张数/当日总传票张数*100%）（不得低于【？】%
	 */
    @ApiModelProperty(value="日配达率（当日已配达传票张数/当日总传票张数*100%）（不得低于【？】%")
	private Double requirementsf;
	/**
	 * （第九.2.（12）项违约金）：【？】
	 */
    @ApiModelProperty(value="（第九.2.（12）项违约金）：【？】")
	private String transfer;
	/**
	 * 合同有效期：开始时间
	 */
    @ApiModelProperty(value="合同有效期：开始时间")
	private Date date2;
	/**
	 * 合同有效期：结束时间
	 */
    @ApiModelProperty(value="合同有效期：结束时间")
	private Date requirementsss;
	/**
	 * 除本合同另有约定外，非经双方协商一致，任一方均不得擅自解除本合同，否则应向对方支付违约金计人民币【？】万元整
	 */
    @ApiModelProperty(value="除本合同另有约定外，非经双方协商一致，任一方均不得擅自解除本合同，否则应向对方支付违约金计人民币【？】万元整")
	private BigDecimal standard;
	/**
	 * 特别约定：【？】
	 */
    @ApiModelProperty(value="特别约定：【？】")
	private String aread;
	/**
	 * 地址
	 */
    @ApiModelProperty(value="地址")
	private String address;
	/**
	 * 地址乙
	 */
    @ApiModelProperty(value="地址乙")
	private String addressb;
	/**
	 * 电话
	 */
    @ApiModelProperty(value="电话")
	private String telephone;
	/**
	 * 电话乙
	 */
    @ApiModelProperty(value="电话乙")
	private String telephoneb;
	/**
	 * 联系人
	 */
    @ApiModelProperty(value="联系人")
	private String contacts;
	/**
	 * 联系人乙
	 */
    @ApiModelProperty(value="联系人乙")
	private String contactsb;
	/**
	 * 法定代表人
	 */
    @ApiModelProperty(value="法定代表人")
	private String representative;
	/**
	 * 法定代表人乙
	 */
    @ApiModelProperty(value="法定代表人乙")
	private String representativeb;
	/**
	 * 委托代理人
	 */
    @ApiModelProperty(value="委托代理人")
	private String agent;
	/**
	 * 委托代理人乙
	 */
    @ApiModelProperty(value="委托代理人乙")
	private String agentb;
	/**
	 * 日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="日期")
	private Date date3;
	/**
	 * 日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="日期")
	private Date date4;
	/**
	 * 委 托 人
	 */
    @ApiModelProperty(value="委 托 人")
	private String client;
	/**
	 * 被委托人1
	 */
    @ApiModelProperty(value="被委托人1")
	private String client1;
	/**
	 * 被委托人2
	 */
    @ApiModelProperty(value="被委托人2")
	private String client2;
	/**
	 * 被委托人3
	 */
    @ApiModelProperty(value="被委托人3")
	private String client3;
	/**
	 * 委 托 人
	 */
    @ApiModelProperty(value="委 托 人")
	private String client4;
	/**
	 * 被委托人
	 */
    @ApiModelProperty(value="被委托人")
	private String client5;
	/**
	 * 身份证号
	 */
    @ApiModelProperty(value="身份证号")
	private String number;
	/**
	 * 身份证号
	 */
    @ApiModelProperty(value="身份证号")
	private String number1;
	/**
	 * 身份证号
	 */
    @ApiModelProperty(value="身份证号")
	private String number2;
	/**
	 * 身份证号
	 */
    @ApiModelProperty(value="身份证号")
	private String number3;
	/**
	 * 邮箱地址
	 */
    @ApiModelProperty(value="邮箱地址")
	private String mail;
	/**
	 * 邮箱地址
	 */
    @ApiModelProperty(value="邮箱地址")
	private String mail1;
	/**
	 * 选择
	 */
    @ApiModelProperty(value="选择")
	private String choice;
	/**
	 * 选择
	 */
    @ApiModelProperty(value="选择")
	private String choice1;
	/**
	 * 选择
	 */
    @ApiModelProperty(value="选择")
	private String choice2;
	/**
	 * 其它
	 */
    @ApiModelProperty(value="其它")
	private String other;
	/**
	 * 其它内容
	 */
	@ApiModelProperty(value="其它内容")
	private String otherContent;
	/**
	 * 日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="日期")
	private Date date5;
	/**
	 * 日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="日期")
	private Date date6;
	/**
	 * 日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="日期")
	private Date date7;
	/**
	 * 日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="日期")
	private Date date8;
	/**
	 * 签章
	 */
    @ApiModelProperty(value="签章")
	private String signature;
	/**
	 * 手 机 号
	 */
    @ApiModelProperty(value="手 机 号")
	private String phone;
	/**
	 * 通讯地址
	 */
    @ApiModelProperty(value="通讯地址")
	private String postal;
	/**
	 * 公司
	 */
    @ApiModelProperty(value="公司")
	private String company;
	/**
	 * 公司
	 */
    @ApiModelProperty(value="公司")
	private String company1;

}
