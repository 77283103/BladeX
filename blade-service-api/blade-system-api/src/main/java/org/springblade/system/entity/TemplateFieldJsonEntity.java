package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.util.List;


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
public class TemplateFieldJsonEntity extends BaseEntity {

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
	 * 字典dataList
	 */
	@TableField(exist = false)
	@ApiModelProperty(value = "字典数据")
	private List dicDataList;

	/**
	 * 校验
	 */
	@ApiModelProperty(value = "校验")
	private String requiredData;

	/**
	 * 校验List
	 */
	@ApiModelProperty(value = "校验")
	private List requiredDataList;

	/**
	 * 列表数据
	 */
	@ApiModelProperty(value = "数据")
	private String tableData;

	/**
	 * 列表数据List
	 */
	@ApiModelProperty(value = "数据")
	private List tableDataList;


	/**
	 * 列表数据对象
	 */
	@ApiModelProperty(value = "数据")
	private String tableDataObject;

	/**
	 * 列表数据对象object
	 */
	@ApiModelProperty(value = "数据")
	private Object tableDataObjectList;


	/**
	 * 二级联动列表数据
	 */
	@ApiModelProperty(value = "数据")
	private String secondSelectData;

	/**
	 * 二级联动列表数据object
	 */
	@ApiModelProperty(value = "数据")
	private Object secondSelectDataObject;


}
