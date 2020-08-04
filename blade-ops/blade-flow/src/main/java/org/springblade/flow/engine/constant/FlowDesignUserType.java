package org.springblade.flow.engine.constant;

/**
 * 流程设计时通过什么方式获取用户数据
 * @author tah
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
