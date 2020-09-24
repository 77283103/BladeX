package org.springblade.contract.vo;

import lombok.*;
import org.springblade.contract.entity.ArchiveEntity;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 合同归档管理 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author XHB
 * @date : 2020-09-23 18:32:13
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合同归档管理请求对象")
public class ArchiveRequestVO extends ArchiveEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="关联合同")
	private String contractId;

    @ApiModelProperty(value="归档说明")
	private String archiveInstructions;

    @ApiModelProperty(value="经办信息")
	private String manageMessage;

}
