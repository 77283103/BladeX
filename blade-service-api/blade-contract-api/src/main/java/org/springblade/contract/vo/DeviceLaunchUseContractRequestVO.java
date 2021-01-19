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
 * 设备投放使用协议 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:18:10
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "设备投放使用协议请求对象")
public class DeviceLaunchUseContractRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String devSaler;
	
    @ApiModelProperty(value="甲方地址")
	private String devSalerAddr;
	
    @ApiModelProperty(value="乙方：（客户名称）")
	private String devBuyer;
	
    @ApiModelProperty(value="乙方地址")
	private String devBuyerAddr;
	
    @ApiModelProperty(value="乙方：(客户代号)")
	private String devBuyerNum;
	
    @ApiModelProperty(value="乙方向甲方借用设备共?台")
	private String devNumber;
	
    @ApiModelProperty(value="大写?台")
	private String devNumberInWord;
	
    @ApiModelProperty(value="设备品牌")
	private String devBrand;
	
    @ApiModelProperty(value="设备型号")
	private String devModel;
	
    @ApiModelProperty(value="设备价值")
	private BigDecimal devValue;
	
    @ApiModelProperty(value="资产编码")
	private String devCode;
	
    @ApiModelProperty(value="设备摆放位置")
	private String devPlace;
	
    @ApiModelProperty(value="设备借用有效期始")
	private Date devBorroStart;
	
    @ApiModelProperty(value="设备借用有效期止")
	private Date devBorroEnd;
	
    @ApiModelProperty(value="本协议签署后?日内")
	private String devLeastDate;
	
    @ApiModelProperty(value="乙方应向甲方支付设备押金?元")
	private BigDecimal devDeposit;
	
    @ApiModelProperty(value="大写人民币?元")
	private String devDepositInWord;
	
    @ApiModelProperty(value="甲方签约人")
	private String devSalerPerson;
	
    @ApiModelProperty(value="甲方签订时间")
	private Date devSalerTime;
	
    @ApiModelProperty(value="乙方签约人")
	private String devBuyerPerson;
	
    @ApiModelProperty(value="乙方签订时间")
	private Date devBuyerTime;
	
}
