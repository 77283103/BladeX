package org.springblade.flow.business.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.FlowNode;
import org.springblade.common.constant.flow.FlowDesignUserType;
import org.springblade.common.constant.flow.FlowEngineConstant;
import org.springblade.core.log.exception.ServiceException;
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
 * 通过固定用户方式获取候选人员list
 *
 * @author tianah
 * @date 2020-8-28
 */
@Slf4j
@Component
@AllArgsConstructor
public class GetUserBySetUserHandler implements HandlerGetUser {
	private IUserClient userClient;

	@Override
	public void afterPropertiesSet() {
		FactoryGetUser.register(FlowDesignUserType.USERS, this);
	}

	@Override
	public List<FlowUserRequest> getUser(FlowNode targetNode, String taskId) {
		List<FlowUserRequest> flowUserRequestList = new ArrayList<>();
		/* 获取自定义人员属性，格式为：user1,user2,user3... */
		String flowUser = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_USER);
		if (Func.isEmpty(flowUser)) {
			log.error("【错误码{}】：流程使用候选人员组的方式，但是并没有获取到候选人信息，taskId={}", ServiceCode.FLOW_CUSTOM_USER_NOT_FOUND.getCode(), taskId);
			throw new ServiceException(ServiceCode.FLOW_CUSTOM_USER_NOT_FOUND);
		}
		List<User> users;
		R<List<User>> userListResult = userClient.userInfoByUserIds(flowUser);
		if (userListResult.isSuccess()) {
			users = userListResult.getData();
		} else {
			log.error("【错误码{}】：固定人员方式获取审批人，flow模块调用user模块发生异常，请查看user模块日志，taskId={}", ServiceCode.FEIGN_FAIL.getCode(),taskId);
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
		return flowUserRequestList;
	}
}
