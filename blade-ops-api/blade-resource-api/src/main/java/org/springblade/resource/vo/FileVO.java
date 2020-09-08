package org.springblade.resource.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.resource.entity.FileEntity;


/**
 * 文件管理 模型VO
 *
 * @author Feng
 */
/**
 * ${model.modelRemark!} 请求模型VO
 *
 * @author ${plan.author!}
 */
@Data
@ApiModel(description = "用户对象")
@EqualsAndHashCode(callSuper = true)
public class FileVO extends FileEntity {

	private static final long serialVersionUID = 1L;
}
