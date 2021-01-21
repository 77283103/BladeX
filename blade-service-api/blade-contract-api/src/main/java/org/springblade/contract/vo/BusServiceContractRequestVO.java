package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.util.Date;

/**
 * 班车服务合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:25:16
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "班车服务合同请求对象")
public class BusServiceContractRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String busSaler;
	
    @ApiModelProperty(value="甲方地址")
	private String busSalerAddr;
	
    @ApiModelProperty(value="乙方")
	private String busBuyer;
	
    @ApiModelProperty(value="乙方地址")
	private String busBuyerAddr;
	
    @ApiModelProperty(value="关联子表bus_service_contract1标识")
	private Long busServiceContract1Id;
	
    @ApiModelProperty(value="服务期限始")
	private Date busServiceTimeStart;
	
    @ApiModelProperty(value="服务期限止")
	private Date busServiceTimeEnd;
	
    @ApiModelProperty(value="每月?日双方对上月产生的服务费进行核对")
	private String busDateRequireFir;
	
    @ApiModelProperty(value="发票(普通/专用)")
	private String busInvoiceType;
	
    @ApiModelProperty(value="户名")
	private String busBuyerAccountName;
	
    @ApiModelProperty(value="账号")
	private String busBuyerAccountId;
	
    @ApiModelProperty(value="开户行")
	private String busBuyerAccountBank;
	
    @ApiModelProperty(value="累计达?日无法为甲方提供班车服务的")
	private String busDateRequireSec;
	
    @ApiModelProperty(value="附件1：报价单")
	private String infAnnexFir;
	
}
