package org.springblade.contract.vo;

import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 合同依据管理 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author XHB
 * @date : 2020-09-24 14:20:30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合同依据管理请求对象")
public class AccordingRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="项目依据")
	private String accordingName;

    @ApiModelProperty(value="关联合同")
	private String contractId;

    @ApiModelProperty(value="依据附件")
	private String accordingFiles;

    @ApiModelProperty(value="依据说明")
	private String remark;

    @ApiModelProperty(value="版本号")
	private String recordVersion;

    @ApiModelProperty(value="创建单位标识")
	private Long createUnit;

}
