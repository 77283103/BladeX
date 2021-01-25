package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 设备投放使用协议 实体类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:18:09
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("device_launch_use_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DeviceLaunchUseContract对象", description = "设备投放使用协议")
public class DeviceLaunchUseContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String devSaler;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String devSalerAddr;
	/**
	 * 乙方：（客户名称）
	 */
	@ApiModelProperty(value = "乙方：（客户名称）")
	private String devBuyer;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String devBuyerAddr;
	/**
	 * 乙方：(客户代号)
	 */
	@ApiModelProperty(value = "乙方：(客户代号)")
	private String devBuyerNum;
	/**
	 * 乙方向甲方借用设备共?台
	 */
	@ApiModelProperty(value = "乙方向甲方借用设备共?台")
	private String devNumber;
	/**
	 * 大写?台
	 */
	@ApiModelProperty(value = "大写?台")
	private String devNumberInWord;
	/**
	 * 设备品牌
	 */
	@ApiModelProperty(value = "设备品牌")
	private String devBrand;
	/**
	 * 设备型号
	 */
	@ApiModelProperty(value = "设备型号")
	private String devModel;
	/**
	 * 设备价值
	 */
	@ApiModelProperty(value = "设备价值")
	private BigDecimal devValue;
	/**
	 * 资产编码
	 */
	@ApiModelProperty(value = "资产编码")
	private String devCode;
	/**
	 * 设备摆放位置
	 */
	@ApiModelProperty(value = "设备摆放位置")
	private String devPlace;
	/**
	 * 设备借用有效期始
	 */
	@ApiModelProperty(value = "设备借用有效期始")
	private Date devBorroStart;
	/**
	 * 设备借用有效期止
	 */
	@ApiModelProperty(value = "设备借用有效期止")
	private Date devBorroEnd;
	/**
	 * 本协议签署后?日内
	 */
	@ApiModelProperty(value = "本协议签署后?日内")
	private String devLeastDate;
	/**
	 * 乙方应向甲方支付设备押金?元
	 */
	@ApiModelProperty(value = "乙方应向甲方支付设备押金?元")
	private BigDecimal devDeposit;
	/**
	 * 大写人民币?元
	 */
	@ApiModelProperty(value = "大写人民币?元")
	private String devDepositInWord;
	/**
	 * 甲方签约人
	 */
	@ApiModelProperty(value = "甲方签约人")
	private String devSalerPerson;
	/**
	 * 甲方签订时间
	 */
	@ApiModelProperty(value = "甲方签订时间")
	private Date devSalerTime;
	/**
	 * 乙方签约人
	 */
	@ApiModelProperty(value = "乙方签约人")
	private String devBuyerPerson;
	/**
	 * 乙方签订时间
	 */
	@ApiModelProperty(value = "乙方签订时间")
	private Date devBuyerTime;
	/**
	 * 新投放本次有收押
	 */
	@ApiModelProperty(value = "新投放本次有收押")
	private String newRelease;
	/**
	 * 协议续签本次无收押
	 */
	@ApiModelProperty(value = "协议续签本次无收押")
	private String agreementRenewal;
}
