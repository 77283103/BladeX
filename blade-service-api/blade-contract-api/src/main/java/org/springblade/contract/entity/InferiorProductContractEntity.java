package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;
import org.springblade.core.tool.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 下脚品买卖合同模板 实体类
 *
 * @author Wang Pengfei
 * @date : 2021-01-15 15:54:13
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("inferior_product_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "InferiorProductContract对象", description = "下脚品买卖合同模板")
public class InferiorProductContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String infSaler;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String infSalerAddr;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String infBuyer;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String infBuyerAddr;
	/**
	 * 填空，提醒一般类、废面类、废油类
	 */
	@ApiModelProperty(value = "填空，提醒一般类、废面类、废油类")
	private String infTypeFir;
	/**
	 * 每周开始
	 */
	@ApiModelProperty(value = "每周开始")
	private String infWeekStart;
	/**
	 * 每周结束
	 */
	@ApiModelProperty(value = "每周结束")
	private String infWeekEnd;
	/**
	 * 时间开始
	 */
	/**
	 * 合同结束时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_TIME)
	@JsonFormat(pattern = DateUtil.PATTERN_TIME)
	@ApiModelProperty(value = "时间开始")
	private Date infTimeStart;
	/**
	 * 时间结束
	 */
	/**
	 * 合同结束时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_TIME)
	@JsonFormat(pattern = DateUtil.PATTERN_TIME)
	@ApiModelProperty(value = "时间结束")
	private Date infTimeEnd;
	/**
	 * 分拣作业地点
	 */
	@ApiModelProperty(value = "分拣作业地点")
	private String infSortAddr;
	/**
	 * 当次赔偿金额小于
	 */
	@ApiModelProperty(value = "当次赔偿金额小于")
	private BigDecimal infLeastAmount;
	/**
	 * 元/次计算赔偿金
	 */
	@ApiModelProperty(value = "元/次计算赔偿金")
	private BigDecimal infTimeAmount;
	/**
	 * 装车地点
	 */
	@ApiModelProperty(value = "装车地点")
	private String infLoadAddr;
	/**
	 * 履约保证金万元
	 */
	@ApiModelProperty(value = "履约保证金万元")
	private BigDecimal infAppointAmount;
	/**
	 * 履约保证金万元(大写)
	 */
	@ApiModelProperty(value = "履约保证金万元(大写)")
	private String infCap;
	/**
	 * 日内将履约保证金补足
	 */
	@ApiModelProperty(value = "日内将履约保证金补足")
	private String infTimeLeastFir;
	/**
	 * 日仍未补足的
	 */
	@ApiModelProperty(value = "日仍未补足的")
	private String infTimeLeastSec;
	/**
	 * 甲方户名
	 */
	@ApiModelProperty(value = "甲方户名")
	private String infSalerAccoutName;
	/**
	 * 甲方账号
	 */
	@ApiModelProperty(value = "甲方账号")
	private String infSalerAccoutId;
	/**
	 * 甲方开户行
	 */
	@ApiModelProperty(value = "甲方开户行")
	private String infSalerAccoutBank;
	/**
	 * 乙方户名
	 */
	@ApiModelProperty(value = "乙方户名")
	private String infBuyerAccoutName;
	/**
	 * 乙方账号
	 */
	@ApiModelProperty(value = "乙方账号")
	private String infBuyerAccoutId;
	/**
	 * 乙方开户行
	 */
	@ApiModelProperty(value = "乙方开户行")
	private String infBuyerAccoutBank;
	/**
	 * 日内甲方向乙方开具发票
	 */
	@ApiModelProperty(value = "日内甲方向乙方开具发票")
	private String infTimeLeastThi;
	/**
	 * 甲方指定邮箱
	 */
	@ApiModelProperty(value = "甲方指定邮箱")
	private String infSalerMail;
	/**
	 * 乙方指定邮箱
	 */
	@ApiModelProperty(value = "乙方指定邮箱")
	private String infBuyerMail;
	/**
	 * 违约金
	 */
	@ApiModelProperty(value = "违约金")
	private BigDecimal infBreachAmountFir;
	/**
	 * 甲方有权解除本合同并要求乙方支付违约金
	 */
	@ApiModelProperty(value = "甲方有权解除本合同并要求乙方支付违约金")
	private BigDecimal infBreachAmountSec;
	/**
	 * 元作为解约补偿金
	 */
	@ApiModelProperty(value = "元作为解约补偿金")
	private BigDecimal infBreachAmountThi;
	/**
	 * 乙方还应向甲方支付
	 */
	@ApiModelProperty(value = "乙方还应向甲方支付")
	private BigDecimal infBreachAmountFou;
	/**
	 * 合同期限开始
	 */
	@ApiModelProperty(value = "合同期限开始")
	private Date infContractStart;
	/**
	 * 合同期限结束
	 */
	@ApiModelProperty(value = "合同期限结束")
	private Date infContractEnd;
	/**
	 * 本合同一式份
	 */
	@ApiModelProperty(value = "本合同一式份")
	private String infContractSum;
	/**
	 * 甲乙双方各执
	 */
	@ApiModelProperty(value = "甲乙双方各执")
	private String infContractNum;
	/**
	 * 甲方电话
	 */
	@ApiModelProperty(value = "甲方电话")
	private String infSalerPhone;
	/**
	 * 乙方电话
	 */
	@ApiModelProperty(value = "乙方电话")
	private String infBuyerPhone;
	/**
	 * 甲方联系人
	 */
	@ApiModelProperty(value = "甲方联系人")
	private String infSalerPerson;
	/**
	 * 乙方联系人
	 */
	@ApiModelProperty(value = "乙方联系人")
	private String infBuyerPerson;
	/**
	 * 废面流向承诺
	 */
	@ApiModelProperty(value = "废面流向承诺")
	private String infSurf;
	/**
	 * 废油流向承诺
	 */
	@ApiModelProperty(value = "废油流向承诺")
	private String infOil;
	/**
	 * 废茶渣流向承诺
	 */
	@ApiModelProperty(value = "废茶渣流向承诺")
	private String infTeaSurf;
	/**
	 * 附件拼接
	 */
	@ApiModelProperty(value="附件拼接")
	private String annex;
}
