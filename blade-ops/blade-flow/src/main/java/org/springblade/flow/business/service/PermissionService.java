package org.springblade.flow.business.service;

import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.identitylink.api.IdentityLink;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.history.HistoricTaskInstance;

import java.util.List;

/**
 * 用于验证流程操作时的权限
 *
 * @author xxx
 * @date 2020年3月24日
 */
public interface PermissionService {

	/**
	 * 是否可以委派任务
	 *
	 * 1.任务所有人可以委派
	 *
	 * 2.任务执行人可以委派
	 *
	 * 3.被委派人不能是任务所有人和当前任务执行人
	 *
	 * @param taskId
	 * @param userId
	 * @param delegater
	 * @return
	 */
	Task validateDelegatePermissionOnTask(String taskId, String userId, String delegater);

	/**
	 * 判断用户是否是任务的所有者或者执行人
	 *
	 * @param currentUser
	 * @param taskId
	 * @return
	 */
	boolean isTaskOwnerOrAssignee(String currentUser, String taskId);

	/**
	 * 判断用户是否是任务的所有者或者执行人
	 *
	 * @param currentUser
	 * @param task
	 * @return
	 */
	boolean isTaskOwnerOrAssignee(String currentUser, Task task);

	/**
	 * 是否可以转办任务
	 *
	 * 1.任务所有人可以转办
	 *
	 * 2.任务执行人可以转办，但要求任务非委派状态
	 *
	 * 3.被转办人不能是当前任务执行人
	 *
	 * @param taskId
	 * @param userId
	 * @param assignee
	 * @return
	 */
	Task validateAssignPermissionOnTask(String taskId, String userId, String assignee);

	/**
	 * 判断任务是否挂起
	 *
	 * @param task
	 * @return
	 */
	boolean isTaskPending(Task task);
}
