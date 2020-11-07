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

    @ApiModelProperty(value="字段值")
	private String fieldValue;

    @ApiModelProperty(value="字段名称")
	private String fieldTitle;

	@ApiModelProperty(value = "bean中的字段")
	private String fieldName;

    @ApiModelProperty(value="组件类型")
	private String componentType;

	@ApiModelProperty(value = "字典code")
	private String dicCode;

    @ApiModelProperty(value="关联code")
	private String relationCode;

    @ApiModelProperty(value="字段类型")
	private String fieldType;

    @ApiModelProperty(value="是否必填")
	private String required;

	@ApiModelProperty(value="顺序")
	private Integer sort;

    @ApiModelProperty(value="表单code")
	private String code;

    @ApiModelProperty(value="bean")
	private String bean;

    @ApiModelProperty(value="bean的名称")
	private String beanName;

    @ApiModelProperty(value="禁止编辑")
	private String disabled;

    @ApiModelProperty(value="日期格式化")
	private String format;

	@ApiModelProperty(value="字典数据")
	private String dicData;

	@ApiModelProperty(value = "提示")
	private String tips;

	@ApiModelProperty(value = "布局")
	private Integer layout;
}
