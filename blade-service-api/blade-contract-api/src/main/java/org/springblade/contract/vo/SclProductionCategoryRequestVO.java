package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.core.mp.base.BaseEntity;


/**
 * 生产类：物流服务合同（一段+调拨运输 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author kx
 * @date : 2021-01-16 18:10:59
 */
@Getter
@Setter
@ToString
@ApiModel(description = "生产类：物流服务合同（一段+调拨运输请求对象")
public class SclProductionCategoryRequestVO extends BaseEntity{

	private static final long serialVersionUID = 1L;

}
