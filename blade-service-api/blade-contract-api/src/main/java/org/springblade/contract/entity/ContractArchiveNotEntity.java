package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 未归档原因 实体类
 *
 * @author 未归档原因
 * @date : 2020-11-09 15:19:16
 */
@Getter
@Setter
@TableName("contract_archive_not")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractArchiveNot对象", description = "未归档原因")
public class ContractArchiveNotEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 关联合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="关联合同ID")
	private Long contractId;
	/**
	 * 合同编号
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同编号")
	private String contractNumber;
	/**
	 * 用印日期
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="用印日期")
	private Date printDate;
	/**
	 * 对方公司名称
	 */
    @ApiModelProperty(value="对方公司名称")
	private String otherCompanyName;
	/**
	 * 预计归档时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="预计归档时间")
	private Date estimateArchiveDate;
	/**
	 * 未归档原因
	 */
    @ApiModelProperty(value="未归档原因")
	private String notArchiveReason;
	/**
	 * 归档原因
	 */
    @ApiModelProperty(value="归档原因")
	private String archiveReason;
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
	 * 经办人
	 */
    @ApiModelProperty(value="经办人")
	private String manager;
	/**
	 * 经办时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="经办时间")
	private Date manageDate;

}
