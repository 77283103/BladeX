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
package org.springblade.system.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.excel.UserExcel;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param user
	 * @param deptIdList
	 * @param tenantId
	 * @return
	 */
	List<User> userPage(IPage<User> page, @Param("user") User user, @Param("deptIdList") List<Long> deptIdList, @Param("tenantId") String tenantId);

	/**
	 * 获取用户
	 *
	 * @param tenantId
	 * @param account
	 * @return
	 */
	UserDTO getUser(String tenantId, String account);

	/**
	 * 获取导出用户数据
	 * @param user
	 * @param deptIdList
	 * @param tenantId
	 * @return
	 */
	List<UserExcel> exportUser( @Param("user") User user, @Param("deptIdList") List<Long> deptIdList, @Param("tenantId") String tenantId);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	UserDTO selectUserDTOById(@Param("id")Long id);

	/**
	 * 根据机构id和岗位id查询用户（流程使用）
	 * @param deptId
	 * @param postId
	 * @return
	 */
	List<User> findByDeptAndPost(String deptId, String postId);

	/**
	 * 基准人的部门部长
	 * @param benchUserId 基准人id
	 * @return
	 */
	List<User> findByBenchMinister(String benchUserId);


	/**
	 * 根据用户名查用户信息
	 * @param userName
	 * @return
	 */
	User getByUserName(String userName);
	/**
	 * 根据用户名查用户信息
	 * @param realName
	 * @return
	 */
	List<User> getByRealName(String realName);

	/**
	 * 根据lunid获取userid
	 *
	 * @param associationId 接口唯一标识
	 * @return
	 */
	User getUserIdByAssociationId(String associationId);

	/**
	 *
	 *批量新增
	 * @param userList
	 * @return
	 */
	boolean saveBatchUser(@Param("userList") List<User> userList);
}
