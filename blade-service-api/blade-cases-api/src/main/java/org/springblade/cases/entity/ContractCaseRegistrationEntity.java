package org.springblade.cases.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 案件登记表 实体类
 *
 * @author xhb
 * @date : 2020-10-30 10:04:48
 */
@Getter
@Setter
@TableName("contract_case_registration")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractCaseRegistration对象", description = "案件登记表")
public class ContractCaseRegistrationEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 案件类型（下拉选）
	 */
	@ApiModelProperty(value = "案件类型（下拉选）")
	private String caseType;
	/**
	 * 案件名称
	 */
	@ApiModelProperty(value = "案件名称")
	private String caseName;
	/**
	 * 案件编号
	 */
	@ApiModelProperty(value = "案件编号")
	private String caseId;
	/**
	 * 案件标的额
	 */
	@ApiModelProperty(value = "案件标的额")
	private BigDecimal caseTargetAmount;
	/**
	 * 案由
	 */
	@ApiModelProperty(value = "案由")
	private String caseReason;
	/**
	 * 原告
	 */
	@ApiModelProperty(value = "原告")
	private String plaintiff;
	/**
	 * 被告
	 */
	@ApiModelProperty(value = "被告")
	private String defendant;
	/**
	 * 受理法院
	 */
	@ApiModelProperty(value = "受理法院")
	private String acceptanceCourt;
	/**
	 * 承办法官
	 */
	@ApiModelProperty(value = "承办法官")
	private String undertakJudge;
	/**
	 * 代理律师
	 */
	@ApiModelProperty(value = "代理律师")
	private String proxyLawyer;
	/**
	 * 联系电话
	 */
	@ApiModelProperty(value = "联系电话")
	private String registerCaseContactNumber;
	/**
	 * 开庭时间
	 */
	@ApiModelProperty(value = "开庭时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date courtTime;
	/**
	 * 立案/签收时间
	 */
	@ApiModelProperty(value = "立案/签收时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date filingTime;
	/**
	 * 涉案单位
	 */
	@ApiModelProperty(value = "涉案单位")
	private String caseRelatedUnit;
	/**
	 * 承办部门
	 */
	@ApiModelProperty(value = "承办部门")
	private String undertakDepartment;
	/**
	 * 司法案号
	 */
	@ApiModelProperty(value = "司法案号")
	private String judicialCaseNumber;
	/**
	 * 案件背景
	 */
	@ApiModelProperty(value = "案件背景")
	private String caseBackground;
	/**
	 * 附件上传
	 */
	@ApiModelProperty(value = "附件上传")
	private String attachedFiles;
	/**
	 * 案件状态（字典）
	 */
	@ApiModelProperty(value = "案件状态（字典）")
	private String caseStatus;
	/**
	 * 关联合同
	 */
	@ApiModelProperty(value = "关联合同")
	private String associatedContract;
	/**
	 * 案件处理
	 */
	@ApiModelProperty(value = "案件处理")
	@TableField(exist = false)
	private ContractCaseHandlingEntity handlingEntity;
	/**
	 * 案件结案
	 */
	@ApiModelProperty(value = "案件结案")
	@TableField(exist = false)
	private ContractCaseClosedEntity closedEntity;

}
