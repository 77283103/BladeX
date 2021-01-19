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
package org.springblade.system.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.tenant.mp.TenantEntity;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 实体类
 *
 * @author Chill
 */
@Data
@TableName("blade_user")
@EqualsAndHashCode(callSuper = true)
public class User extends TenantEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	private String code;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机
	 */
	private String phone;
	/**
	 * 座机号
	 */
	private String landline;
	/**
	 * 生日
	 */
	private LocalDateTime birthday;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 是否启用
	 */
	private Integer isEnable;
	/**
	 * 关联供应商id
	 */
	@ApiModelProperty(value = "关联供应商id")
	private String associationId;
	/**
	 * 厂别代号
	 */
	@ApiModelProperty(value = "厂别代号")
	private String factNo;
	/**
	 * 厂别名称
	 */
	@ApiModelProperty(value = "厂别名称")
	private String factName;
	/**
	 * 部门代号
	 */
	@ApiModelProperty(value = "部门代号")
	private String deptNo;
	/**
	 * 部门名称
	 */
	@ApiModelProperty(value = "部门名称")
	private String deptNm;
	/**
	 * 来源厂别
	 */
	@ApiModelProperty(value = "来源厂别")
	private String laiYuan;
}
