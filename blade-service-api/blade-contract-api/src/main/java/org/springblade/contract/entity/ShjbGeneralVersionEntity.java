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
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本）） 实体类
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））
 * @date : 2020-12-18 16:02:23
 */
@Getter
@Setter
@TableName("shjb_general_version")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShjbGeneralVersion对象", description = "售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））")
public class ShjbGeneralVersionEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方联系地址
	 */
    @ApiModelProperty(value="甲方联系地址")
	private String addressA;
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
	 * 针对乙方租赁的UP售货机，双方约定每台可有【？】条货道（以下称“开放货道”）供乙方销售其自选产品使用
	 */
    @ApiModelProperty(value="针对乙方租赁的UP售货机，双方约定每台可有【？】条货道（以下称“开放货道”）供乙方销售其自选产品使用")
	private String optionalProducts;
	/**
	 * 乙方以每台【？】元（含税）的价格向甲方租用UP售货机共【？】台(大写【？】台)
	 */
    @ApiModelProperty(value="乙方以每台【？】元（含税）的价格向甲方租用UP售货机共【？】台(大写【？】台)")
	private BigDecimal useEach;
	/**
	 * 乙方以每台【？】元（含税）的价格向甲方租用UP售货机共【？】台(大写【？】台)
	 */
    @ApiModelProperty(value="乙方以每台【？】元（含税）的价格向甲方租用UP售货机共【？】台(大写【？】台)")
	private BigDecimal vendingMachine;
	/**
	 * 乙方以每台【？】元（含税）的价格向甲方租用UP售货机共【？】台(大写【？】台)
	 */
    @ApiModelProperty(value="乙方以每台【？】元（含税）的价格向甲方租用UP售货机共【？】台(大写【？】台)")
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
	private String vendingModel;
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
	 * 日
	 */
    @ApiModelProperty(value="日")
	private BigDecimal day;
	/**
	 * 至年
	 */
    @ApiModelProperty(value="至年")
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
