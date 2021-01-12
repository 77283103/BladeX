package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;

/**
 * cgl_proofing_contract1 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author cglProofingContract1
 * @date : 2021-01-12 13:48:12
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "cgl_proofing_contract1请求对象")
public class CglProofingContract1RequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="打样产品")
	private String proofingProducts;
	
    @ApiModelProperty(value="打样内容")
	private String proofingContent;
	
    @ApiModelProperty(value="材质")
	private String textureMaterial;
	
    @ApiModelProperty(value="单价")
	private BigDecimal unitPrice;
	
    @ApiModelProperty(value="数量")
	private Long number;
	
    @ApiModelProperty(value="合计金额（元，含税）")
	private BigDecimal totalAmount;
	
}
