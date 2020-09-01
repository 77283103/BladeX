package org.springblade.flow.business.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 流程定义信息表 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author tianah
 * @date 2020-8-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "流程定义信息表请求对象")
public class ProcessRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @NotBlank(message = "流程定义主键不能为空")
    @ApiModelProperty(value="流程定义主键",required = true)
	private String processDefinitionId;

    @NotBlank(message = "业务类型不能为空")
    @ApiModelProperty(value="业务类型",required = true)
	private String businessType;

    @ApiModelProperty(value="流程使用范围")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long deptRange;

    @ApiModelProperty(value="流程参数")
	private String params;

}
