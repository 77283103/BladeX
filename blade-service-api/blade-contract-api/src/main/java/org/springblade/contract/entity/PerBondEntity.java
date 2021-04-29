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

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 履约计划保证金 实体类
 *
 * @author chenzy
 * @date : 2021-04-27 17:06:20
 */
@Getter
@Setter
@TableName("per_bond")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PerBond对象", description = "履约计划保证金")
public class PerBondEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 合同缴交时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="合同缴交时间")
	private Date actualPayTime;
	/**
	 * 合同缴交金额
	 */
    @ApiModelProperty(value="合同缴交金额")
	private BigDecimal actualPayAmount;
	/**
	 * 结案时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="结案时间")
	private Date actualCloseTime;
	/**
	 * 摘要
	 */
    @ApiModelProperty(value="摘要")
	private String remarks;
	/**
	 * 合同金额
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同金额")
	private Long contractId;
	/**
	 * 合同保证金标识
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同保证金标识")
	private Long bondId;
	/**
	 * 已收/付款
	 */
    @ApiModelProperty(value="已收/付款")
	private BigDecimal alreadyCollectPay;

}
