package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class PerServiceContentListResponseVO implements Serializable {

	@JsonSerialize(
		using = ToStringSerializer.class,
		nullsUsing = NullSerializer.class
	)
	@ApiModelProperty("合同id")
	private Long id;

	@ApiModelProperty("合同名称")
	private String contractName;

	@ApiModelProperty("服务")
	private String service;

	@ApiModelProperty("合同计划类型")
	private String contractTranType;

	@ApiModelProperty("合同状态")
	private String contractStatus;

	@ApiModelProperty("合同内容")
	private String serviceContent;

	@DateTimeFormat(
		pattern = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
		pattern = "yyyy-MM-dd HH:mm:ss"
	)
	@ApiModelProperty("计划完成时间")
	private Date planFinshTime;

	@ApiModelProperty("计划完成内容")
	private String planFinshContent;

	@DateTimeFormat(
		pattern = "yyyy-MM-dd HH:mm:ss"
	)
	@JsonFormat(
		pattern = "yyyy-MM-dd HH:mm:ss"
	)
	@ApiModelProperty("完成时间")
	private Date finshTimeUp;

	@ApiModelProperty("完成内容")
	private String finshContentUp;

	@JsonSerialize(
		using = ToStringSerializer.class,
		nullsUsing = NullSerializer.class
	)
	@ApiModelProperty("服务内容id")
	private Long serviceContentId;

	@JsonSerialize(
		using = ToStringSerializer.class,
		nullsUsing = NullSerializer.class
	)

	@ApiModelProperty("计划完成时间id")
	private Long planFinshTimeId;

	@ApiModelProperty("关联业务标识表达式")
	private String businessIds;
}
