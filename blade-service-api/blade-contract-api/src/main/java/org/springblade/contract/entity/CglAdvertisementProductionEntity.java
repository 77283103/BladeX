package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 采购类：广告制作安装合同模板 实体类
 *
 * @author 采购类：广告制作安装合同模板
 * @date : 2021-01-12 14:01:56
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("cgl_advertisement_production")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglAdvertisementProduction对象", description = "采购类：广告制作安装合同模板")
public class CglAdvertisementProductionEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String cglPartyA;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String cglAddressA;
	/**
	 * 甲方联系方式
	 */
	@ApiModelProperty(value = "甲方联系方式")
	private Integer cglContact;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String cglPartyB;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String cglAddress;
	/**
	 * 区域
	 */
	@ApiModelProperty(value = "区域")
	private String cglArea;
	/**
	 * 个
	 */
	@ApiModelProperty(value = "个")
	private Long individual;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long day;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long dayA;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long dayB;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long dayC;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long dayD;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long dayE;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long dayF;
	/**
	 * 支付方式
	 */
	@ApiModelProperty(value = "支付方式")
	private String paymentMethod;
	/**
	 * 类型
	 */
	@ApiModelProperty(value = "类型")
	private String type;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long dayG;
	/**
	 * 其他类型
	 */
	@ApiModelProperty(value = "其他类型")
	private String otherWays;
	/**
	 * 公司名称
	 */
	@ApiModelProperty(value = "公司名称")
	private String corporateName;
	/**
	 * 开户行
	 */
	@ApiModelProperty(value = "开户行")
	private String bankDeposit;
	/**
	 * 账户
	 */
	@ApiModelProperty(value = "账户")
	private String account;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long dayH;
	/**
	 * 金额
	 */
	@ApiModelProperty(value = "金额")
	private BigDecimal money;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private Long dayI;
	/**
	 * 特别约定
	 */
	@ApiModelProperty(value = "特别约定")
	private String specialAgreement;
	/**
	 * 起始日期
	 */
	@ApiModelProperty(value = "起始日期")
	private Date date;
	/**
	 * 结束日期
	 */
	@ApiModelProperty(value = "结束日期")
	private Date dateA;
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	private Integer number;
	/**
	 * 数量甲方
	 */
	@ApiModelProperty(value = "数量甲方")
	private Integer numberA;
	/**
	 * 数量乙方
	 */
	@ApiModelProperty(value = "数量乙方")
	private Integer numberB;
	/**
	 * 附件一
	 */
	@ApiModelProperty(value = "附件一")
	private String cglAttachment;
	/**
	 * 附件三
	 */
	@ApiModelProperty(value = "附件三")
	private String cglAttachmentA;
	/**
	 * 报价表
	 */
	@ApiModelProperty(value = "报价表")
	private String quotationSheet;
	/**
	 * 电子邮箱
	 */
	@ApiModelProperty(value = "电子邮箱")
	private String cglEmail;
	/**
	 * 年
	 */
	@ApiModelProperty(value = "年")
	private String year;

}
