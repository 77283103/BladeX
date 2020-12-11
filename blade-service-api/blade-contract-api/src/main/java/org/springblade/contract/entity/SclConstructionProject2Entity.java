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
 * 生产类：加工承揽合同（代工合同）关联表2 实体类
 *
 * @author 生产类：加工承揽合同（代工合同）关联表2
 * @date : 2020-12-11 10:22:07
 */
@Getter
@Setter
@TableName("scl_construction_project2")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclConstructionProject2对象", description = "生产类：加工承揽合同（代工合同）关联表2")
public class SclConstructionProject2Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
    @ApiModelProperty(value="序号")
	private BigDecimal serialNumber;
	/**
	 * 原/物料
	 */
    @ApiModelProperty(value="原/物料")
	private String rawMaterial;
	/**
	 * 原物料名称
	 */
    @ApiModelProperty(value="原物料名称")
	private String nameMaterial;
	/**
	 * 损耗率
	 */
    @ApiModelProperty(value="损耗率")
	private String lossRate;
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;

}
