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
package org.springblade.flow.core.entity;

import lombok.Data;
import org.springblade.flow.core.constant.ProcessConstant;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 工作流历史审批记录实体类
 *
 * @author Chill
 */
@Data
public class BladeFlowHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 历史编号
	 */
	private String id;
	/**
	 * 节点名称
	 */
	private String taskName;
	/**
	 * 任务定义Key
	 */
	private String taskDefinitionKey;
	/**
	 * 任务执行人编号（下一办理人）
	 */
	private String assignee;
	/**
	 * 任务执行人名称
	 */
	private String assigneeName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 执行ID
	 */
	private String executionId;
	/**
	 * 流程实例ID
	 */
	private String processInstanceId;
	/**
	 * 流程ID
	 */
	private String processDefinitionId;
	/**
	 * 流程标识
	 */
	private String processDefinitionKey;
	/**
	 * 历史任务流程实例ID 查看流程图会用到
	 */
	private String historyProcessInstanceId;
	/**
	 * 流程实例是否结束
	 */
	private String processIsFinished;
	/**
	 * 历史活动流程
	 */
	private String historyActivityName;
	/**
	 * 历史活动耗时
	 */
	private String historyActivityDurationTime;
	/**
	 * 任务意见
	 */
	private String comment;
	/**
	 * 操作名称
	 */
	private String type;

}
