package org.springblade.system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.TemplateRelationEntity;
import java.util.Date;

/**
 * 关联表单 模型DTO
 *
 * @author szw
 * @date : 2020-10-23 17:24:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateRelationDTO extends TemplateRelationEntity {

	private static final long serialVersionUID = 1L;

}
