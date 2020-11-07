package org.springblade.cases.entity;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 案件处理 实体类
 *
 * @author xhb
 * @date : 2020-10-30 10:03:52
 */
@Getter
@Setter
@TableName("contract_case_handling")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractCaseHandling对象", description = "案件处理")
public class ContractCaseHandlingEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 处理案件对应标号
	 */
	@ApiModelProperty(value = "处理案件对应标号")
	private String handlingCaseId;
	/**
	 * 立案时间
	 */
	@ApiModelProperty(value = "立案时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date filingDate;
	/**
	 * 立案司法机关
	 */
	@ApiModelProperty(value = "立案司法机关")
	private String filingJudicialOrgan;
	/**
	 * 所在地
	 */
	@ApiModelProperty(value = "所在地")
	private String location;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remarks;
	/**
	 * 财产保全立案时间
	 */
	@ApiModelProperty(value = "财产保全立案时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date propertyPreservationCaseFilingDate;
	/**
	 * 司法机关名称
	 */
	@ApiModelProperty(value = "司法机关名称")
	private String filingJudicialOrganName;
	/**
	 * 承办法官
	 */
	@ApiModelProperty(value = "承办法官")
	private String undertakJudge;
	/**
	 * 财产保全案号
	 */
	@ApiModelProperty(value = "财产保全案号")
	private String propertyPreservationCaseNumber;
	/**
	 * 联系电话
	 */
	@ApiModelProperty(value = "联系电话")
	private String handleCaseContactNumber;
	/**
	 * 解除担保时间
	 */
	@ApiModelProperty(value = "解除担保时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date releaseOfGuaranteeDate;
	/**
	 * 保全结果
	 */
	@ApiModelProperty(value = "保全结果")
	private String preservationResult;
	/**
	 * 开庭时间
	 */
	@ApiModelProperty(value = "开庭时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date courtDate;
	/**
	 * 司法机关名称（开庭）
	 */
	@ApiModelProperty(value = "司法机关名称（开庭）")
	private String courtFilingJudicialOrganName;
	/**
	 * 庭审概要
	 */
	@ApiModelProperty(value = "庭审概要")
	private String trialSummary;
	/**
	 * 答辩意见
	 */
	@ApiModelProperty(value = "答辩意见")
	private String reply;
	/**
	 * 结案方式
	 */
	@ApiModelProperty(value = "结案方式")
	private String caseCloseMethod;
	/**
	 * 裁判文书编号
	 */
	@ApiModelProperty(value = "裁判文书编号")
	private String judgmentDocumentNumber;
	/**
	 * 裁决的性质
	 */
	@ApiModelProperty(value = "裁决的性质")
	private String natureOfTheRuling;
	/**
	 * 裁判金额
	 */
	@ApiModelProperty(value = "裁判金额")
	private BigDecimal refereeAmount;
	/**
	 * 裁判结果
	 */
	@ApiModelProperty(value = "裁判结果")
	private String refereeResult;
	/**
	 * 进展描述
	 */
	@ApiModelProperty(value = "进展描述")
	private String progressDescription;
	/**
	 * 进展发生时间
	 */
	@ApiModelProperty(value = "进展发生时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date timeOfProgression;
	/**
	 * 受理法院
	 */
	@ApiModelProperty(value = "受理法院")
	private String acceptanceCourt;
	/**
	 * 上传附件
	 */
	@ApiModelProperty(value = "上传附件")
	private String attachedFiles;

}
