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
import org.springblade.system.feign.ISysClient;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.feign.IUserClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过岗位方式获取候选人员list
 *
 * @author tianah
 * @date 2020-8-28
 */
@Component
@AllArgsConstructor
public class GetUserByPostHandler implements HandlerGetUser {
	private ISysClient sysClient;
	private IUserClient userClient;

	@Override
	public void afterPropertiesSet() throws Exception {
		FactoryGetUser.register(FlowDesignUserType.POSTS,this);
	}

	@Override
	public List<FlowUserRequest> getUser(FlowNode targetNode, String taskId) {
		List<FlowUserRequest> flowUserRequestList = new ArrayList<>();
		/* 获取自定义岗位属性和机构属性 */
		String flowPost = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_POST);
		String flowDept = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_DEPT);
		flowDept = sysClient.getDeptNewId(Long.parseLong(flowDept)).toString();
		if (Func.isEmpty(flowPost)) {
			throw new FlowableException("未获取到自定义岗位信息");
		}
		if (Func.isEmpty(flowDept)) {
			throw new FlowableException("未获取到自定义部门信息");
		}
		List<User> users = userClient.userInfoByDeptAndPost(flowDept, flowPost);
		/* users为空时直接返回List */
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
