package org.springblade.contract.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springblade.core.tool.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;


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
	 * 交付形式
	 */
    @ApiModelProperty(value="交付形式")
	private String mtlDeliveryForm;
	/**
	 * 数量
	 */
    @ApiModelProperty(value="数量")
	private String mtlNumberOf;
	/**
	 * 内容主旨
	 */
    @ApiModelProperty(value="内容主旨")
	private String mtlContentIdea;
	/**
	 * 要求（语言、规格、精度等）
	 */
    @ApiModelProperty(value="要求（语言、规格、精度等）")
	private String mtlRequirements;
	/**
	 * 费用（元，含税）
	 */
    @ApiModelProperty(value="费用（元，含税）")
	private BigDecimal mtlCost;
	/**
	 * 作品中【？】（有/未）涉及第三方知识产权、肖像权
	 */
    @ApiModelProperty(value="作品中【？】（有/未）涉及第三方知识产权、肖像权")
	private String mtlHaveHasNot;
	/**
	 * 第三方知识产权
	 */
    @ApiModelProperty(value="第三方知识产权")
	private String mtlPropertyRights;
	/**
	 * 小样
	 */
    @ApiModelProperty(value="小样")
	private String mtlSmallKind;
	/**
	 * 使用期限
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="使用期限")
	private Date mtlUsePeriod;
	/**
	 * 使用区域
	 */
    @ApiModelProperty(value="使用区域")
	private String mtlUseArea;
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
	 * 上述广告作品给甲方指定人员【？】验收
	 */
    @ApiModelProperty(value="上述广告作品给甲方指定人员【？】验收")
	private String mtlAcceptance;

}
