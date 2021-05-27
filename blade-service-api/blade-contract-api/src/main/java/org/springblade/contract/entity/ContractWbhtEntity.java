package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
import java.util.List;


/**
 * 消防-维保合同 实体类
 *
 * @author kx
 * @date : 2021-05-10 13:41:02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_wbht")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractWbht对象", description = "消防-维保合同")
public class ContractWbhtEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 甲方
	 */
	@ApiModelProperty(value = "甲方")
	private String contactPartyA;
	/**
	 * 乙方
	 */
	@ApiModelProperty(value = "乙方")
	private String contactPartyB;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String contactPartyAAddr;
	/**
	 * 乙方地址
	 */
	@ApiModelProperty(value = "乙方地址")
	private String contactPartyBAddr;
	/**
	 * 甲方指定联络邮箱
	 */
	@ApiModelProperty(value = "甲方指定联络邮箱")
	private String contactPartyAEmail;
	/**
	 * 乙方指定联络邮箱
	 */
	@ApiModelProperty(value = "乙方指定联络邮箱")
	private String contactPartyBEmail;
	/**
	 * 维修、保养所在地
	 */
	@ApiModelProperty(value = "维修、保养所在地")
	private String contactAddr;
	/**
	 * 维修、保养服务期限起
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "维修、保养服务期限起")
	private Date contactTimeStart;
	/**
	 * 维修、保养服务期限止
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "维修、保养服务期限止")
	private Date contactTimeEnd;
	/**
	 * 每月/每季度/每半年 下拉选
	 */
	@ApiModelProperty(value = "每月/每季度/每半年 下拉选")
	private String contactTime;
	/**
	 * 其他建筑消防设施
	 */
	@ApiModelProperty(value = "其他建筑消防设施")
	private String contactElsePlace;
	/**
	 * 保养费用：未税金额计人民币?元/年
	 */
	@ApiModelProperty(value = "保养费用：未税金额计人民币?元/年")
	private BigDecimal contactYuan;
	/**
	 * 税率为?%
	 */
	@ApiModelProperty(value = "税率为?%")
	private Double contactPercent;
	/**
	 * 乙方开户行
	 */
	@ApiModelProperty(value = "乙方开户行")
	private String contactAccountBank;
	/**
	 * 乙方户名
	 */
	@ApiModelProperty(value = "乙方户名")
	private String contactAccountName;
	/**
	 * 乙方账号
	 */
	@ApiModelProperty(value = "乙方账号")
	private String contactAccountNum;
	/**
	 * 若保养项目未达到约定量的?%
	 */
	@ApiModelProperty(value = "若保养项目未达到约定量的?%")
	private Double contactPercentT;
	/**
	 * 乙方应支付甲方该保养项目当期总金额的?%作为违约金
	 */
	@ApiModelProperty(value = "乙方应支付甲方该保养项目当期总金额的?%作为违约金")
	private Double contactPercentF;
	/**
	 * 发生次数达?次及以上的
	 */
	@ApiModelProperty(value = "发生次数达?次及以上的")
	private String contactTimes;
	/**
	 * 甲方联系人
	 */
	@ApiModelProperty(value = "甲方联系人")
	private String contactPartyAPerson;
	/**
	 * 乙方联系人
	 */
	@ApiModelProperty(value = "乙方联系人")
	private String contactPartyBPerson;
	/**
	 * 甲方联系电话
	 */
	@ApiModelProperty(value = "甲方联系电话")
	private String contactPartyAPhone;
	/**
	 * 乙方联系电话
	 */
	@ApiModelProperty(value = "乙方联系电话")
	private String contactPartyBPhone;
	/**
	 * 发包方单位名称
	 */
	@ApiModelProperty(value = "发包方单位名称")
	private String contactFaBaoFang;
	/**
	 * 承包方（承包商）单位名称
	 */
	@ApiModelProperty(value = "承包方（承包商）单位名称")
	private String contactChengBaoFang;
	/**
	 * 法定代表人地址
	 */
	@ApiModelProperty(value = "法定代表人地址")
	private String contactFaDingPlace;
	/**
	 * 乙方需向甲方支付产品价值？倍的违约金
	 */
	@ApiModelProperty(value = "乙方需向甲方支付产品价值？倍的违约金")
	private String contactValue;
	/**
	 * 项目负责单位最高主管
	 */
	@ApiModelProperty(value = "项目负责单位最高主管")
	private String contractLeader;
	/**
	 * 经办人员
	 */
	@ApiModelProperty(value = "经办人员")
	private String contractPeople;
	/**
	 * 拼接附件
	 */
	@ApiModelProperty(value = "拼接附件")
	private String annex;

	@ApiModelProperty(value = "消防设备关联表")
	@TableField(exist = false)
	private List<ContractWbht1Entity> contractWbht1EntityList;

}
