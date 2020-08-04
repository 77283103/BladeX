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
package org.springblade.flow.engine.constant;

/**
 * 流程常量.
 *
 * @author zhuangqian
 */
public interface FlowEngineConstant {


	String NAME_SPACE = "http://flowable.org/bpmn/barcode";

	String FLOW_POST = "post";

	String FLOW_ROLE = "role";

	String FLOW_USER = "user";

	String FLOW_DEPT = "dept";

	String FLOWABLE_BASE_PACKAGES = "org.flowable.ui";

	String SUFFIX = ".bpmn20.xml";

	String ACTIVE = "active";

	String SUSPEND = "suspend";

	String STATUS_TODO = "todo";

	String STATUS_CLAIM = "claim";

	String STATUS_SEND = "send";

	String STATUS_DONE = "done";

	String STATUS_FINISHED = "finished";

	String STATUS_UNFINISHED = "unfinished";

	String STATUS_FINISH = "finish";

	String START_EVENT = "startEvent";

	String END_EVENT = "endEvent";

	String USER_EVENT = "userEvent";

	String INITIATOR = "__initiator__";
	String SPECIAL_GATEWAY_BEGIN_SUFFIX = "_begin";
	String SPECIAL_GATEWAY_END_SUFFIX = "_end";
	String PROCESS_INSTANCE_FORM_DATA = "processInstanceFormData";
	String IDENTITY_USER = "1";
	String IDENTITY_GROUP = "2";
	String ID = "id";
	String CATEGORY = "category";
	String KEY = "key";
	String NAME = "name";
	String VERSION = "version";
	String SUSPENDED = "suspended";
	String LATEST_VERSION = "latestVersion";
	String STARTABLE_BY_USER = "startableByUser";
	String TENANT_ID = "tenantId";
	String PROCESS_INSTANCE_ID = "processInstanceId";
	String PROCESS_INSTANCE_NAME = "processInstanceName";
	String PROCESS_DEFINITION_NAME = "processDefinitionName";
	String PROCESS_DEFINITION_KEY = "processDefinitionKey";
	String PROCESS_DEFINITION_ID = "processDefinitionId";
	String BUSINESS_KEY = "businessKey";
	String INVOLVED_USER = "involvedUser";
	String FINISHED = "finished";
	String SUPER_PROCESS_INSTANCE_ID = "superProcessInstanceId";
	String EXCLUDE_SUBPROCESSES = "excludeSubprocesses";
	String FINISHED_AFTER = "finishedAfter";
	String FINISHED_BEFORE = "finishedBefore";
	String STARTED_AFTER = "startedAfter";
	String STARTED_BEFORE = "startedBefore";
	String STARTED_BY = "startedBy";
	String START_BY_ME = "startByMe";
	String TASK_ID = "taskId";
	String TASK_NAME = "taskName";
	String TASK_DESCRIPTION = "taskDescription";
	String TASK_DEFINITION_KEY = "taskDefinitionKey";
	String TASK_ASSIGNEE = "taskAssignee";
	String TASK_OWNER = "taskOwner";
	String TASK_INVOLVED_USER = "taskInvolvedUser";
	String TASK_PRIORITY = "taskPriority";
	String PARENT_TASK_ID = "parentTaskId";
	String DUE_DATE_AFTER = "dueDateAfter";
	String DUE_DATE_BEFORE = "dueDateBefore";
	String TASK_CREATED_BEFORE = "taskCreatedBefore";
	String TASK_CREATED_AFTER = "taskCreatedAfter";
	String TASK_COMPLETED_BEFORE = "taskCompletedBefore";
	String TASK_COMPLETED_AFTER = "taskCompletedAfter";
	String TASK_CANDIDATE_USER = "taskCandidateUser";
	String TASK_CANDIDATE_GROUP = "taskCandidateGroup";
	String TASK_CANDIDATE_GROUPS = "taskCandidateGroups";
	String PROCESS_INSTANCE_BUSINESS_KEY = "processInstanceBusinessKey";
	String PROCESS_FINISHED = "processFinished";
	String EXECUTION_ID = "executionId";
	String FILE_EXTENSION_BAR = ".bar";
	String FILE_EXTENSION_ZIP = ".zip";
	String CATEGORY_PREFIX = "flow_";
}
