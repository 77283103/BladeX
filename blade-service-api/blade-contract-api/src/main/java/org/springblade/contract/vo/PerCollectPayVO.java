package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tool.jackson.JsonUtil;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PerCollectPayVO implements Serializable {

	@ApiModelProperty(value="主键id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	@ApiModelProperty(value="内容")
	private String content;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="计划完成时间")
	private Date planFinshTime;

	@ApiModelProperty(value="计划金额")
	private BigDecimal planAmount;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="完成时间")
	private Date finshTime;

	@ApiModelProperty(value="完成金额")
	private BigDecimal finshAmount;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同标识")
	private Long contractId;

	@ApiModelProperty(value="合同交易类型")
	private String contractTranType;


	public static void main(String[] args) {
		List<PerCollectPayVO> perCollectPayVOList = new ArrayList<>();
		PerCollectPayVO perCollectPayVO = new PerCollectPayVO();
		perCollectPayVO.setId(123L);
		perCollectPayVO.setContent("测试内容");
		perCollectPayVO.setContractId(1L);
		perCollectPayVO.setContractTranType("合同交易类型");
		perCollectPayVO.setFinshAmount(null);
		perCollectPayVO.setFinshTime(null);
		perCollectPayVO.setPlanAmount(new BigDecimal(10.0));
		perCollectPayVO.setPlanFinshTime(new Date());
		perCollectPayVOList.add(perCollectPayVO);
		System.out.println(JsonUtil.toJson(perCollectPayVOList));
	}

}
