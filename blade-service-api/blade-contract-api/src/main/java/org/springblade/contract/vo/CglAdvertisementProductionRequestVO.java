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
 * 采购类：广告制作安装合同模板 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 采购类：广告制作安装合同模板
 * @date : 2021-01-12 14:01:59
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "采购类：广告制作安装合同模板请求对象")
public class CglAdvertisementProductionRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String cglPartyA;
	
    @ApiModelProperty(value="甲方地址")
	private String cglAddressA;
	
    @ApiModelProperty(value="甲方联系方式")
	private Integer cglContact;
	
    @ApiModelProperty(value="乙方")
	private String cglPartyB;
	
    @ApiModelProperty(value="乙方地址")
	private String cglAddress;
	
    @ApiModelProperty(value="区域")
	private String cglArea;
	
    @ApiModelProperty(value="个")
	private Long individual;
	
    @ApiModelProperty(value="日")
	private Long day;
	
    @ApiModelProperty(value="日")
	private Long dayA;
	
    @ApiModelProperty(value="日")
	private Long dayB;
	
    @ApiModelProperty(value="日")
	private Long dayC;
	
    @ApiModelProperty(value="日")
	private Long dayD;
	
    @ApiModelProperty(value="日")
	private Long dayE;
	
    @ApiModelProperty(value="日")
	private Long dayF;
	
    @ApiModelProperty(value="支付方式")
	private String paymentMethod;
	
    @ApiModelProperty(value="类型")
	private String type;
	
    @ApiModelProperty(value="日")
	private Long dayG;
	
    @ApiModelProperty(value="其他类型")
	private String otherWays;
	
    @ApiModelProperty(value="公司名称")
	private String corporateName;
	
    @ApiModelProperty(value="开户行")
	private String bankDeposit;
	
    @ApiModelProperty(value="账户")
	private String account;
	
    @ApiModelProperty(value="日")
	private Long dayH;
	
    @ApiModelProperty(value="金额")
	private BigDecimal money;
	
    @ApiModelProperty(value="日")
	private Long dayI;
	
    @ApiModelProperty(value="特别约定")
	private String specialAgreement;
	
    @ApiModelProperty(value="起始日期")
	private Date date;
	
    @ApiModelProperty(value="结束日期")
	private Date dateA;
	
    @ApiModelProperty(value="数量")
	private Integer number;
	
    @ApiModelProperty(value="数量甲方")
	private Integer numberA;
	
    @ApiModelProperty(value="数量乙方")
	private Integer numberB;
	
    @ApiModelProperty(value="附件一")
	private String cglAttachment;
	
    @ApiModelProperty(value="附件三")
	private String cglAttachmentA;
	
    @ApiModelProperty(value="报价表")
	private String quotationSheet;
	
    @ApiModelProperty(value="电子邮箱")
	private String cglEmail;
	
    @ApiModelProperty(value="年")
	private String year;
	
}
