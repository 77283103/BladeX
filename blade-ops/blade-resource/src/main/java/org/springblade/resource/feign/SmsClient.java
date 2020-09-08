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

import lombok.AllArgsConstructor;
import org.springblade.core.sms.model.SmsCode;
import org.springblade.core.sms.model.SmsData;
import org.springblade.core.sms.model.SmsResponse;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.builder.sms.SmsBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springblade.resource.utils.SmsUtil.*;

/**
 * 短信远程调用服务
 *
 * @author Chill
 */
@RestController
@AllArgsConstructor
public class SmsClient implements ISmsClient {

	private SmsBuilder smsBuilder;

	@Override
	@PostMapping(SEND_MESSAGE)
	public R<SmsResponse> sendMessage(String code, String params, String phones) {
		SmsData smsData = new SmsData(JsonUtil.readMap(params, String.class, String.class));
		SmsResponse response = smsBuilder.template(code).sendMessage(smsData, Func.toStrList(phones));
		return R.data(response);
	}

	@Override
	@PostMapping(SEND_VALIDATE)
	public R sendValidate(String code, String phone) {
		Map<String, String> params = getValidateParams();
		SmsCode smsCode = smsBuilder.template(code).sendValidate(new SmsData(params).setKey(PARAM_KEY), phone);
		return smsCode.isSuccess() ? R.data(smsCode, SEND_SUCCESS) : R.fail(SEND_FAIL);
	}

	@Override
	@PostMapping(VALIDATE_MESSAGE)
	public R validateMessage(String code, String id, String value) {
		SmsCode smsCode = new SmsCode().setId(id).setValue(value);
		boolean validate = smsBuilder.template(code).validateMessage(smsCode);
		return validate ? R.success(VALIDATE_SUCCESS) : R.fail(VALIDATE_FAIL);
	}
}
