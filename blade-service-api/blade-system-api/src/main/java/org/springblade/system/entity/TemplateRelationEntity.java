package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 关联表单 实体类
 *
 * @author szw
 * @date : 2020-10-23 17:24:26
 */
@Getter
@Setter
@TableName("template_relation")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TemplateRelation对象", description = "关联表单")
public class TemplateRelationEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * bean全路径
	 */
	@ApiModelProperty(value = "bean全路径")
	private String bean;
	/**
	 * bean的名称
	 */
	@ApiModelProperty(value = "bean的名称")
	private String beanName;
	/**
	 * 表单code
	 */
	@ApiModelProperty(value = "表单code")
	private String formCode;
	/**
	 * 表单json
	 */
	@ApiModelProperty(value = "表单json")
	private String json;

}
