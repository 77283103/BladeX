package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

/**
 * 消防-维保合同子表 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author kx
 * @date : 2021-05-10 13:40:19
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "消防-维保合同子表请求对象")
public class ContractWbht1RequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="序号")
	private String contractNum;

    @ApiModelProperty(value="姓名")
	private String contractName;

    @ApiModelProperty(value="身份证号")
	private String contractId;

    @ApiModelProperty(value="工种")
	private String contractBus;

    @ApiModelProperty(value="施工资质证编号")
	private String contractBusNum;

}
