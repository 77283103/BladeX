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
 * cgl_proofing_contract1 实体类
 *
 * @author cglProofingContract1
 * @date : 2021-01-12 13:48:10
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("cgl_proofing_contract1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglProofingContract1对象", description = "cgl_proofing_contract1")
public class CglProofingContract1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 打样产品
	 */
	@ApiModelProperty(value = "打样产品")
	private String proofingProducts;
	/**
	 * 打样内容
	 */
	@ApiModelProperty(value = "打样内容")
	private String proofingContent;
	/**
	 * 材质
	 */
	@ApiModelProperty(value = "材质")
	private String textureMaterial;
	/**
	 * 单价
	 */
	@ApiModelProperty(value = "单价")
	private BigDecimal unitPrice;
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	private Long number;
	/**
	 * 合计金额（元，含税）
	 */
	@ApiModelProperty(value = "合计金额（元，含税）")
	private BigDecimal totalAmount;

	/**
	 * 關聯ID
	 */
	@ApiModelProperty(value="關聯ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long refContractId;
}
