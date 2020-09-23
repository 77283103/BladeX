package org.springblade.flow.business.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;

/**
 * 流程定义信息表 返回模型VO
 *
 * @author tianah
 * @date 2020-8-27
 */
@Setter
@Getter
@ApiModel(description = "流程定义信息表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ProcessResponseVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="流程定义主键",required = true)
	private String processDefinitionId;

	@ApiModelProperty(value="业务类型",required = true)
	private String businessType;

	@ApiModelProperty(value="流程使用范围")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long deptRange;

	@ApiModelProperty(value="流程参数")
	private String params;

	@ApiModelProperty(value = "流程启动条件")
	private String startCondition;
}
