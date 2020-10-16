package org.springblade.contract.vo;

import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

/**
 * 合同依据表 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author XHB
 * @date : 2020-09-19 17:54:45
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合同依据表请求对象")
public class AccordingRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="文件编号")
	private Long fileId;

    @ApiModelProperty(value="依据名称")
	private String accordingName;

    @ApiModelProperty(value="上传时间")
	private LocalDate uploadDate;

    @ApiModelProperty(value="同步时间")
	private LocalDate synchDate;

    @ApiModelProperty(value="主题内容")
	private String themeContext;

    @ApiModelProperty(value="单据类型")
	private String documentType;

    @ApiModelProperty(value="依据地址")
	private String accordingAddress;

    @ApiModelProperty(value="使用状态")
	private String useStatus;

    @ApiModelProperty(value="是否可以复用（0代表不可以复用，1代表可以复用，默认为可复用）")
	private Integer isReused;

}
