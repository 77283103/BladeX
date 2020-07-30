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
package org.springblade.flow.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ActivityInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.support.Kv;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.flow.business.common.CommentTypeEnum;
import org.springblade.flow.business.common.ObjectUtils;
import org.springblade.flow.business.common.cmd.BackUserTaskCmd;
import org.springblade.flow.business.service.FlowBusinessService;
import org.springblade.flow.core.constant.ProcessConstant;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.utils.TaskUtil;
import org.springblade.flow.engine.constant.FlowEngineConstant;
import org.springblade.flow.engine.utils.FlowCache;
import org.springblade.flow.engine.utils.FlowableUtils;
import org.springblade.flow.engine.vo.FlowNodeResponse;
import org.springblade.flow.engine.vo.TaskRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 流程业务实现类
 *
 * @author Chill
 */
@Service
@AllArgsConstructor
public class FlowBusinessServiceImpl implements FlowBusinessService {

	private TaskService taskService;
	private HistoryService historyService;
	private RepositoryService repositoryService;
	private RuntimeService runtimeService;
	protected ManagementService managementService;
	protected PermissionServiceImpl permissionService;

	@Override
	public IPage<BladeFlow> selectClaimPage(IPage<BladeFlow> page, BladeFlow bladeFlow) {
		String taskUser = TaskUtil.getTaskUser();
		String taskGroup = TaskUtil.getCandidateGroup();
		List<BladeFlow> flowList = new LinkedList<>();

		// 个人等待签收的任务
		TaskQuery claimUserQuery = taskService.createTaskQuery().taskCandidateUser(taskUser)
			.includeProcessVariables().active().orderByTaskCreateTime().desc();
		// 定制流程等待签收的任务
		TaskQuery claimRoleWithTenantIdQuery = taskService.createTaskQuery().taskTenantId(AuthUtil.getTenantId()).taskCandidateGroupIn(Func.toStrList(taskGroup))
			.includeProcessVariables().active().orderByTaskCreateTime().desc();
		// 通用流程等待签收的任务
		TaskQuery claimRoleWithoutTenantIdQuery = taskService.createTaskQuery().taskWithoutTenantId().taskCandidateGroupIn(Func.toStrList(taskGroup))
			.includeProcessVariables().active().orderByTaskCreateTime().desc();

		// 构建列表数据
		buildFlowTaskList(bladeFlow, flowList, claimUserQuery, FlowEngineConstant.STATUS_CLAIM);
		buildFlowTaskList(bladeFlow, flowList, claimRoleWithTenantIdQuery, FlowEngineConstant.STATUS_CLAIM);
		buildFlowTaskList(bladeFlow, flowList, claimRoleWithoutTenantIdQuery, FlowEngineConstant.STATUS_CLAIM);

		// 计算总数
		long count = claimUserQuery.count() + claimRoleWithTenantIdQuery.count() + claimRoleWithoutTenantIdQuery.count();
		// 设置页数
		page.setSize(count);
		// 设置总数
		page.setTotal(count);
		// 设置数据
		page.setRecords(flowList);
		return page;
	}

	@Override
	public IPage<BladeFlow> selectTodoPage(IPage<BladeFlow> page, BladeFlow bladeFlow) {
		String taskUser = TaskUtil.getTaskUser();
		List<BladeFlow> flowList = new LinkedList<>();

		// 已签收的任务
		TaskQuery todoQuery = taskService.createTaskQuery().taskAssignee(taskUser).active()
			.includeProcessVariables().orderByTaskCreateTime().desc();

		// 构建列表数据
		buildFlowTaskList(bladeFlow, flowList, todoQuery, FlowEngineConstant.STATUS_TODO);

		// 计算总数
		long count = todoQuery.count();
		// 设置页数
		page.setSize(count);
		// 设置总数
		page.setTotal(count);
		// 设置数据
		page.setRecords(flowList);
		return page;
	}

