package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

/**
 * 合同文件日志 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author wpf
 * @date : 2021-06-23 10:30:36
 */
@Getter
@Setter
@ApiModel(description = "合同文件日志请求对象")
public class ContractFileDownloadLogRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="用户编号")
	private String code;

    @ApiModelProperty(value="账号")
	private String account;

    @ApiModelProperty(value="姓名")
	private String realName;
	@ApiModelProperty(value="合同ID")
	private String contractId;

}
