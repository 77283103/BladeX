package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("mtl_advertising")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglAdvertisementProduction对象", description = "采购类：广告制作安装合同模板")
public class CglAdvertisementProductionEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String mtlPartyA;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String mtlPartyASite;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String mtlPartyB;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String mtlPartyBSite;
	/**
	 * 就甲方委托乙方在?区域制作
	 */
	@ApiModelProperty(value = "就甲方委托乙方在?区域制作")
	private String mtlAreaMake;
	/**
	 * 1、制作物项目名称、规格、单价详见附件一《?报价表》
	 */
	@ApiModelProperty(value = "1、制作物项目名称、规格、单价详见附件一《?报价表》")
	private String mtlAttachmentQuotation;
	/**
	 * 甲方以书面或乙方指定电子邮箱（电子邮箱:?）
	 */
	@ApiModelProperty(value = "甲方以书面或乙方指定电子邮箱（电子邮箱:?）")
	private String mtlParyAEmail;
	/**
	 * 乙方收到通知?个工作日内给出尺寸
	 */
	@ApiModelProperty(value = "乙方收到通知?个工作日内给出尺寸")
	private String mtlInfrom;
	/**
	 * 若同一批数量太多，乙方应收到通知 ?日前向甲方说明情况
	 */
	@ApiModelProperty(value = "若同一批数量太多，乙方应收到通知 ?日前向甲方说明情况")
	private String mtlReceiveInfrom;
	/**
	 * 自同一批的制作物全部安装完毕后 ，乙方应通知甲方于?日内进行验收
	 */
	@ApiModelProperty(value = "自同一批的制作物全部安装完毕后 ，乙方应通知甲方于?日内进行验收")
	private String mtlInfromCheck;
	/**
	 * 每批制作物验收不合格率在10%以下，乙方除有义务在 ?日内免费修复或重新制作外
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "每批制作物验收不合格率在10%以下，乙方除有义务在 ?日内免费修复或重新制作外")
	private Integer mtlFreeRepaira;
	/**
	 * 每批制作物验收不合格率在10%-20%之间，乙方除有义务在?日内免费修复或重新制作外
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "每批制作物验收不合格率在10%-20%之间，乙方除有义务在?日内免费修复或重新制作外")
	private Integer mtlFreeRepairb;
	/**
	 * 每批制作物验收不合格率在20%-25%之间，乙方除有义务在 ?日内免费修复或重新制作外
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "每批制作物验收不合格率在20%-25%之间，乙方除有义务在 ?日内免费修复或重新制作外")
	private Integer mtlFreeRepairc;
	/**
	 * 每批制作物验收不合格率达到25%以上时，乙方除有义务在?日内免费重新制作外
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "每批制作物验收不合格率达到25%以上时，乙方除有义务在?日内免费重新制作外")
	private Integer mtlFreeRepaird;
	/**
	 * 乙方承诺对其制作安装的广告制作物自验收合格之日起免费保修?年
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "乙方承诺对其制作安装的广告制作物自验收合格之日起免费保修?年")
	private Integer mtlFreeWarrantyday;
	/**
	 * 乙方制作安装原因导致制作物无法正常使用时，乙方保证在?日内及时免费修复完毕
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "乙方制作安装原因导致制作物无法正常使用时，乙方保证在?日内及时免费修复完毕")
	private Integer mtlFreeRepair;
	/**
	 * 价格结算方式：双方同意甲方按以下第? 方式支付合同款项
	 */
	@ApiModelProperty(value = "价格结算方式：双方同意甲方按以下第? 方式支付合同款项")
	private String mtlClearingFrom;
	/**
	 * 核算无误且甲方收到  增值税 ?发票,天后支付该批次制作物全款
	 */
	@ApiModelProperty(value = "核算无误且甲方收到  增值税 ?发票,天后支付该批次制作物全款")
	private String mtlReceiveInvoice;
	/**
	 * 核算无误且甲方收到增值税发票?天 后支付该批次制作物全款
	 */
	@JsonSerialize(nullsUsing = NullSerializer.class)
	@ApiModelProperty(value = "核算无误且甲方收到增值税发票?天 后支付该批次制作物全款")
	private Integer mtlReceiveDay;
	/**
	 * (2)其他方式:?
	 */
	@ApiModelProperty(value = "(2)其他方式:?")
	private String mtlOtherMethods;
	/**
	 * 开户行: ?
	 */
	@ApiModelProperty(value = "开户行: ?                         ")
	private String mtlOpeningBankb;
	/**
	 * 帐号:?
	 */
	@ApiModelProperty(value = "帐号:?                         ")
	private String mtlAccountNumber;
	/**
	 * 甲方在使用过程中发现质量问题，乙方应该在接到甲方通知后?个工作日内负责调换或维修
	 */
	@ApiModelProperty(value = "甲方在使用过程中发现质量问题，乙方应该在接到甲方通知后?个工作日内负责调换或维修")
	private String mtlQualityInform;
	/**
	 * 由此给甲方造成的一切经济损失和责任，概由乙方负责，乙方并向甲方另外支付违约金人民币?万元整
	 */
	@ApiModelProperty(value = "由此给甲方造成的一切经济损失和责任，概由乙方负责，乙方并向甲方另外支付违约金人民币?万元整")
	private String mtlLiquidatedDamages;
	/**
	 * （1）迟延制作或安装达?天的.
	 */
	@ApiModelProperty(value = "（1）迟延制作或安装达?天的.")
	private String mtlInstallDay;
	/**
	 * 十四、特别约定?
	 */
	@ApiModelProperty(value = "十四、特别约定?")
	private String mtlSpecialAgreement;
	/**
	 * 本合同有效期自 ? 始
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "本合同有效期自 ? 始")
	private Date mtlStartDate;
	/**
	 * 至?日止
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "至?日止")
	private Date mtlDateClosed;
	/**
	 * 十八、本合同一式 ? 份
	 */
	@ApiModelProperty(value = "十八、本合同一式 ? 份")
	private String mtlContractNumber;
	/**
	 * 甲方执 ?份
	 */
	@ApiModelProperty(value = "甲方执 ?份")
	private String mtlContractNumbera;
	/**
	 * 乙方执 ?份
	 */
	@ApiModelProperty(value = "乙方执 ?份")
	private String mtlContractNumberb;
	/**
	 * 附件一 ? 报价表
	 */
	@ApiModelProperty(value = "附件一 ? 报价表")
	private String mtlAccessoryQuotation;
	/**
	 * 附件三?
	 */
	@ApiModelProperty(value = "附件三? ")
	private String mtlAccessoryThree;
	/**
	 * 甲方联系地址
	 */
	@ApiModelProperty(value = "甲方联系地址")
	private String contactPartyAPlace;
	/**
	 * 乙方联系地址
	 */
	@ApiModelProperty(value = "乙方联系地址")
	private String contactPartyBPlace;
	/**
	 * 甲方联系人
	 */
	@ApiModelProperty(value = "甲方联系人")
	private String contactPartyAPerson;
	/**
	 * 乙方联系人
	 */
	@ApiModelProperty(value = "乙方联系人")
	private String contactPartyBPerson;
	/**
	 * 甲方联系电话
	 */
	@ApiModelProperty(value = "甲方联系电话")
	private String contactPartyAPhone;
	/**
	 * 乙方联系电话
	 */
	@ApiModelProperty(value = "乙方联系电话")
	private String contactPartyBPhone;
	/**
	 * 合同ID
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "合同ID")
	private Long contractId;

	/**
	 * 年
	 */
	@ApiModelProperty(value = "附件公共字段")
	private String annex;

}
