package org.springblade.system.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.TemplateFieldRelationEntity;

/**
 * 关联表字段属性配置表 模型DTO
 *
 * @author szw
 * @date : 2020-10-23 17:24:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateFieldRelationDTO extends TemplateFieldRelationEntity {

	private static final long serialVersionUID = 1L;

}
