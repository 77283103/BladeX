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
package org.springblade.system.user.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springblade.common.constant.CommonConstant;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.secure.utils.SecureUtil;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.*;
import org.springblade.system.cache.ParamCache;
import org.springblade.system.cache.SysCache;
import org.springblade.system.dto.UserDepartDTO;
import org.springblade.system.entity.Tenant;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.dto.UserDTO;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.entity.UserInfo;
import org.springblade.system.user.excel.UserExcel;
import org.springblade.system.user.mapper.UserMapper;
import org.springblade.system.user.service.IUserDepartService;
import org.springblade.system.user.service.IUserService;
import org.springblade.system.user.vo.SelectUserVO;
import org.springblade.system.user.vo.UserVO;
import org.springblade.system.user.wrapper.UserDTOWrapper;
import org.springblade.system.user.wrapper.UserWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springblade.common.constant.CommonConstant.DEFAULT_PARAM_PASSWORD;
import static org.springblade.common.constant.CommonConstant.DEFAULT_TENANT_ID;

/**
 * 服务实现类
 *
 * @author Chill
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

	private IUserDepartService userDepartService;
	private UserMapper userMapper;
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean submit(UserDTO user) {
		if (StringUtil.isBlank(user.getTenantId())) {
			user.setTenantId(BladeConstant.ADMIN_TENANT_ID);
		}
		String tenantId = user.getTenantId();
		Tenant tenant = SysCache.getTenant(tenantId);
		if (Func.isNotEmpty(tenant)) {
			Integer accountNumber = tenant.getAccountNumber();
			Integer tenantCount = baseMapper.selectCount(Wrappers.<User>query().lambda().eq(User::getTenantId, tenantId));
			if (accountNumber != null && accountNumber > 0 && accountNumber < tenantCount) {
				throw new ServiceException("当前租户已到最大账号额度!");
			}
		}
		if (Func.isNotEmpty(user.getPassword())) {
			user.setPassword(DigestUtil.encrypt(user.getPassword()));
		} else {
			user.setPassword(DigestUtil.encrypt("123456"));
		}
		Integer userCount = baseMapper.selectCount(Wrappers.<User>query().lambda().eq(User::getTenantId, tenantId).eq(User::getAccount, user.getAccount()));
		if (userCount > 0 && Func.isEmpty(user.getId())) {
			throw new ServiceException(StringUtil.format("当前用户 [{}] 已存在!", user.getAccount()));
		}
		return save(user) && submitUserDepart(user);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean updateUser(UserDTO user) {
		String tenantId = user.getTenantId();
		Integer userCount = baseMapper.selectCount(
			Wrappers.<User>query().lambda()
				.eq(User::getTenantId, tenantId)
				.eq(User::getAccount, user.getAccount())
				.notIn(User::getId, user.getId())
		);
		if (userCount > 0) {
			throw new ServiceException(StringUtil.format("当前用户 [{}] 已存在!", user.getAccount()));
		}
		return updateUserInfo(user) && submitUserDepart(user);
	}

	@Override
	public boolean updateUserInfo(User user) {
		user.setPassword(null);
		return updateById(user);
	}

	/**
	 * 更新人员身份信息
	 *
	 * @param user
	 * @return
	 */
	private boolean submitUserDepart(UserDTO user) {
		List<UserDepartDTO> userDepartList = user.getUserDepartList();
		if (CollectionUtil.isNotEmpty(userDepartList)) {
			userDepartService.delByUserId(user.getId());
			List<UserDepartEntity> collect = userDepartList.stream().map(depart -> {
				depart.setUserId(user.getId());
				UserDepartEntity userDepartEntity = new UserDepartEntity();
				BeanUtil.copy(depart, userDepartEntity);
				return userDepartEntity;
			}).collect(Collectors.toList());
			return userDepartService.saveBatch(collect);
		} else {
			throw new ServiceException("至少填写一条身份信息");
		}
	}

	@Override
	public IPage<UserVO> userPage(IPage<User> page, User user, Long deptId, String tenantId) {
		List<Long> deptIdList = SysCache.getDeptChildIds(deptId);
		List<User> userList = baseMapper.userPage(page, user, deptIdList, tenantId);
		page.setRecords(userList);
		return UserWrapper.build().pageVO(page);
	}

	@Override
	public User userByAccount(String tenantId, String account) {
		return baseMapper.selectOne(Wrappers.<User>query().lambda().eq(User::getTenantId, tenantId).eq(User::getAccount, account).eq(User::getIsDeleted, BladeConstant.DB_NOT_DELETED));
	}

	@Override
	public User userByCode(String tenantId, String code) {
		return baseMapper.selectOne(Wrappers.<User>query().lambda().eq(User::getTenantId, tenantId).eq(User::getCode, code).eq(User::getIsDeleted, BladeConstant.DB_NOT_DELETED));
	}

	@Override
	public User getByUserName(String userName) {
		User user = baseMapper.getByUserName(userName);
		return user;
	}

	@Override
	public UserInfo userInfo(String tenantId, String account) {
		UserInfo userInfo = new UserInfo();
		UserDTO user = baseMapper.getUser(tenantId, account);
		userInfo.setUser(user);
		return userInfo;
	}

	@Override
	public boolean grant(String userIds, String roleIds) {
		UserDTO user = new UserDTO();
		user.setRoleId(roleIds);
		return this.update(user, Wrappers.<User>update().lambda().in(User::getId, Func.toLongList(userIds)));
	}

	@Override
	public boolean resetPassword(String userIds) {
		User user = new User();
		user.setPassword(DigestUtil.encrypt(CommonConstant.DEFAULT_PASSWORD));
		user.setUpdateTime(DateUtil.now());
		return this.update(user, Wrappers.<User>update().lambda().in(User::getId, Func.toLongList(userIds)));
	}

	@Override
	public boolean updatePassword(Long userId, String oldPassword, String newPassword, String newPassword1) {
		User user = getById(userId);
		if (!newPassword.equals(newPassword1)) {
			throw new ServiceException("请输入正确的确认密码!");
		}
		if (!user.getPassword().equals(DigestUtil.encrypt(oldPassword))) {
			throw new ServiceException("原密码不正确!");
		}
		return this.update(Wrappers.<User>update().lambda().set(User::getPassword, DigestUtil.encrypt(newPassword)).eq(User::getId, userId));
	}

	@Override
	public boolean removeUser(String userIds) {
		if (Func.contains(Func.toLongArray(userIds), SecureUtil.getUserId())) {
			throw new ServiceException("不能删除本账号!");
		}
		return deleteLogic(Func.toLongList(userIds));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void importUser(List<UserExcel> data, Boolean isCovered) {
		data.forEach(userExcel -> {
			UserDTO user = Objects.requireNonNull(BeanUtil.copy(userExcel, UserDTO.class));
			// 初始化身份信息
			List<UserDepartDTO> userDepartDTOList = Lists.newArrayList();
			UserDepartDTO userDepartDTO = new UserDepartDTO();
			String deptIdStr = SysCache.getDeptIds(DEFAULT_TENANT_ID, userExcel.getDeptName());
			String postIdStr = SysCache.getPostIds(DEFAULT_TENANT_ID, userExcel.getPostName());
			String roleIdStr = SysCache.getRoleIds(DEFAULT_TENANT_ID, userExcel.getRoleName());
			userDepartDTO.setDeptId(Func.toLong(deptIdStr));
			userDepartDTO.setPostId(Func.toLong(postIdStr));
			userDepartDTO.setRoleId(Func.toLong(roleIdStr));
			userDepartDTOList.add(userDepartDTO);
			//
			user.setUserDepartList(userDepartDTOList);
			// 覆盖数据
			if (isCovered) {
				// 查询用户是否存在
				User oldUser = UserCache.getUser(DEFAULT_TENANT_ID, userExcel.getAccount());
				if (oldUser != null && oldUser.getId() != null) {
					user.setId(oldUser.getId());
					this.updateUser(user);
					return;
				}
			}
			// 获取默认密码配置
			String initPassword = ParamCache.getValue(DEFAULT_PARAM_PASSWORD);
			String initTenantId = ParamCache.getValue(DEFAULT_TENANT_ID);
			user.setPassword(DigestUtil.encrypt(initPassword));
			user.setTenantId(initTenantId);
			this.submit(user);
		});
	}

	@Override
	public List<UserExcel> exportUser(User user, Long deptId, String tenantId) {
		List<Long> deptIdList = SysCache.getDeptChildIds(deptId);
		List<UserExcel> userList = baseMapper.exportUser(user, deptIdList, tenantId);
		return userList;
	}

	@Override
	public boolean changeStatus(UserDTO user) {
		return save(user);
	}

	@Override
	public UserVO selectById(Long id) {
		UserDTO userDTO = baseMapper.selectUserDTOById(id);
		return UserDTOWrapper.build().toVO(userDTO);
	}

	@Override
	public boolean updateStatus(String id, Integer isEnable) {
		User user = baseMapper.selectById(id);
		user.setIsEnable(isEnable);
		baseMapper.updateById(user);
		return true;
	}

	@Override
	public IPage<SelectUserVO> selectUserPage(IPage<User> page, User user, Long deptId, String tenantId) {
		List<Long> deptIdList = SysCache.getDeptChildIds(deptId);
		List<User> userList = baseMapper.userPage(page, user, deptIdList, tenantId);
		page.setRecords(userList);
		return UserWrapper.build().userToSelectUserpageVO(page);
	}

	@Override
	public List<User> userInfoByDeptIdAndPostId(String deptId, String postId) {
		return baseMapper.findByDeptAndPost(deptId, postId);
	}

	@Override
	public List<User> userInfoByUserIds(String userIds) {
		return baseMapper.selectBatchIds(Arrays.asList(userIds.split(",")));
	}

	@Override
	public List<User> userInfoByBenchMinister(String benchUserId) {
		return baseMapper.findByBenchMinister(benchUserId);
	}

	@Override
	public Long getUserIdByAssociationId(String associationId) {
		User user = userMapper.getUserIdByAssociationId(associationId);
		/*调用前已经将用户进行全量同步*/
		if (Func.isNotEmpty(user)) {
			return user.getId();
		}
		return 0L;
	}

	@Override
	public boolean saveBatchUser(List<User> userList) {
		return userMapper.saveBatchUser(userList);
	}

	@Override
	public List<User> getByRealName(String realName) {
		return userMapper.getByRealName(realName);
	}
}
