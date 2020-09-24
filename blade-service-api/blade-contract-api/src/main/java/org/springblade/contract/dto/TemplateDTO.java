package org.springblade.contract.dto;

import lombok.*;
import org.springblade.contract.entity.TemplateEntity;

/**
 * 范本管理 模型DTO
 *
 * @author XHB
 * @date : 2020-09-24 13:57:35
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TemplateDTO extends TemplateEntity {

	private static final long serialVersionUID = 1L;

}
