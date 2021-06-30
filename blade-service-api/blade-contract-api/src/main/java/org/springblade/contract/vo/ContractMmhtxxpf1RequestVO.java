package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 行销品买卖合同子表 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author kx
 * @date : 2021-05-10 13:36:58
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "行销品买卖合同子表请求对象")
public class ContractMmhtxxpf1RequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="品名")
	private String contractName;

    @ApiModelProperty(value="材质/规格")
	private String contractType;

    @ApiModelProperty(value="未税单价 未税单价（元/  ） ")
	private BigDecimal contractPrice;

    @ApiModelProperty(value="合作期限")
	private String contractTime;

    @ApiModelProperty(value="备注")
	private String contractElse;

}
