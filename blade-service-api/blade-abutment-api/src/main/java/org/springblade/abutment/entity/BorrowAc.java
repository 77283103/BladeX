package org.springblade.abutment.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xhbbo
 */
@Data
public class BorrowAc {
	@ApiModelProperty(value = "资料类型")
	private String fd_type;
	@ApiModelProperty(value = "借阅周期起始时间")
	private String fd_begin;
	@ApiModelProperty(value = "借阅周期结束时间")
	private String fd_end;
	@ApiModelProperty(value = "申请编号")
	private String applicationId;
	@ApiModelProperty(value = "申请人")
	private String applicant;
	@ApiModelProperty(value = "申请部门")
	private String appDepartment;
	@ApiModelProperty(value = "借阅方式")
	private String fd_method;
	@ApiModelProperty(value = "资料名称")
	private String fd_name;
	@ApiModelProperty(value = "事由说明")
	private String fd_reason;
}
