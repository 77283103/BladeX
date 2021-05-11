package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springblade.core.tool.jackson.JsonUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PerServiceContentVO implements Serializable {

	@JsonSerialize(
		using = ToStringSerializer.class,
		nullsUsing = NullSerializer.class
	)
	@ApiModelProperty(value = "主键id")
	private Long id;

	@ApiModelProperty(value="服务内容")
	private String serviceContent;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同标识")
	private Long contractId;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同交易类型")
	private String contractTranType;

	@ApiModelProperty(value="服务")
	private String service;

	@ApiModelProperty(value="关联业务标识表达式")
	private String businessIds;

	@ApiModelProperty(value="内容对应计划时间集合")
	private List<PerPlanTimeVO> planTimeVOList;


	public static void main(String[] args) {
		PerServiceContentVO perServiceContentVO = new PerServiceContentVO();
		List<PerPlanTimeVO> planTimeVOList =new ArrayList<>();
		List<PerPlanContentVO> perPlanContentVOList = new ArrayList<>();
		PerPlanContentVO perPlanContentVO = new PerPlanContentVO();

		perServiceContentVO.setId(66666666L);
		perServiceContentVO.setService("接收");
		perServiceContentVO.setContractTranType("有效期交易");
		perServiceContentVO.setServiceContent("测试服务内容");
		perServiceContentVO.setBusinessIds("我方、子公司、向对方ids表达式（如有需要，可传名称表达式（表达式为逗号分隔，多方起草时使用，其他起草默认为空））");

		perPlanContentVO.setId(88888888L);
		perPlanContentVO.setPlanFinshContent("测试计划完成内容。。。。");
		perPlanContentVO.setContractId(1L);
		perPlanContentVOList.add(perPlanContentVO);
		PerPlanTimeVO perPlanTimeVO = new PerPlanTimeVO();

		perPlanTimeVO.setId(77777777L);
		perPlanTimeVO.setPlanFinshTime(new Date());
		perPlanTimeVO.setServiceContentId(66666666L);
		perPlanTimeVO.setContractId(1L);
		perPlanTimeVO.setPerPlanContentVOList(perPlanContentVOList);
		planTimeVOList.add(perPlanTimeVO);
		perServiceContentVO.setPlanTimeVOList(planTimeVOList);

		System.out.println(JsonUtil.toJson(perServiceContentVO));
	}

}
