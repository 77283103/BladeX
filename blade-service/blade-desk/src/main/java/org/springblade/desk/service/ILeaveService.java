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
package org.springblade.desk.service;

import org.springblade.core.mp.base.BaseService;
import org.springblade.desk.entity.ProcessLeave;
import org.springblade.flow.core.vo.FlowNodeRequest;

import java.util.List;

/**
 * 服务类
 *
 * @author Chill
 */
public interface ILeaveService extends BaseService<ProcessLeave> {

	/**
	 * 开启流程
	 *
	 * @param leave 请假实体
	 * @return boolean
	 */
	boolean startProcess(ProcessLeave leave, List<FlowNodeRequest> flowNodeRequestList);

	/**
	 * 发起流程之前根据业务类型返回下一审批节点和审批人
	 *
	 * @param leave        业务对象
	 * @param businessType 业务类型
	 * @return 可以提交的节点（包含审批人）
	 */
	List<FlowNodeRequest> startProcessBefore(ProcessLeave leave, String businessType);

}
