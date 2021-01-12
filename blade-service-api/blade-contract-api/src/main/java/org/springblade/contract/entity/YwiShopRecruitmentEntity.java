package org.springblade.contract.entity;

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


/**
 * 业务类：14.店招合同 实体类
 *
 * @author szw
 * @date : 2020-12-04 19:04:54
 */
@Getter
@Setter
@TableName("ywl_shop_recruitment")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "YwlShopRecruitment对象", description = "业务类：14.店招合同")
public class YwiShopRecruitmentEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String ywlPatyA;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String ywlPatyB;
	/**
	 * 乙方店招宣传牌规格及位置
	 */
    @ApiModelProperty(value="乙方店招宣传牌规格及位置")
	private String ywlLocation;
	/**
	 * 乙方店招宣传牌悬挂开始时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="乙方店招宣传牌悬挂开始时间")
	private Date ywlSuspensionStart;
	/**
	 * 乙方店招宣传牌悬挂结束时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="乙方店招宣传牌悬挂结束时间")
	private Date ywlSuspensionEnd;
	/**
	 * 本协议起始时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="本协议起始时间")
	private Date ywlAgreementPeriodStart;
	/**
	 * 本协议终止时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value="本协议终止时间")
	private Date ywlAgreementPeriodEnd;
	/**
	 * 宣传牌制作费(人民币小写)
	 */
    @ApiModelProperty(value="宣传牌制作费(人民币小写)")
	private BigDecimal ywlProductionCosts;
	/**
	 * 金额大写
	 */
    @ApiModelProperty(value="金额大写")
	private String ywlAmountOf;
	/**
	 * 其他约定
	 */
    @ApiModelProperty(value="其他约定")
	private String ywlOtherConventions;


}
