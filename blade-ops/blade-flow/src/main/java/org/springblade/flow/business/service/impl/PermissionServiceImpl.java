package org.springblade.flow.business.service.impl;

import org.flowable.common.engine.api.FlowableException;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.*;
import org.flowable.task.api.DelegationState;
import org.flowable.task.api.Task;
import org.springblade.flow.business.exception.FlowableNoPermissionException;
import org.springblade.flow.business.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tah
 * @date 2020年7月20日
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	protected TaskService taskService;

	@Override
	public Task validateExcutePermissionOnTask(String taskId, String userId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new FlowableObjectNotFoundException("待办ID: " + taskId + "不存在");
		}
		throw new FlowableNoPermissionException("用户无权限");
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
	 * @param taskId
	 * @param userId
	 * @param delegater
	 * @return
	 */
	@Override
	public Task validateDelegatePermissionOnTask(String taskId, String userId, String delegater) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new FlowableObjectNotFoundException("Task with id: " + taskId + " does not exist");
		}
		if (isTaskOwnerOrAssignee(userId, task)) {
			String owner = task.getOwner();
			String oldAssignee = task.getAssignee();
			if (delegater == null || delegater.length() == 0) {
				throw new FlowableException("Assignee cannot be empty");
			} else if (delegater.equals(owner)) {
				throw new FlowableException("Cannot delegate to owner");
			} else if (delegater.equals(oldAssignee)) {
				throw new FlowableException("The executor is already " + delegater);
			}
			return task;
		}
		throw new FlowableNoPermissionException("User does not have permission");
	}

	@Override
	public boolean isTaskOwnerOrAssignee(String currentUser, Task task) {
		return currentUser.equals(task.getOwner()) || currentUser.equals(task.getAssignee());
	}

	@Override
	public boolean isTaskOwnerOrAssignee(String currentUser, String taskId) {
		return isTaskOwnerOrAssignee(currentUser, taskService.createTaskQuery().taskId(taskId).singleResult());
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
	 * @param taskId
	 * @param userId
	 * @param assignee
	 * @return
	 */
	@Override
	public Task validateAssignPermissionOnTask(String taskId, String userId, String assignee) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new FlowableObjectNotFoundException("Task with id: " + taskId + " does not exist");
		}
		String owner = task.getOwner();
		String oldAssignee = task.getAssignee();
		boolean canAssignFlag = userId.equals(owner)
			|| (userId.equals(oldAssignee) && !isTaskPending(task));
		if (canAssignFlag) {
			if (assignee == null || assignee.length() == 0) {
				throw new FlowableException("Assignee cannot be empty");
			} else if (assignee.equals(oldAssignee)) {
				throw new FlowableException("The assignee is already " + assignee);
			}
			return task;
		}
		throw new FlowableNoPermissionException("User does not have permission");
	}

	@Override
	public boolean isTaskPending(Task task) {
		return DelegationState.PENDING.equals(task.getDelegationState());
	}
}
