package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 媒体类：平面广告拍摄制作合同 实体类
 *
 * @author 韩杨
 * @date : 2021-01-21 11:26:17
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("mtb_production_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtbProductionContract对象", description = "媒体类：平面广告拍摄制作合同")
public class MtbProductionContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String mtbPatyA;
	/**
	 * 甲方联络邮箱
	 */
	@ApiModelProperty(value = "甲方联络邮箱")
	private String mtbContactEmail;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String mtbAddress;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String mtbPatyB;
	/**
	 * 乙方联络邮箱
	 */
	@ApiModelProperty(value = "乙方联络邮箱")
	private String mtbPatyBEmail;
	/**
	 * 住所
	 */
	@ApiModelProperty(value = "住所")
	private String mtbPatyBAddress;
	/**
	 * 甲方委托乙方完成【？】拍摄制作事宜
	 */
	@ApiModelProperty(value = "甲方委托乙方完成【？】拍摄制作事宜")
	private String mtbMakeMatters;
	/**
	 * 广告名称
	 */
	@ApiModelProperty(value = "广告名称")
	private String mtbNameOfAdvertising;
	/**
	 * 广告内容
	 */
	@ApiModelProperty(value = "广告内容")
	private String mtbContentsOfAdvertisements;
	/**
	 * 【？】（有/未）做成下拉选 二选一
	 */
	@ApiModelProperty(value = "【？】（有/未）做成下拉选 二选一")
	private String mtbHaveHasNot;
	/**
	 * 拍摄开始时间
	 */
	@ApiModelProperty(value = "拍摄开始时间")
	private Date mtbShootingStartTime;
	/**
	 * 拍摄完成时间
	 */
	@ApiModelProperty(value = "拍摄完成时间")
	private Date mtbShootingCompletionTime;
	/**
	 * 甲方验收人员
	 */
	@ApiModelProperty(value = "甲方验收人员")
	private String mtbAcceptancePersonnel;
	/**
	 * 乙方制作的广告作品被甲方全部确认后，应同时提交以下内容（勾选）：
	 */
	@ApiModelProperty(value = "乙方制作的广告作品被甲方全部确认后，应同时提交以下内容（勾选）：")
	private String mtbSubmitContent;
	/**
	 * 本广告制作合同价款为未税额人民币
	 */
	@ApiModelProperty(value = "本广告制作合同价款为未税额人民币")
	private String mtbUnpaidTaxRmb;
	/**
	 * 税率
	 */
	@ApiModelProperty(value = "税率")
	private Double mtbRate;
	/**
	 * 含税金额人民币
	 */
	@ApiModelProperty(value = "含税金额人民币")
	private BigDecimal mtbTaxInclusiveInRmb;
	/**
	 * 合同付款时间及比例依以下第【？】项执行
	 */
	@ApiModelProperty(value = "合同付款时间及比例依以下第【？】项执行")
	private String mtbManyItems;
	/**
	 * 合同签订且甲方收到乙方相应金额合法税务发票后 【？】天内
	 */
	@ApiModelProperty(value = "合同签订且甲方收到乙方相应金额合法税务发票后 【？】天内")
	private String mtbLegalTaxInvoice;
	/**
	 * 乙方支付人民币（大写）【？】元
	 */
	@ApiModelProperty(value = "乙方支付人民币（大写）【？】元")
	private String mtbPayRmb;
	/**
	 * 甲方向乙方支付剩余全部款项即人民币（大写）【？】元。
	 */
	@ApiModelProperty(value = "甲方向乙方支付剩余全部款项即人民币（大写）【？】元。")
	private String mtbAllRemainingMoney;
	/**
	 * 乙方公司名
	 */
	@ApiModelProperty(value = "乙方公司名")
	private String mtbCompanyName;
	/**
	 * 乙方开户行
	 */
	@ApiModelProperty(value = "乙方开户行")
	private String mtbBankOfPartyB;
	/**
	 * 乙方账号
	 */
	@ApiModelProperty(value = "乙方账号")
	private String mtbPartyAccount;
	/**
	 * 合同包含甲方对本广告中出现演员的肖像权 【？】 年
	 */
	@ApiModelProperty(value = "合同包含甲方对本广告中出现演员的肖像权 【？】 年")
	private String mtbPortrait;
	/**
	 * 合同签订日期
	 */
	@ApiModelProperty(value = "合同签订日期")
	private Date qiandingTime;
	/**
	 * 知识产权确认书签订日期
	 */
	@ApiModelProperty(value = "知识产权确认书签订日期")
	private Date zhiqiandingTime;
	/**
	 * 知识产权编号
	 */
	@ApiModelProperty(value = "知识产权编号")
	private String zhichichanquan;
	/**
	 * 合同编号
	 */
	@ApiModelProperty(value = "合同编号")
	private String hetongbianhao;
	/**
	 * 委托我司进行****之工作
	 */
	@ApiModelProperty(value = "委托我司进行****之工作")
	private String weituo;
	/**
	 * 平面广告拍摄制作合同 关联表1
	 */
	@ApiModelProperty(value = "平面广告拍摄制作合同 关联表1")
	@TableField(exist = false)
	private List<MtbProductionContract1Entity> mtbProductionContract1EntityList;
	/**
	 * 平面广告拍摄制作合同 关联表2
	 */
	@ApiModelProperty(value = "平面广告拍摄制作合同 关联表2")
	@TableField(exist = false)
	private List<MtbProductionContract2Entity> mtbProductionContract2EntityList;
	/**
	 * 平面广告拍摄制作合同 关联表3
	 */
	@ApiModelProperty(value = "平面广告拍摄制作合同 关联表3")
	@TableField(exist = false)
	private List<MtbProductionContract3Entity> mtbProductionContract3EntityList;


}
