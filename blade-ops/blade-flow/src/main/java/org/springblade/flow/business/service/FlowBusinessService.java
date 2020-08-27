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
package org.springblade.flow.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.flowable.bpmn.model.FlowNode;
import org.springblade.flow.business.common.CommentTypeEnum;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.entity.FlowNodeVo;
import org.springblade.flow.business.vo.FlowNodeResponse;
import org.springblade.flow.business.vo.FlowNodeResponseReceive;
import org.springblade.flow.business.vo.FlowUserResponse;
import org.springblade.flow.business.vo.TaskRequest;

import java.util.List;

/**
 * 流程业务类
 *
 * @author Chill
 */
public interface FlowBusinessService {

	/**
	 * 流程待签列表
	 *
	 * @param page      分页工具
	 * @param bladeFlow 流程类
	 * @return
	 */
	IPage<BladeFlow> selectClaimPage(IPage<BladeFlow> page, BladeFlow bladeFlow);

	/**
	 * 流程待办列表
	 *
	 * @param page      分页工具
	 * @param bladeFlow 流程类
	 * @return
	 */
	IPage<BladeFlow> selectTodoPage(IPage<BladeFlow> page, BladeFlow bladeFlow);

	/**
	 * 流程已发列表
	 *
	 * @param page      分页工具
	 * @param bladeFlow 流程类
	 * @return
	 */
	IPage<BladeFlow> selectSendPage(IPage<BladeFlow> page, BladeFlow bladeFlow);

	/**
	 * 流程办结列表
	 *
	 * @param page      分页工具
	 * @param bladeFlow 流程类
	 * @return
	 */
	IPage<BladeFlow> selectDonePage(IPage<BladeFlow> page, BladeFlow bladeFlow);

	/**
	 * 完成任务
	 * @param flowNodeResponseList 接收前台返回的数据
	 * @return boolean
	 */
	boolean completeTask(List<FlowNodeResponseReceive> flowNodeResponseList);

	/**
	 * 查询可以退回的节点
	 * @param taskId 执行实例id
	 * @return 节点List
	 *
	 */
	List<FlowNodeResponse> backNodes(String taskId);

	/**
	 * 退回操作
	 * @param taskRequest 退回信息
	 * @return
	 */
	boolean backTask(TaskRequest taskRequest);

	/**
	 * 转办任务
	 *
	 * @param flow 工作流通用实体类
	 * @return void
	 */
	void assignTask(BladeFlow flow);

	/**
	 * 委托任务
	 *
	 * @param flow 工作流通用实体类
	 * @return
	 */
	void delegateTask(BladeFlow flow);
	/**
	 * 委托返回
	 *
	 * @param flow 工作流通用实体类
	 * @return 是否成功
	 */
	boolean delegateBack(BladeFlow flow);
	/**
	 * 新增审批意见
	 *
	 * @param taskId 待办id
	 * @param processInstanceId 流程实例id
	 * @param userId 用户id
	 * @param type 操作类型
	 * @param message 审批意见
	 */
	void addComment(String taskId, String processInstanceId, String userId, CommentTypeEnum type, String message);

	/**
	 * 首次点击提交时返回前台的信息，提示用户下一节点和办理人信息
	 * @param taskId ru_task的id
	 * @return 节点List
	 */
	List<FlowNodeResponse> completeTempResult(String taskId);

	/**
	 * 发起人终止流程
	 * @param flow 工作流通用实体类
	 */
	void cancelTask(BladeFlow flow);


	/**
	 * 退回任务
	 *
	 * @param flow 工作流通用实体类
	 * @return 是否成功
	 */
	boolean takeItBackTask(BladeFlow flow);

	/**
	 * 查询可退回节点
	 *
	 * @param flow 工作流通用实体类
	 * @return 节点List
	 */
	List<FlowNodeVo> takeItBackTaskLook(BladeFlow flow);

	/**
	 * 拿回任务
	 *
	 * @param flow 工作流通用实体类
	 * @return 是否成功
	 */
	boolean takeBackTask(BladeFlow flow);

	/**
	 * 根据流程自定义属性查询候选人列表
	 *
	 * @param targetNode 节点对象
	 * @param taskId 待办id
	 * @return 候选人列表
	 */
	List<FlowUserResponse> getCandidateUsers(FlowNode targetNode, String taskId);

	/**
	 * 获取审批页面按钮权限
	 * @param taskId 待办id
	 * @return 按钮权限
	 */
	String btnPermission(String taskId);
}
