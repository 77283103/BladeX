package org.springblade.flow.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 借阅 实体类
 *
 * @author Liu Meng
 */
@Data
@TableName("blade_borrow_flow")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BorrowFlow对象", description = "借阅")
public class BorrowFlowEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 传阅人id
	 */
	@ApiModelProperty(value = "传阅人id")
	private Integer sendPersonId;
	/**
	 * 被传阅人id
	 */
	@ApiModelProperty(value = "被传阅人id")
	private Integer getPersonId;
	/**
	 * 流程名称
	 */
	@ApiModelProperty(value = "流程名称")
	private String processDefinitionName;
	/**
	 * 流程版本
	 */
	@ApiModelProperty(value = "流程版本")
	private String processDefinitionVersion;
	/**
	 * 流程分类
	 */
	@ApiModelProperty(value = "流程分类")
	private String category;
	/**
	 * 任务编号
	 */
	@ApiModelProperty(value = "任务编号")
	private String taskId;
	/**
	 * 流程实例ID
	 */
	@ApiModelProperty(value = "流程实例ID")
	private String processInstanceId;
	/**
	 * 业务绑定ID
	 */
	@ApiModelProperty(value = "业务绑定ID")
	private String businessId;

	/**
	 * 是否已阅（0代表否，1代表是
	 */
	@ApiModelProperty(value = "是否已阅")
	private String readCategory;
}
