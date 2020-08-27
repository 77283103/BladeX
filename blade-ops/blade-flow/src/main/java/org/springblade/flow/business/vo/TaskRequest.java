package org.springblade.flow.business.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tah
 * @date 2020-7-20
 * 功能：退回时传递信息
 */
@Setter
@Getter
public class TaskRequest implements Serializable {
	/**
	 * 运行示例id
	 */
    private String taskId;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 审批意见
	 */
    private String message;
	/**
	 * 节点id
	 */
	private String nodeId;
	/**
	 * 节点名称
	 */
    private String nodeName;
}
