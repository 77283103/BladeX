package org.springblade.contract.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;

import java.math.BigDecimal;
import java.util.Date;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import org.springblade.core.mp.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


/**
 * 履约收付款 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author chenzy
 * @date : 2021-04-25 10:32:28
 */
@Getter
@Setter
@ToString
@ApiModel(description = "履约收付款请求对象")
public class PerCollectPayRequestVO extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 内容
	 */
	@ApiModelProperty(value="内容")
	private String content;
	/**
	 * 计划完成时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="计划完成时间")
	private Date planFinshTime;
	/**
	 * 计划金额
	 */
	@ApiModelProperty(value="计划金额")
	private BigDecimal planAmount;
	/**
	 * 完成时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="完成时间")
	private Date finshTime;
	/**
	 * 完成金额
	 */
	@ApiModelProperty(value="完成金额")
	private BigDecimal finshAmount;
	/**
	 * 合同标识
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同标识")
	private Long contractId;
	/**
	 * 合同交易类型
	 */
	@ApiModelProperty(value="合同交易类型")
	private String contractTranType;


	/**
	 * 查询条件
	 */
	@ApiModelProperty(value = "合同名称")
	private String contractName;

}
