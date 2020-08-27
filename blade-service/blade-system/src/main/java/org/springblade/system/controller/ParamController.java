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
package org.springblade.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.constant.RoleConstant;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Param;
import org.springblade.system.service.IParamService;
import org.springblade.system.vo.ParamVO;
import org.springblade.system.wrapper.ParamWrapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

import static org.springblade.core.cache.constant.CacheConstant.PARAM_CACHE;

/**
 * 控制器
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("/param")
@Api(value = "参数管理", tags = "接口")
public class ParamController extends BladeController {

	private IParamService paramService;

	/**
	 * 参数详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入param")
	public R<Param> detail(String id) {
		Param detail = paramService.getById(id);
		return R.data(ParamWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 代码自定义代号
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入param")
	public R<IPage<ParamVO>> list(Param param, Query query) {
		IPage<Param> pages = paramService.pageList(Condition.getPage(query), param);
		return R.data(ParamWrapper.build().pageVO(pages));
	}

	/**
	 * 新增或修改参数信息
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增或修改", notes = "传入param")
	@PreAuth(RoleConstant.HAS_ROLE_ADMINISTRATOR)
	public R submit(@Valid @RequestBody Param param) {
		try {
			paramService.saveOrUpdate(param);
			CacheUtil.clear(PARAM_CACHE);
		/*数据库设置唯一约束，捕获到该异常后返回编号冲突*/
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			return R.fail("编号冲突");
		} catch (Exception e) {
			e.printStackTrace();
			return R.status(false);
		}
		return R.status(true);
	}

	/**
	 * 删除参数信息
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth(RoleConstant.HAS_ROLE_ADMINISTRATOR)
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		if(paramService.deleteLogic(Func.toLongList(ids))){
			CacheUtil.clear(PARAM_CACHE);
			return R.status(true);
		}
		return R.status(false);
	}
}
