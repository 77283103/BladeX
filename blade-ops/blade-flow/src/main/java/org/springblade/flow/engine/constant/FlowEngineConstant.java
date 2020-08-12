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

	String FLOW_START_ELEMENT = "UserTask_start";

	String FLOW_FIRST_ELEMENT = "UserTask_first";

	String GET_USER_TYPE = "getUserType";

	String BTN_PERMISSION = "btnPermission";

	String NAME_SPACE = "http://flowable.org/bpmn/barcode";

	String FLOW_POST = "post";

	String FLOW_USER = "user";

	String FLOW_RELATION_TYPE = "relationtype";

	String FLOW_BENCHMARK = "benchmark";

	String FLOW_DEPT = "dept";

	String FLOW_TRUE = "${true}";

	String SUFFIX = ".bpmn20.xml";

	String ACTIVE = "active";

	String SUSPEND = "suspend";

	String STATUS_TODO = "todo";

	String STATUS_CLAIM = "claim";

	String STATUS_FINISHED = "finished";

	String STATUS_UNFINISHED = "unfinished";

	String STATUS_FINISH = "finish";

	String START_EVENT = "startEvent";

	String END_EVENT = "endEvent";

	String INITIATOR = "__initiator__";

	String SPECIAL_GATEWAY_BEGIN_SUFFIX = "_begin";

	String SPECIAL_GATEWAY_END_SUFFIX = "_end";
}
