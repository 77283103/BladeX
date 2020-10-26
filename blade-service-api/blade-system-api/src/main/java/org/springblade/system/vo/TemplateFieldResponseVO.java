package org.springblade.system.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.TemplateFieldEntity;
import io.swagger.annotations.ApiModel;

/**
 *  返回模型VO
 *
 * @author szw
 * @date : 2020-10-20 14:45:06
 */
@Data
@ApiModel(description = "返回对象")
@EqualsAndHashCode(callSuper = true)
public class TemplateFieldResponseVO extends TemplateFieldEntity {

	private static final long serialVersionUID = 1L;

}
