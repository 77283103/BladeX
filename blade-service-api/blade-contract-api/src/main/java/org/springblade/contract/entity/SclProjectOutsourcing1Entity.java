package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 生产类：生产项目外包服务合同 实体类
 *
 * @author kx
 * @date : 2020-12-11 11:05:04
 */
@Getter
@Setter
@TableName("scl_project_outsourcing_1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "SclProjectOutsourcing1对象", description = "生产类：生产项目外包服务合同")
public class SclProjectOutsourcing1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="序号")
	private Integer sclNumber;
	/**
	 * 服务需求项目名称
	 */
    @ApiModelProperty(value="服务需求项目名称")
	private String sclAcount;
	/**
	 * 计量单位
	 */
    @ApiModelProperty(value="计量单位")
	private String sclMeasuring;
	/**
	 * 未税单价
	 */
    @ApiModelProperty(value="未税单价")
	private BigDecimal sclPrice;
	/**
	 * 服务内容及要求概述
	 */
    @ApiModelProperty(value="服务内容及要求概述")
	private String sclOverview;

	@ApiModelProperty(value="关联合同id")
	@JsonSerialize(using = ToStringSerializer.class)
    private Long contractId;
}
