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
 * 履约计划完成内容 实体类
 *
 * @author chenzy
 * @date : 2021-04-20 16:28:57
 */
@Getter
@Setter
@TableName("per_plan_finsh_content")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PerPlanFinshContent对象", description = "履约计划完成内容")
public class PerPlanFinshContentEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 计划完成内容
	 */
    @ApiModelProperty(value="计划完成内容")
	private String planFinshContent;
	/**
	 * 完成时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="完成时间")
	private Date finshTimeUp;
	/**
	 * 完成结果
	 */
    @ApiModelProperty(value="完成结果")
	private String finshContentUp;
	/**
	 * 计划完成时间标识
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="计划完成时间标识")
	private Long planFinshTimeId;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同标识")
	private Long contractId;

}
