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
package org.springblade.flow.business.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.flowable.engine.TaskService;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.flow.business.service.FlowBusinessService;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.entity.FlowNodeVo;
import org.springblade.flow.core.utils.TaskUtil;
import org.springblade.flow.engine.entity.FlowProcess;
import org.springblade.flow.engine.service.FlowEngineService;
import org.springblade.flow.engine.vo.FlowNodeResponse;
import org.springblade.flow.engine.vo.TaskRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程事务通用接口
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
@RequestMapping("work")
@Api(value = "流程事务通用接口", tags = "流程事务通用接口")
public class WorkController {

	private TaskService taskService;
	private FlowEngineService flowEngineService;
	private FlowBusinessService flowBusinessService;

	/**
	 * 发起事务列表页
	 */
	@GetMapping("start-list")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "发起事务列表页", notes = "传入流程类型")
	public R<IPage<FlowProcess>> startList(@ApiParam("流程类型") String category, Query query, @RequestParam(required = false, defaultValue = "1") Integer mode) {
		IPage<FlowProcess> pages = flowEngineService.selectProcessPage(Condition.getPage(query), category, mode);
		return R.data(pages);
	}

	/**
	 * 待签事务列表页
	 */
	@GetMapping("claim-list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "待签事务列表页", notes = "传入流程信息")
	public R<IPage<BladeFlow>> claimList(@ApiParam("流程信息") BladeFlow bladeFlow, Query query) {
		IPage<BladeFlow> pages = flowBusinessService.selectClaimPage(Condition.getPage(query), bladeFlow);
		return R.data(pages);
	}

	/**
	 * 待办事务列表页
	 */
	@GetMapping("todo-list")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "待办事务列表页", notes = "传入流程信息")
	public R<IPage<BladeFlow>> todoList(@ApiParam("流程信息") BladeFlow bladeFlow, Query query) {
		IPage<BladeFlow> pages = flowBusinessService.selectTodoPage(Condition.getPage(query), bladeFlow);
		return R.data(pages);
	}

	/**
	 * 已发事务列表页
	 */
	@GetMapping("send-list")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "已发事务列表页", notes = "传入流程信息")
	public R<IPage<BladeFlow>> sendList(@ApiParam("流程信息") BladeFlow bladeFlow, Query query) {
		IPage<BladeFlow> pages = flowBusinessService.selectSendPage(Condition.getPage(query), bladeFlow);
		return R.data(pages);
	}

	/**
	 * 办结事务列表页
	 */
	@GetMapping("done-list")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "办结事务列表页", notes = "传入流程信息")
	public R<IPage<BladeFlow>> doneList(@ApiParam("流程信息") BladeFlow bladeFlow, Query query) {
		IPage<BladeFlow> pages = flowBusinessService.selectDonePage(Condition.getPage(query), bladeFlow);
		return R.data(pages);
	}

	/**
	 * 签收事务
	 *
	 * @param taskId 任务id
	 */
	@PostMapping("claim-task")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "签收事务", notes = "传入流程信息")
	public R claimTask(@ApiParam("任务id") String taskId) {
		taskService.claim(taskId, TaskUtil.getTaskUser());
		return R.success("签收事务成功");
	}

	/**
	 * 点击完成按钮查询下一节点和办理人信息返回前台
	 */
	@GetMapping("complete-before")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "提交前的处理", notes = "传入流程信息")
	public R<List<FlowNodeResponse>> completeTempResult(@ApiParam("任务信息") String taskId){
		return R.data(flowBusinessService.completeTempResult(taskId));
	}

	/**
	 * 完成任务
	 *
	 * @param flowList
	 */
	@PostMapping("complete-task")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "完成任务", notes = "传入流程信息")
	public R completeTask(@ApiParam("任务信息") @RequestBody List<FlowNodeResponse> flowList) {
		return R.status(flowBusinessService.completeTask(flowList));
	}

	/**
	 * 删除任务
	 *
	 * @param taskId 任务id
	 * @param reason 删除原因
	 */
	@PostMapping("delete-task")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "删除任务", notes = "传入流程信息")
	public R deleteTask(@ApiParam("任务id") String taskId, @ApiParam("删除原因") String reason) {
		taskService.deleteTask(taskId, reason);
		return R.success("删除任务成功");
	}

	/**
	 * 查询可以退回的节点
	 *
	 * @param taskId 任务id
	 */
	@GetMapping("backSearch-task")
	@ApiOperationSupport(order = 9)
	@ApiOperation(value = "退回节点查询", notes = "传入流程信息")
	public R<List<FlowNodeResponse>> searchBackNodes(@ApiParam("任务id") String taskId) {
		return R.data(flowBusinessService.backNodes(taskId));
	}

	/**
	 * 退回操作
	 *
	 * @param taskRequest
	 * @return
	 */
	@PutMapping(value = "/back-task")
	@ApiOperationSupport(order = 10)
	@ApiOperation(value = "退回操作", notes = "传入退回信息")
	public R back(@RequestBody TaskRequest taskRequest) {
		return R.status(flowBusinessService.backTask(taskRequest));
	}

	/**
	 * 转办任务
	 */
	@PostMapping("assign-task")
	@ApiOperationSupport(order = 11)
	@ApiOperation(value = "转办操作", notes = "传入流程信息")
	public R assign(@RequestBody BladeFlow flow) {
		flowBusinessService.assignTask(flow);
		return R.status(true);
	}
	/**
	 * 委派任务
	 */
	@PutMapping(value = "delegate-task")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "委派操作", notes = "传入流程信息")
	public R delegate(@RequestBody BladeFlow flow) {
		flowBusinessService.delegateTask(flow);
		return R.status(true);
	}
	/**
	 * 发起人终止流程
	 */
	@PostMapping("cancel-task")
	@ApiOperationSupport(order = 11)
	@ApiOperation(value = "发起人终止流程", notes = "传入流程信息")
	public R cancel(@RequestBody BladeFlow flow) {
		flowBusinessService.cancelTask(flow);
		return R.status(true);
	}

	/**
	 * 查看退回节点
	 */
	@PostMapping(value = "takeItBackLook-task")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "查看退回节点操作", notes = "传入流程信息")
	public R<List<FlowNodeVo>> takeItBackLook(@RequestBody BladeFlow flow) {
		return R.data(flowBusinessService.takeItBackTaskLook(flow));
	}


	/**
	 * 退回任务
	 */
	@PostMapping(value = "takeItBack-task")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "退回操作", notes = "传入流程信息")
	public R takeItBack(@RequestBody BladeFlow flow) {
		flowBusinessService.takeItBackTask(flow);
		return R.status(true);
	}


	/**
	 * 拿回任务
	 */
	@PostMapping(value = "takeBack-task")
	@ApiOperationSupport(order = 14)
	@ApiOperation(value = "退回操作", notes = "传入流程信息")
	public R takeBack(@RequestBody BladeFlow flow) {
		flowBusinessService.takeBackTask(flow);
		return R.success("拿回成功");
	}



}
