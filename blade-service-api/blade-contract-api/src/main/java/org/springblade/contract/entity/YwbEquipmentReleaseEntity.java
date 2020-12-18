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
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 业务类：19.设备投放使用协议 实体类
 *
 * @author 业务类：19.设备投放使用协议
 * @date : 2020-12-18 16:07:44
 */
@Getter
@Setter
@TableName("ywb_equipment_release")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "YwbEquipmentRelease对象", description = "业务类：19.设备投放使用协议")
public class YwbEquipmentReleaseEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String firstParty;
	/**
	 * 甲方地址
	 */
    @ApiModelProperty(value="甲方地址")
	private String addressPartyA;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String partyB;
	/**
	 * 乙方地址
	 */
    @ApiModelProperty(value="乙方地址")
	private String addressPartyB;
	/**
	 * 乙方（客户代号）
	 */
    @ApiModelProperty(value="乙方（客户代号）")
	private String customerCode;
	/**
	 * 向甲方借用设备共 【？】台
	 */
    @ApiModelProperty(value="向甲方借用设备共 【？】台")
	private BigDecimal equipmentBorrowed;
	/**
	 * 大写【？】台
	 */
    @ApiModelProperty(value="大写【？】台")
	private BigDecimal inWords;
	/**
	 * 设备品牌为
	 */
    @ApiModelProperty(value="设备品牌为")
	private String equipmentBrand;
	/**
	 * 设备型号为
	 */
    @ApiModelProperty(value="设备型号为")
	private String equipmentModel;
	/**
	 *  设备单价
	 */
    @ApiModelProperty(value=" 设备单价")
	private String priceEquipment;
	/**
	 * 设备内应按【？】%以上陈列面陈列统一公司产品
	 */
    @ApiModelProperty(value="设备内应按【？】%以上陈列面陈列统一公司产品")
	private Double equipmentShoul;
	/**
	 * 设备摆放位置
	 */
    @ApiModelProperty(value="设备摆放位置")
	private String equipmentLocation;
	/**
	 * 本协议有效期为： 【？】年
	 */
    @ApiModelProperty(value="本协议有效期为： 【？】年")
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
	 * 日
	 */
    @ApiModelProperty(value="日")
	private BigDecimal day1;
	/**
	 * 本协议签署后，乙方应向甲方支付设备押金人民币【？】元（大写）
	 */
    @ApiModelProperty(value="本协议签署后，乙方应向甲方支付设备押金人民币【？】元（大写）")
	private BigDecimal signingAgreement;
	/**
	 * 甲方公章
	 */
    @ApiModelProperty(value="甲方公章")
	private String partyOfficialSeal;
	/**
	 * 其他约定
	 */
    @ApiModelProperty(value="其他约定")
	private String otherAgreements;

}
