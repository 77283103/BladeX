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
package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.system.entity.ApiScope;
import org.springblade.system.mapper.ApiScopeMapper;
import org.springblade.system.service.IApiScopeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2019-06-23
 */
@Service
public class ApiScopeServiceImpl extends BaseServiceImpl<ApiScopeMapper, ApiScope> implements IApiScopeService {

	@Override
	public IPage<ApiScope> pageList(IPage<Object> page, ApiScope apiScope) {
		return baseMapper.pageList(page, apiScope);
	}

	@Override
	public void delApiScopeByMenuId(List<Long> menuIds) {
		baseMapper.delApiScopeByMenuId(menuIds);
	}
}
