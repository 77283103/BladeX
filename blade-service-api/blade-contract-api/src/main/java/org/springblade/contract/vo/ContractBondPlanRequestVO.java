package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * 保证金 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author szw
 * @date : 2020-11-04 18:28:10
 */
@Getter
@Setter
@ToString
@ApiModel(description = "保证金请求对象")
public class ContractBondPlanRequestVO extends BaseEntity{

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "保证金名称")
	private String bondName;
	@ApiModelProperty(value = "服务内容")
	private String name;
	@ApiModelProperty(value = "交易类别")
	private String type;
	@NotNull(message = "计划缴纳时间不能为空")
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "计划缴纳时间", required = true)
	private Date planPayTime;
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "实际缴纳时间")
	private Date actualPayTime;
	@ApiModelProperty(value = "保证金履约金额缴纳期限")
	private String  contractBond;


}
