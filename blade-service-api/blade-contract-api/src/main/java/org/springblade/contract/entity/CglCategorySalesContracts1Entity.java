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
 * 采购类：买卖合同（行销品） 实体类
 *
 * @author 采购类：买卖合同（行销品）
 * @date : 2020-12-10 18:58:22
 */
@Getter
@Setter
@TableName("cgl_category_sales_contracts1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglCategorySalesContracts1对象", description = "采购类：买卖合同（行销品）")
public class CglCategorySalesContracts1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 品名
	 */
    @ApiModelProperty(value="品名")
	private String cglOfTheGoods;
	/**
	 * 材质/规格
	 */
    @ApiModelProperty(value="材质/规格")
	private String cglSpecification;
	/**
	 * 未税单价
	 */
    @ApiModelProperty(value="未税单价")
	private BigDecimal cglUnitPrice;
	/**
	 * 合作期限
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="合作期限")
	private Date cglCooperationTerm;
	/**
	 * 备注
	 */
    @ApiModelProperty(value="备注")
	private String cglNote;

	/**
	 * 合同ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同ID")
	private Long contractId;
}
