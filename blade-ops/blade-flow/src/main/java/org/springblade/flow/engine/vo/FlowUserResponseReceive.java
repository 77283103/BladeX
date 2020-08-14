package org.springblade.flow.engine.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tah
 * 功能：用于接收前台返回的User信息，lombok版本问题引发不可解析故建此类
 * @date 2020-7-29
 */
@Data
@NoArgsConstructor
public class FlowUserResponseReceive implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private String id;
	/**
	 * 用户姓名
	 */
	private String name;
}
