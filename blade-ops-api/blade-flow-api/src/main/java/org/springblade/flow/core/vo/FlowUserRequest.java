package org.springblade.flow.core.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tah
 * @date 2020-7-29
 */
@Setter
@Getter
@Builder
public class FlowUserRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 用户姓名
	 */
	private String name;
	/**
	 * 是否xml设置的人员，用于区分用户在前台选择的人员
	 */
	private boolean xmlUser;
}
