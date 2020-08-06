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
package org.springblade.system.user.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.system.user.entity.User;
import org.springblade.system.vo.UserDepartVO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


/**
 * 在页面中选择用户
 * 视图实体类
 *
 * @author Chill
 */
@Data
@ApiModel(value = "UserVO对象", description = "UserVO对象")
public class SelectUserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
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
	 * 性别
	 */
	private Integer sex;
	/**
	 * 邮箱
	 */
	private String email;

}
