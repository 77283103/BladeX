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
 * 关联表字段属性配置表 实体类
 *
 * @author szw
 * @date : 2020-10-23 17:24:41
 */
@Getter
@Setter
@TableName("template_field_relation")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TemplateFieldRelation对象", description = "关联表字段属性配置表")
public class TemplateFieldRelationEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 字段关联code
	 */
	@ApiModelProperty(value = "字段关联code")
	private String fieldPropertyCode;
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
	 * 对应bean中的字段
	 */
	@ApiModelProperty(value = "对应bean中的字段")
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
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
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

}
