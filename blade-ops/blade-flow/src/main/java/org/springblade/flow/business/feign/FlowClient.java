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
package org.springblade.flow.business.feign;

import lombok.AllArgsConstructor;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.flow.business.common.CommentTypeEnum;
import org.springblade.flow.business.service.impl.FlowBusinessServiceImpl;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.feign.IFlowClient;
import org.springblade.flow.core.utils.TaskUtil;
import org.springblade.flow.engine.constant.FlowEngineConstant;
import org.springblade.flow.engine.vo.FlowUserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流程远程调用实现类
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
public class FlowClient implements IFlowClient {

	private RuntimeService runtimeService;
	private IdentityService identityService;
	private RepositoryService repositoryService;
	private TaskService taskService;
	private FlowBusinessServiceImpl flowBusinessService;

	@Override
	@PostMapping(START_PROCESS_INSTANCE_BY_ID)
	public R<BladeFlow> startProcessInstanceById(String processDefinitionId, String businessKey, @RequestBody Map<String, Object> variables) {
		/* 设置流程启动用户 */
		identityService.setAuthenticatedUserId(TaskUtil.getTaskUser());
		/* 获取第一个节点，第一个节点的id约定固定为：【UserTask_first】 */
		FlowElement flowElement = repositoryService.getBpmnModel(processDefinitionId).getMainProcess().getFlowElement(FlowEngineConstant.FLOW_FIRST_ELEMENT);
		/* 开启流程 */
		variables.put(FlowEngineConstant.FLOW_START_ELEMENT,"");
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey, variables);
		String processInstanceId = processInstance.getProcessInstanceId();
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		tasks.forEach(task -> {
			/* 约定：发起者节点为 UserTask_first ,则自动完成任务 */
				if (FlowEngineConstant.FLOW_START_ELEMENT.equals(task.getTaskDefinitionKey())) {
					flowBusinessService.addComment(task.getId(), processInstanceId, AuthUtil.getUserId().toString(), CommentTypeEnum.TJ, null);
					taskService.setAssignee(task.getId(), AuthUtil.getUserId().toString());
					variables.put(FlowEngineConstant.FLOW_FIRST_ELEMENT, "taskUser_1123598821738675202");
					taskService.complete(task.getId(),variables);
				}
			});
		/* 组装流程通用类 */
		BladeFlow flow = new BladeFlow();
		flow.setProcessInstanceId(processInstance.getId());
		return R.data(flow);
	}

	@Override
	@PostMapping(START_PROCESS_INSTANCE_BY_KEY)
	public R<BladeFlow> startProcessInstanceByKey(String processDefinitionKey, String businessKey, @RequestBody Map<String, Object> variables) {
		/* 设置流程启动用户 */
		identityService.setAuthenticatedUserId(TaskUtil.getTaskUser());
		/* 开启流程 */
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
		/* 组装流程通用类 */
		BladeFlow flow = new BladeFlow();
		flow.setProcessInstanceId(processInstance.getId());
		return R.data(flow);
	}

	@Override
	@PostMapping(COMPLETE_TASK)
	public R completeTask(String taskId, String processInstanceId, String comment, @RequestBody Map<String, Object> variables) {
		/* 增加评论 */
		if (StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		/* 非空判断 */
		if (Func.isEmpty(variables)) {
			variables = Kv.create();
		}
		/* 完成任务 */
		taskService.complete(taskId, variables);
		return R.success("流程提交成功");
	}

	@Override
	@GetMapping(TASK_VARIABLE)
	public R<Object> taskVariable(String taskId, String variableName) {
		return R.data(taskService.getVariable(taskId, variableName));
	}

	@Override
	@GetMapping(TASK_VARIABLES)
	public R<Map<String, Object>> taskVariables(String taskId) {
		return R.data(taskService.getVariables(taskId));
	}

}
