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
import java.util.List;

@Data
public class PerPlanTimeVO implements Serializable {

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="主键标识")
	private Long id;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="计划完成时间")
	private Date planFinshTime;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="服务内容标识")
	private Long serviceContentId;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同标识")
	private Long contractId;

	@ApiModelProperty(value="计划完成时间对应计划完成内容集合")
	private List<PerPlanContentVO> perPlanContentVOList;

}
