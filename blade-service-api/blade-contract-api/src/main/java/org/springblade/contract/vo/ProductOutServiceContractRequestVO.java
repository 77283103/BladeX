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
 * 生产项目外包服务合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:20:03
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "生产项目外包服务合同请求对象")
public class ProductOutServiceContractRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String proSaler;
	
    @ApiModelProperty(value="甲方住所")
	private String proSalerAddr;
	
    @ApiModelProperty(value="乙方")
	private String proBuyer;
	
    @ApiModelProperty(value="乙方住所")
	private String proBuyerAddr;
	
    @ApiModelProperty(value="承包项目服务期限-始")
	private Date proTimeStart;
	
    @ApiModelProperty(value="承包项目服务期限-止")
	private Date proTimeEnd;
	
    @ApiModelProperty(value="则以?元计算赔偿金")
	private BigDecimal proAmount;
	
    @ApiModelProperty(value="年满?周岁-?周岁")
	private String proAgeRequire;
	
    @ApiModelProperty(value="未投保人数比例超过?%")
	private Double proMaxPercent;
	
    @ApiModelProperty(value="乙方需按?元/次支付违约金")
	private BigDecimal proTimeAmount;
	
    @ApiModelProperty(value="乙方联系人")
	private String proBuyerPerson;
	
    @ApiModelProperty(value="乙方联系方式")
	private String proBuyerPhone;
	
    @ApiModelProperty(value="次月?日前")
	private String proLastDayFir;
	
    @ApiModelProperty(value="次月?日前开具合法增值税专用发票")
	private String proLastDaySec;
	
    @ApiModelProperty(value="甲方收到发票后?日内")
	private String proLastDayThi;
	
    @ApiModelProperty(value="以?形式支付上月承包服务作业费用")
	private String inPayType;
	
    @ApiModelProperty(value="乙方户名")
	private String proBuyerAccountName;
	
    @ApiModelProperty(value="乙方账号")
	private String proBuyerAccountId;
	
    @ApiModelProperty(value="乙方开户行")
	private String proBuyerAccountBank;
	
    @ApiModelProperty(value="向甲方支付人民币?万元作为履约保证金")
	private BigDecimal proBondAmountFir;
	
    @ApiModelProperty(value="乙方应在?日内将履约保证金补足")
	private String proLastDayFou;
	
    @ApiModelProperty(value="超过?日仍未补足的")
	private String proLastDayFiv;
	
    @ApiModelProperty(value="甲方户名")
	private String proSalerAccoutName;
	
    @ApiModelProperty(value="乙方账号")
	private String proSalerAccoutId;
	
    @ApiModelProperty(value="乙方开户行")
	private String proSalerAccoutBank;
	
    @ApiModelProperty(value="支付?万元作为提前解约违约金")
	private BigDecimal proBondAmountSec;
	
    @ApiModelProperty(value="要求乙方支付违约金?万元")
	private BigDecimal proBondAmountThi;
	
    @ApiModelProperty(value="要求乙方支付违约金?万元")
	private BigDecimal proBondAmountFou;
	
    @ApiModelProperty(value="乙方须同时支付甲方违约金?万元")
	private BigDecimal proBondAmountFiv;
	
    @ApiModelProperty(value="违约金金额为?万元")
	private BigDecimal proBondAmountSix;
	
    @ApiModelProperty(value="补充约定")
	private String proSupplyArrange;
	
    @ApiModelProperty(value="附件一：项目服务作业内容和要求")
	private String proAnnexFir;
	
    @ApiModelProperty(value="附件二：甲方厂区管理制度")
	private String proAnnexSec;
	
    @ApiModelProperty(value="关联子表1标识")
	private Long proOutServiceCon1Id;
	
    @ApiModelProperty(value="关联子表2标识")
	private Long proOutServiceCon2Id;
	
    @ApiModelProperty(value="关联子表3标识")
	private Long proOutServiceCon3Id;
	
}
