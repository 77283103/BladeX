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
 * 合同归档 实体类
 *
 * @author 合同归档
 * @date : 2020-11-05 09:41:38
 */
@Getter
@Setter
@TableName("contract_archive")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractArchive对象", description = "合同归档")
public class ContractArchiveEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 关联合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="关联合同ID")
	private Long contractId;
	/**
	 * 履约完成时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="履约完成时间")
	private Date performanceCompleteTime;
	/**
	 * 用印公司
	 */
    @ApiModelProperty(value="用印公司")
	private String printCompany;
	/**
	 * 归档月份
	 */
    @ApiModelProperty(value="归档月份")
	private String archiveMonth;
	/**
	 * 归档序号
	 */
    @ApiModelProperty(value="归档序号")
	private String archiveNumber;
	/**
	 * 合同用印发起部门
	 */
    @ApiModelProperty(value="合同用印发起部门")
	private String contractPrintInitDept;
	/**
	 * 用印申请发起时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="用印申请发起时间")
	private Date printApplyInitTime;
	/**
	 * 用印申请人
	 */
    @ApiModelProperty(value="用印申请人")
	private String printApplicant;
	/**
	 * 用印申请单号
	 */
    @ApiModelProperty(value="用印申请单号")
	private String printApplyNumber;
	/**
	 * 合同主要内容
	 */
    @ApiModelProperty(value="合同主要内容")
	private String contractMainContent;
	/**
	 * 归档描述
	 */
    @ApiModelProperty(value="归档描述")
	private String archiveDescription;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String remark;
	/**
	 * 经办单位
	 */
    @ApiModelProperty(value="经办单位")
	private String manageUnit;
	/**
	 * 经办部门
	 */
    @ApiModelProperty(value="经办部门")
	private String manageDept;
	/**
	 * 归档日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="归档日期")
	private Date archiveDate;
	/**
	 * 归档人
	 */
    @ApiModelProperty(value="归档人")
	private String archiver;

}
