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
import org.springblade.system.entity.ApiScope;

import java.util.List;

/**
 *  Mapper 接口
 *
 * @author BladeX
 * @since 2019-06-23
 */
public interface ApiScopeMapper extends BaseMapper<ApiScope> {

	IPage<ApiScope> pageList(IPage<Object> page, @Param("apiScope") ApiScope apiScope);

	/**
	 * 根据菜单ID删除接口权限
	 * 逻辑删除
	 * @param menuIds
	 */
	void delApiScopeByMenuId(@Param("menuIds") List<Long> menuIds);
}
