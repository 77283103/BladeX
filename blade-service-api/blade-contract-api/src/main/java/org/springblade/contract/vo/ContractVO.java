package org.springblade.contract.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.*;
import org.springblade.core.mp.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 请求模型VO
 *
 * @author 史智伟
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 * @date : 2020-09-23 18:04:37
 */
@Getter
@Setter
@ToString
@ApiModel(description = "请求对象")
public class ContractVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "范本id")
	private Long contractTemplateId;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "合同大类id")
	private String contractBigCategory;

	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "合同小类id")
	private String contractSmallCategory;

	@ApiModelProperty(value = "json")
	private String json;


}
