package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;


/**
 * 国内设备买卖合同 实体类
 *
 * @author kx
 * @date : 2021-05-10 13:39:18
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_mmht")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractMmht对象", description = "国内设备买卖合同")
public class ContractMmhtEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * ?统一
	 */
	@ApiModelProperty(value = "?统一")
	private String contactPartyAA;
	/**
	 * 统一？有限公司
	 */
	@ApiModelProperty(value = "统一？有限公司")
	private String contactPartyAB;
	/**
	 * 甲方住所
	 */
	@ApiModelProperty(value = "甲方住所")
	private String contactPartyAAddr;
	/**
	 * 乙方住所
	 */
	@ApiModelProperty(value = "乙方住所")
	private String contactPartyBAddr;
	/**
	 * 未税总价人民币?元
	 */
	@ApiModelProperty(value = "未税总价人民币?元")
	private BigDecimal contactYuanSma;
	/**
	 * 未税总价人民币?元大写
	 */
	@ApiModelProperty(value = "未税总价人民币?元大写")
	private String contactYuanBig;
	/**
	 * 包装标准
	 */
	@ApiModelProperty(value = "包装标准")
	private String contactBzbz;
	/**
	 * 交货时间
	 */
	@ApiModelProperty(value = "交货时间")
	private Date contactJhss;
	/**
	 * 交货地址
	 */
	@ApiModelProperty(value = "交货地址")
	private String contactJhdz;
	/**
	 * 运输方式
	 */
	@ApiModelProperty(value = "运输方式")
	private String contactYsff;
	/**
	 * 运费承担方
	 */
	@ApiModelProperty(value = "运费承担方")
	private String contactCdf;
	/**
	 * 卸货方
	 */
	@ApiModelProperty(value = "卸货方")
	private String contactBcdf;
	/**
	 * 费用承担方
	 */
	@ApiModelProperty(value = "费用承担方")
	private String contactFycdf;
	/**
	 * 特别约定
	 */
	@ApiModelProperty(value = "特别约定")
	private String contactTbyd;
	/**
	 * 乙方应随设备提供以下文件?
	 */
	@ApiModelProperty(value = "乙方应随设备提供以下文件?")
	private String contactFile;
	/**
	 * 七2（2）特别约定
	 */
	@ApiModelProperty(value = "七2（2）特别约定")
	private String contactSpecil;
	/**
	 * 甲方有权在?时至乙方工厂对设备状况进行初步检查
	 */
	@ApiModelProperty(value = "甲方有权在?时至乙方工厂对设备状况进行初步检查")
	private String contactWhen;
	/**
	 * 甲方收到乙方的设备后，应在?日内
	 */
	@ApiModelProperty(value = "甲方收到乙方的设备后，应在?日内")
	private String contactDay;
	/**
	 * 本合同适用以下?的约定
	 */
	@ApiModelProperty(value = "本合同适用以下?的约定")
	private String contactNext;
	/**
	 * 乙方应在甲方指定的期限内派人员在?日内
	 */
	@ApiModelProperty(value = "乙方应在甲方指定的期限内派人员在?日内")
	private String contactDayt;
	/**
	 * 甲方确认安装调试运行?小时无误后
	 */
	@ApiModelProperty(value = "甲方确认安装调试运行?小时无误后")
	private String contactHour;
	/**
	 * 安装调试运行?小时
	 */
	@ApiModelProperty(value = "安装调试运行?小时")
	private String contactHourt;
	/**
	 * 本合同适用以下?的约定
	 */
	@ApiModelProperty(value = "本合同适用以下?的约定")
	private String contactSpecilf;
	/**
	 * 质保期满?天内
	 */
	@ApiModelProperty(value = "质保期满?天内")
	private String contactDayf;
	/**
	 * 乙方应在?天内
	 */
	@ApiModelProperty(value = "乙方应在?天内")
	private String contactDays;
	/**
	 * 逾期?天
	 */
	@ApiModelProperty(value = "逾期?天")
	private String contactDayx;
	/**
	 * 合同总金额?%作为违约金
	 */
	@ApiModelProperty(value = "合同总金额?%作为违约金")
	private Double contactPercent;
	/**
	 * 七3（6）特别约定
	 */
	@ApiModelProperty(value = "七3（6）特别约定")
	private String contactSpecilu;
	/**
	 * 双方同意采用以下第?种约定付款
	 */
	@ApiModelProperty(value = "双方同意采用以下第?种约定付款")
	private String contactTypeg;
	/**
	 * 款项均以?方式支付
	 */
	@ApiModelProperty(value = "款项均以?方式支付")
	private String contactTypes;
	/**
	 * 发票后?日内付清全款
	 */
	@ApiModelProperty(value = "发票后?日内付清全款")
	private String contactDayn;
	/**
	 * 甲方与乙方签订本合同后?日内
	 */
	@ApiModelProperty(value = "甲方与乙方签订本合同后?日内")
	private String contactDayk;
	/**
	 * 合同总金额?%
	 */
	@ApiModelProperty(value = "合同总金额?%")
	private Double contactPencenty;
	/**
	 * 发货前在乙方工厂初验合格后?日内
	 */
	@ApiModelProperty(value = "发货前在乙方工厂初验合格后?日内")
	private String contactAcceptw;
	/**
	 * 支付合同总金额?%
	 */
	@ApiModelProperty(value = "支付合同总金额?%")
	private Double contactPencentr;
	/**
	 * 甲方收货验收合格后?日内
	 */
	@ApiModelProperty(value = "甲方收货验收合格后?日内")
	private String contactAccept;
	/**
	 * 付合同总金额?%
	 */
	@ApiModelProperty(value = "付合同总金额?%")
	private Double contactPencente;
	/**
	 * 设备安装调试经甲方竣工验收合格后?日内
	 */
	@ApiModelProperty(value = "设备安装调试经甲方竣工验收合格后?日内")
	private String contactDayp;
	/**
	 * 付合同总金额?%
	 */
	@ApiModelProperty(value = "付合同总金额?%")
	private Double contactPencentw;
	/**
	 * 合同总金额?%作为质保金
	 */
	@ApiModelProperty(value = "合同总金额?%作为质保金")
	private Double contactPencentq;
	/**
	 * 质保验收合格后?日内
	 */
	@ApiModelProperty(value = "质保验收合格后?日内")
	private String contactQuaty;
	/**
	 * 前述金额合法税务发票提供时间
	 */
	@ApiModelProperty(value = "前述金额合法税务发票提供时间")
	private Date contactLawtime;
	/**
	 * 其他方式
	 */
	@ApiModelProperty(value = "其他方式")
	private String contactElsytype;
	/**
	 * 乙方开户行
	 */
	@ApiModelProperty(value = "乙方开户行")
	private String contactBbank;
	/**
	 * 乙方户  名
	 */
	@ApiModelProperty(value = "乙方户  名")
	private String contactBname;
	/**
	 * 乙方账  号
	 */
	@ApiModelProperty(value = "乙方账  号")
	private String contactBnum;
	/**
	 * 方竣工验收合格之日）起?个月
	 */
	@ApiModelProperty(value = "方竣工验收合格之日）起?个月")
	private String contactMonth;
	/**
	 * 乙方应在接到甲方通知后?天内
	 */
	@ApiModelProperty(value = "乙方应在接到甲方通知后?天内")
	private String contactMonthq;
	/**
	 * 若经甲方?次催告
	 */
	@ApiModelProperty(value = "若经甲方?次催告")
	private String contactMonthw;
	/**
	 * （3）特别约定?
	 */
	@ApiModelProperty(value = "（3）特别约定?")
	private String contactMonthe;
	/**
	 * 并向甲方支付合同总金额?%作为违约金
	 */
	@ApiModelProperty(value = "并向甲方支付合同总金额?%作为违约金")
	private Double contactMonthr;
	/**
	 * 本合同总金额的 ?%的违约金
	 */
	@ApiModelProperty(value = "本合同总金额的 ?%的违约金")
	private Double contactMontht;
	/**
	 * 逾期交货超过（含）?日
	 */
	@ApiModelProperty(value = "逾期交货超过（含）?日")
	private String contactMonthy;
	/**
	 * 付款金额的?%作为违约金
	 */
	@ApiModelProperty(value = "付款金额的?%作为违约金")
	private Double contactMonthyy;
	/**
	 * 产品质量鉴定以甲方住所地?单位出具
	 */
	@ApiModelProperty(value = "产品质量鉴定以甲方住所地?单位出具")
	private String contactMonthu;
	/**
	 * 应向甲方支付合同总金额?%作为违约金
	 */
	@ApiModelProperty(value = "应向甲方支付合同总金额?%作为违约金")
	private Double contactMonthi;
	/**
	 * 7、其他约定?
	 */
	@ApiModelProperty(value = "7、其他约定?")
	private String contactMontho;
	/**
	 * 本合同一式?份
	 */
	@ApiModelProperty(value = "本合同一式?份")
	private String contactMonthp;
	/**
	 * 甲方执?份
	 */
	@ApiModelProperty(value = "甲方执?份")
	private String contactMontha;
	/**
	 * 乙方执?份
	 */
	@ApiModelProperty(value = "乙方执?份")
	private String contactMonths;
	/**
	 * 本合同包括以下附件?
	 */
	@ApiModelProperty(value = "本合同包括以下附件?")
	private String contactMonthd;
	/**
	 * 十八：特别约定?
	 */
	@ApiModelProperty(value = "十八：特别约定?")
	private String contactMonthf;
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

}
