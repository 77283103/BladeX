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
package org.springblade.common.constant;

/**
 * 通用常量
 *
 * @author Chill
 */
public interface CommonConstant {

	/**
	 * 组织机构类别名称
	 */
	String DEPT_CATEGORY_NAME = "deptCategoryName";

	/**
	 * 组织机构类别id
	 */
	String ORG_CATEGORY = "org_category";

	/**
	 * 父级id
	 */
	String PARENT_ID = "parentId";

	/**
	 * sword 系统名
	 */
	String SWORD_NAME = "sword";

	/**
	 * saber 系统名
	 */
	String SABER_NAME = "saber";

	/**
	 * 顶级父节点id
	 */
	Long TOP_PARENT_ID = 0L;

	/**
	 * 默认密码
	 */
	String DEFAULT_PASSWORD = "123456";
	/**
	 * 默认租户id
	 */
	String DEFAULT_TENANT_ID = "000000";

	/**
	 * 默认密码参数值
	 */
	String DEFAULT_PARAM_PASSWORD = "account.initPassword";

	/**
	 * 数据权限类型
	 */
	Integer DATA_SCOPE_CATEGORY = 1;

	/**
	 * 接口权限类型
	 */
	Integer API_SCOPE_CATEGORY = 2;


}
