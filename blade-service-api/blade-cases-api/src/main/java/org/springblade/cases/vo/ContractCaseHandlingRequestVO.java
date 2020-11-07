package org.springblade.cases.vo;

import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 案件处理 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author xhb
 * @date : 2020-10-30 10:04:15
 */
@Getter
@Setter
@ApiModel(description = "案件处理请求对象")
public class ContractCaseHandlingRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="处理案件对应标号")
	private String handlingCaseId;

    @ApiModelProperty(value="立案时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date filingDate;

    @ApiModelProperty(value="立案司法机关")
	private String filingJudicialOrgan;

    @ApiModelProperty(value="所在地")
	private String location;

    @ApiModelProperty(value="备注")
	private String remarks;

    @ApiModelProperty(value="财产保全立案时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date propertyPreservationCaseFilingDate;

    @ApiModelProperty(value="司法机关名称")
	private String filingJudicialOrganName;

    @ApiModelProperty(value="承办法官")
	private String undertakJudge;

    @ApiModelProperty(value="财产保全案号")
	private String propertyPreservationCaseNumber;

    @ApiModelProperty(value="联系电话")
	private String handleCaseContactNumber;

    @ApiModelProperty(value="解除担保时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date releaseOfGuaranteeDate;

    @ApiModelProperty(value="保全结果")
	private String preservationResult;

    @ApiModelProperty(value="开庭时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date courtDate;

    @ApiModelProperty(value="司法机关名称（开庭）")
	private String courtFilingJudicialOrganName;

    @ApiModelProperty(value="庭审概要")
	private String trialSummary;

    @ApiModelProperty(value="答辩意见")
	private String reply;

    @ApiModelProperty(value="结案方式")
	private String caseCloseMethod;

    @ApiModelProperty(value="裁判文书编号")
	private String judgmentDocumentNumber;

    @ApiModelProperty(value="裁决的性质")
	private String natureOfTheRuling;

    @ApiModelProperty(value="裁判金额")
	private BigDecimal refereeAmount;

    @ApiModelProperty(value="裁判结果")
	private String refereeResult;

    @ApiModelProperty(value="进展描述")
	private String progressDescription;

    @ApiModelProperty(value="进展发生时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date timeOfProgression;

    @ApiModelProperty(value="受理法院")
	private String acceptanceCourt;

    @ApiModelProperty(value="上传附件")
	private String attachedFiles;

}
