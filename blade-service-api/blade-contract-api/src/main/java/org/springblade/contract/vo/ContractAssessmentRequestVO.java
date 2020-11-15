package org.springblade.contract.vo;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import org.springblade.core.mp.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;


/**
 * 合同评估表 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 合同评估表
 * @date : 2020-11-05 09:37:39
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同评估表请求对象")
public class ContractAssessmentRequestVO extends BaseEntity{

	private static final long serialVersionUID = 1L;

	/**
	 * 合同id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同id")
	private Long contractId;
	/**
	 * 合同评估编号
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同评估编号")
	private Long contractEvaluationId;
	/**
	 * 合同执行情况评估
	 */
	@ApiModelProperty(value="合同执行情况评估")
	private String contractPerformanceEvaluation;
	/**
	 * 合同履行情况说明
	 */
	@ApiModelProperty(value="合同履行情况说明")
	private String contractPerformanceDescription;
	/**
	 * 向对方配合度（尤/良/差）
	 */
	@ApiModelProperty(value="向对方配合度（尤/良/差）")
	private String accordingDegreeCooperation;
	/**
	 * 相关附件
	 */
	@ApiModelProperty(value="相关附件")
	private String attachedFiles;
	/**
	 * 评估说明
	 */
	@ApiModelProperty(value="评估说明")
	private String assessmentRemark;

}
