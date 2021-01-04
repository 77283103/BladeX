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
 * 媒体类：平面广告拍摄制作合同（关联表1） 实体类
 *
 * @author 张文武
 * @date : 2021-01-04 11:27:45
 */
@Getter
@Setter
@TableName("mtb_production_contract1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtbProductionContract1对象", description = "媒体类：平面广告拍摄制作合同（关联表1）")
public class MtbProductionContract1Entity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 交付形式
	 */
    @ApiModelProperty(value="交付形式")
	private String formDelivery;
	/**
	 * 数量
	 */
    @ApiModelProperty(value="数量")
	private BigDecimal number;
	/**
	 * 要求（规格、精度等）
	 */
    @ApiModelProperty(value="要求（规格、精度等）")
	private String requirements;
	/**
	 * 费用（元，含税）
	 */
    @ApiModelProperty(value="费用（元，含税）")
	private BigDecimal expenses;
	/**
	 * 合同ID
	 */
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty(value="合同ID")
	private Long contractId;

}
