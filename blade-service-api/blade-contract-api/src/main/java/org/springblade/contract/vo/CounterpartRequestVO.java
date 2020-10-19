package org.springblade.contract.vo;

import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 合同相对方的管理 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author XHB
 * @date : 2020-09-18 21:13:53
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合同相对方的管理请求对象")
public class CounterpartRequestVO extends BaseEntity {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="相对方名称")
	private String counterpartName;

    @ApiModelProperty(value="身份证件号")
	private String idNumber;

    @ApiModelProperty(value="证件号码")
	private String licenseNumber;

    @ApiModelProperty(value="统一社会信用代码")
	private String unifiedSocialCreditCode;

    @ApiModelProperty(value="相对方性质")
	private String natureCategory;

    @ApiModelProperty(value="相对方类型")
	private String counterpartCategory;

    @ApiModelProperty(value="注册地址")
	private String registeredAddress;

    @ApiModelProperty(value="联系人电话")
	private String contactPersonPhone;

    @ApiModelProperty(value="联系人姓名")
	private String contactPersonName;

    @ApiModelProperty(value="联系人地址")
	private String contactAddress;

    @ApiModelProperty(value="护照号码")
	private String passportId;

    @ApiModelProperty(value="合作时间")
	private LocalDate cooperationDate;

    @ApiModelProperty(value="首次合作时间")
	private LocalDate firstCooperationDate;

    @ApiModelProperty(value="电子印章序列号")
	private Long electronicSealSerialId;

    @ApiModelProperty(value="税率")
	private Double taxRate;

    @ApiModelProperty(value="付款方式")
	private String paymentMethod;

    @ApiModelProperty(value="信息同步时间")
	private LocalDateTime informationSynDate;

    @ApiModelProperty(value="成立日期")
	private LocalDate establishDate;

    @ApiModelProperty(value="公司地址")
	private String companyAddress;

    @ApiModelProperty(value="注册资金")
	private Double registeredCapital;

    @ApiModelProperty(value="法定代表人")
	private String legalRepresentative;

    @ApiModelProperty(value="联系人邮箱")
	private String contactPersonMail;

    @ApiModelProperty(value="开户银行")
	private String depositBank;

    @ApiModelProperty(value="银行账号")
	private String bankAccount;

    @ApiModelProperty(value="相关附件")
	private String files;

    @ApiModelProperty(value="币种")
	private String currency;

    @ApiModelProperty(value="父级相对方id")
	private String parentId;

    @ApiModelProperty(value="版本号(默认值V1.0)")
	private String versionNumber;

    @ApiModelProperty(value="是否是最新版本（0是最新版本，1是历史版本）")
	private Integer isNew;

}
