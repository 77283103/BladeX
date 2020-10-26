package org.springblade.system.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.TemplateRelationEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 关联表单 返回模型VO
 *
 * @author szw
 * @date : 2020-10-23 17:24:29
 */
@Data
@ApiModel(description = "关联表单返回对象")
@EqualsAndHashCode(callSuper = true)
public class TemplateRelationResponseVO extends TemplateRelationEntity {

	private static final long serialVersionUID = 1L;

}
