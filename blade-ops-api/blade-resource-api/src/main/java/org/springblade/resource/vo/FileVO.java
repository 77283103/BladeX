package org.springblade.resource.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.resource.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * 文件管理 模型VO
 *
 * @author Feng
 */
/**
 * ${model.modelRemark!} 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author ${plan.author!}
 */
@Data
@ApiModel(description = "用户对象")
@EqualsAndHashCode(callSuper = true)
public class FileVO extends FileEntity {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "name is notnull")
	@ApiModelProperty(value="用户名",required = true)
	private MultipartFile multipartFile;
}
