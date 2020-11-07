package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 借阅申请 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author xhb
 * @date : 2020-10-30 09:27:03
 */
@Getter
@Setter
@ApiModel(description = "借阅申请请求对象")
public class ContractBorrowApplicationRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="申请编号")
	private String applicationId;

    @ApiModelProperty(value="申请人")
	private String applicant;

    @ApiModelProperty(value="申请部门")
	private String applicationDepartment;

    @ApiModelProperty(value="资料类型")
	private String dataType;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="借阅周期（起始时间）")
	private Date borrowCycleStart;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="借阅周期（结束时间）")
	private Date borrowCycleEnd;

    @ApiModelProperty(value="借阅方式")
	private String borrowMode;

    @ApiModelProperty(value="资料名称")
	private String dataName;

    @ApiModelProperty(value="事由说明")
	private String explanation;

    @ApiModelProperty(value="进度")
	private String borrowSchedule;

    @ApiModelProperty(value="借阅状态")
	private String borrowStatus;

}