	@Override
	public IPage<BladeFlow> selectSendPage(IPage<BladeFlow> page, BladeFlow bladeFlow) {
		String taskUser = TaskUtil.getTaskUser();
		List<BladeFlow> flowList = new LinkedList<>();

		HistoricProcessInstanceQuery historyQuery = historyService.createHistoricProcessInstanceQuery().startedBy(taskUser).orderByProcessInstanceStartTime().desc();

		if (bladeFlow.getCategory() != null) {
			historyQuery.processDefinitionCategory(bladeFlow.getCategory());
		}
		if (bladeFlow.getBeginDate() != null) {
			historyQuery.startedAfter(bladeFlow.getBeginDate());
		}
		if (bladeFlow.getEndDate() != null) {
			historyQuery.startedBefore(bladeFlow.getEndDate());
		}
		// 查询列表
		List<HistoricProcessInstance> historyList = historyQuery.listPage(Func.toInt((page.getCurrent() - 1) * page.getSize()), Func.toInt(page.getSize()));

		historyList.forEach(historicProcessInstance -> {
			BladeFlow flow = new BladeFlow();
			// historicProcessInstance
			flow.setCreateTime(historicProcessInstance.getStartTime());
			flow.setEndTime(historicProcessInstance.getEndTime());
			flow.setVariables(historicProcessInstance.getProcessVariables());
			String[] businessKey = Func.toStrArray(StringPool.COLON, historicProcessInstance.getBusinessKey());
			if (businessKey.length > 1) {
				flow.setBusinessTable(businessKey[0]);
				flow.setBusinessId(businessKey[1]);
			}
			flow.setHistoryActivityName(historicProcessInstance.getName());
			flow.setProcessInstanceId(historicProcessInstance.getId());
			flow.setHistoryProcessInstanceId(historicProcessInstance.getId());
			// ProcessDefinition
			ProcessDefinition processDefinition = FlowCache.getProcessDefinition(historicProcessInstance.getProcessDefinitionId());
			flow.setProcessDefinitionId(processDefinition.getId());
			flow.setProcessDefinitionName(processDefinition.getName());
			flow.setProcessDefinitionVersion(processDefinition.getVersion());
			flow.setProcessDefinitionKey(processDefinition.getKey());
			flow.setCategory(processDefinition.getCategory());
			flow.setCategoryName(FlowCache.getCategoryName(processDefinition.getCategory()));
			flow.setProcessInstanceId(historicProcessInstance.getId());
			// HistoricTaskInstance
			List<HistoricTaskInstance> historyTasks = historyService.createHistoricTaskInstanceQuery().processInstanceId(historicProcessInstance.getId()).orderByHistoricTaskInstanceEndTime().desc().list();
			if (Func.isNotEmpty(historyTasks)) {
				HistoricTaskInstance historyTask = historyTasks.iterator().next();
				flow.setTaskId(historyTask.getId());
				flow.setTaskName(historyTask.getName());
				flow.setTaskDefinitionKey(historyTask.getTaskDefinitionKey());
			}
			// Status
			if (historicProcessInstance.getEndActivityId() != null) {
				flow.setProcessIsFinished(FlowEngineConstant.STATUS_FINISHED);
			} else {
				flow.setProcessIsFinished(FlowEngineConstant.STATUS_UNFINISHED);
			}
			flow.setStatus(FlowEngineConstant.STATUS_FINISH);
			flowList.add(flow);
		});

		// 计算总数
		long count = historyQuery.count();
		// 设置总数
		page.setTotal(count);
		page.setRecords(flowList);
		return page;
	}

