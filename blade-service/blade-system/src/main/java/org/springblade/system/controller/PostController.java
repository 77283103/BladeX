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
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.Post;
import org.springblade.system.service.IPostService;
import org.springblade.system.vo.PostVO;
import org.springblade.system.wrapper.PostWrapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springblade.core.cache.constant.CacheConstant.PARAM_CACHE;

/**
 * 岗位表 控制器
 *
 * @author 田爱华
 * @date 2020-8-25 13:30:43
 */
@RestController
@AllArgsConstructor
@RequestMapping("/post")
@Api(value = "岗位表", tags = "岗位表接口")
public class PostController extends BladeController {

	private IPostService postService;

	/**
	 * 岗位详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入post")
	public R<PostVO> detail(String id) {
		Post detail = postService.getById(id);
		return R.data(PostWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 岗位表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入post")
	public R<IPage<PostVO>> list(Post post, Query query) {
		IPage<Post> pages = postService.pageList(Condition.getPage(query), post);
		return R.data(PostWrapper.build().pageVO(pages));
	}

	/**
	 * 自定义分页 岗位表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入post")
	public R<IPage<PostVO>> page(PostVO post, Query query) {
		IPage<PostVO> pages = postService.selectPostPage(Condition.getPage(query), post);
		return R.data(pages);
	}

	/**
	 * 新增或修改 岗位表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增或修改", notes = "传入post")
	public R submit(@Valid @RequestBody Post post) {
		try {
			postService.saveOrUpdate(post);
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
	 * 删除 岗位表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		try {
			postService.deleteLogic(Func.toLongList(ids));
		}catch (Exception e){
			e.printStackTrace();
			return R.status(false);
		}
		return R.status(true);
	}

	/**
	 * 下拉数据源
	 */
	@GetMapping("/select")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "下拉数据源", notes = "传入post")
	public R<List<Post>> select(String tenantId, BladeUser bladeUser) {
		List<Post> list = postService.list(Wrappers.<Post>query().lambda().eq(Post::getTenantId, Func.toStrWithEmpty(tenantId, bladeUser.getTenantId())));
		return R.data(list);
	}

}
