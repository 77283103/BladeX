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
 * 消防-维保合同 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author kx
 * @date : 2021-05-10 13:41:03
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "消防-维保合同请求对象")
public class ContractWbhtRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="甲方")
	private String contactPartyA;

    @ApiModelProperty(value="乙方")
	private String contactPartyB;

    @ApiModelProperty(value="甲方地址")
	private String contactPartyAAddr;

    @ApiModelProperty(value="乙方地址")
	private String contactPartyBAddr;

    @ApiModelProperty(value="甲方指定联络邮箱")
	private String contactPartyAEmail;

    @ApiModelProperty(value="乙方指定联络邮箱")
	private String contactPartyBEmail;

    @ApiModelProperty(value="维修、保养所在地")
	private String contactAddr;

    @ApiModelProperty(value="维修、保养服务期限起")
	private Date contactTimeStart;

    @ApiModelProperty(value="维修、保养服务期限止")
	private Date contactTimeEnd;

    @ApiModelProperty(value="每月/每季度/每半年 下拉选")
	private Integer contactTime;

    @ApiModelProperty(value="其他建筑消防设施")
	private String contactElsePlace;

    @ApiModelProperty(value="保养费用：未税金额计人民币?元/年")
	private BigDecimal contactYuan;

    @ApiModelProperty(value="税率为?%")
	private Double contactPercent;

    @ApiModelProperty(value="乙方开户行")
	private String contactAccountBank;

    @ApiModelProperty(value="乙方户名")
	private String contactAccountName;

    @ApiModelProperty(value="乙方账号")
	private String contactAccountNum;

    @ApiModelProperty(value="若保养项目未达到约定量的?%")
	private Double contactPercentT;

    @ApiModelProperty(value="乙方应支付甲方该保养项目当期总金额的?%作为违约金")
	private Double contactPercentF;

    @ApiModelProperty(value="发生次数达?次及以上的")
	private String contactTimes;

    @ApiModelProperty(value="甲方联系人")
	private String contactPartyAPerson;

    @ApiModelProperty(value="乙方联系人")
	private String contactPartyBPerson;

    @ApiModelProperty(value="甲方联系电话")
	private String contactPartyAPhone;

    @ApiModelProperty(value="乙方联系电话")
	private String contactPartyBPhone;

    @ApiModelProperty(value="发包方单位名称")
	private String contactFaBaoFang;

    @ApiModelProperty(value="承包方（承包商）单位名称")
	private String contactChengBaoFang;

    @ApiModelProperty(value="法定代表人地址")
	private String contactFaDingPlace;

    @ApiModelProperty(value="乙方需向甲方支付产品价值？倍的违约金")
	private String contactValue;

    @ApiModelProperty(value="项目负责单位最高主管")
	private String contractLeader;

    @ApiModelProperty(value="经办人员")
	private String contractPeople;

}