	@Override
	public IPage<BladeFlow> selectDonePage(IPage<BladeFlow> page, BladeFlow bladeFlow) {
		String taskUser = TaskUtil.getTaskUser();
		List<BladeFlow> flowList = new LinkedList<>();

		HistoricTaskInstanceQuery doneQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(taskUser).finished()
			.includeProcessVariables().orderByHistoricTaskInstanceEndTime().desc();

		if (bladeFlow.getCategory() != null) {
			doneQuery.processCategoryIn(Func.toStrList(bladeFlow.getCategory()));
		}
		if (bladeFlow.getBeginDate() != null) {
			doneQuery.taskCompletedAfter(bladeFlow.getBeginDate());
		}
		if (bladeFlow.getEndDate() != null) {
			doneQuery.taskCompletedBefore(bladeFlow.getEndDate());
		}

		// 查询列表
		List<HistoricTaskInstance> doneList = doneQuery.listPage(Func.toInt((page.getCurrent() - 1) * page.getSize()), Func.toInt(page.getSize()));
		doneList.forEach(historicTaskInstance -> {
			BladeFlow flow = new BladeFlow();
			flow.setTaskId(historicTaskInstance.getId());
			flow.setTaskDefinitionKey(historicTaskInstance.getTaskDefinitionKey());
			flow.setTaskName(historicTaskInstance.getName());
			flow.setAssignee(historicTaskInstance.getAssignee());
			flow.setCreateTime(historicTaskInstance.getCreateTime());
			flow.setExecutionId(historicTaskInstance.getExecutionId());
			flow.setHistoryTaskEndTime(historicTaskInstance.getEndTime());
			flow.setVariables(historicTaskInstance.getProcessVariables());

			ProcessDefinition processDefinition = FlowCache.getProcessDefinition(historicTaskInstance.getProcessDefinitionId());
			flow.setProcessDefinitionId(processDefinition.getId());
			flow.setProcessDefinitionName(processDefinition.getName());
			flow.setProcessDefinitionKey(processDefinition.getKey());
			flow.setProcessDefinitionVersion(processDefinition.getVersion());
			flow.setCategory(processDefinition.getCategory());
			flow.setCategoryName(FlowCache.getCategoryName(processDefinition.getCategory()));

			flow.setProcessInstanceId(historicTaskInstance.getProcessInstanceId());
			flow.setHistoryProcessInstanceId(historicTaskInstance.getProcessInstanceId());
			HistoricProcessInstance historicProcessInstance = getHistoricProcessInstance((historicTaskInstance.getProcessInstanceId()));
			if (Func.isNotEmpty(historicProcessInstance)) {
				String[] businessKey = Func.toStrArray(StringPool.COLON, historicProcessInstance.getBusinessKey());
				flow.setBusinessTable(businessKey[0]);
				flow.setBusinessId(businessKey[1]);
				if (historicProcessInstance.getEndActivityId() != null) {
					flow.setProcessIsFinished(FlowEngineConstant.STATUS_FINISHED);
				} else {
					flow.setProcessIsFinished(FlowEngineConstant.STATUS_UNFINISHED);
				}
			}
			flow.setStatus(FlowEngineConstant.STATUS_FINISH);
			flowList.add(flow);
		});
		// 计算总数
		long count = doneQuery.count();
		// 设置总数
		page.setTotal(count);
		page.setRecords(flowList);
		return page;
	}

	@Override
	public boolean completeTask(BladeFlow flow) {
		String taskId = flow.getTaskId();
		String processInstanceId = flow.getProcessInstanceId();
		String comment = Func.toStr(flow.getComment(), ProcessConstant.PASS_COMMENT);
		// 增加评论
		if (StringUtil.isNoneBlank(processInstanceId, comment)) {
			taskService.addComment(taskId, processInstanceId, comment);
		}
		// 创建变量
		Map<String, Object> variables = flow.getVariables();
		if (variables == null) {
			variables = Kv.create();
		}
		variables.put(ProcessConstant.PASS_KEY, flow.isPass());
		// 完成任务
		taskService.complete(taskId, variables);
		return true;
	}

