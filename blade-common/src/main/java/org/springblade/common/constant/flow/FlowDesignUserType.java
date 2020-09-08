package org.springblade.common.constant.flow;

/**
 * 流程设计时通过什么方式获取用户数据
 * @author tah
 * @date 2020-8-26
 */
public interface FlowDesignUserType {
	/**
	 *	固定用户
	 */
	String USERS = "1";
	/**
	 *  固定岗位
	 */
	String POSTS = "2";
	/**
	 * 关系定义
	 */
	String RELATIONS = "3";
}
