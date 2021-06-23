package org.springblade.abutment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
@ApiModel(value = "获取组织及人员信息的入参")
public class OrgParme {
	@ApiModelProperty(value = "可选参数时间")
	private String  parme;
}