	/**
	 * 构建流程
	 *
	 * @param bladeFlow 流程通用类
	 * @param flowList  流程列表
	 * @param taskQuery 任务查询类
	 * @param status    状态
	 */
	private void buildFlowTaskList(BladeFlow bladeFlow, List<BladeFlow> flowList, TaskQuery taskQuery, String status) {
		if (bladeFlow.getCategory() != null) {
			taskQuery.processCategoryIn(Func.toStrList(bladeFlow.getCategory()));
		}
		if (bladeFlow.getBeginDate() != null) {
			taskQuery.taskCreatedAfter(bladeFlow.getBeginDate());
		}
		if (bladeFlow.getEndDate() != null) {
			taskQuery.taskCreatedBefore(bladeFlow.getEndDate());
		}
		taskQuery.list().forEach(task -> {
			BladeFlow flow = new BladeFlow();
			flow.setTaskId(task.getId());
			flow.setTaskDefinitionKey(task.getTaskDefinitionKey());
			flow.setTaskName(task.getName());
			flow.setAssignee(task.getAssignee());
			flow.setCreateTime(task.getCreateTime());
			flow.setClaimTime(task.getClaimTime());
			flow.setExecutionId(task.getExecutionId());
			flow.setVariables(task.getProcessVariables());

			HistoricProcessInstance historicProcessInstance = getHistoricProcessInstance(task.getProcessInstanceId());
			if (Func.isNotEmpty(historicProcessInstance)) {
				String[] businessKey = Func.toStrArray(StringPool.COLON, historicProcessInstance.getBusinessKey());
				flow.setBusinessTable(businessKey[0]);
				flow.setBusinessId(businessKey[1]);
			}

			ProcessDefinition processDefinition = FlowCache.getProcessDefinition(task.getProcessDefinitionId());
			flow.setCategory(processDefinition.getCategory());
			flow.setCategoryName(FlowCache.getCategoryName(processDefinition.getCategory()));
			flow.setProcessDefinitionId(processDefinition.getId());
			flow.setProcessDefinitionName(processDefinition.getName());
			flow.setProcessDefinitionKey(processDefinition.getKey());
			flow.setProcessDefinitionVersion(processDefinition.getVersion());
			flow.setProcessInstanceId(task.getProcessInstanceId());
			flow.setStatus(status);
			flowList.add(flow);
		});
	}

	/**
	 * 获取历史流程
	 *
	 * @param processInstanceId 流程实例id
	 * @return HistoricProcessInstance
	 */
	private HistoricProcessInstance getHistoricProcessInstance(String processInstanceId) {
		return historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
	}

	/**
	 * 查询可以退回的节点
	 */
	@Override
	public List<FlowNodeResponse> backNodes(String taskId){
		TaskEntity taskEntity = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = taskEntity.getProcessInstanceId();
		String currActId = taskEntity.getTaskDefinitionKey();
		String processDefinitionId = taskEntity.getProcessDefinitionId();
		Process process = repositoryService.getBpmnModel(processDefinitionId).getMainProcess();
		FlowNode currentFlowElement = (FlowNode) process.getFlowElement(currActId, true);
		List<ActivityInstance> activitys = runtimeService.createActivityInstanceQuery()
			.processInstanceId(processInstanceId).finished().orderByActivityInstanceStartTime().asc().list();
		List<String> activityIds = activitys.stream()
			.filter(activity -> activity.getActivityType().equals(BpmnXMLConstants.ELEMENT_TASK_USER))
			.filter(activity -> !activity.getActivityId().equals(currActId)).map(ActivityInstance::getActivityId)
			.distinct().collect(Collectors.toList());
		List<FlowNodeResponse> result = new ArrayList<>();
		for (String activityId : activityIds) {
			FlowNode toBackFlowElement = (FlowNode) process.getFlowElement(activityId, true);
			if (FlowableUtils.isReachable(process, toBackFlowElement, currentFlowElement)) {
				FlowNodeResponse vo = new FlowNodeResponse();
				vo.setNodeId(activityId);
				vo.setNodeName(toBackFlowElement.getName());
				result.add(vo);
			}
		}
		return result;
	}

