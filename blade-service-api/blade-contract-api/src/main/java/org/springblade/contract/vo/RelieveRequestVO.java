package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;

/**
 *  请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "请求对象")
public class RelieveRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="解除原因")
	private String relieveCategory;

    @ApiModelProperty(value="违约金额")
	private BigDecimal breachAmount;

    @ApiModelProperty(value="违约金流向")
	private String breachCategory;

    @ApiModelProperty(value="赔偿金额")
	private BigDecimal compensationAmount;

    @ApiModelProperty(value="赔偿款流向")
	private String compensationCategory;

    @ApiModelProperty(value="解除说明")
	private String relieveRemark;

    @ApiModelProperty(value="关联合同id")
	private Long refContractId;

}
