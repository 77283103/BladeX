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
package org.springblade.system.user.dto;

import lombok.Data;
import org.springblade.system.dto.UserDepartDTO;
import org.springblade.system.user.entity.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 用户基本信息
 *
 * @author Feng
 */
@Data
public class UserBaseInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 用户编号
	 */
	private String code;
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
}
