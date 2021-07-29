package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.util.List;


/**
 * 采购类：买卖合同（行销品） 实体类
 *
 * @author 王策
 * @date : 2020-12-10 18:52:44
 */
@Getter
@Setter
@TableName("cgl_category_sales_contracts")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglCategorySalesContracts对象", description = "采购类：买卖合同（行销品）")
public class CglCategorySalesContractsEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String cglPartyA;
	/**
	 * 甲方住所
	 */
    @ApiModelProperty(value="甲方住所")
	private String cglDomicile;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String cglPartyB;
	/**
	 * 乙方住所
	 */
    @ApiModelProperty(value="乙方住所")
	private String cglPartyBHome;
	/**
	 * 约定由甲方向乙方购买  “【？】”作为甲方促销活动的赠品或奖品
	 */
    @ApiModelProperty(value="约定由甲方向乙方购买  “【？】”作为甲方促销活动的赠品或奖品")
	private String cglActivity;
	/**
	 * 交货时间
	 */
    @ApiModelProperty(value="交货时间")
	private String cglOfDelivery;
	/**
	 * 甲方指定邮箱
	 */
    @ApiModelProperty(value="甲方指定邮箱")
	private String cglPEmailAddress;
	/**
	 * 乙方指定邮箱
	 */
    @ApiModelProperty(value="乙方指定邮箱")
	private String cglBEmailAddress;
	/**
	 * 甲方传真号码
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="甲方传真号码")
	private String cglPFaxNumber;
	/**
	 * 乙方传真号码
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方传真号码")
	private String cglBFaxNumber;
	/**
	 * 双方约定采用第【？】种账期付款
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="双方约定采用第【？】种账期付款")
	private String cglPaymentDays;
	/**
	 * 特殊付款
	 */
    @ApiModelProperty(value="特殊付款")
	private String cglSpecialPayment;
	/**
	 * 乙方开户行名称
	 */
    @ApiModelProperty(value="乙方开户行名称")
	private String cglNameOfBank;
	/**
	 * 乙方账户名称
	 */
    @ApiModelProperty(value="乙方账户名称")
	private String cglAccountName;
	/**
	 * 乙方账号
	 */
    @ApiModelProperty(value="乙方账号")
	private String cglPartyBAccount;
	/**
	 * 其他约定
	 */
    @ApiModelProperty(value="其他约定")
	private String cglOtherConventions;
	/**
	 * 甲方联系地址
	 */
	@ApiModelProperty(value = "甲方联系地址")
	private String contactPartyAPlace;
	/**
	 * 乙方联系地址
	 */
	@ApiModelProperty(value = "乙方联系地址")
	private String contactPartyBPlace;
	/**
	 * 甲方联系方式
	 */
	@ApiModelProperty(value = "甲方联系方式")
	private String contactPartyAPhone;
	/**
	 * 乙方联系方式
	 */
	@ApiModelProperty(value = "乙方联系方式")
	private String contactPartyBPhone;
	/**
	 * 拼接附件
	 */
	@ApiModelProperty(value="拼接附件")
	private String annex;

	@ApiModelProperty(value = "新陈列协议书集合")
	@TableField(exist = false)
	private List<CglCategorySalesContracts1Entity> cglCategorySalesContracts1List;
}
