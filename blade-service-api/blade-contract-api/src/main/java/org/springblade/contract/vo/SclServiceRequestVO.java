package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 生产类：物流服务合同（二段仓储+配送） 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 刘是罕
 * @date : 2021-01-19 14:41:12
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "生产类：物流服务合同（二段仓储+配送）请求对象")
public class SclServiceRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="立合同人甲方")
	private String sclPartya;

	@ApiModelProperty(value="立合同人乙方")
	private String sclPartyb;

	@ApiModelProperty(value="签订日期")
	private Date date;

	@ApiModelProperty(value="签订地点")
	private String sclSite;

	@ApiModelProperty(value="仓储地点【？】市")
	private String sclStorage;

	@ApiModelProperty(value="【？】区")
	private String sclArea;

	@ApiModelProperty(value="【？】号")
	private String sclNo;

	@ApiModelProperty(value="仓储面积：【？】平方米")
	private String sclStorageee;

	@ApiModelProperty(value="其他条件：【？】")
	private String sclConditionsa;

	@ApiModelProperty(value="甲方应将库存数量控制在【？】箱以内")
	private Integer sclNumber;

	@ApiModelProperty(value="乙方必须协助部分特殊出货的增值服务：【？】")
	private String sclServices;

	@ApiModelProperty(value="剩余保质期：食品少于【？】天")
	private String sclFood;

	@ApiModelProperty(value="饮料（茶、果汁、咖啡、奶茶）少于【？】天")
	private Integer sclDrinks;

	@ApiModelProperty(value="乳品少于【？】天")
	private Integer sclDairy;

	@ApiModelProperty(value="水少于【？】天的")
	private Integer sclWater;

	@ApiModelProperty(value="其他要求")
	private String sclRequirementsp;

	@ApiModelProperty(value="乙方保证每日配送运力在下列幅度之内：【？】万件")
	private Integer sclRange;

	@ApiModelProperty(value="乙方为甲方提供配送服务的区域为：【？】")
	private String sclAreae;

	@ApiModelProperty(value="（第二.3.（2）项压单量要求）：【？】")
	private String sclRequirementse;

	@ApiModelProperty(value="（第二.3.（2）.1项违约金）：【？】")
	private String sclContractd;

	@ApiModelProperty(value="（第二.3.（2）.1项违约金）：【？】")
	private String sclSecond;

	@ApiModelProperty(value="（第二.3.（2）.3项违约金）： 【？】")
	private String sclBreach;

	@ApiModelProperty(value="乙方向甲方提供一年【？】天")
	private String sclProvide;

	@ApiModelProperty(value="每天【？】小时的仓储服务和不间断的运输服务")
	private String sclHours;

	@ApiModelProperty(value="甲方进货时间应尽量安排在正常工作日内（周一至周六）的上午【？】")
	private String sclMorning;

	@ApiModelProperty(value="至下午【？】")
	private String sclAfternoon;

	@ApiModelProperty(value="因特殊情况，在每天工作时间以外的时间，甲方应提前【？】小时通知乙方，以便乙方安排作业")
	private String sclAdvance;

	@ApiModelProperty(value="节假日进货的甲方应提前【？】个小时通知乙方")
	private String sclSeason;

	@ApiModelProperty(value="退货作业：等待【？】分钟内如无法解决可收下退货")
	private String sclReturn;

	@ApiModelProperty(value="时间")
	private String sclRequesta;

	@ApiModelProperty(value="日")
	private String sclItems;

	@ApiModelProperty(value="日")
	private String sclDate;

	@ApiModelProperty(value="日")
	private String sclDate1;

	@ApiModelProperty(value="天")
	private String sclDate2;

	@ApiModelProperty(value="时间")
	private Date sclRequirementsf;

	@ApiModelProperty(value="数据传送：【？】（乙方对应数据接口）")
	private String sclTransfer;

	@ApiModelProperty(value="库存盘点日")
	private String sclOfs;

	@ApiModelProperty(value="日")
	private String sclRequirementsss;

	@ApiModelProperty(value="产成品仓储费用（请选择（1）、（2）两种收费标准中的一种）：【？】")
	private String sclStandard;

	@ApiModelProperty(value="乙方提供给甲方之仓库面积为【？】平米")
	private String sclAread;

	@ApiModelProperty(value="行销品仓储费用：乙方提供给甲方之行销品仓库面积为【？】平米")
	private String sclWarehouse;

	@ApiModelProperty(value="依订单按单箱计价：饮料、乳品、方便面等商品按【？】元/箱计算")
	private BigDecimal sclGoods;

	@ApiModelProperty(value="日")
	private String day1;

	@ApiModelProperty(value="日")
	private String day2;

	@ApiModelProperty(value="日")
	private String day3;

	@ApiModelProperty(value="工作日")
	private String day4;

	@ApiModelProperty(value="履约保证金人民币【？】")
	private BigDecimal sclYuan;

	@ApiModelProperty(value="整（大写：【？】）")
	private String sclWhole;

	@ApiModelProperty(value="乙方缴纳的履约保证金因赔偿甲方损失或本合同中约定的其他扣款原因导致不足【？】万元人民币时，乙方应在7日内补足")
	private BigDecimal sclLead;

	@ApiModelProperty(value="（第九.1项次数要求）：【？】次（含")
	private Integer sclRequirementsd;

	@ApiModelProperty(value="【？】次）")
	private Integer sclTimes;

	@ApiModelProperty(value="（第九.3项违约金）：【？】")
	private Double sclContract;

	@ApiModelProperty(value="日")
	private String day5;

	@ApiModelProperty(value="次")
	private String sclNumber1;

	@ApiModelProperty(value="总价倍数")
	private String sclMultiple;

	@ApiModelProperty(value="次")
	private String sclRequirementsa;

	@ApiModelProperty(value="次")
	private String sclContaining;

	@ApiModelProperty(value="%")
	private Double sclNinth;

	@ApiModelProperty(value="元/月")
	private String sclCost;

	@ApiModelProperty(value="办公等条件")
	private Integer sclBank;

	@ApiModelProperty(value="办公等条件")
	private String sclConditions;

	@ApiModelProperty(value="第十一.3项日期区间要求）：【？】")
	private String sclDate3;

	@ApiModelProperty(value="天")
	private String day6;

	@ApiModelProperty(value="配达率：（不得低于【？】%）")
	private Double sclRatio;

	@ApiModelProperty(value="本合同有效期限开始时间:")
	private Date startTimes;

	@ApiModelProperty(value="本合同有效期限结束时间:")
	private Date endTimes;

	@ApiModelProperty(value="合同ID")
	private Long contractId;

	@ApiModelProperty(value="日")
	private String day;

	@ApiModelProperty(value="按流量计费")
	private String traffic;

	@ApiModelProperty(value="按面积计费")
	private String area;

	@ApiModelProperty(value="地址甲")
	private String address;

	@ApiModelProperty(value="地址乙")
	private String addressb;

	@ApiModelProperty(value="电话甲")
	private String telephone;

	@ApiModelProperty(value="电话乙")
	private String telephoneb;

	@ApiModelProperty(value="传真甲")
	private String fax;

	@ApiModelProperty(value="传真乙")
	private String faxb;

	@ApiModelProperty(value="银行帐号甲")
	private String accountNumber;

	@ApiModelProperty(value="银行帐号乙")
	private String accountNumberb;

	@ApiModelProperty(value="开户银行甲")
	private String deposit;

	@ApiModelProperty(value="开户银行乙")
	private String depositb;

	@ApiModelProperty(value="日期")
	private String date1;

	@ApiModelProperty(value="日期")
	private Date date2;

	@ApiModelProperty(value="委 托 人")
	private String client;

	@ApiModelProperty(value="被委托人1")
	private String client1;

	@ApiModelProperty(value="被委托人2")
	private String client2;

	@ApiModelProperty(value="被委托人3")
	private String client3;

	@ApiModelProperty(value="身份证号")
	private String number;

	@ApiModelProperty(value="身份证号")
	private String number1;

	@ApiModelProperty(value="身份证号")
	private String number2;

	@ApiModelProperty(value="邮箱地址")
	private String mail;

	@ApiModelProperty(value="出货传票")
	private String shippingSummons;

	@ApiModelProperty(value="现场调度")
	private String dispatching;

	@ApiModelProperty(value="签收单据管理")
	private String management;

	@ApiModelProperty(value="其它")
	private String other;

	@ApiModelProperty(value="委托期间")
	private Date date3;

	@ApiModelProperty(value="委托期间")
	private Date date4;

	@ApiModelProperty(value="委 托 人2")
	private String client4;

	@ApiModelProperty(value="被委托人")
	private String client5;

	@ApiModelProperty(value="身份证号")
	private String number3;

	@ApiModelProperty(value="手机号")
	private String phone;

	@ApiModelProperty(value="邮箱地址")
	private String mail1;

	@ApiModelProperty(value="通讯地址")
	private String postalAddress;

	@ApiModelProperty(value="公司")
	private String company;

	@ApiModelProperty(value="日")
	private String day7;

	@ApiModelProperty(value = "拼接附件")
	private String annex;

	@ApiModelProperty(value = "《出货传票》及《出货传票交接清单》样式")
	private String image;

}

