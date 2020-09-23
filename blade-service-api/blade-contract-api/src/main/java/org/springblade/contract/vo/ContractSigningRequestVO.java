package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

/**
 * 合同签订表 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合同签订表请求对象")
public class ContractSigningRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="签订时间")
	private LocalDateTime signDate;
	
    @ApiModelProperty(value="我方签约人")
	private String signForUs;
	
    @ApiModelProperty(value="对方签约人")
	private String signForOther;
	
    @ApiModelProperty(value="合同起始时间")
	private LocalDateTime contractStartTime;
	
    @ApiModelProperty(value="合同结束时间")
	private LocalDateTime contractEndTime;
	
    @ApiModelProperty(value="合同文本扫描件")
	private String textFiles;
	
    @ApiModelProperty(value="合同附件扫描件")
	private String attachedFiles;
	
    @ApiModelProperty(value="备注说明")
	private String remark;
	
}
