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
 * 采购类：买卖合同（国内设备购买） 实体类
 *
 * @author 王策
 * @date : 2020-12-18 15:36:07
 */
@Getter
@Setter
@TableName("cgl_sales_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglSalesContract对象", description = "采购类：买卖合同（国内设备购买）")
public class CglSalesContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 买方
	 */
    @ApiModelProperty(value="买方")
	private String cglBuyer;
	/**
	 * 买方住所
	 */
    @ApiModelProperty(value="买方住所")
	private String cglDomicile;
	/**
	 * 卖方
	 */
    @ApiModelProperty(value="卖方")
	private String cglTheSeller;
	/**
	 * 卖方住所
	 */
    @ApiModelProperty(value="卖方住所")
	private String cglResidence;
	/**
	 * 序号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="序号")
	private Integer cglSerialNumber;
	/**
	 * 设备名称
	 */
    @ApiModelProperty(value="设备名称")
	private String cglDeviceName;
	/**
	 * 型号（规格）
	 */
    @ApiModelProperty(value="型号（规格）")
	private String cglModel;
	/**
	 * 数量
	 */
    @ApiModelProperty(value="数量")
	private Double cgl numberOf;
	/**
	 * 未税单价
	 */
    @ApiModelProperty(value="未税单价")
	private BigDecimal cglUnitPrice;
	/**
	 * 未税总价
	 */
    @ApiModelProperty(value="未税总价")
	private BigDecimal cglTaxPrice;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String cglNote;
	/**
	 * 未税总价人民币
	 */
    @ApiModelProperty(value="未税总价人民币")
	private BigDecimal cglTotalRmb;
	/**
	 * 未税总价人民币（大写）
	 */
    @ApiModelProperty(value="未税总价人民币（大写）")
	private String cglCapitalRmb;
	/**
	 * 包装标准
	 */
    @ApiModelProperty(value="包装标准")
	private String cglPackaging;
	/**
	 * 交货时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="交货时间")
	private Date cglDelivery;
	/**
	 * 交货地址
	 */
    @ApiModelProperty(value="交货地址")
	private String cglDeliveryAddress;
	/**
	 * 设备运输方式
	 */
    @ApiModelProperty(value="设备运输方式")
	private String cglOfTransportation;
	/**
	 * 运输承担方
	 */
    @ApiModelProperty(value="运输承担方")
	private String cglUndertakingParty;
	/**
	 * 卸货承担方
	 */
    @ApiModelProperty(value="卸货承担方")
	private String cglDischargingParty;
	/**
	 * 卸货费用承担方
	 */
    @ApiModelProperty(value="卸货费用承担方")
	private String cglDischarging;
	/**
	 * 特别约定
	 */
    @ApiModelProperty(value="特别约定")
	private String cglSpecificallyAgreed;
	/**
	 * 乙方应随设备提
供以下文件
	 */
    @ApiModelProperty(value="乙方应随设备提
供以下文件")
	private String cglFiles;
	/**
	 * 第十八项：特别约定
	 */
    @ApiModelProperty(value="第十八项：特别约定")
	private String cglDischargings;
	/**
	 * 第七.3.（1）项日期要求
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="第七.3.（1）项日期要求")
	private Integer cglDateOfRequest;
	/**
	 * 第七.3.（2）项日期要求
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="第七.3.（2）项日期要求")
	private Integer cglDateOfRequests;

}
