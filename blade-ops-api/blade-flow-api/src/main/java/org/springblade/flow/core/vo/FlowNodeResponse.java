package org.springblade.flow.core.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author tah
 * 功能：用于接收前台返回的Node信息，lombok版本问题引发不可解析故建此类
 * @date 2020-7-29
 */
@Setter
@Getter
@NoArgsConstructor
public class FlowNodeResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 流程参数
	 */
	private Map<String, Object> variables;
	/**
	 * 审批意见
	 */
	private String comment;
	/**
	 * 任务id
	 */
	private String taskId;
    /**
     * 节点id
     */
    private String id;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 执行人的id
     */
    private String userId;
    /**
     * 执行人姓名
     */
    private String userName;

    /**
     * 任务节点结束时间
     */
    private Date endTime;
	/**
	 * 处理人信息列表
	 */
	private List<FlowUserResponse> userResponseList;
	/**
	 * 是否为结束节点
	 */
	private boolean end;
	/**
	 * 是否可以选择节点
	 */
	private boolean enableChooseNode;
}
