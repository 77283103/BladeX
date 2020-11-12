package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.time.LocalDateTime;


/**
 *  实体类
 *
 * @author szw
 * @date : 2020-10-20 14:45:04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("template_field")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TemplateField对象", description = "")
public class TemplateFieldEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 字段值
	 */
	@ApiModelProperty(value = "字段值")
	private String fieldValue;
	/**
	 * 字段名称
	 */
	@ApiModelProperty(value = "字段名称")
	private String fieldTitle;
	/**
	 * bean中的字段
	 */
	@ApiModelProperty(value = "bean中的字段")
	private String fieldName;
	/**
	 * 组件类型
	 */
	@ApiModelProperty(value = "组件类型")
	private String componentType;
	/**
	 * 字典code
	 */
	@ApiModelProperty(value = "字典code")
	private String dicCode;
	/**
	 * 字典data
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "字典数据")
	private String dicData;
	/**
	 * 关联code
	 */
	@ApiModelProperty(value = "关联code")
	private String relationCode;
	/**
	 * 字段类型
	 */
	@ApiModelProperty(value = "字段类型")
	private String fieldType;
	/**
	 * 是否必填
	 */
	@ApiModelProperty(value = "是否必填")
	private String required;
	/**
	 * 顺序
	 */
	@ApiModelProperty(value="顺序")
	private Integer sort;
	/**
	 * 表单code
	 */
	@ApiModelProperty(value = "表单code")
	private String code;
	/**
	 * bean
	 */
	@ApiModelProperty(value = "bean")
	private String bean;
	/**
	 * bean的名称
	 */
	@ApiModelProperty(value = "bean的名称")
	private String beanName;
	/**
	 * 禁止编辑
	 */
	@ApiModelProperty(value = "禁止编辑")
	private String disabled;
	/**
	 * 日期格式化
	 */
	@ApiModelProperty(value = "日期格式化")
	private String format;
	/**
	 * 提示
	 */
	@ApiModelProperty(value = "提示")
	private String tips;
	/**
	 * 布局
	 */
	@ApiModelProperty(value = "布局")
	private Integer layout;
	/**
	 * 布局
	 */
	@ApiModelProperty(value = "数据")
	private Object tableData;

}
