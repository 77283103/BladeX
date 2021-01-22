package org.springblade.contract.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;

/**
 * 媒体类：市调合同（定性+定量) 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:23
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "媒体类：市调合同（定性+定量)请求对象")
public class MtbMarketResearchContractRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String patya;
	
    @ApiModelProperty(value="甲方地址")
	private String partyAddress;
	
    @ApiModelProperty(value="甲方电话")
	private Integer number;
	
    @ApiModelProperty(value="甲方传真")
	private Integer patyFax;
	
    @ApiModelProperty(value="乙方")
	private String patyb;
	
    @ApiModelProperty(value="乙方地址")
	private String patyAddressb;
	
    @ApiModelProperty(value="乙方电话")
	private Integer patyPhoneb;
	
    @ApiModelProperty(value="乙方传真")
	private Integer patyFaxb;
	
    @ApiModelProperty(value="项目名称")
	private String projectName;
	
    @ApiModelProperty(value="附件：【？】项目计划书/报价单（择一）")
	private String attachment;
	
    @ApiModelProperty(value="研究方法")
	private String research;
	
    @ApiModelProperty(value="研究方法")
	private String methods;
	
    @ApiModelProperty(value="受访者人数")
	private Integer numberRespondents;
	
    @ApiModelProperty(value="受访者要求")
	private String requireRespondents;
	
    @ApiModelProperty(value="调研简报")
	private String results;
	
    @ApiModelProperty(value="调研报告")
	private String research1;
	
    @ApiModelProperty(value="视频录像文件")
	private String video;
	
    @ApiModelProperty(value="定性研究现场原始问答记录")
	private String qualitative;
	
    @ApiModelProperty(value="定量研究原始数据记录")
	private String quantitative1;
	
    @ApiModelProperty(value="其他")
	private String other;
	
    @ApiModelProperty(value="其他")
	private String other1;
	
    @ApiModelProperty(value="其他")
	private String other2;
	
    @ApiModelProperty(value="总费用：本合同总费用为：未税额人民币")
	private BigDecimal totalCost;
	
    @ApiModelProperty(value="税率")
	private Double rate;
	
    @ApiModelProperty(value="现含税金额人民币")
	private BigDecimal amount;
	
    @ApiModelProperty(value="乙方承担")
	private String undertakesb;
	
    @ApiModelProperty(value="甲方承担")
	private String undertakesa;
	
    @ApiModelProperty(value="元")
	private BigDecimal element;
	
    @ApiModelProperty(value="元")
	private BigDecimal element1;
	
    @ApiModelProperty(value="元")
	private BigDecimal element2;
	
    @ApiModelProperty(value="元")
	private BigDecimal element3;
	
    @ApiModelProperty(value="元")
	private BigDecimal element4;
	
    @ApiModelProperty(value="元含税")
	private BigDecimal element5;
	
    @ApiModelProperty(value="元")
	private BigDecimal element6;
	
    @ApiModelProperty(value="元")
	private BigDecimal element7;
	
    @ApiModelProperty(value="元")
	private BigDecimal element8;
	
    @ApiModelProperty(value="元")
	private BigDecimal element9;
	
    @ApiModelProperty(value="元")
	private BigDecimal element10;
	
    @ApiModelProperty(value="元")
	private BigDecimal element11;
	
    @ApiModelProperty(value="元含税")
	private BigDecimal element12;
	
    @ApiModelProperty(value="公司名")
	private String company;
	
    @ApiModelProperty(value="开户行")
	private String bank;
	
    @ApiModelProperty(value="账号")
	private String account;
	
    @ApiModelProperty(value="甲方联系地址")
	private String contactAddress;
	
    @ApiModelProperty(value="邮编")
	private String postcode;
	
    @ApiModelProperty(value="甲方主要联系人")
	private String contact;
	
    @ApiModelProperty(value="电话号码")
	private String telephone;
	
    @ApiModelProperty(value="乙方联系地址")
	private String patyContact;
	
    @ApiModelProperty(value="乙方主要联系人")
	private String contact1;
	
    @ApiModelProperty(value="邮编")
	private String postcode1;
	
    @ApiModelProperty(value="电话号码")
	private String phon;
	
    @ApiModelProperty(value="附件二 【？】调研计划书/报价单（择一）")
	private String annex;

	
}
