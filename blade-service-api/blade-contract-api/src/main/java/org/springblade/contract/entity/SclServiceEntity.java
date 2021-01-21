package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;


/**
 * 生产类：物流服务合同（二段仓储+配送） 实体类
 *
 * @author 刘是罕
 * @date : 2021-01-19 14:41:08
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("scl_service")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclService对象", description = "生产类：物流服务合同（二段仓储+配送）")
public class SclServiceEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 立合同人甲方
	 */
	@ApiModelProperty(value = "立合同人甲方")
	private String sclPartya;
	/**
	 * 立合同人乙方
	 */
	@ApiModelProperty(value = "立合同人乙方")
	private String sclPartyb;
	/**
	 * 签订日期
	 */
	@ApiModelProperty(value = "签订日期")
	private Date date;
	/**
	 * 签订地点
	 */
	@ApiModelProperty(value = "签订地点")
	private String sclSite;
	/**
	 * 仓储地点【？】市
	 */
	@ApiModelProperty(value = "仓储地点【？】市")
	private String sclStorage;
	/**
	 * 【？】区
	 */
	@ApiModelProperty(value = "【？】区")
	private String sclArea;
	/**
	 * 【？】号
	 */
	@ApiModelProperty(value = "【？】号")
	private String sclNo;
	/**
	 * 仓储面积：【？】平方米
	 */
	@ApiModelProperty(value = "仓储面积：【？】平方米")
	private Integer sclStorageee;
	/**
	 * 其他条件：【？】
	 */
	@ApiModelProperty(value = "其他条件：【？】")
	private String sclConditionsa;
	/**
	 * 甲方应将库存数量控制在【？】箱以内
	 */
	@ApiModelProperty(value = "甲方应将库存数量控制在【？】箱以内")
	private Integer sclNumber;
	/**
	 * 乙方必须协助部分特殊出货的增值服务：【？】
	 */
	@ApiModelProperty(value = "乙方必须协助部分特殊出货的增值服务：【？】")
	private String sclServices;
	/**
	 * 剩余保质期：食品少于【？】天
	 */
	@ApiModelProperty(value = "剩余保质期：食品少于【？】天")
	private String sclFood;
	/**
	 * 饮料（茶、果汁、咖啡、奶茶）少于【？】天
	 */
	@ApiModelProperty(value = "饮料（茶、果汁、咖啡、奶茶）少于【？】天")
	private Integer sclDrinks;
	/**
	 * 乳品少于【？】天
	 */
	@ApiModelProperty(value = "乳品少于【？】天")
	private Integer sclDairy;
	/**
	 * 水少于【？】天的
	 */
	@ApiModelProperty(value = "水少于【？】天的")
	private Integer sclWater;
	/**
	 * 其他要求
	 */
	@ApiModelProperty(value = "其他要求")
	private String sclRequirementsp;
	/**
	 * 乙方保证每日配送运力在下列幅度之内：【？】万件
	 */
	@ApiModelProperty(value = "乙方保证每日配送运力在下列幅度之内：【？】万件")
	private Integer sclRange;
	/**
	 * 乙方为甲方提供配送服务的区域为：【？】
	 */
	@ApiModelProperty(value = "乙方为甲方提供配送服务的区域为：【？】")
	private String sclAreae;
	/**
	 * （第二.3.（2）项压单量要求）：【？】
	 */
	@ApiModelProperty(value = "（第二.3.（2）项压单量要求）：【？】")
	private String sclRequirementse;
	/**
	 * （第二.3.（2）.1项违约金）：【？】
	 */
	@ApiModelProperty(value = "（第二.3.（2）.1项违约金）：【？】")
	private String sclContractd;
	/**
	 * （第二.3.（2）.1项违约金）：【？】
	 */
	@ApiModelProperty(value = "（第二.3.（2）.1项违约金）：【？】")
	private String sclSecond;
	/**
	 * （第二.3.（2）.3项违约金）： 【？】
	 */
	@ApiModelProperty(value = "（第二.3.（2）.3项违约金）： 【？】")
	private String sclBreach;
	/**
	 * 乙方向甲方提供一年【？】天
	 */
	@ApiModelProperty(value = "乙方向甲方提供一年【？】天")
	private String sclProvide;
	/**
	 * 每天【？】小时的仓储服务和不间断的运输服务
	 */
	@ApiModelProperty(value = "每天【？】小时的仓储服务和不间断的运输服务")
	private String sclHours;
	/**
	 * 甲方进货时间应尽量安排在正常工作日内（周一至周六）的上午【？】
	 */
	@ApiModelProperty(value = "甲方进货时间应尽量安排在正常工作日内（周一至周六）的上午【？】")
	private String sclMorning;
	/**
	 * 至下午【？】
	 */
	@ApiModelProperty(value = "至下午【？】")
	private String sclAfternoon;
	/**
	 * 因特殊情况，在每天工作时间以外的时间，甲方应提前【？】小时通知乙方，以便乙方安排作业
	 */
	@ApiModelProperty(value = "因特殊情况，在每天工作时间以外的时间，甲方应提前【？】小时通知乙方，以便乙方安排作业")
	private String sclAdvance;
	/**
	 * 节假日进货的甲方应提前【？】个小时通知乙方
	 */
	@ApiModelProperty(value = "节假日进货的甲方应提前【？】个小时通知乙方")
	private String sclSeason;
	/**
	 * 退货作业：等待【？】分钟内如无法解决可收下退货
	 */
	@ApiModelProperty(value = "退货作业：等待【？】分钟内如无法解决可收下退货")
	private String sclReturn;
	/**
	 * 时间
	 */
	@ApiModelProperty(value = "时间")
	private Date sclRequesta;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String sclItems;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String sclDate;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String sclDate1;
	/**
	 * 天
	 */
	@ApiModelProperty(value = "天")
	private String sclDate2;
	/**
	 * 时间
	 */
	@ApiModelProperty(value = "时间")
	private Date sclRequirementsf;
	/**
	 * 数据传送：【？】（乙方对应数据接口）
	 */
	@ApiModelProperty(value = "数据传送：【？】（乙方对应数据接口）")
	private String sclTransfer;
	/**
	 * 库存盘点日
	 */
	@ApiModelProperty(value = "库存盘点日")
	private String sclOfs;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String sclRequirementsss;
	/**
	 * 产成品仓储费用（请选择（1）、（2）两种收费标准中的一种）：【？】
	 */
	@ApiModelProperty(value = "产成品仓储费用（请选择（1）、（2）两种收费标准中的一种）：【？】")
	private String sclStandard;
	/**
	 * 乙方提供给甲方之仓库面积为【？】平米
	 */
	@ApiModelProperty(value = "乙方提供给甲方之仓库面积为【？】平米")
	private String sclAread;
	/**
	 * 行销品仓储费用：乙方提供给甲方之行销品仓库面积为【？】平米
	 */
	@ApiModelProperty(value = "行销品仓储费用：乙方提供给甲方之行销品仓库面积为【？】平米")
	private String sclWarehouse;
	/**
	 * 依订单按单箱计价：饮料、乳品、方便面等商品按【？】元/箱计算
	 */
	@ApiModelProperty(value = "依订单按单箱计价：饮料、乳品、方便面等商品按【？】元/箱计算")
	private BigDecimal sclGoods;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String day1;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String day2;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String day3;
	/**
	 * 工作日
	 */
	@ApiModelProperty(value = "工作日")
	private String day4;
	/**
	 * 履约保证金人民币【？】
	 */
	@ApiModelProperty(value = "履约保证金人民币【？】")
	private BigDecimal sclYuan;
	/**
	 * 整（大写：【？】）
	 */
	@ApiModelProperty(value = "整（大写：【？】）")
	private String sclWhole;
	/**
	 * 乙方缴纳的履约保证金因赔偿甲方损失或本合同中约定的其他扣款原因导致不足【？】万元人民币时，乙方应在7日内补足
	 */
	@ApiModelProperty(value = "乙方缴纳的履约保证金因赔偿甲方损失或本合同中约定的其他扣款原因导致不足【？】万元人民币时，乙方应在7日内补足")
	private BigDecimal sclLead;
	/**
	 * （第九.1项次数要求）：【？】次（含
	 */
	@ApiModelProperty(value = "（第九.1项次数要求）：【？】次（含")
	private Integer sclRequirementsd;
	/**
	 * 【？】次）
	 */
	@ApiModelProperty(value = "【？】次）")
	private Integer sclTimes;
	/**
	 * （第九.3项违约金）：【？】
	 */
	@ApiModelProperty(value = "（第九.3项违约金）：【？】")
	private Double sclContract;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String day5;
	/**
	 * 次
	 */
	@ApiModelProperty(value = "次")
	private String sclNumber1;
	/**
	 * 总价倍数
	 */
	@ApiModelProperty(value = "总价倍数")
	private String sclMultiple;
	/**
	 * 次
	 */
	@ApiModelProperty(value = "次")
	private String sclRequirementsa;
	/**
	 * 次
	 */
	@ApiModelProperty(value = "次")
	private String sclContaining;
	/**
	 * %
	 */
	@ApiModelProperty(value = "%")
	private Double sclNinth;
	/**
	 * 元/月
	 */
	@ApiModelProperty(value = "元/月")
	private BigDecimal sclCost;
	/**
	 * 办公等条件
	 */
	@ApiModelProperty(value = "办公等条件")
	private Integer sclBank;
	/**
	 * 办公等条件
	 */
	@ApiModelProperty(value = "办公等条件")
	private String sclConditions;
	/**
	 * 第十一.3项日期区间要求）：【？】
	 */
	@ApiModelProperty(value = "第十一.3项日期区间要求）：【？】")
	private String sclDate3;
	/**
	 * 天
	 */
	@ApiModelProperty(value = "天")
	private String day6;
	/**
	 * 配达率：（不得低于【？】%）
	 */
	@ApiModelProperty(value = "配达率：（不得低于【？】%）")
	private Double sclRatio;
	/**
	 * 本合同有效期限开始时间:
	 */
	@ApiModelProperty(value = "本合同有效期限开始时间:")
	private Date startTimes;
	/**
	 * 本合同有效期限结束时间:
	 */
	@ApiModelProperty(value = "本合同有效期限结束时间:")
	private Date endTimes;
	/**
	 * 合同ID
	 */
	@ApiModelProperty(value = "合同ID")
	private Long contractId;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String day;
	/**
	 * 按流量计费
	 */
	@ApiModelProperty(value = "按流量计费")
	private String traffic;
	/**
	 * 按面积计费
	 */
	@ApiModelProperty(value = "按面积计费")
	private String area;
	/**
	 * 地址甲
	 */
	@ApiModelProperty(value = "地址甲")
	private String address;
	/**
	 * 地址乙
	 */
	@ApiModelProperty(value = "地址乙")
	private String addressb;
	/**
	 * 电话甲
	 */
	@ApiModelProperty(value = "电话甲")
	private String telephone;
	/**
	 * 电话乙
	 */
	@ApiModelProperty(value = "电话乙")
	private String telephoneb;
	/**
	 * 传真甲
	 */
	@ApiModelProperty(value = "传真甲")
	private String fax;
	/**
	 * 传真乙
	 */
	@ApiModelProperty(value = "传真乙")
	private String faxb;
	/**
	 * 银行帐号甲
	 */
	@ApiModelProperty(value = "银行帐号甲")
	private String accountNumber;
	/**
	 * 银行帐号乙
	 */
	@ApiModelProperty(value = "银行帐号乙")
	private String accountNumberb;
	/**
	 * 开户银行甲
	 */
	@ApiModelProperty(value = "开户银行甲")
	private String deposit;
	/**
	 * 开户银行乙
	 */
	@ApiModelProperty(value = "开户银行乙")
	private String depositb;
	/**
	 * 日期
	 */
	@ApiModelProperty(value = "日期")
	private Date date1;
	/**
	 * 日期
	 */
	@ApiModelProperty(value = "日期")
	private Date date2;
	/**
	 * 委 托 人
	 */
	@ApiModelProperty(value = "委 托 人")
	private String client;
	/**
	 * 被委托人1
	 */
	@ApiModelProperty(value = "被委托人1")
	private String client1;
	/**
	 * 被委托人2
	 */
	@ApiModelProperty(value = "被委托人2")
	private String client2;
	/**
	 * 被委托人3
	 */
	@ApiModelProperty(value = "被委托人3")
	private String client3;
	/**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号")
	private String number;
	/**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号")
	private String number1;
	/**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号")
	private String number2;
	/**
	 * 邮箱地址
	 */
	@ApiModelProperty(value = "邮箱地址")
	private String mail;
	/**
	 * 出货传票
	 */
	@ApiModelProperty(value = "出货传票")
	private String shippingSummons;
	/**
	 * 现场调度
	 */
	@ApiModelProperty(value = "现场调度")
	private String dispatching;
	/**
	 * 签收单据管理
	 */
	@ApiModelProperty(value = "签收单据管理")
	private String management;
	/**
	 * 其它
	 */
	@ApiModelProperty(value = "其它")
	private String other;
	/**
	 * 委托期间
	 */
	@ApiModelProperty(value = "委托期间")
	private Date date3;
	/**
	 * 委托期间
	 */
	@ApiModelProperty(value = "委托期间")
	private Date date4;
	/**
	 * 委 托 人2
	 */
	@ApiModelProperty(value = "委 托 人2")
	private String client4;
	/**
	 * 被委托人
	 */
	@ApiModelProperty(value = "被委托人")
	private String client5;
	/**
	 * 身份证号
	 */
	@ApiModelProperty(value = "身份证号")
	private String number3;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phone;
	/**
	 * 邮箱地址
	 */
	@ApiModelProperty(value = "邮箱地址")
	private String mail1;
	/**
	 * 通讯地址
	 */
	@ApiModelProperty(value = "通讯地址")
	private String postalAddress;
	/**
	 * 公司
	 */
	@ApiModelProperty(value = "公司")
	private String company;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String day7;

}
