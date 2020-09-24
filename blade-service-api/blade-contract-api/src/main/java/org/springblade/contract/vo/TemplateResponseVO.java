package org.springblade.contract.vo;

import lombok.*;
import org.springblade.contract.entity.TemplateEntity;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;

/**
 * 范本管理 返回模型VO
 *
 * @author XHB
 * @date : 2020-09-24 13:57:40
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "范本管理返回对象")
@EqualsAndHashCode(callSuper = true)
public class TemplateResponseVO extends TemplateEntity {

	private static final long serialVersionUID = 1L;

}
