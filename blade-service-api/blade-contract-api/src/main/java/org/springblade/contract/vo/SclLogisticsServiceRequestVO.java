package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

/**
 * 生产类：物流服务合同（二段配送） 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 生产类：物流服务合同（二段配送）
 * @date : 2021-01-19 14:45:04
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "生产类：物流服务合同（二段配送）请求对象")
public class SclLogisticsServiceRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="立合同人甲方")
	private String partya;
	
    @ApiModelProperty(value="立合同人乙方")
	private String partyb;
	
    @ApiModelProperty(value="签订日期：")
	private Date date;
	
    @ApiModelProperty(value="签订地点")
	private String site;
	
    @ApiModelProperty(value="（第一项时间节点）：【？】年")
	private Integer storage;
	
    @ApiModelProperty(value="中标的物流线路为")
	private String area;
	
    @ApiModelProperty(value="日")
	private Integer day;
	
    @ApiModelProperty(value="正常单")
	private String no;
	
    @ApiModelProperty(value="加单")
	private Integer storageee;
	
    @ApiModelProperty(value="加急单")
	private String conditionsa;
	
    @ApiModelProperty(value="日")
	private Integer day1;
	
    @ApiModelProperty(value="台")
	private String services;
	
    @ApiModelProperty(value="其他要求：【？】")
	private Integer drinks;
	
    @ApiModelProperty(value="配送")
	private Integer dairy;
	
    @ApiModelProperty(value="日常配送量")
	private Double dairy1;
	
    @ApiModelProperty(value="交接手续")
	private String requirementsp;
	
    @ApiModelProperty(value="万件")
	private Integer range;
	
    @ApiModelProperty(value="元/单/天")
	private String areae;
	
    @ApiModelProperty(value="%计算")
	private Double requirementse;
	
    @ApiModelProperty(value="次日【？】时")
	private String contractd;
	
    @ApiModelProperty(value="【？】万元整")
	private BigDecimal second;
	
    @ApiModelProperty(value="（大写：         【？】）")
	private String breach;
	
    @ApiModelProperty(value="不足【？】万元人民币")
	private BigDecimal provide;
	
    @ApiModelProperty(value="双方配送、装卸费用结算时间段为：【？】")
	private String hours;
	
    @ApiModelProperty(value="乙方于结算截止日之后【？】日内，将运费清单递至甲方")
	private Integer morning;
	
    @ApiModelProperty(value="将运费清单递至甲方           ")
	private String manifest;
	
    @ApiModelProperty(value="对于账目不清之处，乙方有责任与甲方物流部核算员共同核对，并限于【？】内达成共识")
	private String afternoon;
	
    @ApiModelProperty(value="退仓费用按相同地点正常运价【？】%计算")
	private Double advance;
	
    @ApiModelProperty(value="因甲方或甲方客户原因，导致乙方配送的车辆未能及时卸货而在当地住宿的，甲方须")
	private String season;
	
    @ApiModelProperty(value="次")
	private String times;
	
    @ApiModelProperty(value="元/单/天")
	private BigDecimal items;
	
    @ApiModelProperty(value="违约金")
	private String date1;
	
    @ApiModelProperty(value="次")
	private String requirementsddd;
	
    @ApiModelProperty(value="日配达率（当日已配达传票张数/当日总传票张数*100%）（不得低于【？】%")
	private Double requirementsf;
	
    @ApiModelProperty(value="（第九.2.（12）项违约金）：【？】")
	private String transfer;
	
    @ApiModelProperty(value="合同有效期：开始时间")
	private LocalTime date2;
	
    @ApiModelProperty(value="合同有效期：结束时间")
	private LocalTime requirementsss;
	
    @ApiModelProperty(value="除本合同另有约定外，非经双方协商一致，任一方均不得擅自解除本合同，否则应向对方支付违约金计人民币【？】万元整")
	private BigDecimal standard;
	
    @ApiModelProperty(value="特别约定：【？】")
	private String aread;
	
    @ApiModelProperty(value="合同ID")
	private Long contractId;
	
    @ApiModelProperty(value="地址")
	private String address;
	
    @ApiModelProperty(value="地址乙")
	private String addressb;
	
    @ApiModelProperty(value="电话")
	private String telephone;
	
    @ApiModelProperty(value="电话乙")
	private String telephoneb;
	
    @ApiModelProperty(value="联系人")
	private String contacts;
	
    @ApiModelProperty(value="联系人乙")
	private String contactsb;
	
    @ApiModelProperty(value="法定代表人")
	private String representative;
	
    @ApiModelProperty(value="法定代表人乙")
	private String representativeb;
	
    @ApiModelProperty(value="委托代理人")
	private String agent;
	
    @ApiModelProperty(value="委托代理人乙")
	private String agentb;
	
    @ApiModelProperty(value="日期")
	private Date date3;
	
    @ApiModelProperty(value="日期")
	private Date date4;
	
    @ApiModelProperty(value="委 托 人")
	private String client;
	
    @ApiModelProperty(value="被委托人1")
	private String client1;
	
    @ApiModelProperty(value="被委托人2")
	private String client2;
	
    @ApiModelProperty(value="被委托人3")
	private String client3;
	
    @ApiModelProperty(value="委 托 人")
	private String client4;
	
    @ApiModelProperty(value="被委托人")
	private String client5;
	
    @ApiModelProperty(value="身份证号")
	private String number;
	
    @ApiModelProperty(value="身份证号")
	private String number1;
	
    @ApiModelProperty(value="身份证号")
	private String number2;
	
    @ApiModelProperty(value="身份证号")
	private String number3;
	
    @ApiModelProperty(value="邮箱地址")
	private String mail;
	
    @ApiModelProperty(value="邮箱地址")
	private String mail1;
	
    @ApiModelProperty(value="选择")
	private String choice;
	
    @ApiModelProperty(value="选择")
	private String choice1;
	
    @ApiModelProperty(value="选择")
	private String choice2;
	
    @ApiModelProperty(value="其它")
	private String other;
	
    @ApiModelProperty(value="日期")
	private Date date5;
	
    @ApiModelProperty(value="日期")
	private Date date6;
	
    @ApiModelProperty(value="日期")
	private Date date7;
	
    @ApiModelProperty(value="日期")
	private Date date8;
	
    @ApiModelProperty(value="签章")
	private String signature;
	
    @ApiModelProperty(value="手 机 号")
	private String phone;
	
    @ApiModelProperty(value="通讯地址")
	private String postal;
	
    @ApiModelProperty(value="公司")
	private String company;
	
    @ApiModelProperty(value="公司")
	private String company1;
	
}
