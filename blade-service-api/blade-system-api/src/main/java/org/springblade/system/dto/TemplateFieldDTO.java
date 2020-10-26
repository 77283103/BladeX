package org.springblade.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.TemplateFieldEntity;

/**
 *  模型DTO
 *
 * @author szw
 * @date : 2020-10-20 14:45:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateFieldDTO extends TemplateFieldEntity {

	private static final long serialVersionUID = 1L;

}
