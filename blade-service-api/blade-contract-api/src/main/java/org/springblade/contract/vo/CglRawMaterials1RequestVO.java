package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.CglRawMaterials1Entity;


/**
 * 采购类：原物料-买卖合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:34
 */
@Getter
@Setter
@ToString
@ApiModel(description = "采购类：原物料-买卖合同请求对象")
public class CglRawMaterials1RequestVO extends CglRawMaterials1Entity {

	private static final long serialVersionUID = 1L;

}
