package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;

/**
 * 班车服务合同子表1 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:29:13
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "班车服务合同子表1请求对象")
public class BusServiceContract1RequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="A线发车时间")
	private Date busTimeA;
	
    @ApiModelProperty(value="B线发车时间")
	private Date busTimeB;
	
    @ApiModelProperty(value="关联主表标识")
	private Long contractId;
	
}
