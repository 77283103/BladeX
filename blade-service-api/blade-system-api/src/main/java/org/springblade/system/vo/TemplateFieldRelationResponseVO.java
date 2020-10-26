package org.springblade.system.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import org.springblade.system.entity.TemplateFieldRelationEntity;
import io.swagger.annotations.ApiModel;
import java.util.Date;

/**
 * 关联表字段属性配置表 返回模型VO
 *
 * @author szw
 * @date : 2020-10-23 17:24:46
 */
@Data
@ApiModel(description = "关联表字段属性配置表返回对象")
@EqualsAndHashCode(callSuper = true)
public class TemplateFieldRelationResponseVO extends TemplateFieldRelationEntity {

	private static final long serialVersionUID = 1L;

}
