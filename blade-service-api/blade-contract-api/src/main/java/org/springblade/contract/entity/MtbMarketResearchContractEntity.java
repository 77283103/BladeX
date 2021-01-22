package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.core.mp.base.BaseEntity;

import java.math.BigDecimal;
import java.util.List;


/**
 * 媒体类：市调合同（定性+定量) 实体类
 *
 * @author 刘是罕
 * @date : 2021-01-21 11:07:21
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("mtb_market_research_contract")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MtbMarketResearchContract对象", description = "媒体类：市调合同（定性+定量)")
public class MtbMarketResearchContractEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String patya;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String partyAddress;
	/**
	 * 甲方电话
	 */
	@ApiModelProperty(value = "甲方电话")
	private Integer number;
	/**
	 * 甲方传真
	 */
	@ApiModelProperty(value = "甲方传真")
	private Integer patyFax;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String patyb;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String patyAddressb;
	/**
	 * 乙方电话
	 */
	@ApiModelProperty(value = "乙方电话")
	private Integer patyPhoneb;
	/**
	 * 乙方传真
	 */
	@ApiModelProperty(value = "乙方传真")
	private Integer patyFaxb;
	/**
	 * 项目名称
	 */
	@ApiModelProperty(value = "项目名称")
	private String projectName;
	/**
	 * 附件：【？】项目计划书/报价单（择一）
	 */
	@ApiModelProperty(value = "附件：【？】项目计划书/报价单（择一）")
	private String attachment;
	/**
	 * 研究方法
	 */
	@ApiModelProperty(value = "研究方法")
	private String research;
	/**
	 * 研究方法
	 */
	@ApiModelProperty(value = "研究方法")
	private String methods;
	/**
	 * 受访者人数
	 */
	@ApiModelProperty(value = "受访者人数")
	private Integer numberRespondents;
	/**
	 * 受访者要求
	 */
	@ApiModelProperty(value = "受访者要求")
	private String requireRespondents;
	/**
	 * 调研简报
	 */
	@ApiModelProperty(value = "调研简报")
	private String results;
	/**
	 * 调研报告
	 */
	@ApiModelProperty(value = "调研报告")
	private String research1;
	/**
	 * 视频录像文件
	 */
	@ApiModelProperty(value = "视频录像文件")
	private String video;
	/**
	 * 定性研究现场原始问答记录
	 */
	@ApiModelProperty(value = "定性研究现场原始问答记录")
	private String qualitative;
	/**
	 * 定量研究原始数据记录
	 */
	@ApiModelProperty(value = "定量研究原始数据记录")
	private String quantitative1;
	/**
	 * 其他
	 */
	@ApiModelProperty(value = "其他")
	private String other;
	/**
	 * 其他
	 */
	@ApiModelProperty(value = "其他")
	private String other1;
	/**
	 * 其他
	 */
	@ApiModelProperty(value = "其他")
	private String other2;
	/**
	 * 总费用：本合同总费用为：未税额人民币
	 */
	@ApiModelProperty(value = "总费用：本合同总费用为：未税额人民币")
	private BigDecimal totalCost;
	/**
	 * 税率
	 */
	@ApiModelProperty(value = "税率")
	private Double rate;
	/**
	 * 现含税金额人民币
	 */
	@ApiModelProperty(value = "现含税金额人民币")
	private BigDecimal amount;
	/**
	 * 乙方承担
	 */
	@ApiModelProperty(value = "乙方承担")
	private String undertakesb;
	/**
	 * 甲方承担
	 */
	@ApiModelProperty(value = "甲方承担")
	private String undertakesa;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element1;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element2;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element3;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element4;
	/**
	 * 元含税
	 */
	@ApiModelProperty(value = "元含税")
	private BigDecimal element5;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element6;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element7;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element8;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element9;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element10;
	/**
	 * 元
	 */
	@ApiModelProperty(value = "元")
	private BigDecimal element11;
	/**
	 * 元含税
	 */
	@ApiModelProperty(value = "元含税")
	private BigDecimal element12;
	/**
	 * 公司名
	 */
	@ApiModelProperty(value = "公司名")
	private String company;
	/**
	 * 开户行
	 */
	@ApiModelProperty(value = "开户行")
	private String bank;
	/**
	 * 账号
	 */
	@ApiModelProperty(value = "账号")
	private String account;
	/**
	 * 甲方联系地址
	 */
	@ApiModelProperty(value = "甲方联系地址")
	private String contactAddress;
	/**
	 * 邮编
	 */
	@ApiModelProperty(value = "邮编")
	private String postcode;
	/**
	 * 甲方主要联系人
	 */
	@ApiModelProperty(value = "甲方主要联系人")
	private String contact;
	/**
	 * 电话号码
	 */
	@ApiModelProperty(value = "电话号码")
	private String telephone;
	/**
	 * 乙方联系地址
	 */
	@ApiModelProperty(value = "乙方联系地址")
	private String patyContact;
	/**
	 * 乙方主要联系人
	 */
	@ApiModelProperty(value = "乙方主要联系人")
	private String contact1;
	/**
	 * 邮编
	 */
	@ApiModelProperty(value = "邮编")
	private String postcode1;
	/**
	 * 电话号码
	 */
	@ApiModelProperty(value = "电话号码")
	private String phon;
	/**
	 * 附件二 【？】调研计划书/报价单（择一）
	 */
	@ApiModelProperty(value = "附件二 【？】调研计划书/报价单（择一）")
	private String annex;
	/**
	 * 关联子表
	 */
    @ApiModelProperty(value = "关联子表")
	@TableField(exist = false)
	private List<MtbMarketResearchContract1Entity> mtbMarketResearchContract1EntityList;
}