	/**
	 * 退回操作
	 * @param taskRequest 退回信息
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean backTask(TaskRequest taskRequest){
		String taskId = taskRequest.getTaskId();
		// 获取当前登录人
		BladeUser user = AuthUtil.getUser();
		String userId =String.valueOf(user.getUserId());
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String backSysMessage = "退回到" + taskRequest.getActivityName() + "。";
		this.addComment(taskId, task.getProcessInstanceId(), userId, CommentTypeEnum.TH,
			backSysMessage + taskRequest.getMessage());
		String targetRealActivityId = managementService.executeCommand(
			new BackUserTaskCmd(runtimeService, taskRequest.getTaskId(), taskRequest.getActivityId()));
		// 退回发起者处理,退回到发起者,默认设置任务执行人为发起者
		if (FlowEngineConstant.INITIATOR.equals(targetRealActivityId)) {
			String initiator = runtimeService.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).singleResult().getStartUserId();
			List<Task> newTasks = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
			for (Task newTask : newTasks) {
				// 约定：发起者节点为 __initiator__
				if (FlowEngineConstant.INITIATOR.equals(newTask.getTaskDefinitionKey())) {
					if (ObjectUtils.isEmpty(newTask.getAssignee())) {
						taskService.setAssignee(newTask.getId(), initiator);
					}
				}
			}
		}
		return true;
	}

	/**
	 * 增加审批意见
	 * @param taskId
	 * @param processInstanceId
	 * @param userId
	 * @param type
	 * @param message
	 */
	@Override
	public void addComment(String taskId, String processInstanceId, String userId, CommentTypeEnum type,
						   String message) {
		Authentication.setAuthenticatedUserId(userId);
		type = type == null ? CommentTypeEnum.SP : type;
		message = (message == null || message.length() == 0) ? type.getName() : message;
		taskService.addComment(taskId, processInstanceId, type.toString(), message);
	}

	@Override
	public void cancelTask(BladeFlow flow) {
		if (Func.isEmpty(flow.getProcessInstanceId())){
			throw new ServiceException("流程实例ID不能为空!");
		}
		runtimeService.deleteProcessInstance(flow.getProcessInstanceId(),"终止原因");
	}

	/**
	 * 是否可以转办任务
	 *
	 * 1.任务所有人可以转办
	 *
	 * 2.任务执行人可以转办，但要求任务非委派状态
	 *
	 * 3.被转办人不能是当前任务执行人
	 *
	 * @param flow
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void assignTask(BladeFlow flow) {
		// 任务id
		String taskId = flow.getTaskId();
		// 转办人员id
		String assignee = TaskUtil.getTaskUser(flow.getUserId());
		// 当前人员id
		BladeUser user = AuthUtil.getUser();
		String userId =String.valueOf(user.getUserId());
		// 是否可以转办任务，如果不可以的话直接被异常处理拦截
//		Task task = permissionService.validateAssignPermissionOnTask(taskId, userId, assignee);
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		this.addComment(taskId, task.getProcessInstanceId(), userId,CommentTypeEnum.ZB, flow.getComment());
		taskService.setAssignee(task.getId(), assignee);
	}

	/**
	 * 是否可以委派任务
	 *
	 * 1.任务所有人可以委派
	 *
	 * 2.任务执行人可以委派
	 *
	 * 3.被委派人不能是任务所有人和当前任务执行人
	 *
	 * @param flow
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delegateTask(BladeFlow flow) {
		// 任务id
		String taskId = flow.getTaskId();
		// 委托人员id
		String delegater = TaskUtil.getTaskUser("1285030425345622018");
		// 当前人员id
		BladeUser user = AuthUtil.getUser();
		String userId =String.valueOf(user.getUserId());
		// 是否可以委派任务，如果不可以的话直接被异常处理拦截
		Task task =permissionService.validateDelegatePermissionOnTask(taskId, userId, delegater);
		this.addComment(taskId, task.getProcessInstanceId(), userId,CommentTypeEnum.WP, flow.getComment());
		taskService.delegateTask(task.getId(), delegater);
	}

}
