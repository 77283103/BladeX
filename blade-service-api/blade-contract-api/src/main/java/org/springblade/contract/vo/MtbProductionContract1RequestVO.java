package org.springblade.contract.vo;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import org.springblade.core.mp.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;


/**
 * 媒体类：平面广告拍摄制作合同（关联表1） 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 韩杨
 * @date : 2021-01-21 11:26:39
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "媒体类：平面广告拍摄制作合同（关联表1）请求对象")
public class MtbProductionContract1RequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="交付形式")
	private String formDelivery;

	@ApiModelProperty(value="数量")
	private BigDecimal number;

	@ApiModelProperty(value="要求（规格、精度等）")
	private String requirements;

	@ApiModelProperty(value="费用（元，含税）")
	private BigDecimal expenses;

	@ApiModelProperty(value="合同ID")
	private Long contractId;

}
