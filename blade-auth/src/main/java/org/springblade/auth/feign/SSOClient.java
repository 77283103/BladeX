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
package org.springblade.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * SSO项目调用接口
 *
 * @author Chill
 */
@FeignClient(
	name = "SSO",url = "http://sso.pec.com.cn"
)
@RequestMapping(value="/sso")
public interface SSOClient {

	/**
	 * 获取sso用户名
	 * @param service 重定向地址
	 * @param ticket ticket
	 * @return R
	 */
	@GetMapping("/validate")
	String  validate(@RequestParam("ticket") String ticket,@RequestParam("service") String service);
}
