package org.springblade.flow.business.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.Process;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springblade.common.constant.flow.FlowDesignUserType;
import org.springblade.common.constant.flow.FlowEngineConstant;
import org.springblade.common.constant.flow.FlowProcessBenchMark;
import org.springblade.common.constant.flow.FlowRelationType;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.api.ServiceCode;
import org.springblade.core.tool.utils.Func;
import org.springblade.flow.business.factory.FactoryGetUser;
import org.springblade.flow.business.service.HandlerGetUser;
import org.springblade.flow.core.vo.FlowUserRequest;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过关系定义方式获取候选人员list
 *
 * @author tianah
 * @date 2020-8-28
 */
@Slf4j
@Component
@AllArgsConstructor
public class GetUserByRelationsHandler implements HandlerGetUser {
	private IUserClient userClient;
	private TaskService taskService;
	private RepositoryService repositoryService;
	private HistoryService historyService;

	@Override
	public void afterPropertiesSet() {
		FactoryGetUser.register(FlowDesignUserType.RELATIONS, this);
	}

	@Override
	public List<FlowUserRequest> getUser(FlowNode targetNode, String taskId) {
		List<FlowUserRequest> flowUserRequestList = new ArrayList<>();
		/* 获取关系基准人和关系类型 */
		String flowBenchMark = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_BENCHMARK);
		String flowRelationType = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_RELATION_TYPE);
		if (Func.isEmpty(flowBenchMark)) {
			log.error("【错误码{}】：自定义基准人信息为空，taskId={}",ServiceCode.FLOW_CUSTOM_BENCHMARK_NOT_FOUND.getCode(),taskId);
			throw new ServiceException(ServiceCode.FLOW_CUSTOM_BENCHMARK_NOT_FOUND);
		}
		if (Func.isEmpty(flowRelationType)) {
			log.error("【错误码{}】：自定义关系类型为空，taskId={}",ServiceCode.FLOW_CUSTOM_RELATION_NOT_FOUND.getCode(),taskId);
			throw new ServiceException(ServiceCode.FLOW_CUSTOM_RELATION_NOT_FOUND);
		}
		/* 基准人 */
		String benchUser = "";
		/* 流程启动者为基准人 */
		if (Func.equals(flowBenchMark,FlowProcessBenchMark.PROCESS_CREATER)) {
			/*如果taskid为空，表示流程发起，当前登录人即为流程启动者*/
			if (Func.isEmpty(taskId)) {
				benchUser = AuthUtil.getUserId().toString();
			} else {
				/* 获取taskEntity对象 */
				TaskEntity taskEntity = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();
				/* 流程定义id */
				String processDefinitionId = taskEntity.getProcessDefinitionId();
				/* 获取流程实例对象 */
				Process process = repositoryService.getBpmnModel(processDefinitionId).getMainProcess();
				benchUser = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(process.getId())
					.singleResult()
					.getStartUserId();
			}
			/* 当前审批人为基准人 */
		} else if (Func.equals(flowBenchMark, FlowProcessBenchMark.PROCESS_CURRENT_USER)) {
			benchUser = AuthUtil.getUserId().toString();
		}
		switch (flowRelationType) {
			case FlowRelationType.BENCHMARK_MINISTER:
				List<User> users;
				R<List<User>> userListResult = userClient.userInfoByBenchMinister(benchUser);
				if(userListResult.isSuccess()){
					users = userListResult.getData();
				}else{
					log.error("【错误码{}】：关系定义获取审批人，flow模块调用user模块发生异常，请查看user模块日志", ServiceCode.FEIGN_FAIL.getCode());
					throw new ServiceException(ServiceCode.FEIGN_FAIL);
				}
				if (Func.isNotEmpty(users)) {
					users.forEach(user -> {
						FlowUserRequest flowUserRequest = FlowUserRequest.builder()
							.id(user.getId().toString())
							.name(user.getRealName())
							.build();
						flowUserRequestList.add(flowUserRequest);
					});
				}
				break;
			default:
				break;
		}
		return flowUserRequestList;
	}
}
