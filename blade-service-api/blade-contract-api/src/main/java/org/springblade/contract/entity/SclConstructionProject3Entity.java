package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 生产类：加工承揽合同（代工合同）关联表3 实体类
 *
 * @author 生产类：加工承揽合同（代工合同）关联表3
 * @date : 2020-12-11 10:36:42
 */
@Getter
@Setter
@TableName("scl_construction_project3")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclConstructionProject3对象", description = "生产类：加工承揽合同（代工合同）关联表3")
public class SclConstructionProject3Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 品名
	 */
    @ApiModelProperty(value="品名")
	private String productName;
	/**
	 * 产品规格
	 */
    @ApiModelProperty(value="产品规格")
	private String productSpecifications;
	/**
	 * 供应厂商
	 */
    @ApiModelProperty(value="供应厂商")
	private String supplier;
	/**
	 * 未税单价
	 */
    @ApiModelProperty(value="未税单价")
	private BigDecimal withoutTax;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String remarks;
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;

}
