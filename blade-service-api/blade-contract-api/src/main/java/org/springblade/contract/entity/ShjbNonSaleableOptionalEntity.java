package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本） 实体类
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）
 * @date : 2020-12-18 16:01:15
 */
@Getter
@Setter
@TableName("shjb_non_saleable_optional")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShjbNonSaleableOptional对象", description = "售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）")
public class ShjbNonSaleableOptionalEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方联系地址
	 */
    @ApiModelProperty(value="甲方联系地址")
	private String contactAddressA;
	/**
	 * 乙方法定代表人/经营者
	 */
    @ApiModelProperty(value="乙方法定代表人/经营者")
	private String operatorB;
	/**
	 * 乙方地址
	 */
    @ApiModelProperty(value="乙方地址")
	private String addressB;
	/**
	 * 元
	 */
    @ApiModelProperty(value="元")
	private BigDecimal element;
	/**
	 * 台
	 */
    @ApiModelProperty(value="台")
	private BigDecimal platform;
	/**
	 * 大写【？】台
	 */
    @ApiModelProperty(value="大写【？】台")
	private String inWords;
	/**
	 * 序号
	 */
    @ApiModelProperty(value="序号")
	private BigDecimal serialNumber;
	/**
	 * 售货机型号
	 */
    @ApiModelProperty(value="售货机型号")
	private String vendingMachine;
	/**
	 * 台数
	 */
    @ApiModelProperty(value="台数")
	private BigDecimal numberSets;
	/**
	 * 租赁单价（元/台）
	 */
    @ApiModelProperty(value="租赁单价（元/台）")
	private BigDecimal rentalPrice;
	/**
	 * 户名
	 */
    @ApiModelProperty(value="户名")
	private String accountName;
	/**
	 * 账号
	 */
    @ApiModelProperty(value="账号")
	private String accountNumber;
	/**
	 * 开户行全称
	 */
    @ApiModelProperty(value="开户行全称")
	private String openingBank;
	/**
	 * 租赁有效期为【？】年
	 */
    @ApiModelProperty(value="租赁有效期为【？】年")
	private BigDecimal year;
	/**
	 * 月
	 */
    @ApiModelProperty(value="月")
	private BigDecimal month;
	/**
	 * 日至
	 */
    @ApiModelProperty(value="日至")
	private BigDecimal day;
	/**
	 * 年
	 */
    @ApiModelProperty(value="年")
	private BigDecimal year1;
	/**
	 * 月
	 */
    @ApiModelProperty(value="月")
	private BigDecimal month1;
	/**
	 * 日止
	 */
    @ApiModelProperty(value="日止")
	private BigDecimal day1;

}
