package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
import java.util.List;


/**
 * 媒体类：平面广告拍摄制作合同 实体类
 *
 * @author 王策
 * @date : 2020-12-10 19:30:50
 */
@Getter
@Setter
@TableName("mtb_production_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtbProductionContract对象", description = "媒体类：平面广告拍摄制作合同")
public class MtbProductionContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String mtbPatyA;
	/**
	 * 甲方联络邮箱
	 */
    @ApiModelProperty(value="甲方联络邮箱")
	private String mtbContactEmail;
	/**
	 * 甲方地址
	 */
    @ApiModelProperty(value="甲方地址")
	private String mtbAddress;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String mtbPatyB;
	/**
	 * 乙方联络邮箱
	 */
    @ApiModelProperty(value="乙方联络邮箱")
	private String mtbPatyBEmail;
	/**
	 * 乙方地址
	 */
    @ApiModelProperty(value="乙方地址")
	private String mtbPatyBAddress;
	/**
	 * 甲方委托乙方完成【？】拍摄制作事宜
	 */
    @ApiModelProperty(value="甲方委托乙方完成【？】拍摄制作事宜")
	private String mtbMakeMatters;
	/**
	 * 广告名称
	 */
    @ApiModelProperty(value="广告名称")
	private String mtbNameOfAdvertising;
	/**
	 * 广告内容
	 */
    @ApiModelProperty(value="广告内容")
	private String mtbContentsOfAdvertisements;
	/**
	 * 交付形式
	 */
    @ApiModelProperty(value="交付形式")
	private String mtbDeliveryForm;
	/**
	 * 数量
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="数量")
	private Integer mtbNumberOf;
	/**
	 * 要求（规格、精度等）
	 */
    @ApiModelProperty(value="要求（规格、精度等）")
	private String mtbRequirements;
	/**
	 * 费用（元，含税）
	 */
    @ApiModelProperty(value="费用（元，含税）")
	private BigDecimal mtbCost;
	/**
	 * 作品中【？】（有/未）涉及第三方知识产权、肖像权等
	 */
    @ApiModelProperty(value="作品中【？】（有/未）涉及第三方知识产权、肖像权等")
	private String mtbHaveHasNot;
	/**
	 * 第三方知识产权
	 */
    @ApiModelProperty(value="第三方知识产权")
	private String mtbPropertyRights;
	/**
	 * 小样
	 */
    @ApiModelProperty(value="小样")
	private String mtbSmallKind;
	/**
	 * 使用期限
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="使用期限")
	private Date mtbUsePeriod;
	/**
	 * 使用区域
	 */
    @ApiModelProperty(value="使用区域")
	private String mtbUseArea;
	/**
	 * 拍摄开始时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="拍摄开始时间")
	private Date mtbShootingStartTime;
	/**
	 * 拍摄完成时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="拍摄完成时间")
	private Date mtbShootingCompletionTime;
	/**
	 * 甲方验收人员
	 */
    @ApiModelProperty(value="甲方验收人员")
	private String mtbAcceptancePersonnel;
	/**
	 * 本广告制作合同价款为未税额人民币
	 */
    @ApiModelProperty(value="本广告制作合同价款为未税额人民币")
	private String mtbUnpaidTaxRmb;
	/**
	 * 税率
	 */
    @ApiModelProperty(value="税率")
	private Double mtbRate;
	/**
	 * 含税金额人民币
	 */
    @ApiModelProperty(value="含税金额人民币")
	private BigDecimal mtbTaxInclusiveInRmb;
	/**
	 * 乙方公司名
	 */
    @ApiModelProperty(value="乙方公司名")
	private String mtbCompanyName;
	/**
	 * 乙方开户行
	 */
    @ApiModelProperty(value="乙方开户行")
	private String mtbBankOfPartyB;
	/**
	 * 乙方账号
	 */
    @ApiModelProperty(value="乙方账号")
	private String mtbPartyAccount;

	@ApiModelProperty(value = "平面广告拍摄制作合同1(关联表）")
	@TableField(exist = false)
	private List<MtlAdaptationContract1Entity> mtlAdaptationContract1EntityList;

	@ApiModelProperty(value = "平面广告拍摄制作合同2(关联表）")
	@TableField(exist = false)
	private List<MtlAdaptationContract2Entity> mtlAdaptationContract2EntityList;
}
