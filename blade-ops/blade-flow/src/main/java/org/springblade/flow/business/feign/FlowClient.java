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
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.CollectionUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.flow.business.common.CommentTypeEnum;
import org.springblade.flow.business.service.impl.FlowBusinessServiceImpl;
import org.springblade.flow.business.service.impl.ProcessServiceImpl;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.entity.ProcessEntity;
import org.springblade.flow.core.feign.IFlowClient;
import org.springblade.flow.core.utils.TaskUtil;
import org.springblade.flow.core.vo.FlowNodeRequest;
import org.springblade.flow.core.vo.FlowUserRequest;
import org.springblade.system.feign.ISysClient;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 流程远程调用实现类
 *
 * @author 田爱华
 * @date 2020-8-26
 */
@RestController
@AllArgsConstructor
public class FlowClient implements IFlowClient {

	private RuntimeService runtimeService;
	private IdentityService identityService;
	private RepositoryService repositoryService;
	private TaskService taskService;
	private FlowBusinessServiceImpl flowBusinessService;
	private ProcessServiceImpl processService;
	private ISysClient sysClient;

	@Override
	@PostMapping(START_PROCESS_INSTANCE_BY_ID)
	public R<BladeFlow> startProcessInstanceById(String processDefinitionId, String businessKey, @RequestBody Map<String, Object> variables) {
		/* 设置流程启动用户 */
		identityService.setAuthenticatedUserId(TaskUtil.getTaskUser());
		/* 获取主流程开始节点 */
		FlowNode startEvent = (FlowNode) repositoryService.getBpmnModel(processDefinitionId)
			.getMainProcess().getFlowElements()
			.stream()
			.filter(flowElement -> flowElement instanceof StartEvent)
			.limit(1)
			.collect(Collectors.toList()).get(0);
		/* 获取第一个用户节点id，即起草人节点，开始节点之后有且仅有一个用户节点 */
		FlowNode firstNode = (FlowNode) startEvent.getOutgoingFlows().get(0).getTargetFlowElement();
		/* 获取第一个用户节点id，自动提交流程 */
		String firstId = firstNode.getId();
		/* 开启流程，给第一个用户节点赋空值，否则流程无法提交，此处无法设置办理人，后续会设置 */
		variables.put(firstId, "");
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
				if (secondNode instanceof UserTask) {
					List<FlowUserRequest> candidateUsers = flowBusinessService.getCandidateUsers(secondNode, task.getId());
					if (CollectionUtil.isEmpty(candidateUsers)) {
						/*记录日志未实现*/
						throw new FlowableException("未获取到下一办理人信息");
					}
					/* 通过全局变量设置办理人 */
					variables.put(secondNode.getId(), TaskUtil.getTaskUser(candidateUsers.get(0).getId()));
				}
				/*此时secondNode是网关节点*/
				if (secondNode instanceof Gateway) {
					secondNode.getOutgoingFlows().forEach(sequenceFlow -> {
						/*网关连接的每一个用户节点，此处不判断流程将要提交给哪些节点，将所有节点候选人都设置到全局变量*/
						FlowNode secondUserNode = (FlowNode) sequenceFlow.getTargetFlowElement();
						List<FlowUserRequest> candidateUsers = flowBusinessService.getCandidateUsers(secondUserNode, task.getId());
						if (CollectionUtil.isEmpty(candidateUsers)) {
							/*记录日志未实现*/
							throw new FlowableException("未获取到下一办理人信息");
						}
						/* 通过全局变量设置办理人 */
						variables.put(secondUserNode.getId(), TaskUtil.getTaskUser(candidateUsers.get(0).getId()));
					});
				}
				taskService.complete(task.getId(), variables);
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

	@Override
	@PostMapping(START_PROCESS_BEFORE)
	public R<List<FlowNodeRequest>> startProcessBefore(Map<String,Object> maps,String businessType) throws ScriptException {
		/* 当前登录人的部门id */
		String currentUserDeptId = AuthUtil.getDeptId();
		/*根据业务类型获取满足条件的流程xml*/
		List<ProcessEntity> processEntityList =	processService.getProcessByBusinessType(businessType);
		List<FlowNodeRequest> flowNodeRequests = new ArrayList<>();
		if (Func.isNotEmpty(processEntityList)) {
			/*获取当前登录人部门的祖籍列表，拼接当前部门id*/
			String deptAncestors = sysClient.getDept(Long.parseLong(currentUserDeptId)).getData().getAncestors()+","+currentUserDeptId;
			List<String> deptIds = Arrays.asList(deptAncestors.split(","));
			/*用于存储每个候选流程的使用范围id，用于和当前登录人部门去匹配*/
			List<String> processDefineIds = new ArrayList<>();
			/*取出每一个流程使用范围id*/
			processEntityList.forEach(processEntity -> processDefineIds.add(processEntity.getDeptRange().toString()));
			/*先倒序祖籍列表，从最底级开始依次排查是否包含在流程定义的List中，取出第一个匹配的，即最小级的部门id*/
			List<String> deptIdResult = deptIds.stream().sorted(Comparator.reverseOrder()).filter(processDefineIds::contains).limit(1).collect(Collectors.toList());
			/*过滤流程定义List，取出与最低小级部门匹配的流程定义信息，即 即将启动的流程*/
			List<ProcessEntity> processEntityResult = processEntityList.stream().filter(processEntity -> Func.equals(deptIdResult.get(0), processEntity.getDeptRange().toString())).collect(Collectors.toList());
			/*计算表达式*/
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("js");
			/*过滤流程启动条件*/
			processEntityResult = processEntityResult.stream().filter(processEntity -> {
				String condition = processEntity.getStartCondition();
				boolean flag = false;
				for (Map.Entry<String, Object> map : maps.entrySet()) {
					if(condition.contains(map.getKey())) {
						condition = condition.replace(map.getKey(), "'"+map.getValue().toString()+"'");
					}
				}
				try {
					flag =(boolean) engine.eval(condition);
				} catch (javax.script.ScriptException e) {
					e.printStackTrace();
				}
				return flag;
			}).collect(Collectors.toList());
			if (Func.isNotEmpty(processEntityResult)) {
				ProcessEntity processEntity = processEntityResult.get(0);
				/*获取主流程*/
				Process mainProcess = repositoryService.getBpmnModel(processEntityResult.get(0).getProcessDefinitionId()).getMainProcess();
				/*定义全局变量*/
				Kv variables = Kv.create();
				/*获取流程全局变量参数，逗号分隔*/
				String params = processEntity.getParams();
				if(Func.isNotEmpty(params)) {
					/*通过流程全局参数名获取value给全局变量赋值*/
					Arrays.asList(params.split(",")).forEach(param -> variables.put(param, maps.get(param)));
				}
				/* 获取主流程开始节点 */
				FlowNode startEvent = (FlowNode) mainProcess.getFlowElements().stream().filter(flowElement -> flowElement instanceof StartEvent).limit(1).collect(Collectors.toList()).get(0);
				/* 获取第一个用户节点id，即起草人节点，开始节点之后有且仅有一个用户节点 */
				FlowNode firstNode = (FlowNode) startEvent.getOutgoingFlows().get(0).getTargetFlowElement();
				/*获取承办人节点后边的节点（可能是用户节点或网关）*/
				FlowNode secondNode = (FlowNode) firstNode.getOutgoingFlows().get(0).getTargetFlowElement();
				if (secondNode instanceof UserTask) {
					List<FlowUserRequest> candidateUsers = flowBusinessService.getCandidateUsers(secondNode);
					FlowNodeRequest flowNodeRequest = FlowNodeRequest.builder()
						.userResponseList(candidateUsers)
						.id(secondNode.getId())
						.processDefinitionId(processEntity.getProcessDefinitionId())
						.name(secondNode.getName())
						.variables(variables)
						.enableChooseNode(false)
						.end(false)
						.build();
					flowNodeRequests.add(flowNodeRequest);
				}
				/*如果secondNode是网关节点*/
				if (secondNode instanceof Gateway) {
					// 排他网关
					if (secondNode instanceof ExclusiveGateway) {
						/* 遍历所有连线，确定流程即将进入的连线 */
						for (SequenceFlow outLine : secondNode.getOutgoingFlows()) {
							/* 获取连线的条件 */
							String conditionExpression = outLine.getConditionExpression();
							/* 去掉$、{、}， */
							conditionExpression = conditionExpression.replace("$", "")
								.replace("{", "")
								.replace("}", "");
							/* 遍历全局变量，将全局变量中与条件表达式对应的key替换为全局变量的value */
							/* 得到类似：value（全局变量） == value（连线表达式） 的字符串表达式 */
							for (Map.Entry<String, Object> map : variables.entrySet()) {
								conditionExpression = conditionExpression.replace(map.getKey(), "'"+map.getValue().toString()+"'");
							}
							/* 调用js对字符串表达式进行计算，可以计算数字和字符串：1==1返回true，'a'=='a'返回true */
							try {
								boolean result = (boolean) engine.eval(conditionExpression);
								if (result) {
									FlowNode flowNodeExclusive = (FlowNode) outLine.getTargetFlowElement();
									/* 获取节点自定义属性 */
									List<FlowUserRequest> candidateUserList = flowBusinessService.getCandidateUsers(flowNodeExclusive);
									FlowNodeRequest flowNodeRequest = FlowNodeRequest.builder()
										.end(false)
										.processDefinitionId(processEntity.getProcessDefinitionId())
										.variables(variables)
										.id(flowNodeExclusive.getId())
										.name(flowNodeExclusive.getName())
										.userResponseList(candidateUserList)
										.build();
									flowNodeRequests.add(flowNodeRequest);
									/* 排他网关只能有一条线可以走，只要遇到满足条件的就结束循环 */
									break;
								}
							} catch (Exception e) {
								throw new FlowableException("排他网关连线条件判断时出现异常");
							}
						}
					}
					/* 并行网关，所有分支必须执行，无法自由选择 */
					if (secondNode instanceof ParallelGateway) {
						/* 遍历并行网关的每一条出线获取每一个节点 */
						secondNode.getOutgoingFlows().forEach(sequenceFlow -> {
							FlowNode targetFlowNode = (FlowNode) sequenceFlow.getTargetFlowElement();
							/* 获取节点自定义属性并返回人员List */
							List<FlowUserRequest> candidateUserList = flowBusinessService.getCandidateUsers(targetFlowNode);
							FlowNodeRequest flowNodeRequest = FlowNodeRequest.builder()
								.id(targetFlowNode.getId())
								.name(targetFlowNode.getName())
								.processDefinitionId(processEntity.getProcessDefinitionId())
								.end(false)
								.variables(variables)
								.userResponseList(candidateUserList)
								.build();
							flowNodeRequests.add(flowNodeRequest);
						});
					}
					/* 包容网关，可以选择一条或多条出线，此处在前台展示所有可选的节点和用户信息 */
					if (secondNode instanceof InclusiveGateway) {
						secondNode.getOutgoingFlows().forEach(sequenceFlow -> {
							FlowNode targetNodeInclusive = (FlowNode) sequenceFlow.getTargetFlowElement();
							List<FlowUserRequest> candidateUserList = flowBusinessService.getCandidateUsers(secondNode);
							FlowNodeRequest flowNodeRequest = FlowNodeRequest.builder()
								.end(false)
								.enableChooseNode(true)
								.id(targetNodeInclusive.getId())
								.processDefinitionId(processEntity.getProcessDefinitionId())
								.name(targetNodeInclusive.getName())
								.variables(variables)
								.userResponseList(candidateUserList)
								.build();
							flowNodeRequests.add(flowNodeRequest);
						});
					}
				}
				return R.data(flowNodeRequests);
			}else{
				throw new ServiceException("获取流程定义信息失败");
			}
		} else {
			throw new ServiceException("获取流程定义信息失败");
		}
	}
}


