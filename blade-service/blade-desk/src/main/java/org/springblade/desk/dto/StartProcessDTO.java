package org.springblade.desk.dto;

import lombok.Getter;
import lombok.Setter;
import org.springblade.desk.entity.ProcessLeave;
import org.springblade.flow.core.vo.FlowNodeRequest;

import java.io.Serializable;
import java.util.List;

/**
 * 流程发起时获取数据对象，每一种业务对应不同的DTO
 *
 * @author tah
 * @date 2020-9-2
 */
@Setter
@Getter
public class StartProcessDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *  请假实体类
	 */
	private ProcessLeave processLeave;

	/**
	 * 下一候选审批节点及人员List
	 */
	private List<FlowNodeRequest> flowNodeRequestList;
}



