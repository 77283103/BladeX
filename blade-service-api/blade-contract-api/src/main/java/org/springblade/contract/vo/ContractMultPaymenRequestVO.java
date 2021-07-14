package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.contract.entity.ContractMultPaymenEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 国内设备买卖合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author kx
 * @date : 2021-05-10 13:39:21
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractMultPaymen对象", description = "多方相对方收付款")
public class ContractMultPaymenRequestVO extends ContractMultPaymenEntity {

	private static final long serialVersionUID = 1L;

}
