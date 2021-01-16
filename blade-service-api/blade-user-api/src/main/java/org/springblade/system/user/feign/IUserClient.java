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
package org.springblade.system.user.feign;


import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * User Feign接口类
 *
 * @author Chill
 */
@FeignClient(
	value = AppConstant.APPLICATION_USER_NAME
)
public interface IUserClient {

	String API_PREFIX = "/client";
	String USER_INFO = API_PREFIX + "/user-info";
	String USER_INFO_BY_ID = API_PREFIX + "/user-info-by-id";
	String USER_INFO_BY_USER_NAME = API_PREFIX + "/user-info-by-userName";
	String USER_INFO_BY_ACCOUNT = API_PREFIX + "/user-info-by-account";
	String SAVE_USER = API_PREFIX + "/save-user";
	String REMOVE_USER = API_PREFIX + "/remove-user";
	String USER_INFO_BY_DEPTID_AND_POSTID = API_PREFIX + "/user-info-by-deptAndPost";
	String USER_INFO_BY_USERID_ARRAY = API_PREFIX + "/user-info-by-userIdArray";
	String USER_INFO_BY_BENCH_MINISTER = API_PREFIX + "/user-Info-by-bench-minister";
	String SAVE_USER_BACH_API=API_PREFIX +"/save-user-api";
	String SAVE_USER_DEPART_BACH_API=API_PREFIX +"/save-user-depart-bach-api";
	String GET_ID_BY_LUNID = API_PREFIX + "/get-id-by-lunid";
	/**
	 * 获取用户信息
	 *
	 * @param userId 用户id
	 * @return
	 */
	@GetMapping(USER_INFO_BY_ID)
	R<User> userInfoById(@RequestParam("userId") Long userId);


	/**
	 * 根据用户名获取用户信息
	 *
	 * @param userName 用户userName
	 * @return
	 */
	@GetMapping(USER_INFO_BY_USER_NAME)
	R<User> userInfoByUserName(@RequestParam("userId") String userName);


	/**
	 * 根据账号获取用户信息
	 *
	 * @param tenantId 租户id
	 * @param account  账号
	 * @return
	 */
	@GetMapping(USER_INFO_BY_ACCOUNT)
	R<User> userByAccount(@RequestParam("tenantId") String tenantId, @RequestParam("account") String account);

	/**
	 * 获取用户信息
	 *
	 * @param tenantId 租户ID
	 * @param account  账号
	 * @return
	 */
	@GetMapping(USER_INFO)
	R<UserInfo> userInfo(@RequestParam("tenantId") String tenantId, @RequestParam("account") String account);

	/**
	 * 新建用户
	 *
	 * @param user 用户实体
	 * @return
	 */
	@PostMapping(SAVE_USER)
	R<Boolean> saveUser(@RequestBody UserDTO user);

	/**
	 * 同步用户
	 *
	 * @param user API
	 * @return
	 */
	@PostMapping(SAVE_USER_BACH_API)
	R<Boolean> saveOrUpdateBatch(@RequestBody List<User> user);

	/**
	 * 同步用户
	 *
	 * @param userDepart API
	 * @return
	 */
	@PostMapping(SAVE_USER_DEPART_BACH_API)
	R<Boolean> saveOrUpdateBatchDepart(@RequestBody List<UserDepartEntity> userDepart);
	/**
	 * 删除用户
	 *
	 * @param tenantIds 租户id集合
	 * @return
	 */
	@PostMapping(REMOVE_USER)
	R<Boolean> removeUser(@RequestParam("tenantIds") String tenantIds);

	/**
	 * 根据机构和岗位获取用户List
	 *
	 * @param deptId 机构id
	 * @param postId 岗位id
	 * @return
	 */
	@GetMapping(USER_INFO_BY_DEPTID_AND_POSTID)
	R<List<User>> userInfoByDeptAndPost(@RequestParam("deptId") String deptId, @RequestParam("postId") String postId);

	/**
	 * 根据userIds返回userList
	 */
	@GetMapping(USER_INFO_BY_USERID_ARRAY)
	R<List<User>> userInfoByUserIds(@RequestParam("userIds") String userIds);

	/**
	 * 获取基准人的部门部长
	 */
	@GetMapping(USER_INFO_BY_BENCH_MINISTER)
	R<List<User>> userInfoByBenchMinister(@RequestParam("benchUserId") String benchUserId);

	/**
	 * 根据lunid获取Id（接口用）
	 * @param associationId
	 * @return
	 */
	@GetMapping(GET_ID_BY_LUNID)
	R<Long> getUserIdByAssociationId(@RequestParam("associationId") String associationId);
}
