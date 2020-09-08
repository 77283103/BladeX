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
package org.springblade.resource.utils;

import org.springblade.core.sms.model.SmsCode;
import org.springblade.core.sms.model.SmsResponse;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.RandomType;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springblade.resource.feign.ISmsClient;

import java.util.HashMap;
import java.util.Map;

/**
 * 短信服务工具类
 *
 * @author Chill
 */
public class SmsUtil {

	public static final String PARAM_KEY = "code";
	public static final String SEND_SUCCESS = "短信发送成功";
	public static final String SEND_FAIL = "短信发送失败";
	public static final String VALIDATE_SUCCESS = "短信校验成功";
	public static final String VALIDATE_FAIL = "短信校验失败";

	private static ISmsClient smsClient;

	/**
	 * 获取短信服务构建类
	 *
	 * @return SmsBuilder
	 */
	public static ISmsClient getSmsClient() {
		if (smsClient == null) {
			smsClient = SpringUtil.getBean(ISmsClient.class);
		}
		return smsClient;
	}

	/**
	 * 获取短信验证码参数
	 *
	 * @return 验证码参数
	 */
	public static Map<String, String> getValidateParams() {
		Map<String, String> params = new HashMap<>(1);
		params.put(PARAM_KEY, StringUtil.random(6, RandomType.INT));
		return params;
	}

	/**
	 * 发送短信
	 *
	 * @param code   资源编号
	 * @param params 模板参数
	 * @param phones 手机号集合
	 * @return 发送结果
	 */
	public static SmsResponse sendMessage(String code, Map<String, String> params, String phones) {
		R<SmsResponse> result = getSmsClient().sendMessage(code, JsonUtil.toJson(params), phones);
		return result.getData();
	}

	/**
	 * 发送验证码
	 *
	 * @param code  资源编号
	 * @param phone 手机号
	 * @return 发送结果
	 */
	public static SmsCode sendValidate(String code, String phone) {
		SmsCode smsCode = new SmsCode();
		R result = getSmsClient().sendValidate(code, phone);
		if (result.isSuccess()) {
			smsCode = JsonUtil.parse(JsonUtil.toJson(result.getData()), SmsCode.class);
		} else {
			smsCode.setSuccess(Boolean.FALSE);
		}
		return smsCode;
	}

	/**
	 * 校验短信
	 *
	 * @param code  资源编号
	 * @param id    校验id
	 * @param value 校验值
	 * @return 发送结果
	 */
	public static boolean validateMessage(String code, String id, String value) {
		R result = getSmsClient().validateMessage(code, id, value);
		return result.isSuccess();
	}

}
