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
 * 采购类：新增原物料补充协议--买卖合同 实体类
 *
 * @author 采购类：新增原物料补充协议--买卖合同
 * @date : 2020-12-10 18:50:19
 */
@Getter
@Setter
@TableName("cgl_the_sales_contract_1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglTheSalesContract1对象", description = "采购类：新增原物料补充协议--买卖合同")
public class CglTheSalesContract1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 新增标的序号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="新增标的序号")
	private Integer cglSerialNumber;
	/**
	 * 统一料号
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="统一料号")
	private Integer cglMaterialNo;
	/**
	 * 品名
	 */
    @ApiModelProperty(value="品名")
	private String cglOfTheGoods;
	/**
	 * 包装规格
	 */
    @ApiModelProperty(value="包装规格")
	private String cglSpecifications;
	/**
	 * 未税价格
	 */
    @ApiModelProperty(value="未税价格")
	private BigDecimal cglPrice;
	/**
	 * 税率
	 */
    @ApiModelProperty(value="税率")
	private Double cglRate;
	/**
	 * 合同ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value="合同ID")
	private Long contractId;
}
