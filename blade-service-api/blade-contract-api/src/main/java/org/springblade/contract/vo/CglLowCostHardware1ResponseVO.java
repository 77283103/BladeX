package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.CglLowCostHardware1Entity;

import java.math.BigDecimal;

/**
 * 采购类：原物料-买卖合同 返回模型VO
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:37
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：原物料-买卖合同返回对象")
@EqualsAndHashCode(callSuper = true)
public class CglLowCostHardware1ResponseVO extends CglLowCostHardware1Entity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
	/**
	 * 序号
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value="序号")
	public Integer cglNumber;
	/**
	 * 料号
	 */
	@ApiModelProperty(value="料号")
	public String cglMaterial;
	/**
	 * 品名
	 */
	@ApiModelProperty(value="品名")
	public String cglOfTheGoods;
	/**
	 * 规格
	 */
	@ApiModelProperty(value="规格")
	public String cglSpecifications;
	/**
	 * 未税单价
	 */
	@ApiModelProperty(value="未税单价")
	public BigDecimal cglUnitPrice;
	/**
	 * 账期（日）
	 */
	@ApiModelProperty(value="账期（日）")
	public String cglPaymentDays;
	/**
	 * 合作起始时间
	 */
	@ApiModelProperty(value="合作起始时间")
	public String cglStartingTime;
	/**
	 * 合作结束时间
	 */
	@ApiModelProperty(value="合作结束时间")
	public String cglEndOfTime;
	/**
	 * 起订量
	 */
	@ApiModelProperty(value="起订量")
	public String cglMinimumQuantity;
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	public String cglNote;
	/**
	 * 税率
	 */
	@ApiModelProperty(value="税率")
	public String cglRate;
	/**
	 * 合作数量
	 */
	@ApiModelProperty(value="合作数量")
	public String cglMount;
	/**
	 * 品牌
	 */
	@ApiModelProperty(value="品牌")
	public String cglBrand;
	/**
	 * 单位  度量衡
	 */
	@ApiModelProperty(value="单位 度量衡")
	public String cglUnit;
	/**
	 * 执行公司
	 */
	@ApiModelProperty(value="执行公司")
	public String cglCompany;
	/**
	 * 合同ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同ID")
	public Long contractId;
}
