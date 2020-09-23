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
@Slf4j
@Component
@AllArgsConstructor
public class GetUserByPostHandler implements HandlerGetUser {
	private ISysClient sysClient;
	private IUserClient userClient;

	@Override
	public void afterPropertiesSet() {
		FactoryGetUser.register(FlowDesignUserType.POSTS, this);
	}

	@Override
	public List<FlowUserRequest> getUser(FlowNode targetNode, String taskId) {
		List<FlowUserRequest> flowUserRequestList = new ArrayList<>();
		/* 获取自定义岗位属性和机构属性 */
		String flowPost = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_POST);
		String flowDept = targetNode.getAttributeValue(FlowEngineConstant.NAME_SPACE, FlowEngineConstant.FLOW_DEPT);
		R<Long> deptNewId = sysClient.getDeptNewId(Long.parseLong(flowDept));
		if (deptNewId.isSuccess()) {
			flowDept = String.valueOf(deptNewId.getData());
		} else {
			log.error("【错误码{}】：flow模块调用system模块获取deptNewId出现异常，请查看system模块日志",ServiceCode.FEIGN_FAIL.getCode());
			throw new ServiceException(ServiceCode.FEIGN_FAIL);
		}
		if (Func.isEmpty(flowPost)) {
			log.error("【错误码{}】：自定义岗位信息为空",ServiceCode.FLOW_CUSTOM_POST_NOT_FOUND.getCode());
			throw new ServiceException(ServiceCode.FLOW_CUSTOM_POST_NOT_FOUND);
		}
		if (Func.isEmpty(flowDept)) {
			log.error("【错误码{}】：自定义部门信息为空",ServiceCode.FLOW_CUSTOM_DEPT_NOT_FOUND.getCode());
			throw new ServiceException(ServiceCode.FLOW_CUSTOM_DEPT_NOT_FOUND);
		}
		R<List<User>> userListResult = userClient.userInfoByDeptAndPost(flowDept, flowPost);
		List<User> users;
		if(userListResult.isSuccess()) {
			users = userListResult.getData();
		}else {
			log.error("【错误码{}】：flow调用user发生错误，请查看user模块日志信息",ServiceCode.FEIGN_FAIL.getCode());
			throw new ServiceException(ServiceCode.FEIGN_FAIL);
		}
		/* users为空时直接返回List */
		if (null != users) {
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
