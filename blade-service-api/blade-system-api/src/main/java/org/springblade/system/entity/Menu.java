/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tenant.mp.TenantEntity;
import org.springblade.core.tool.utils.Func;

import java.io.Serializable;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@TableName("blade_menu")
@ApiModel(value = "Menu对象", description = "Menu对象")
public class Menu extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 上级菜单
	 */
	@ApiModelProperty(value = "上级菜单")
	private Long parentId;
	/**
	 * 英文名称
	 */
	@ApiModelProperty(value = "英文名称")
	private String englishName;
	/**
	 * 菜单编号
	 */
	@ApiModelProperty(value = "菜单编号")
	private String code;
	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称")
	private String name;
	/**
	 * 菜单别名
	 */
	@ApiModelProperty(value = "菜单别名")
	private String alias;
	/**
	 * 路由地址
	 */
	@ApiModelProperty(value = "路由地址")
	private String path;
	/**
	 * 菜单图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String source;
	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;
	/**
	 * 菜单类型
	 */
	@ApiModelProperty(value = "菜单类型")
	private Integer category;
	/**
	 * 操作按钮类型
	 */
	@ApiModelProperty(value = "操作按钮类型")
	private Integer action;
	/**
	 * 是否打开新页面
	 */
	@ApiModelProperty(value = "是否打开新页面")
	private Integer isOpen;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		Menu other = (Menu) obj;
		if (Func.equals(this.getId(), other.getId())) {
			return true;
		}
		return false;
	}

}
