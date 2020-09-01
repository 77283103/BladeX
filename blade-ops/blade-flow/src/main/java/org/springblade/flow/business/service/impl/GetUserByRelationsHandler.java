package org.springblade.flow.business.service.impl;

import lombok.AllArgsConstructor;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springblade.common.constant.flow.FlowDesignUserType;
import org.springblade.common.constant.flow.FlowEngineConstant;
import org.springblade.common.constant.flow.FlowProcessBenchMark;
import org.springblade.common.constant.flow.FlowRelationType;
import org.springblade.core.secure.utils.AuthUtil;
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
@Component
@AllArgsConstructor
public class GetUserByRelationsHandler implements HandlerGetUser {
	private IUserClient userClient;
	private TaskService taskService;
	private RepositoryService repositoryService;
	private HistoryService historyService;

	@Override
	public void afterPropertiesSet() throws Exception {
		FactoryGetUser.register(FlowDesignUserType.POSTS, this);
	}

	@Override
	public List<FlowUserRequest> getUser(FlowNode targetNode, String taskId) {
		List<FlowUserRequest> flowUserRequestList = new ArrayList<>();
		/* 获取关系基准人和关系类型 */
		String flowBenchMark = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_BENCHMARK);
		String flowRelationType = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_RELATION_TYPE);
		if (null == flowBenchMark) {
			throw new FlowableException("未获取到自定义基准人信息");
		}
		if (null == flowRelationType) {
			throw new FlowableException("未获取到自定义关系类型");
		}
		/* 基准人 */
		String benchUser = "";
		/* 流程启动者为基准人 */
		if (flowBenchMark.equals(FlowProcessBenchMark.PROCESS_CREATER)) {
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
		} else if (flowBenchMark.equals(FlowProcessBenchMark.PROCESS_CURRENT_USER)) {
			benchUser = AuthUtil.getUserId().toString();
		}
		switch (flowRelationType) {
			case FlowRelationType.BENCHMARK_MINISTER:
				List<User> users = userClient.userInfoByBenchMinister(benchUser);
				if (null != users) {
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
