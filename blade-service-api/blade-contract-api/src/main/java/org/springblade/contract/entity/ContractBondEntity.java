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
 * 保证金 实体类
 *
 * @author szw
 * @date : 2020-11-04 18:28:10
 */
@Getter
@Setter
@TableName("contract_bond")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractBond对象", description = "保证金")
public class ContractBondEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 保证金名称
	 */
	@ApiModelProperty(value = "保证金名称", required = true)
	private String bondName;
	/**
	 * 有无保证金(字典)
	 */
	@ApiModelProperty(value = "有无保证金(字典)", required = true)
	private String isNotBond;
	/**
	 * 保证金类别(字典)
	 */
	@NotBlank(message = "保证金类别(字典)不能为空")
	@ApiModelProperty(value = "保证金类别(字典)", required = true)
	private String type;
	/**
	 * 关联合同数量
	 */
	@ApiModelProperty(value = "关联合同数量")
	private Integer contractNumber;
	/**
	 * 币种
	 */
	@ApiModelProperty(value = "币种", required = true)
	private String currencyCategory;
	/**
	 * 计划缴纳时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "计划缴纳时间", required = true)
	private Date planPayTime;
	/**
	 * 计划缴纳金额
	 */
	@ApiModelProperty(value = "计划缴纳金额", required = true)
	private BigDecimal planPayAmount;
	/**
	 * 实际缴纳时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "实际缴纳时间")
	private Date actualPayTime;
	/**
	 * 实际缴纳金额
	 */
	@ApiModelProperty(value = "实际缴纳金额")
	private BigDecimal actualPayAmount;
	/**
	 * 计划退回时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "计划退回时间", required = true)
	private Date planReturnTime;
	/**
	 * 计划退回金额
	 */
	@ApiModelProperty(value = "计划退回金额", required = true)
	private BigDecimal planReturnAmount;
	/**
	 * 实际退回时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "实际退回时间")
	private Date actualReturnTime;
	/**
	 * 实际退回金额
	 */
	@ApiModelProperty(value = "实际退回金额")
	private BigDecimal actualReturnAmount;

	/**
	 * 退回条件
	 */
	@ApiModelProperty(value = "退回条件", required = true)
	private String returnCondition;
	/**
	 * 相对方id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "实际退回金额")
	private Long counterpartId;

	/**
	 * 厂别
	 */
	@ApiModelProperty(value = "厂别")
	private String factories;


}
