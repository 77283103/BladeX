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
package org.springblade.system.feign;


import org.springblade.core.launch.constant.AppConstant;
import org.springblade.core.tool.api.R;
import org.springblade.system.entity.DictBiz;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Feign接口类
 *
 * @author Chill
 */
@FeignClient(
	value = AppConstant.APPLICATION_SYSTEM_NAME,
	fallback = IDictBizClientFallback.class
)
public interface IDictBizClient {

	String API_PREFIX = "/client";
	String GET_BY_ID = API_PREFIX + "/dict-biz/get-by-id";
	String GET_VALUE = API_PREFIX + "/dict-biz/get-value";
	String GET_VALUES = API_PREFIX + "/dict-biz/get-values";
	String GET_LIST = API_PREFIX + "/dict-biz/get-list";
	String GET_DICT_BY_CODE_VALUE = API_PREFIX + "/dict-biz/get-dict-by-code-value";
	String GET_KEY = API_PREFIX + "/dict-biz/get-key";
	String INFO = API_PREFIX + "/dict-biz/info";

	/**
	 * 获取字典实体
	 *
	 * @param id 主键
	 * @return
	 */
	@GetMapping(GET_BY_ID)
	R<DictBiz> getById(@RequestParam("id") Long id);

	/**
	 * 获取字典表对应值
	 *
	 * @param code    字典编号
	 * @param dictKey 字典序号
	 * @return
	 */
	@GetMapping(GET_VALUE)
	R<String> getValue(@RequestParam("code") String code, @RequestParam("dictKey") String dictKey);

	/**
	 * 获取字典表对应键
	 *
	 * @param code      字典编号
	 * @param dictValue 字典序号
	 * @return
	 */
	@GetMapping(GET_KEY)
	R<String> getKey(@RequestParam("code") String code, @RequestParam("dictValue") String dictValue);

	/**
	 * 获取字典表对应中文
	 *
	 * @param code 字典编号
	 * @param id   父主键
	 * @return
	 */
	@GetMapping(GET_VALUES)
	R<String> getValues(@RequestParam("code") String code, @RequestParam("id") Long id);

	/**
	 * 获取字典表
	 *
	 * @param code 字典编号
	 * @return
	 */
	@GetMapping(GET_LIST)
	R<List<DictBiz>> getList(@RequestParam("code") String code);


	/**
	 * 根据code、value获取key或id
	 * @param code
	 * @param value
	 * @param returnId
	 * @return
	 */
	@GetMapping(GET_DICT_BY_CODE_VALUE)
	R<String> getDictByCodeValue(@RequestParam("code") String code,@RequestParam("value") String value,@RequestParam("returnId") Boolean returnId);


	/**
	 * 新增或修改
	 *
	 * @param dict
	 * @return
	 */
	@PostMapping(INFO)
	R<Boolean> submit(@RequestBody DictBiz dict);
}
