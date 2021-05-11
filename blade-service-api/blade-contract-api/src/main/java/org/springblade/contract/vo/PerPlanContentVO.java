package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class PerPlanContentVO implements Serializable {

	@ApiModelProperty(value="主键标识")
	private Long id;

	@ApiModelProperty(value="计划完成内容")
	private String planFinshContent;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="完成时间-被动更新")
	private Date finshTimeUp;

	@ApiModelProperty(value="完成结果-被动更新")
	private String finshContentUp;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="计划完成时间标识")
	private Long planFinshTimeId;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同标识")
	private Long contractId;


}
