package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 合同变更 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author szw
 * @date : 2020-09-23 19:24:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合同变更请求对象")
public class ContractChangeRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="变更协议名称")
	private String changeAgreementName;

    @ApiModelProperty(value="变更原因")
	private String changeReason;

    @ApiModelProperty(value="变更内容")
	private String changeContent;

    @ApiModelProperty(value="变更主导方")
	private String changeParty;

    @ApiModelProperty(value="变更说明")
	private String changeExplain;

    @ApiModelProperty(value="补充协议")
	private String suppleAgreement;

    @ApiModelProperty(value="关联合同id")
	private Long refContractId;

}
