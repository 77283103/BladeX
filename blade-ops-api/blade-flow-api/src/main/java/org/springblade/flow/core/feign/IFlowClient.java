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
package org.springblade.flow.core.feign;

import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.flow.core.entity.BladeFlow;
import org.springblade.flow.core.vo.FlowNodeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 工作流远程调用接口.
 *
 * @author Chill
 */
@FeignClient(
	value = AppConstant.APPLICATION_FLOW_NAME,
	fallback = IFlowClientFallback.class
)
public interface IFlowClient {

	String API_PREFIX = "/client";
	String START_PROCESS_INSTANCE_BY_ID = API_PREFIX + "/start-process-instance-by-id";
	String START_PROCESS_INSTANCE_BY_KEY = API_PREFIX + "/start-process-instance-by-key";
	String COMPLETE_TASK = API_PREFIX + "/complete-task";
	String TASK_VARIABLE = API_PREFIX + "/task-variable";
	String TASK_VARIABLES = API_PREFIX + "/task-variables";
	String START_PROCESS_BEFORE = API_PREFIX + "/start-process-before";

	/**
	 * 正式开启流程之前，获取下一节点及其办理人
	 *
	 * @param map 对象转换的map
	 * @param businessType 业务类型
	 * @return 流程定义信息List
	 */
	@PostMapping(START_PROCESS_BEFORE)
	R<List<FlowNodeRequest>> startProcessBefore(@RequestBody Map<String, Object> map, @RequestParam("businessType") String businessType);

	/**
	 * 开启流程
	 *
	 * @param flowNodeRequest	前台回传的节点对象
	 * @param businessKey       业务key
	 * @return BladeFlow
	 */
	@PostMapping(START_PROCESS_INSTANCE_BY_ID)
	R<BladeFlow> startProcessInstanceById(@RequestBody FlowNodeRequest flowNodeRequest, @RequestParam("businessKey") String businessKey);

	/**
	 * 开启流程
	 *
	 * @param processDefinitionKey 流程标识
	 * @param businessKey          业务key
	 * @param variables            参数
	 * @return BladeFlow
	 */
	@PostMapping(START_PROCESS_INSTANCE_BY_KEY)
	R<BladeFlow> startProcessInstanceByKey(@RequestParam("processDefinitionKey") String processDefinitionKey, @RequestParam("businessKey") String businessKey, @RequestBody Map<String, Object> variables);

	/**
	 * 完成任务
	 *
	 * @param taskId            任务id
	 * @param processInstanceId 流程实例id
	 * @param comment           评论
	 * @param variables         参数
	 * @return R
	 */
	@PostMapping(COMPLETE_TASK)
	R completeTask(@RequestParam("taskId") String taskId, @RequestParam("processInstanceId") String processInstanceId, @RequestParam("comment") String comment, @RequestBody Map<String, Object> variables);

	/**
	 * 获取流程变量
	 *
	 * @param taskId       任务id
	 * @param variableName 变量名
	 * @return R
	 */
	@GetMapping(TASK_VARIABLE)
	R<Object> taskVariable(@RequestParam("taskId") String taskId, @RequestParam("variableName") String variableName);

	/**
	 * 获取流程变量集合
	 *
	 * @param taskId 任务id
	 * @return R
	 */
	@GetMapping(TASK_VARIABLES)
	R<Map<String, Object>> taskVariables(@RequestParam("taskId") String taskId);
}
