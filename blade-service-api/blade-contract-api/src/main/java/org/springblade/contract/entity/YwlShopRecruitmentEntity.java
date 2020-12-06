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
public class YwlShopRecruitmentEntity extends BaseEntity {

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
	 * 乙方店招宣传牌悬挂时间
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="乙方店招宣传牌悬挂时间")
	private Date ywlSuspension;
	/**
	 * 本协议期限
	 */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
    @JsonFormat(pattern = DateUtil.PATTERN_DATE)
    @ApiModelProperty(value="本协议期限")
	private Date ywlAgreementPeriod;
	/**
	 * 宣传牌制作费（人民币小写
	 */
    @ApiModelProperty(value="宣传牌制作费（人民币小写")
	private BigDecimal ywlProductionCosts;
	/**
	 * 金额大写:
	 */
    @ApiModelProperty(value="金额大写:")
	private String ywlAmountOf;
	/**
	 * 其他约定
	 */
    @ApiModelProperty(value="其他约定")
	private String ywlOtherConventions;

}
