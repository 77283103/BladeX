package org.springblade.flow.business.service.impl;

import lombok.AllArgsConstructor;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.common.engine.api.FlowableException;
import org.springblade.common.constant.flow.FlowDesignUserType;
import org.springblade.common.constant.flow.FlowEngineConstant;
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
 *  通过固定用户方式获取候选人员list
 *
 * @author tianah
 * @date 2020-8-28
 */
@Component
@AllArgsConstructor
public class GetUserBySetUserHandler implements HandlerGetUser {
	private IUserClient userClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		FactoryGetUser.register(FlowDesignUserType.USERS,this);
	}

	@Override
	public List<FlowUserRequest> getUser(FlowNode targetNode, String taskId) {
			List<FlowUserRequest> flowUserRequestList = new ArrayList<>();
			/* 获取自定义人员属性，格式为：user1,user2,user3... */
			String flowUser = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_USER);
			if (Func.isEmpty(flowUser)) {
				throw new FlowableException("固定人员流程设计方式未获取到自定义人员信息");
			}
			List<User> users = userClient.userInfoByUserIds(flowUser);
			if(null != users) {
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
