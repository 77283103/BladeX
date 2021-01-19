package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 配送服务合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 王策
 * @date : 2021-01-18 17:24:26
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "配送服务合同请求对象")
public class DistributionServiceContractRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="委托方")
	private String clientA;
	
    @ApiModelProperty(value="委托方地址")
	private String clientAddress;
	
    @ApiModelProperty(value="受托方")
	private String trusteeB;
	
    @ApiModelProperty(value="受托方地址")
	private String trusteeAddress;
	
    @ApiModelProperty(value="甲方指定收货卖场及地址")
	private String designatedAddress;
	
    @ApiModelProperty(value="双方约定按如下第几种方式核算服务费用")
	private String several;
	
    @ApiModelProperty(value="甲方将以什么方式于费用确认之日起日内支付乙方上月服务费用")
	private String inWay;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="本合同有效期自年月日起")
	private Date validityContractA;

	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="本合同有效期至年月日止")
	private Date validityContractB;
	
    @ApiModelProperty(value="委托方电话")
	private String clientTelephone;
	
    @ApiModelProperty(value="受托方电话")
	private String trusteeTelephone;
	
    @ApiModelProperty(value="委托方传真")
	private String clientFax;
	
    @ApiModelProperty(value="受托方传真")
	private String trusteeFax;
	
}
