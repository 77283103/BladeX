package org.springblade.resource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 文件管理 实体类
 *
 * @author Feng
 */
@Data
@TableName("blade_file")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "File对象", description = "文件管理")
public class FileEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 全地址
	 */
	@ApiModelProperty(value = "全地址")
	private String link;
	/**
	 * 域名地址
	 */
	@ApiModelProperty(value = "域名地址")
	private String domain;
	/**
	 * 文件名
	 * Element-UI自动读取name字段为展示名称，不能修改
	 */
	@ApiModelProperty(value = "文件名")
	private String name;
	/**
	 * 自动生成的文件名
	 */
	@ApiModelProperty(value = "自动生成的文件名")
	private String generateName;
	/**
	 * 文件大小
	 */
	@ApiModelProperty(value = "文件大小")
	private String size;

}
