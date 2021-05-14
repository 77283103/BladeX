package org.springblade.contract.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 行销品买卖合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author kx
 * @date : 2021-05-10 13:37:38
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "行销品买卖合同请求对象")
public class ContractMmhtxxpfRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String contactPartyA;

    @ApiModelProperty(value="乙方")
	private String contactPartyB;

    @ApiModelProperty(value="甲方地址")
	private String contactPartyAAddr;

    @ApiModelProperty(value="乙方地址")
	private String contactPartyBAddr;

    @ApiModelProperty(value="约定由甲方向乙方购买?作为甲方")
	private String contactThing;

    @ApiModelProperty(value="收到订购单后?天内")
	private String contactDay;

    @ApiModelProperty(value="甲方指定联络邮箱")
	private String contactPartyAEmail;

    @ApiModelProperty(value="乙方指定联络邮箱")
	private String contactPartyBEmail;

    @ApiModelProperty(value="甲方指定传真")
	private String contactPartyAFax;

    @ApiModelProperty(value="乙方指定传真")
	private String contactPartyBFax;

    @ApiModelProperty(value="双方约定采用第?种账期付款")
	private String contactType;

    @ApiModelProperty(value="（2）特殊付款")
	private String contactSpecilpay;

    @ApiModelProperty(value="开户行名称")
	private String contactBank;

    @ApiModelProperty(value="账户名称")
	private String contactBankname;

    @ApiModelProperty(value="账号")
	private String contactBanknum;

    @ApiModelProperty(value="九、其他约定?")
	private String contactOther;

    @ApiModelProperty(value="甲方联系地址")
	private String contactPartyAPlace;

    @ApiModelProperty(value="乙方联系地址")
	private String contactPartyBPlace;

    @ApiModelProperty(value="甲方联系方式")
	private String contactPartyAPhone;

    @ApiModelProperty(value="乙方联系方式")
	private String contactPartyBPhone;

}
