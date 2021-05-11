package org.springblade.contract.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.StringUtil;
import org.springframework.format.annotation.DateTimeFormat;

import org.springblade.core.mp.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;


/**
 * 履约服务内容 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author chenzy
 * @date : 2021-04-20 16:02:05
 */
@Getter
@Setter
@ToString
@ApiModel(description = "履约服务内容请求对象")
public class PerServiceContentRequestVO extends BaseEntity{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="服务内容")
	private String serviceContent;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同标识")
	private Long contractId;

	@ApiModelProperty(value="合同交易类型")
	private String contractTranType;

	@ApiModelProperty(value="服务")
	private String service;

	@ApiModelProperty(value="关联业务标识表达式")
	private List<String> businessIdList;

	@ApiModelProperty(value="计划完成时间集合")
	private List<PerPlanFinshTimeRequestVO> perPlanFinshTimeRequestVOList;


	public String generateBusinessIds(){
		if(!StringUtil.isEmpty(this.businessIdList)){
			return String.join(",", this.businessIdList);
		}
		return "";
	}

}
