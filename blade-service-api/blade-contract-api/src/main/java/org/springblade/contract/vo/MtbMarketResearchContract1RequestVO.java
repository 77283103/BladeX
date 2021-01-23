package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;

/**
 * 市调合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:49
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "市调合同请求对象")
public class MtbMarketResearchContract1RequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="时间")
	private Date mtbTime;
	
    @ApiModelProperty(value="事项")
	private String mtbMatter;
	
    @ApiModelProperty(value="合同ID")
	private Long contractId;
	
}
