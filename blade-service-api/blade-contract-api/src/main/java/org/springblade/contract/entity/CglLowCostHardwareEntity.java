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

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 采购类：买卖合同（五金低耗类） 实体类
 *
 * @author 王策
 * @date : 2020-12-10 19:01:53
 */
@Getter
@Setter
@TableName("cgl_low_cost_hardware")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglLowCostHardware对象", description = "采购类：买卖合同（五金低耗类）")
public class CglLowCostHardwareEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 买方
	 */
    @ApiModelProperty(value="买方 ")
	private String cglTheBuyer;
	/**
	 * 买方住所
	 */
    @ApiModelProperty(value="买方住所")
	private String cglBuyerDomicile;
	/**
	 * 卖方
	 */
    @ApiModelProperty(value="卖方")
	private String cglTheSeller;
	/**
	 * 卖方住所
	 */
    @ApiModelProperty(value="卖方住所")
	private String cglSellerResidence;
	/**
	 * 金额单位：人民币【？】元
	 */
    @ApiModelProperty(value="金额单位：人民币【？】元")
	private BigDecimal cglAmountOfUnits;
	/**
	 * 甲方指定邮箱
	 */
    @ApiModelProperty(value="甲方指定邮箱")
	private String cglEmailAddress;
	/**
	 * 甲方传真号码
	 */
    @ApiModelProperty(value="甲方传真号码")
	private String cglNumber;
	/**
	 * 乙方指定邮箱
	 */
    @ApiModelProperty(value="乙方指定邮箱")
	private String cglEmailAddresss;
	/**
	 * 乙方传真号码
	 */
    @ApiModelProperty(value="乙方传真号码")
	private String cglNumbers;
	/**
	 * 双方确认，乙方将采用如下第【？】种方式回复甲方《订购单》
	 */
    @ApiModelProperty(value="双方确认，乙方将采用如下第【？】种方式回复甲方《订购单》")
	private String cglWay;
	/**
	 * 邮件方式:乙方通过指定邮箱
	 */
    @ApiModelProperty(value="邮件方式:乙方通过指定邮箱")
	private String cglSpecifyTheEmail;
	/**
	 * 将盖章原件的扫描件回复至甲方指定邮箱
	 */
    @ApiModelProperty(value="将盖章原件的扫描件回复至甲方指定邮箱")
	private String cglEmail;
	/**
	 * 原件快递：乙方将收到的《订购单》打印并加盖公章或合同章后于2个工作日内快递给甲方指定地址：【？】
	 */
    @ApiModelProperty(value="原件快递：乙方将收到的《订购单》打印并加盖公章或合同章后于2个工作日内快递给甲方指定地址：【？】")
	private String cglOriginalDelivery;
	/**
	 * 收件人：【？】
	 */
    @ApiModelProperty(value="收件人：【？】")
	private String cglRecipient;
	/**
	 * 交货地点：【？】
	 */
    @ApiModelProperty(value="交货地点：【？】")
	private String cglDelivery;
	/**
	 * 由【？】方负责卸货
	 */
    @ApiModelProperty(value="由【？】方负责卸货")
	private String cglUnloading;
	/**
	 * 卸货费用由【？】方承担
	 */
    @ApiModelProperty(value="卸货费用由【？】方承担")
	private String cglCost;
	/**
	 * 特别约定
	 */
    @ApiModelProperty(value="特别约定")
	private String cglAgreed;
	/**
	 * 货款支付：按如下第【？】种账期予以付款
	 */
    @ApiModelProperty(value="货款支付：按如下第【？】种账期予以付款")
	private String cglPayment;
	/**
	 * 乙方开户行
	 */
    @ApiModelProperty(value="乙方开户行")
	private String cglBankNumber;
	/**
	 * 乙方户号
	 */
    @ApiModelProperty(value="乙方户号")
	private String cglAccount;
	/**
	 * 乙方账号
	 */
    @ApiModelProperty(value="乙方账号")
	private String cglAccounts;
	/**
	 * 本合同履行期限:开始时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="本合同履行期限:开始时间")
	private Date cglStartTime;
	/**
	 * 结束时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="结束时间")
	private Date cglOfTime;
	/**
	 * 其他约定
	 */
    @ApiModelProperty(value="其他约定")
	private String cglConventions;

}
