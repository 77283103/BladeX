package org.springblade.abutment.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.entity.ContractBorrowReturnEntity;

import java.util.Date;

/**
 * @author xhbbo
 */
@Data
public class BorrowAc {
	@ApiModelProperty(value = "资料类型")
	private String dataType;
	@ApiModelProperty(value = "借阅周期起始时间")
	private String acCycleStart;
	@ApiModelProperty(value = "借阅周期结束时间")
	private String acCycleEnd;
	@ApiModelProperty(value = "申请编号")
	private String applicationId;
	@ApiModelProperty(value = "申请人")
	private String applicant;
	@ApiModelProperty(value = "申请部门")
	private String appDepartment;
	@ApiModelProperty(value = "借阅方式")
	private String borrowMode;
	@ApiModelProperty(value = "资料名称")
	private String dataName;
	@ApiModelProperty(value = "事由说明")
	private String explanation;
}
