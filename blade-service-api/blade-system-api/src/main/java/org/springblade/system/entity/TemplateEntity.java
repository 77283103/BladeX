package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 表单模板 实体类
 *
 * @author szw
 * @date : 2020-10-19 20:00:56
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("form_template")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Template对象", description = "表单模板")
public class TemplateEntity extends BaseEntity {

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

}
