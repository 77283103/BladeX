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
 * 合同用印 实体类
 *
 * @author 合同用印
 * @date : 2020-11-05 09:29:26
 */
@Getter
@Setter
@TableName("contract_seal_using_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractSealUsingInfo对象", description = "合同用印")
public class ContractSealUsingInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 关联合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="关联合同ID")
	private Long refContractId;
	/**
	 * 用印时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="用印时间")
	private Date signTime;
	/**
	 * 用印人
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="用印人")
	private String  signPerson;
	/**
	 * 承办单位
	 */
    @ApiModelProperty(value="承办单位")
	private String manageUnit;
	/**
	 * 承办部门
	 */
    @ApiModelProperty(value="承办部门")
	private String manageDept;
	/**
	 * 承办人
	 */
    @ApiModelProperty(value="承办人")
	private String manager;
	/**
	 * 承办时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="承办时间")
	private Date manageDate;
	/**
	 * 实际用印份数
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="实际用印份数")
	private Integer actualPrintingCount;
	/**
	 * 实际用印类型（字典）
	 */
    @ApiModelProperty(value="实际用印类型（字典）")
	private String actualPrintingType;
	/**
	 * 合同类型（字典）
	 */
    @ApiModelProperty(value="合同类型（字典）")
	private String contractType;
	/**
	 * 用印说明
	 */
    @ApiModelProperty(value="用印说明")
	private String signRemark;

}
