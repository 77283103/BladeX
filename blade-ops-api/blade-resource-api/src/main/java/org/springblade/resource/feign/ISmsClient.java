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
package org.springblade.resource.feign;

import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.sms.model.SmsResponse;
import org.springblade.core.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ISmsClient
 *
 * @author Chill
 */
@FeignClient(
	value = AppConstant.APPLICATION_RESOURCE_NAME,
	fallback = ISmsClientFallback.class
)
public interface ISmsClient {
	String API_PREFIX = "/client";
	String SEND_MESSAGE = API_PREFIX + "/send-message";
	String SEND_VALIDATE = API_PREFIX + "/send-validate";
	String VALIDATE_MESSAGE = API_PREFIX + "/validate-message";

	/**
	 * 通用短信发送
	 *
	 * @param code   资源编号
	 * @param params 模板参数
	 * @param phones 手机号集合
	 * @return R
	 */
	@PostMapping(SEND_MESSAGE)
	R<SmsResponse> sendMessage(@RequestParam("code") String code, @RequestParam("params") String params, @RequestParam("phones") String phones);

	/**
	 * 短信验证码发送
	 *
	 * @param code  资源编号
	 * @param phone 手机号
	 * @return R
	 */
	@PostMapping(SEND_VALIDATE)
	R sendValidate(@RequestParam("code") String code, @RequestParam("phone") String phone);

	/**
	 * 校验短信
	 *
	 * @param code  资源编号
	 * @param id    校验id
	 * @param value 校验值
	 * @return R
	 */
	@PostMapping(VALIDATE_MESSAGE)
	R validateMessage(@RequestParam("code") String code, @RequestParam("id") String id, @RequestParam("value") String value);

}
