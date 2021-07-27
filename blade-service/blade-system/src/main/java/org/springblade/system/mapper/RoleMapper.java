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
package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.system.entity.Role;
import org.springblade.system.vo.DictVO;
import org.springblade.system.vo.RoleVO;

import javax.websocket.server.PathParam;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mapper 接口
 *
 * @author Chill
 */
public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param role
	 * @return
	 */
	List<RoleVO> selectRolePage(IPage page, RoleVO role);

	/**
	 * 获取树形节点
	 *
	 * @param tenantId
	 * @param excludeRole
	 * @return
	 */
	List<RoleVO> tree(String tenantId, String excludeRole);

	/**
	 * 获取角色名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getRoleNames(Long[] ids);

	/**
	 * 获取角色名
	 *
	 * @param ids
	 * @return
	 */
	List<String> getRoleAliases(Long[] ids);

	/**
	 * 根据父级id查询
	 * @param idsList
	 * @return
	 */
	List<Role> selectRoleByParentId(@Param("idsList") Set<Long> idsList);

	/**
	 * 懒加载列表
	 *
	 * @param parentId
	 * @param param
	 * @return
	 */
	List<RoleVO> lazyList(Long parentId, Map<String, Object> param);

	/**
	 * 获取角色根据名称
	 * @param roleName
	 * @return
	 */
	Role getRoleByName(@Param("roleName") String roleName);
}
