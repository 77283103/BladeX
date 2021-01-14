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
 * 媒体类：视频制作合同 实体类
 *
 * @author 媒体类：视频制作合同
 * @date : 2020-12-10 19:30:59
 */
@Getter
@Setter
@TableName("mtl_video_production_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtlVideoProductionContract对象", description = "媒体类：视频制作合同")
public class MtlVideoProductionContractEntity extends BaseEntity {

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
	 * 甲方委托乙方完成【？】改编事宜，乙方接受甲方的委托
	 */
    @ApiModelProperty(value="甲方委托乙方完成【？】改编事宜，乙方接受甲方的委托")
	private String mtlAdaptationIssues;
	/**
	 * 视频名称
	 */
    @ApiModelProperty(value="视频名称")
	private String mtlNameOfTheVideo;
	/**
	 * 视频内容
	 */
    @ApiModelProperty(value="视频内容")
	private String mtlVideoContent;
	/**
	 * 作品中【？】（有/未）涉及第三方知识产权、肖像权
	 */
    @ApiModelProperty(value="作品中【？】（有/未）涉及第三方知识产权、肖像权")
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
	 * 乙方制作的作品被甲方全部确认后，应同时提交以下内容（勾选）：
	 */
	@ApiModelProperty(value="乙方制作的作品被甲方全部确认后，应同时提交以下内容（勾选）：")
    private String mtlSubmitContent;
	/**
	 * 上述广告作品给甲方指定人员${mtlAcceptance}验收。
	 */
	@ApiModelProperty(value="上述广告作品给甲方指定人员${mtlAcceptance}验收。")
	private String mtlAcceptance;
	/**
	 * 本视频制作合同价款为(未税额人民币)
	 */
    @ApiModelProperty(value="本视频制作合同价款为(未税额人民币)")
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
	 * 本合同付款时间及比例依以下第${mtlPaymentMethod}项执行：
	 */
	@ApiModelProperty(value="本合同付款时间及比例依以下第${mtlPaymentMethod}项执行：")
    private String mtlPaymentMethod;
	/**
	 * 项执行：(1)本合同签订
	 */
	@ApiModelProperty(value=" 项执行：(1)本合同签订")
	private String mtlInvoiceDays1;
	/**
	 * 项执行：(2)法税务发票后${ mtlInvoiceDays2}天内，
	 */
	@ApiModelProperty(value="项执行：(2)法税务发票后${ mtlInvoiceDays2}天内")
	private String mtlInvoiceDays2;
	/**
	 *向乙方支付人民币（大写）${mtlCapitalizationRmb2}元
	 */
	@ApiModelProperty(value="向乙方支付人民币（大写）${mtlCapitalizationRmb2}元")
	private String mtlCapitalizationRmb2;
	/**
	 * 甲方向乙方支付剩余全部款项即人民币（大写${mtlCapitalizationRmbSy2}元
	 */
	@ApiModelProperty(value="甲方向乙方支付剩余全部款项即人民币（大写${mtlCapitalizationRmbSy2}元。")
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
	 * 肖像权
	 */
	@ApiModelProperty(value="肖像权")
	private String mtlPortraitRights;
	/**
	 * 广告中音乐
	 */
	@ApiModelProperty(value="广告中音乐")
	private String mtlLiquidatedDamages2;
	/**
	 * 配音员VO
	 */
	@ApiModelProperty(value="配音员VO")
	private String mtlDubbingUse;
	/**
	 * 视频制作合同关联表1 交付形式
	 */
	@ApiModelProperty(value="视频制作合同关联表1")
	@TableField(exist = false)
	private List<MtlVideoProductionContract1Entity> mtlVideoProductionContract1EntityList;
	/**
	 * 视频制作合同关联表2 涉及第三方知识产权、肖像权
	 */
	@ApiModelProperty(value="视频制作合同关联表2")
	@TableField(exist = false)
	private List<MtlVideoProductionContract2Entity> mtlVideoProductionContract2EntityList;

}
