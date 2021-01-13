package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 媒体类：音频制作合同 实体类
 *
 * @author 媒体类：音频制作合同
 * @date : 2020-12-10 19:21:34
 */
@Getter
@Setter
@TableName("mtl_audio_production_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlAudioProductionContract对象", description = "媒体类：音频制作合同")
public class MtlAudioProductionContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value="甲方")
	private String mtlPatyA;
	/**
	 * 甲方联络邮箱
	 */
	@ApiModelProperty(value="甲方联络邮箱")
	private String mtlPatyAEmail;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value="甲方地址")
	private String mtlContactEmail;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value="乙方")
	private String mtlPatyB;
	/**
	 * 乙方联络邮箱
	 */
	@ApiModelProperty(value="乙方联络邮箱")
	private String mtlPatyBEmail;
	/**
	 * 乙方住所
	 */
	@ApiModelProperty(value="乙方住所")
	private String mtlPatyBHome;
	/**
	 * 甲方委托乙方完成【？】音频制作事宜，乙方接受甲方的委托
	 */
	@ApiModelProperty(value="甲方委托乙方完成【？】音频制作事宜，乙方接受甲方的委托")
	private String mtlAdaptationIssues;
	/**
	 * 音频名称
	 */
	@ApiModelProperty(value="音频名称")
	private String mtlNameOfTheAudio;
	/**
	 * 音频内容
	 */
	@ApiModelProperty(value="音频内容")
	private String mtlAudioContent;
	/**
	 * 作品中【？】（有/未）涉及第三方知识产权、VO等
	 */
	@ApiModelProperty(value="作品中【？】（有/未）涉及第三方知识产权、VO等")
	private String mtlHaveHasNot;
	/**
	 * 制作开始时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="制作开始时间")
	private Date mtlProductionStartTime;
	/**
	 * 制作完成时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="制作完成时间")
	private Date mtlProductionCompletionTime;
	/**
	 * 乙方制作的作品被甲方全部确认后，应同时提交以下内容
	 */
	@ApiModelProperty(value="乙方制作的作品被甲方全部确认后，应同时提交以下内容")
	private String mtlSubmitContent;
	/**
	 * 甲方指定验收人员
	 */
	@ApiModelProperty(value="甲方指定验收人员")
	private String mtlDesignatedPerson;
	/**
	 * 本音频制作合同价款为(未税额人民币)
	 */
	@ApiModelProperty(value="本音频制作合同价款为(未税额人民币)")
	private BigDecimal mtlUnpaidTaxRmb;
	/**
	 * 税率
	 */
	@ApiModelProperty(value="税率")
	private Double mtlRate;
	/**
	 * 现含税金额人民币
	 */
	@ApiModelProperty(value="现含税金额人民币")
	private BigDecimal mtlTaxAmountIsRmb;
	/**
	 * 本合同付款时间及比例依以下第*项执行
	 */
	@ApiModelProperty(value="本合同付款时间及比例依以下第*项执行")
	private String mtlTerm;
	/**
	 * 1金额合法税务发票后*天内，甲方向乙方支付全部款项
	 */
	@ApiModelProperty(value="1金额合法税务发票后*天内，甲方向乙方支付全部款项")
	private String mtlInvoiceDays1;
	/**
	 * 2金额合法税务发票后*天内，甲方向乙方支付全部款项
	 */
	@ApiModelProperty(value="2金额合法税务发票后*天内，甲方向乙方支付全部款项")
	private String mtlInvoiceDays2;
	/**
	 * 2向乙方支付人民币（大写）*元
	 */
	@ApiModelProperty(value="2向乙方支付人民币（大写）*元")
	private String mtlCapitalizationRmb2;
	/**
	 * 2甲方向乙方支付剩余全部款项即人民币（大写）*元
	 */
	@ApiModelProperty(value="2甲方向乙方支付剩余全部款项即人民币（大写）*元")
	private String mtlCapitalizationRmbSy2;
	/**
	 * 公司名
	 */
	@ApiModelProperty(value="公司名")
	private String mtlCompanyName;
	/**
	 * 开户行
	 */
	@ApiModelProperty(value="开户行")
	private String mtlWhereItIs;
	/**
	 * 账号
	 */
	@ApiModelProperty(value="账号")
	private String mtlAccount;
	/**
	 * 甲方应按日支付未付款项的*%的违约金给乙方
	 */
	@ApiModelProperty(value="甲方应按日支付未付款项的*%的违约金给乙方")
	private String mtlLiquidatedDamages2;
	/**
	 * 每逾一日乙方应向甲方支付合同总金额0.3%的违约金
	 */
	@ApiModelProperty(value="每逾一日乙方应向甲方支付合同总金额0.3%的违约金")
	private String mtlLiquidatedDamages3;
	/**
	 * 音乐词  *  年使用权
	 */
	@ApiModelProperty(value="音乐词  *  年使用权")
	private String mtlMusicUse;
	/**
	 * 歌手演唱  *  年的使用权
	 */
	@ApiModelProperty(value="歌手演唱  *  年的使用权")
	private String mtlSingerUse;
	/**
	 * 配音员VO  *  年的使用权
	 */
	@ApiModelProperty(value="配音员VO  *  年的使用权")
	private String mtlDubbingUse;
	/**
	 * 音频制作合同 关联表1
	 */
	@ApiModelProperty(value="音频制作合同 关联表1")
	@TableField(exist = false)
    private List<MtlAudioProductionContract1Entity> mtlAudioProductionContract1EntityList;
	/**
	 * 音频制作合同 关联表2
	 */
	@ApiModelProperty(value="音频制作合同 关联表2")
	@TableField(exist = false)
	private List<MtlAudioProductionContract2Entity> mtlAudioProductionContract2EntityList;
}
