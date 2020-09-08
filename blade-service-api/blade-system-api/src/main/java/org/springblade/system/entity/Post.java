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

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.tenant.mp.TenantEntity;

/**
 * 岗位表实体类
 *
 * @author Chill
 */
@Data
@TableName("blade_post")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Post对象", description = "岗位表")
public class Post extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 类型
	 */
	@ApiModelProperty(value = "类型")
	private Integer category;
	/**
	 * 岗位编号
	 */
	@ApiModelProperty(value = "岗位编号")
	private String postCode;
	/**
	 * 岗位名称
	 */
	@ApiModelProperty(value = "岗位名称")
	private String postName;
	/**
	 * 岗位排序
	 */
	@ApiModelProperty(value = "岗位排序")
	private Integer sort;
	/**
	 * 岗位描述
	 */
	@ApiModelProperty(value = "岗位描述")
	private String remark;


}
