package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 相对方管理 实体类
 *
 * @author XHB
 * @date : 2020-09-23 19:35:02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_counterpart")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Counterpart对象", description = "相对方管理")
public class ContractCounterpartEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 相对方名称
	 */
	@ApiModelProperty(value = "相对方名称")
	private String name;
	/**
	 * 身份证号码
	 */
	@ApiModelProperty(value = "身份证号码")
	private String idNumber;
	/**
	 * 统一社会信用代码
	 */
	@ApiModelProperty(value = "统一社会信用代码")
	private String unifiedSocialCreditCode;
	/**
	 * 相对方类型
	 */
	@ApiModelProperty(value = "相对方类型")
	private String counterpartCategory;
	/**
	 * 相对方性质
	 */
	@ApiModelProperty(value = "相对方性质")
	private String natureCategory;
	/**
	 * 注册地址
	 */
	@ApiModelProperty(value = "注册地址")
	private String registeredAddress;
	/**
	 * 联系人电话
	 */
	@ApiModelProperty(value = "联系人电话")
	private String contactPersonPhone;
	/**
	 * 联系人地址
	 */
	@ApiModelProperty(value = "联系人地址")
	private String contactAddress;
	/**
	 * 联系人姓名
	 */
	@ApiModelProperty(value = "联系人姓名")
	private String contactPersonName;
	/**
	 * 护照号码
	 */
	@ApiModelProperty(value = "护照号码")
	private String passportId;
	/**
	 * 首次合作日期
	 */
	@ApiModelProperty(value = "首次合作日期")
	private Date firstCooperationDate;
	/**
	 * 电子印章序列号
	 */
	@ApiModelProperty(value = "电子印章序列号")
	private String electronicSealSerialId;
	/**
	 * 税率
	 */
	@ApiModelProperty(value = "税率")
	private BigDecimal taxRate;
	/**
	 * 付款方式
	 */
	@ApiModelProperty(value = "付款方式")
	private String paymentMethod;/**
	 * 信息同步时间
	 */
	@ApiModelProperty(value = "信息同步时间")
	private Date informationSynDate;
	/**
	 * 成立日期
	 */
	@ApiModelProperty(value = "成立日期")
	private Date establishDate;
	/**
	 * 公司地址
	 */
	@ApiModelProperty(value = "公司地址")
	private String companyAddress;
	/**
	 * 注册资金
	 */
	@ApiModelProperty(value = "注册资金")
	private BigDecimal registeredCapital;
	/**
	 * 法定代表人
	 */
	@ApiModelProperty(value = "法定代表人")
	private String legalRepresentative;
	/**
	 * 联系人邮箱
	 */
	@ApiModelProperty(value = "联系人邮箱")
	private String contactPersonMail;
	/**
	 * 开户银行
	 */
	@ApiModelProperty(value = "开户银行")
	private String depositBank;
	/**
	 * 银行账号
	 */
	@ApiModelProperty(value = "银行账号")
	private String bankAccount;
	/**
	 * 相关附件
	 */
	@ApiModelProperty(value = "相关附件")
	private String attachedFiles;
	/**
	 * 币种
	 */
	@ApiModelProperty(value = "币种")
	private String currencyCategory;

}
