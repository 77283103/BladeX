package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 采购类：买卖合同（国内设备购买）附表 实体类
 *
 * @author 王策
 * @date : 2020-12-18 16:12:23
 */
@Getter
@Setter
@TableName("cgl_sales_contract1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglSalesContract1对象", description = "采购类：买卖合同（国内设备购买）附表")
public class CglSalesContract1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="序号")
	private Integer cqlSNumber;
	/**
	 * 设备名称
	 */
    @ApiModelProperty(value="设备名称")
	private String cqlEquipmentName;
	/**
	 * 型号（规格）
	 */
    @ApiModelProperty(value="型号（规格）")
	private String cqlModelS;
	/**
	 * 数量
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="数量")
	private Integer cqlNumber;
	/**
	 * 未税单价
	 */
    @ApiModelProperty(value="未税单价")
	private BigDecimal cqlWithoutTax;
	/**
	 * 未税总价
	 */
    @ApiModelProperty(value="未税总价")
	private BigDecimal cqlFreePrice;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String cqlRemarks;
	/**
	 * 關聯ID
	 */
	@ApiModelProperty(value="關聯ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long refContractId;
}
