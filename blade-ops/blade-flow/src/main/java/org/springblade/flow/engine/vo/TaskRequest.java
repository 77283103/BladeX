package org.springblade.flow.engine.vo;

import lombok.Data;

import java.util.Map;

/**
 * @author tah
 * @date 2020-7-20
 * 功能：退回时传递信息
 */
@Data
public class TaskRequest {
    private String taskId;
    private String userId;
    private String message;
    private String activityId;
    private String activityName;
    private Map<String, Object> values;
}
