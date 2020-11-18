package org.springblade.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springblade.system.entity.TemplateFieldEntity;

/**
 *  请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author szw
 * @date : 2020-10-20 14:45:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "请求对象")
public class TemplateFieldRequestVO extends TemplateFieldEntity {

	private static final long serialVersionUID = 1L;


}
