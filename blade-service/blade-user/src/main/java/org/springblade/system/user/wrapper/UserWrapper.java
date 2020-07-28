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
package org.springblade.system.user.wrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.cache.DictCache;
import org.springblade.system.cache.SysCache;
import org.springblade.system.entity.Tenant;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.vo.SelectUserVO;
import org.springblade.system.user.vo.UserVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class UserWrapper extends BaseEntityWrapper<User, UserVO> {

	public static UserWrapper build() {
		return new UserWrapper();
	}

	@Override
	public UserVO entityVO(User user) {
		return Objects.requireNonNull(BeanUtil.copy(user, UserVO.class));
	}

	/**
	 * 备选用户实体类集合包装
	 * @param user
	 * @return
	 */
	private SelectUserVO entitySelectVO(User user) {
		return Objects.requireNonNull(BeanUtil.copy(user, SelectUserVO.class));
	}

	/**
	 * 备选用户实体类集合包装
	 * @param page
	 * @return
	 */
	public IPage<SelectUserVO> userToSelectUserpageVO(IPage<User> page) {
		List<SelectUserVO> records = page.getRecords().stream().map(this::entitySelectVO).collect(Collectors.toList());
		IPage<SelectUserVO> pageVo = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
		pageVo.setRecords(records);
		return pageVo;
	}
}
