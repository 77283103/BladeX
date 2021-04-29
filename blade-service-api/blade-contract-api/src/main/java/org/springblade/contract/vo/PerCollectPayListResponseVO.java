package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PerCollectPayListResponseVO implements Serializable {

	@JsonSerialize(
		using = ToStringSerializer.class,
		nullsUsing = NullSerializer.class
	)
	@ApiModelProperty("合同id")
	private Long id;

	@ApiModelProperty("合同名称")
	private String contractName;

	@ApiModelProperty("收付款类型")
	private String colPayType;

	@ApiModelProperty("合同状态")
	private String contractStatus;

	@ApiModelProperty("内容")
	private String content;

	@ApiModelProperty("计划完成时间")
	private Date planFinshTime;

	@ApiModelProperty("计划金额")
	private BigDecimal planAmount;

	@ApiModelProperty("完成时间")
	private Date finshTime;

	@ApiModelProperty("完成金额")
	private BigDecimal finshAmount;

	@ApiModelProperty("合同计划类型")
	private String contractTranType;

	@JsonSerialize(
		using = ToStringSerializer.class,
		nullsUsing = NullSerializer.class
	)
	@ApiModelProperty("履约收付款id")
	private Long collectPayId;

}
