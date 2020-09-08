package org.springblade.flow.business.service;

import org.flowable.bpmn.model.FlowNode;
import org.springblade.flow.core.vo.FlowUserRequest;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

/**
 * 工作流获取下一节点用户策略类
 *
 * @author tah
 * @date 2020-8-20
 */
public interface HandlerGetUser extends InitializingBean {
	/**
	 * 获取下一节点审批人
	 *
	 * @param targetNode 节点
	 * @param taskId 执行实例id
	 * @return 候选用户List
	 */
	List<FlowUserRequest> getUser(FlowNode targetNode, String taskId);
}
