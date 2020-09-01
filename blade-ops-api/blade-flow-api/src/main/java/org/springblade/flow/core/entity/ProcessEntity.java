package org.springblade.flow.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Map;


/**
 * 流程定义信息表 实体类
 *
 * @author tianah
 * @date 2020-8-27
 */
@Data
@TableName("blade_process")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Process对象", description = "流程定义信息表")
public class ProcessEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 流程定义主键
	 */
	@ApiModelProperty(value = "流程定义主键")
	private String processDefinitionId;
	/**
	 * 业务类型
	 */
	@ApiModelProperty(value = "业务类型")
	private String businessType;
	/**
	 * 流程使用范围
	 */
	@ApiModelProperty(value = "流程使用范围")
	private Long deptRange;
	/**
	 * 流程启动条件
	 */
	@ApiModelProperty(value = "流程启动条件")
	private String params;
}
