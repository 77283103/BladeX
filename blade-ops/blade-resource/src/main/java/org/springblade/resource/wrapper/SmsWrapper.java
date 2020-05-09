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
package org.springblade.resource.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.resource.entity.Sms;
import org.springblade.resource.vo.SmsVO;
import org.springblade.system.cache.DictCache;

import java.util.Objects;

/**
 * 短信配置表包装类,返回视图层所需的字段
 *
 * @author BladeX
 * @since 2020-02-20
 */
public class SmsWrapper extends BaseEntityWrapper<Sms, SmsVO> {

	public static SmsWrapper build() {
		return new SmsWrapper();
	}

	@Override
	public SmsVO entityVO(Sms sms) {
		SmsVO smsVO = Objects.requireNonNull(BeanUtil.copy(sms, SmsVO.class));
		String categoryName = DictCache.getValue("sms", sms.getCategory());
		String statusName = DictCache.getValue("yes_no", sms.getStatus());
		smsVO.setCategoryName(categoryName);
		smsVO.setStatusName(statusName);
		return smsVO;
	}

}
