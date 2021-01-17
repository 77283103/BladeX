package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 1 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author kx
 * @date : 2021-01-15 15:45:46
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "1请求对象")
public class MtlEditedTheContractRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String mtlPatyA;
	
    @ApiModelProperty(value="甲方联络邮箱")
	private String mtlPatyAEmail;
	
    @ApiModelProperty(value="甲方地址")
	private String mtlContactEmail;
	
    @ApiModelProperty(value="乙方")
	private String mtlPatyB;
	
    @ApiModelProperty(value="乙方联络邮箱")
	private String mtlPatyBEmail;
	
    @ApiModelProperty(value="乙方住所")
	private String mtlPatyBHome;
	
    @ApiModelProperty(value="制作总费用(未税额人民币)")
	private BigDecimal mtlUnpaidTaxRmb;
	
    @ApiModelProperty(value="税率")
	private Double mtlRate;
	
    @ApiModelProperty(value="现含税金额人民币")
	private BigDecimal mtlTaxAmountIsRmb;
	
    @ApiModelProperty(value="乙方公司名")
	private String mtlCompanyName;
	
    @ApiModelProperty(value="乙方开户行")
	private String mtlWhereItIs;
	
    @ApiModelProperty(value="乙方账号")
	private String mtlAccount;
	
}
