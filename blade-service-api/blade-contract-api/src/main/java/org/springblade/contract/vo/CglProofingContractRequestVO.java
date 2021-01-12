package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 采购类_打样合同书 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 采购类_打样合同书
 * @date : 2021-01-12 13:24:34
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "采购类_打样合同书请求对象")
public class CglProofingContractRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String partyA;
	
    @ApiModelProperty(value="乙方")
	private String partyB;
	
    @ApiModelProperty(value="元")
	private BigDecimal element;
	
    @ApiModelProperty(value="日期")
	private Date date;
	
    @ApiModelProperty(value="元")
	private BigDecimal element1;
	
    @ApiModelProperty(value="税率")
	private Long taxRate;
	
    @ApiModelProperty(value="元")
	private BigDecimal element2;
	
    @ApiModelProperty(value="户名")
	private String accountName;
	
    @ApiModelProperty(value="账号")
	private String accountNumber;
	
    @ApiModelProperty(value="开户行")
	private String bankDeposit;
	
    @ApiModelProperty(value="是否启用（0未启用，1已启用）")
	private Integer isEnable;
	
}
