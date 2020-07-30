package org.springblade.flow.engine.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tah
 * @date 2020-7-29
 */
@Data
public class FlowUserResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 用户姓名
	 */
	private String userName;
}
