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
import org.springblade.system.dto.UserDepartDTO;
import org.springblade.system.entity.Tenant;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.vo.UserVO;
import org.springblade.system.vo.UserDepartVO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class UserDTOWrapper extends BaseEntityWrapper<UserDTO, UserVO> {

	public static UserDTOWrapper build() {
		return new UserDTOWrapper();
	}


	@Override
	public UserVO entityVO(UserDTO entity) {
		return Objects.requireNonNull(BeanUtil.copy(entity, UserVO.class));
	}

	/**
	 * 分页实体类集合包装
	 * @param pages
	 * @return
	 */
	public IPage<UserVO> toPageVO(IPage<UserDTO> pages) {
		List<UserDTO> records = pages.getRecords();
		List<UserVO> userVOList = records.stream().map(this::toVO).collect(Collectors.toList());
		IPage<UserVO> pageVo = new Page<>(pages.getCurrent(), pages.getSize(), pages.getTotal());
		pageVo.setRecords(userVOList);
		return pageVo;
	}

	/**
	 * dto转vo
	 * @param userDTO
	 * @return
	 */
	public UserVO toVO(UserDTO userDTO) {
		List<UserDepartDTO> userDepartList = userDTO.getUserDepartList();
		List<UserDepartVO> collect = userDepartList.stream().map(userDepartDTO -> {
			UserDepartVO userDepartVO = new UserDepartVO();
			BeanUtil.copy(userDepartDTO, userDepartVO);
			return userDepartVO;
		}).collect(Collectors.toList());
		UserVO userVO = entityVO(userDTO);
		userVO.setUserDepartList(collect);
		return userVO;
	}
}
