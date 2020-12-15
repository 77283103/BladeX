package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 相对方管理 请求模型VO
 *
 * @NotNull 验证对象是否不为null, 无法查检长度为0的字符串
 * @NotBlank 检查约束 (字符串) 是不是Null还有被Trim的长度是否大于0,只对字符串,且会去掉前后空格.
 * @NotEmpty 检查(集合)约束元素是否为NULL或者是EMPTY.
 *
 * @author XHB
 * @date : 2020-09-23 19:35:03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "相对方管理请求对象")
public class ContractCounterpartRequestVO extends ContractCounterpartEntity {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "相对方名称")
	private String name;

	@ApiModelProperty(value = "身份证号码")
	private String idNumber;

	@ApiModelProperty(value = "统一社会信用代码")
	private String unifiedSocialCreditCode;

	@ApiModelProperty(value = "相对方类型")
	private String counterpartCategory;

	@ApiModelProperty(value = "相对方性质")
	private String natureCategory;

	@ApiModelProperty(value = "注册地址")
	private String registeredAddress;

	@ApiModelProperty(value = "联系人电话")
	private String contactPersonPhone;

	@ApiModelProperty(value = "联系人地址")
	private String contactAddress;

	@ApiModelProperty(value = "联系人姓名")
	private String contactPersonName;

	@ApiModelProperty(value = "护照号码")
	private String passportId;

	@ApiModelProperty(value = "首次合作日期")
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date firstCooperationDate;

	@ApiModelProperty(value = "电子印章序列号")
	private String electronicSealSerialId;

	@ApiModelProperty(value = "税率")
	private BigDecimal taxRate;

	@ApiModelProperty(value = "付款方式")
	private String paymentMethod;

	@ApiModelProperty(value = "信息同步时间")
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date informationSynDate;

	@ApiModelProperty(value = "成立日期")
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date establishDate;

	@ApiModelProperty(value = "公司地址")
	private String companyAddress;

	@ApiModelProperty(value = "注册资金")
	private BigDecimal registeredCapital;

	@ApiModelProperty(value = "法定代表人")
	private String legalRepresentative;

	@ApiModelProperty(value = "联系人邮箱")
	private String contactPersonMail;

	@ApiModelProperty(value = "开户银行")
	private String depositBank;

	@ApiModelProperty(value = "银行账号")
	private String bankAccount;

	@ApiModelProperty(value = "相关附件")
	private String attachedFiles;

	@ApiModelProperty(value = "币种")
	private String currencyCategory;
}
