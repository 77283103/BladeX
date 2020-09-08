package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *  请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author feng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "请求对象")
public class ContractAccordingRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	@NotBlank(message = "依据名称不能为空")
	@ApiModelProperty(value="依据名称",required = true)
	private String name;

	@NotBlank(message = "依据附件不能为空")
	@ApiModelProperty(value="依据附件",required = true)
	private String accordingFiles;

	@ApiModelProperty(value="备注")
	private String remark;

	@NotNull(message = "是否复用不能为空")
	@ApiModelProperty(value="是否复用",required = true)
	private Integer isReused;
}
