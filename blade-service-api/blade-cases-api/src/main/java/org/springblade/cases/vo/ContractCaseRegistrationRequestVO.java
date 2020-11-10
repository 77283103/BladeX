package org.springblade.cases.vo;

import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 案件登记表 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author xhb
 * @date : 2020-10-30 10:04:52
 */
@Getter
@Setter
@ApiModel(description = "案件登记表请求对象")
public class ContractCaseRegistrationRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="案件类型（下拉选）")
	private String caseType;

    @ApiModelProperty(value="案件名称")
	private String caseName;

    @ApiModelProperty(value="案件编号")
	private String caseId;

    @ApiModelProperty(value="案件标的额")
	private BigDecimal caseTargetAmount;

    @ApiModelProperty(value="案由")
	private String caseReason;

    @ApiModelProperty(value="原告")
	private String plaintiff;

    @ApiModelProperty(value="被告")
	private String defendant;

    @ApiModelProperty(value="受理法院")
	private String acceptanceCourt;

    @ApiModelProperty(value="承办法官")
	private String undertakJudge;

    @ApiModelProperty(value="代理律师")
	private String proxyLawyer;

    @ApiModelProperty(value="联系电话")
	private String registerCaseContactNumber;

    @ApiModelProperty(value="开庭时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date courtTime;

    @ApiModelProperty(value="立案/签收时间")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	private Date filingTime;

    @ApiModelProperty(value="涉案单位")
	private String caseRelatedUnit;

    @ApiModelProperty(value="承办部门")
	private String undertakDepartment;

    @ApiModelProperty(value="司法案号")
	private String judicialCaseNumber;

    @ApiModelProperty(value="案件背景")
	private String caseBackground;

    @ApiModelProperty(value="附件上传")
	private String attachedFiles;

    @ApiModelProperty(value="案件状态（字典）")
	private String caseStatus;

    @ApiModelProperty(value="关联合同")
	private String associatedContract;

	@ApiModelProperty(value="案件状态集合")
    private List<String> code;

}
