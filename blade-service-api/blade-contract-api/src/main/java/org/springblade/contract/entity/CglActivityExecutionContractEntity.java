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
 * 采购类：活动执行合同 实体类
 *
 * @author 王策
 * @date : 2020-12-10 22:28:03
 */
@Getter
@Setter
@TableName("cgl_activity_execution_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CglActivityExecutionContract对象", description = "采购类：活动执行合同")
public class CglActivityExecutionContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String cglPartya;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String cglPartyb;
	/**
	 * 活动
	 */
	@ApiModelProperty(value = "活动")
	private String cglActivity;
	/**
	 * 区域
	 */
	@ApiModelProperty(value = "区域")
	private String cglArea;
	/**
	 * 活动名称
	 */
	@ApiModelProperty(value = "活动名称")
	private String activityName;
	/**
	 * 本活动执行发起时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	@ApiModelProperty(value = "本活动执行发起时间")
	private Date cglByTime;
	/**
	 * 本活动执行截止时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
	@JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
	@ApiModelProperty(value = "本活动执行截止时间")
	private Date cglAsTime;
	/**
	 * 本活动总费用
	 */
	@ApiModelProperty(value = "本活动总费用")
	private String cglTotal;
	/**
	 * 活动场次
	 */
	@ApiModelProperty(value = "活动场次")
	private Integer cglSession;
	/**
	 * 甲、乙双方同意按约定向乙方付款
	 */
	@ApiModelProperty(value = "甲、乙双方同意按约定向乙方付款")
	private String cglPayment;
	/**
	 * 首款付款签订后天数
	 */
	@ApiModelProperty(value = "首款付款签订后天数")
	private Integer days;
	/**
	 * 总金额百分比金额
	 */
	@ApiModelProperty(value = "总金额百分比金额")
	private BigDecimal amount;
	/**
	 * 大写百分比总金额
	 */
	@ApiModelProperty(value = "大写百分比总金额")
	private String amountWords;
	/**
	 * 一次性支付剩余款项比例
	 */
	@ApiModelProperty(value = "一次性支付剩余款项比例")
	private Double cglProportion;
	/**
	 * 一次性支付金额
	 */
	@ApiModelProperty(value = "一次性支付金额")
	private BigDecimal cglLumpSum;
	/**
	 * 大写金额2
	 */
	@ApiModelProperty(value = "大写金额2")
	private String cglCapitalize;
	/**
	 * 其他方式
	 */
	@ApiModelProperty(value = "其他方式")
	private String other;
	/**
	 * 乙方开户行
	 */
	@ApiModelProperty(value = "乙方开户行")
	private String cglBank;
	/**
	 * 乙方户名
	 */
	@ApiModelProperty(value = "乙方户名")
	private String cglAccountName;
	/**
	 * 乙方账号
	 */
	@ApiModelProperty(value = "乙方账号")
	private String cglAccount;
	/**
	 * 乙方提供增值税发票【】%
	 */
	@ApiModelProperty(value = "乙方提供增值税发票【】%")
	private Double cglInvoice;
	/**
	 * 天
	 */
	@ApiModelProperty(value = "天")
	private String days1;
	/**
	 * 日
	 */
	@ApiModelProperty(value = "日")
	private String days2;
	/**
	 * 违约金比例
	 */
	@ApiModelProperty(value = "违约金比例")
	private Double breachContract;
	/**
	 * 活动策划方案
	 */
	@ApiModelProperty(value = "活动策划方案")
	private String cglPlanningScheme;
	/**
	 * 附件三：价格
	 */
	@ApiModelProperty(value = "附件三：价格")
	private String cglPrice;
	/**
	 * 附件四：考核标准
	 */
	@ApiModelProperty(value = "附件四：考核标准")
	private String cglInspectionStandard;
	/**
	 * 活动排期
	 */
	@ApiModelProperty(value = "活动排期")
	private String scheduling;
	/**
	 * 活动策划方案
	 */
	@ApiModelProperty(value = "活动策划方案")
	private String planningScheme;
	/**
	 * 大写：人民币
	 */
	@ApiModelProperty(value = "大写：人民币")
	private String capitalization;
	/**
	 * 活动场次
	 */
	@ApiModelProperty(value = "活动场次")
	private String events;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element;
	/**
	 * 附件一
	 */
	@ApiModelProperty(value = "附件一")
	private String one;
	/**
	 * 附件二
	 */
	@ApiModelProperty(value = "附件二")
	private String two;
	/**
	 * 附件三
	 */
	@ApiModelProperty(value = "附件三")
	private String three;
	/**
	 * 附件四
	 */
	@ApiModelProperty(value = "附件四")
	private String four;

}
