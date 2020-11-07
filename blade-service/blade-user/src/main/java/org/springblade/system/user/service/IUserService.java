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
package org.springblade.system.user.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.excel.UserExcel;
import org.springblade.system.user.vo.SelectUserVO;
import org.springblade.system.user.vo.UserVO;

import java.util.List;

/**
 * 服务类
 *
 * @author Chill
 */
public interface IUserService extends BaseService<User> {

	/**
	 * 新增用户
	 *
	 * @param user
	 * @return
	 */
	boolean submit(UserDTO user);

	/**
	 * 修改用户
	 *
	 * @param user
	 * @return
	 */
	boolean updateUser(UserDTO user);

	/**
	 * 修改用户基本信息
	 *
	 * @param user
	 * @return
	 */
	boolean updateUserInfo(User user);

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param user
	 * @param deptId
	 * @param tenantId
	 * @return
	 */
	IPage<UserVO> userPage(IPage<User> page, User user, Long deptId, String tenantId);

	/**
	 * 用户信息
	 *
	 * @param tenantId
	 * @param account
	 * @return
	 */
	UserInfo userInfo(String tenantId, String account);

	/**
	 * 根据账号获取用户
	 *
	 * @param tenantId
	 * @param account
	 * @return
	 */
	User userByAccount(String tenantId, String account);

	/**
	 * 根据账号获取用户
	 *
	 * @param userName
	 * @return
	 */
	User getByUserName(String userName);

	/**
	 * 给用户设置角色
	 *
	 * @param userIds
	 * @param roleIds
	 * @return
	 */
	boolean grant(String userIds, String roleIds);

	/**
	 * 初始化密码
	 *
	 * @param userIds
	 * @return
	 */
	boolean resetPassword(String userIds);

	/**
	 * 修改密码
	 *
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @param newPassword1
	 * @return
	 */
	boolean updatePassword(Long userId, String oldPassword, String newPassword, String newPassword1);

	/**
	 * 删除用户
	 *
	 * @param userIds
	 * @return
	 */
	boolean removeUser(String userIds);

	/**
	 * 导入用户数据
	 * @param data
	 * @param isCovered
	 */
	void importUser(List<UserExcel> data, Boolean isCovered);

	/**
	 * 获取导出用户数据
	 * @param user
	 * @param deptId
	 * @param tenantId
	 * @return
	 */
	List<UserExcel> exportUser(User user, Long deptId, String tenantId);

	/**
	 * 停用/启用
	 * @param user
	 * @return
	 */
	boolean changeStatus(UserDTO user);

	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	UserVO selectById(Long id);

	/**
	 * 修改启用状态
	 * @param id
	 * @param isEnable
	 * @return
	 */
	boolean updateStatus(String id, Integer isEnable);

	/**
	 * 用于页面中选择用户
	 * 分页列表
	 * @param page
	 * @param user
	 * @param deptId
	 * @param tenantId
	 * @return
	 */
	IPage<SelectUserVO> selectUserPage(IPage<User> page, User user, Long deptId, String tenantId);

	/**
	 * 根据机构id和岗位id获取候选人List
	 * @param deptId
	 * @param postId
	 * @return
	 */
	List<User> userInfoByDeptIdAndPostId(String deptId, String postId);

	/**
	 * 根据userid数组返回候选人list
	 * @param userIds
	 * @return
	 */
	List<User> userInfoByUserIds(String userIds);

	/**
	 * 基准人部门部长
	 * @param benchUserId
	 * @return
	 */
	List<User> userInfoByBenchMinister(String benchUserId);
}
