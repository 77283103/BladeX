package org.springblade.contract.vo;

import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 范本管理 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author XHB
 * @date : 2020-09-24 13:57:36
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "范本管理请求对象")
public class TemplateRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="范本名称")
	private String name;

    @ApiModelProperty(value="范本类型")
	private String templateCategory;

    @ApiModelProperty(value="所属合同大类")
	private String contractBigCategory;

    @ApiModelProperty(value="所属合同小类")
	private String contractSmallCategory;

    @ApiModelProperty(value="范本说明")
	private String templateDescription;

    @ApiModelProperty(value="版本号")
	private String recordVersion;

    @ApiModelProperty(value="范本编号")
	private String templateNumber;

    @ApiModelProperty(value="范本附件")
	private String attachedFiles;

    @ApiModelProperty(value="创建单位标识")
	private Long createUnit;

    @ApiModelProperty(value="关联合同")
	private String contractId;

}
