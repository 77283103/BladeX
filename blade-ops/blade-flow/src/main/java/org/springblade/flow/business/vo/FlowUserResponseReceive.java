package org.springblade.flow.business.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tah
 * 功能：用于接收前台返回的User信息，lombok版本问题引发不可解析故建此类
 * @date 2020-7-29
 */
@Setter
@Getter
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
