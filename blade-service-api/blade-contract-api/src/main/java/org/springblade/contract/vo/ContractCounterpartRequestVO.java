package org.springblade.contract.vo;

import lombok.*;
import org.springblade.contract.entity.ContractCounterpartEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 相对方管理 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author XHB
 * @date : 2020-09-23 19:35:03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "相对方管理请求对象")
public class ContractCounterpartRequestVO extends ContractCounterpartEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="相对方名称")
	private String name;

    @ApiModelProperty(value="相对方性质")
	private String counterpartCategory;

    @ApiModelProperty(value="注册地址")
	private String registeredAddress;

    @ApiModelProperty(value="法定代表人")
	private String legalRepresentative;

    @ApiModelProperty(value="注册资金")
	private BigDecimal registeredCapital;

    @ApiModelProperty(value="币种")
	private String currencyCategory;

    @ApiModelProperty(value="成立日期")
	private LocalDate establishDate;

    @ApiModelProperty(value="统一社会信用代码")
	private String unifiedSocialCreditCode;

    @ApiModelProperty(value="首次合作日期")
	private LocalDate firstCooperationDate;

    @ApiModelProperty(value="合同记录")
	private String contractRecord;

    @ApiModelProperty(value="上榜记录")
	private String listRecord;

    @ApiModelProperty(value="相关合同")
	private String  contractId;

}
