package org.springblade.flow.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : bruce.liu
 * @date : 2019/12/616:24
 */
@Getter
@Setter
public class FlowNodeVo implements Serializable {
    /**
     * 节点id
     */
    private String nodeId;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 执行人姓名
     */
    private String userName;
    /**
     * 任务节点结束时间
     */
    private Date endTime;

    @Override
    public String toString() {
        return "FlowNodeVo{" +
                "nodeId='" + nodeId + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", userName='" + userName + '\'' +
                ", endTime=" + endTime +
                '}';
    }
}
