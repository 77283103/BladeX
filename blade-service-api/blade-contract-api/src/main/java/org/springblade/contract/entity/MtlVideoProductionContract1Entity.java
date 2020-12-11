package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;


/**
 * 媒体类：视频制作合同关联表 实体类
 *
 * @author 媒体类：视频制作合同关联表
 * @date : 2020-12-11 08:47:25
 */
@Getter
@Setter
@TableName("mtl_video_production_contract1")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlVideoProductionContract1对象", description = "媒体类：视频制作合同关联表")
public class MtlVideoProductionContract1Entity extends BaseEntity {

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
	 * 内容主旨
	 */
    @ApiModelProperty(value="内容主旨")
	private String contentTheme;
	/**
	 * 要求（语言、规格、精度等）
	 */
    @ApiModelProperty(value="要求（语言、规格、精度等）")
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
