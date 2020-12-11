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
 * 生产类：加工承揽合同（代工合同）关联表 实体类
 *
 * @author 生产类：加工承揽合同（代工合同）关联表
 * @date : 2020-12-11 10:10:12
 */
@Getter
@Setter
@TableName("scl_construction_project1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclConstructionProject1对象", description = "生产类：加工承揽合同（代工合同）关联表")
public class SclConstructionProject1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 品种
	 */
    @ApiModelProperty(value="品种")
	private String varieties;
	/**
	 * 规格
	 */
    @ApiModelProperty(value="规格")
	private String specifications;
	/**
	 * 加工费用（元/箱；）
	 */
    @ApiModelProperty(value="加工费用（元/箱；）")
	private BigDecimal processingCost;
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;

}
