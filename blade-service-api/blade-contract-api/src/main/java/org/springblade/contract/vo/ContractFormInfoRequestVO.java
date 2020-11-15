package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.contract.entity.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *  请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:37
 */
@Getter
@Setter
@ToString
@ApiModel(description = "请求对象")
public class ContractFormInfoRequestVO extends ContractFormInfoEntity {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value="最低合同金额")
	private String minContractAmount;

	@ApiModelProperty(value="最高合同金额")
	private String maxContractAmount;

	@ApiModelProperty(value = "用印id")
	private Long seal;

	@ApiModelProperty(value = "签订id")
	private Long signing;

	@ApiModelProperty(value="评估id")
	private Long assessment;

	@ApiModelProperty(value="归档id")
	private Long archive;

	@ApiModelProperty(value="依据集合")
	private String[] sealNameList;

	@ApiModelProperty(value="相对方集合")
	private List<ContractCounterpartEntity> counterpart;

	@ApiModelProperty(value="依据集合")
	private List<ContractAccordingEntity> according;

	@ApiModelProperty(value="保证金集合")
	private List<ContractBondEntity> contractBond;

	@ApiModelProperty(value="履约计划集合")
	private List<ContractPerformanceEntity> performanceList;

	@ApiModelProperty(value="履约计划收付款集合")
	private List<ContractPerformanceColPayEntity> performanceColPayList;

	@ApiModelProperty(value="合同状态集合")
	private List<String> code;

	@ApiModelProperty(value="合同销毁专用")
	private String undestruction;


}
