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
package org.springblade.system.cache;

import org.springblade.core.cache.utils.CacheUtil;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.core.tool.utils.StringPool;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.feign.IDictBizClient;

import java.util.List;

import static org.springblade.core.cache.constant.CacheConstant.DICT_CACHE;

/**
 * 业务字典缓存工具类
 *
 * @author Chill
 */
public class DictBizCache {

	private static final String DICT_ID = "dictBiz:id";
	private static final String DICT_VALUE = "dictBiz:value";
	private static final String DICT_LIST = "dictBiz:list";

	private static IDictBizClient dictClient;

	private static IDictBizClient getDictClient() {
		if (dictClient == null) {
			dictClient = SpringUtil.getBean(IDictBizClient.class);
		}
		return dictClient;
	}

	/**
	 * 获取字典实体
	 *
	 * @param id 主键
	 * @return
	 */
	public static DictBiz getById(Long id) {
		String keyPrefix = DICT_ID.concat(StringPool.DASH).concat(AuthUtil.getTenantId()).concat(StringPool.COLON);
		return CacheUtil.get(DICT_CACHE, keyPrefix, id, () -> {
			R<DictBiz> result = getDictClient().getById(id);
			return result.getData();
		});
	}

	/**
	 * 获取字典值
	 *
	 * @param code    字典编号
	 * @param dictKey Integer型字典键
	 * @return
	 */
	public static String getValue(String code, Integer dictKey) {
		String keyPrefix = DICT_VALUE.concat(StringPool.DASH).concat(AuthUtil.getTenantId()).concat(StringPool.COLON);
		return CacheUtil.get(DICT_CACHE, keyPrefix + code + StringPool.COLON, String.valueOf(dictKey), () -> {
			R<String> result = getDictClient().getValue(code, String.valueOf(dictKey));
			return result.getData();
		});
	}

	/**
	 * 获取字典值
	 *
	 * @param code    字典编号
	 * @param dictKey String型字典键
	 * @return
	 */
	public static String getValue(String code, String dictKey) {
		String keyPrefix = DICT_VALUE.concat(StringPool.DASH).concat(AuthUtil.getTenantId()).concat(StringPool.COLON);
		return CacheUtil.get(DICT_CACHE, keyPrefix + code + StringPool.COLON, dictKey, () -> {
			R<String> result = getDictClient().getValue(code, dictKey);
			return result.getData();
		});
	}

	/**
	 * 获取字典集合
	 *
	 * @param code 字典编号
	 * @return
	 */
	public static List<DictBiz> getList(String code) {
		String keyPrefix = DICT_LIST.concat(StringPool.DASH).concat(AuthUtil.getTenantId()).concat(StringPool.COLON);
		return CacheUtil.get(DICT_CACHE, keyPrefix, code, () -> {
			R<List<DictBiz>> result = getDictClient().getList(code);
			return result.getData();
		});
	}

}
