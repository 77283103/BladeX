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
 * 采购类：原物料-买卖合同 实体类
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:33
 */
@Getter
@Setter
@TableName("cgl_raw_materials_1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglRawMaterials1对象", description = "采购类：原物料-买卖合同")
public class CglRawMaterials1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="序号")
	private Integer cglNumber;
	/**
	 * 料号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="料号")
	private Integer cglMaterial;
	/**
	 * 品名
	 */
    @ApiModelProperty(value="品名")
	private String cglOfTheGoods;
	/**
	 * 规格
	 */
    @ApiModelProperty(value="规格")
	private String cglSpecifications;
	/**
	 * 未税单价
	 */
    @ApiModelProperty(value="未税单价")
	private BigDecimal cglUnitPrice;
	/**
	 * 账期（日）
	 */
    @ApiModelProperty(value="账期（日）")
	private String cglPaymentDays;
	/**
	 * 合作起始时间
	 */

    @ApiModelProperty(value="合作起始时间")
	private String cglStartingTime;
	/**
	 * 合作结束时间
	 */
    @ApiModelProperty(value="合作结束时间")
	private String cglEndOfTime;
	/**
	 * 起订量
	 */
    @ApiModelProperty(value="起订量")
	private String cglMinimumQuantity;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String cglNote;
	/**
	 * 税率
	 */
	@ApiModelProperty(value="税率")
	private String cglRate;
	/**
	 * 合作数量
	 */
	@ApiModelProperty(value="合作数量")
	private String cglMount;
	/**
	 * 单位  度量衡
	 */
	@ApiModelProperty(value="单位 度量衡")
	private String cglUnit;
	/**
	 * 执行公司
	 */
	@ApiModelProperty(value="执行公司")
	private String cglCompany;
	/**
	 * 合同ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同ID")
	private Long contractId;
}
