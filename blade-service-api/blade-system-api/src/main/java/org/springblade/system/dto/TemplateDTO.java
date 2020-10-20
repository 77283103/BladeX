package org.springblade.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.TemplateEntity;

/**
 * 表单模板 模型DTO
 *
 * @author szw
 * @date : 2020-10-19 20:00:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateDTO extends TemplateEntity {

	private static final long serialVersionUID = 1L;

}
