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
 * 媒体类：市调合同（定性+定量) 实体类
 *
 * @author 王策
 * @date : 2020-12-10 19:37:14
 */
@Getter
@Setter
@TableName("mtb_market_research_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtbMarketResearchContract对象", description = "媒体类：市调合同（定性+定量)")
public class MtbMarketResearchContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
    @ApiModelProperty(value="甲方")
	private String mtbPatyA;
	/**
	 * 甲方地址
	 */
    @ApiModelProperty(value="甲方地址")
	private String mtbPartyAAddress;
	/**
	 * 甲方电话
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="甲方电话")
	private Integer mtbNumber;
	/**
	 * 甲方传真
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="甲方传真")
	private Integer mtbPatyAFax;
	/**
	 * 乙方
	 */
    @ApiModelProperty(value="乙方")
	private String mtbPatyB;
	/**
	 * 乙方地址
	 */
    @ApiModelProperty(value="乙方地址")
	private String mtbPatyBAddress;
	/**
	 * 乙方电话
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方电话")
	private Integer mtbPatyBPhone;
	/**
	 * 乙方传真
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="乙方传真")
	private Integer mtbPatyBFax;
	/**
	 * 项目名称
	 */
    @ApiModelProperty(value="项目名称")
	private String mtbProjectName;
	/**
	 * 附件：【？】项目计划书/报价单（择一）
	 */
    @ApiModelProperty(value="附件：【？】项目计划书/报价单（择一）")
	private String mtbAttachment;
	/**
	 * 研究方法
	 */
    @ApiModelProperty(value="研究方法")
	private String mtbResearchMethods;
	/**
	 * 受访者人数
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="受访者人数")
	private Integer mtbNumberOfRespondents;
	/**
	 * 成果物
	 */
    @ApiModelProperty(value="成果物")
	private String mtbResultsContent;
	/**
	 * 时间安排
	 */
    @ApiModelProperty(value="时间安排")
	private String mtbSchedule;
	/**
	 * 事项
	 */
    @ApiModelProperty(value="事项")
	private String mtbMatters;
	/**
	 * 总费用：本合同总费用为：未税额人民币
	 */
    @ApiModelProperty(value="总费用：本合同总费用为：未税额人民币")
	private String mtbTotalCost;
	/**
	 * 税率
	 */
    @ApiModelProperty(value="税率")
	private Double mtbRate;
	/**
	 * 现含税金额人民币
	 */
    @ApiModelProperty(value="现含税金额人民币")
	private BigDecimal mtbTaxAmount;
	/**
	 * 费用详单
	 */
    @ApiModelProperty(value="费用详单")
	private String mtbCostDetails;
	/**
	 * 定性研究：执行费用(约人，礼金，设备，场地)
	 */
    @ApiModelProperty(value="定性研究：执行费用(约人，礼金，设备，场地)")
	private BigDecimal mtbPerformCostA;
	/**
	 * 会议主持
	 */
    @ApiModelProperty(value="会议主持")
	private BigDecimal mtbPresidedOverBy;
	/**
	 * 差旅费
	 */
    @ApiModelProperty(value="差旅费")
	private BigDecimal mtbTravel;
	/**
	 * 大纲设计，报告撰写
	 */
    @ApiModelProperty(value="大纲设计，报告撰写")
	private BigDecimal mtbOutlineDesigns;
	/**
	 * 其他
	 */
    @ApiModelProperty(value="其他")
	private BigDecimal mtbOther;
	/**
	 * 合计
	 */
    @ApiModelProperty(value="合计")
	private BigDecimal mtbCombined;
	/**
	 * 定量研究：问卷设计、印刷
	 */
    @ApiModelProperty(value="定量研究：问卷设计、印刷")
	private BigDecimal mtbDesignAndPrinting;
	/**
	 * 场地费用
	 */
    @ApiModelProperty(value="场地费用")
	private BigDecimal mtbGreenFees;
	/**
	 * 执行费用（含受访者、礼金等费用）
	 */
    @ApiModelProperty(value="执行费用（含受访者、礼金等费用）")
	private BigDecimal mtbPerformCost;
	/**
	 * 定量研究：大纲设计、报告撰写
	 */
    @ApiModelProperty(value="定量研究：大纲设计、报告撰写")
	private BigDecimal mtbOutlineDesign;
	/**
	 * 差旅费用
	 */
    @ApiModelProperty(value="差旅费用")
	private BigDecimal mtbTravelExpenses;
	/**
	 * 其他
	 */
    @ApiModelProperty(value="其他")
	private BigDecimal mtbOtherA;
	/**
	 * 合计
	 */
    @ApiModelProperty(value="合计")
	private String mtdACombined;
	/**
	 * 乙方账户如下：公司名
	 */
    @ApiModelProperty(value="乙方账户如下：公司名")
	private String mtbCompanyName;
	/**
	 * 开户行
	 */
    @ApiModelProperty(value="开户行")
	private String mtbBank;
	/**
	 * 账号
	 */
    @ApiModelProperty(value="账号")
	private String mtbAccount;
	/**
	 * 函件送达地址：甲方联系地址
	 */
    @ApiModelProperty(value="函件送达地址：甲方联系地址")
	private String mtbMailDelivery;
	/**
	 * 主要联系人
	 */
    @ApiModelProperty(value="主要联系人")
	private String mtbTheContact;
	/**
	 * 函件送达地址：乙方联系地址
	 */
    @ApiModelProperty(value="函件送达地址：乙方联系地址")
	private String mtbPatyTheContact;
	/**
	 * 邮编
	 */
    @ApiModelProperty(value="邮编")
	private String mtbZipCode;
	/**
	 * 电话号码
	 */
    @JsonSerialize(nullsUsing = NullSerializer.class)
    @ApiModelProperty(value="电话号码")
	private Integer mtbPhon;
	/**
	 * 附件二 【？】调研计划书/报价单（择一）
	 */
    @ApiModelProperty(value="附件二 【？】调研计划书/报价单（择一）")
	private String mtbAnnexIi;

}
