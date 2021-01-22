package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 媒体类：平面广告拍摄制作合同（关联表1） 实体类
 *
 * @author 韩杨
 * @date : 2021-01-21 11:26:39
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("mtb_production_contract1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtbProductionContract1对象", description = "媒体类：平面广告拍摄制作合同（关联表1）")
public class MtbProductionContract1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 交付形式
	 */
	@ApiModelProperty(value = "交付形式")
	private String formDelivery;
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	private BigDecimal number;
	/**
	 * 要求（规格、精度等）
	 */
	@ApiModelProperty(value = "要求（规格、精度等）")
	private String requirements;
	/**
	 * 费用（元，含税）
	 */
	@ApiModelProperty(value = "费用（元，含税）")
	private BigDecimal expenses;
	/**
	 * 合同ID
	 */
	@ApiModelProperty(value = "合同ID")
	private Long contractId;

}
