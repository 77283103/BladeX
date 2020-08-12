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
import org.flowable.bpmn.model.*;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.CollectionUtil;
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
		/* 获取主流程开始节点 */
		FlowNode startEvent =(FlowNode) repositoryService.getBpmnModel(processDefinitionId).getMainProcess().getFlowElement(FlowEngineConstant.FLOW_START_EVENT);
		/* 获取第一个用户节点id，即起草人节点，开始节点之后有且仅有一个用户节点 */
		FlowNode firstNode =(FlowNode) startEvent.getOutgoingFlows().get(0).getTargetFlowElement();
		/* 获取第一个用户节点id，自动提交流程 */
		String firstId = firstNode.getId();
		/* 开启流程，给第一个用户节点赋空值，否则流程无法提交，此处无法设置办理人，后续会设置 */
		variables.put(firstId,"");
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinitionId, businessKey, variables);
		String processInstanceId = processInstance.getProcessInstanceId();
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		tasks.forEach(task -> {
			/* 第一个用户节点为起草人,自动完成任务 */
				if (firstId.equals(task.getTaskDefinitionKey())) {
					flowBusinessService.addComment(task.getId(), processInstanceId, AuthUtil.getUserId().toString(), CommentTypeEnum.TJ, null);
					/*给第一个用户节点设置办理人为当前登录人，即流程发起者*/
					taskService.setAssignee(task.getId(), AuthUtil.getUserId().toString());
					/*查询第一个用户节点后边的节点（可能是用户节点或网关）*/
					FlowNode secondNode = (FlowNode) firstNode.getOutgoingFlows().get(0).getTargetFlowElement();
					/*约定第一个用户节点后续的用户节点（如果连接网关则表示网关连接的所有节点）必须有且仅有一个办理人，否则抛出异常信息*/
					/*此时secondNode是用户节点*/
					if(secondNode instanceof UserTask){
						List<FlowUserResponse> candidateUsers = flowBusinessService.getCandidateUsers(secondNode, task.getId());
						if(CollectionUtil.isEmpty(candidateUsers)){
							/*记录日志未实现*/
							throw new FlowableException("未获取到下一办理人信息");
						}
						/* 通过全局变量设置办理人 */
						variables.put(secondNode.getId(),TaskUtil.getTaskUser(candidateUsers.get(0).getId()));
					}
					/*此时secondNode是网关节点*/
					if(secondNode instanceof Gateway){
						secondNode.getOutgoingFlows().forEach(sequenceFlow -> {
							/*网关连接的每一个用户节点，此处不判断流程将要提交给哪些节点，将所有节点候选人都设置到全局变量*/
							FlowNode secondUserNode = (FlowNode)sequenceFlow.getTargetFlowElement();
							List<FlowUserResponse> candidateUsers = flowBusinessService.getCandidateUsers(secondUserNode, task.getId());
							if(CollectionUtil.isEmpty(candidateUsers)){
								/*记录日志未实现*/
								throw new FlowableException("未获取到下一办理人信息");
							}
							/* 通过全局变量设置办理人 */
							variables.put(secondUserNode.getId(),TaskUtil.getTaskUser(candidateUsers.get(0).getId()));
						});
					}
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
