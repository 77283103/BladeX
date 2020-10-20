package org.springblade.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.TemplateEntity;
import io.swagger.annotations.ApiModel;

/**
 * 表单模板 返回模型VO
 *
 * @author szw
 * @date : 2020-10-19 20:00:56
 */
@Data
@ApiModel(description = "表单模板返回对象")
@EqualsAndHashCode(callSuper = true)
public class TemplateResponseVO extends TemplateEntity {

	private static final long serialVersionUID = 1L;

}
