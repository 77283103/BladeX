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
@TableName("template")
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
	 * bean编号
	 */
	@ApiModelProperty(value = "bean编号")
	private String formCode;
	/**
	 * 表单json
	 */
	@ApiModelProperty(value="表单json",required = true)
	private String json;
	/**
	 * 模板编号
	 */
	@ApiModelProperty(value = "模板编号")
	private String templateCode;
}
