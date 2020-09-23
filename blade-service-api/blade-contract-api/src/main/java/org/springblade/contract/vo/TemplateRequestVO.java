package org.springblade.contract.vo;

import lombok.*;
import org.springblade.contract.entity.TemplateEntity;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * 范本管理 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author XHB
 * @date : 2020-09-23 20:17:09
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "范本管理请求对象")
public class TemplateRequestVO extends TemplateEntity {

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

    @ApiModelProperty(value="上传文件")
	private String attachedFiles;

    @ApiModelProperty(value="管理部门")
	private Long managementDept;

    @ApiModelProperty(value="管理单位")
	private Long managementUnit;

    @ApiModelProperty(value="管理人员")
	private Long manager;

    @ApiModelProperty(value="上传时间")
	private LocalDateTime uploadTime;

    @ApiModelProperty(value="关联合同")
	private String contractId;

}
