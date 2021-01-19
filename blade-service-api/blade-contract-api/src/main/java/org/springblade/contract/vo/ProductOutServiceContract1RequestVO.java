package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 生产项目外包服务合同子表1 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:22:11
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "生产项目外包服务合同子表1请求对象")
public class ProductOutServiceContract1RequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="序号")
	private Integer num;
	
    @ApiModelProperty(value="服务需求项目名称")
	private String name;
	
    @ApiModelProperty(value="计量单位")
	private String unit;
	
    @ApiModelProperty(value="未税单价")
	private BigDecimal unitPrice;
	
    @ApiModelProperty(value="服务内容及要求概述")
	private String content;
	
    @ApiModelProperty(value="关联主表标识")
	private Long proOutSerConId;
	
}
