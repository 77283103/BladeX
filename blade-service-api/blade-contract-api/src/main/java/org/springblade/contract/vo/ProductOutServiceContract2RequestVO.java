package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 生产项目外包服务合同子表2 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:23:05
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "生产项目外包服务合同子表2请求对象")
public class ProductOutServiceContract2RequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="序号")
	private Integer rewardNum;
	
    @ApiModelProperty(value="奖励内容")
	private String rewardContent;
	
    @ApiModelProperty(value="奖励金额（元/次）")
	private BigDecimal rewardAmount;
	
    @ApiModelProperty(value="关联主表标识")
	private Long proOutSerConId;
	
}
