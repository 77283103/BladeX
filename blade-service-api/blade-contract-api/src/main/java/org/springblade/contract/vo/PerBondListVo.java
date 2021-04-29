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
public class PerBondListVo implements Serializable {

	@JsonSerialize(
		using = ToStringSerializer.class,
		nullsUsing = NullSerializer.class
	)
	@ApiModelProperty("主键id")
	private Long id;

	@ApiModelProperty("合同模板")
	private String contractName;

	@ApiModelProperty("收付款")
	private String colPayType;

	@ApiModelProperty("类型")
	private String type;

	@ApiModelProperty("状态")
	private String contractStatus;

	@ApiModelProperty("保证金id")
	private Long contractBondId;

	@ApiModelProperty("合同缴交时间")
	private Date planPayTime;

	@ApiModelProperty("保证金金额")
	private BigDecimal planPayAmount;

	@ApiModelProperty("结案时间")
	private Date closeCaseDate;

	@ApiModelProperty("实际缴交时间")
	private Date actualPayTime;

	@ApiModelProperty("实际结案时间")
	private Date actualCloseTime;

	@ApiModelProperty("实际缴交金额")
	private BigDecimal actualPayAmount;

	@ApiModelProperty("摘要")
	private String remarks;

	@ApiModelProperty("已付/收金额")
	private String alreadyCollectPay;

}
