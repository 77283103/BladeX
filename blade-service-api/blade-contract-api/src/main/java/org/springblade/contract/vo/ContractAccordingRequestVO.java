package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
public class ContractAccordingRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "依据名称")
	private String accordingName;

	@ApiModelProperty(value = "文件编号")
	private String fileId;

	@ApiModelProperty(value = "同步时间")
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date synchDate;

	@ApiModelProperty(value = "同步时间搜索")
	private String synchDateString;

	@ApiModelProperty(value = "主题内容")
	private String themeContext;
	@ApiModelProperty(value = "依据code")
	private String code;

	@ApiModelProperty(value = "使用状态")
	private String useStatus;

	@ApiModelProperty(value = "单据类型")
	private String documentType;

	@ApiModelProperty(value = "依据地址")
	private String accordingAddress;

	@ApiModelProperty(value = "是否可以复用")
	private Integer isReused;

	@ApiModelProperty(value = "依据地址")
	private String[] contractIds;
}
