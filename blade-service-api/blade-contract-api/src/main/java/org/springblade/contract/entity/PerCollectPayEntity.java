package org.springblade.contract.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springblade.core.tool.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 履约收付款 实体类
 *
 * @author chenzy
 * @date : 2021-04-25 10:32:27
 */
@Getter
@Setter
@TableName("per_collect_pay")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PerCollectPay对象", description = "履约收付款")
public class PerCollectPayEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 内容
	 */
    @ApiModelProperty(value="内容")
	private String content;
	/**
	 * 计划完成时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="计划完成时间")
	private Date planFinshTime;
	/**
	 * 计划金额
	 */
    @ApiModelProperty(value="计划金额")
	private BigDecimal planAmount;
	/**
	 * 完成时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="完成时间")
	private Date finshTime;
	/**
	 * 完成金额
	 */
    @ApiModelProperty(value="完成金额")
	private BigDecimal finshAmount;
	/**
	 * 合同标识
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同标识")
	private Long contractId;
	/**
	 * 合同交易类型
	 */
	@ApiModelProperty(value="合同交易类型")
    private String contractTranType;

}
