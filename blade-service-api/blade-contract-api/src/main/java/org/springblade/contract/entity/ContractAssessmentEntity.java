package org.springblade.contract.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springblade.core.tool.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 合同评估表 实体类
 *
 * @author 合同评估表
 * @date : 2020-11-05 09:37:39
 */
@Getter
@Setter
@TableName("contract_assessment")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractAssessment对象", description = "合同评估表")
public class ContractAssessmentEntity extends BaseEntity {

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
