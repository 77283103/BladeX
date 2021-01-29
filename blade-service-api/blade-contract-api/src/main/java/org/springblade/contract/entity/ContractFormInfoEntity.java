package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * 实体类
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:37
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("contract_form_info")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ContractFormInfo对象", description = "")
public class ContractFormInfoEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 合同名称
	 */
	@ApiModelProperty(value = "合同名称")
	private String contractName;

	/**
	 * 份数
	 */
	@ApiModelProperty(value = "份数")
	private String share;
	/**
	 * 合同负责人
	 */
	@ApiModelProperty(value = "合同负责人")
	private String personContract;

	/**
	 * 合同负责人
	 */
	@ApiModelProperty(value = "合同负责人")
	private String personCodeContract;
	/**
	 * 合同期限
	 */
	@ApiModelProperty(value = "合同期限")
	private String contractPeriod;
	/**
	 * 合同起始时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "合同起始时间")
	private Date startingTime;
	/**
	 * 合同结束时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "合同结束时间")
	private Date endTime;
	/**
	 * 付款方式
	 */
	@ApiModelProperty(value = "付款方式")
	private String colPayType;
	/**
	 * 收付款期限
	 */
	@ApiModelProperty(value = "收付款期限")
	private String colPayTerm;
	/**
	 * 天数
	 */
	@ApiModelProperty(value = "天数")
	private Integer days;
	/**
	 * 是否有自我延展的功能
	 */
	@ApiModelProperty(value = "是否有自我延展的功能")
	private String extension;
	/**
	 * 相对方联系人
	 */
	@ApiModelProperty(value = "相对方联系人")
	private String counterpartPerson;

	/**
	 * 联系人电话
	 */
	@ApiModelProperty(value = "联系人电话")
	private String telephonePerson;
	/**
	 * 联系人地址
	 */
	@ApiModelProperty(value = "联系人地址")
	private String addressPerson;
	/**
	 * 联系人邮箱
	 */
	@ApiModelProperty(value = "联系人邮箱")
	private String emailPerson;
	/**
	 * 合同形式（字典）
	 */
	@ApiModelProperty(value = "合同形式（字典）")
	private String contractForm;
	/**
	 * 用印全称
	 */
	@ApiModelProperty(value = "用印全称")
	private String sealName;
	/**
	 * 用印类型（字典）
	 */
	@ApiModelProperty(value = "用印类型（字典）")
	private String sealType;
	/**
	 * 用印份数
	 */
	@ApiModelProperty(value = "用印份数")
	private Integer sealNumber;
	/**
	 * 合同编号
	 */
	@ApiModelProperty(value = "合同编号")
	private String contractNumber;
	/**
	 * 合同文本
	 */
	@ApiModelProperty(value = "合同文本")
	private String textFile;
	/**
	 * 合同文本PDF
	 */
	@ApiModelProperty(value = "合同文本PDF")
	private String textFilePdf;
	/**
	 * 合同附件
	 */
	@ApiModelProperty(value = "合同附件")
	private String attachedFiles;
	/**
	 * 币种
	 */
	@ApiModelProperty(value = "币种")
	private String currencyCategory;
	/**
	 * 合同未税金额
	 */
	@ApiModelProperty(value = "合同未税金额")
	private BigDecimal contractAmount;
	/**
	 * 合同含税金额
	 */
	@ApiModelProperty(value = "合同含税金额")
	private BigDecimal contractTaxAmount;
	/**
	 * 税率
	 */
	@ApiModelProperty(value = "税率")
	private Double contactTaxRate;
	/**
	 * 是否有其它信息
	 */
	@ApiModelProperty(value = "其它信息")
	private String isNotOther;

	/**
	 * 其它信息
	 */
	@ApiModelProperty(value = "其它信息")
	private String otherInformation;

	/**
	 * 变更状态
	 */
	@ApiModelProperty(value = "变更状态")
	private String changeCategory;
	/**
	 * 变更合同ID
	 */
	@ApiModelProperty(value = "变更合同ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long changeContractId;
	/**
	 * 合同节点状态
	 */
	@ApiModelProperty(value = "合同节点状态")
	private String contractStatus;
	/**
	 * 合同审核状态
	 */
	@ApiModelProperty(value = "合同审核状态")
	private String submitStatus;
	/**
	 * 合同来源
	 */
	@ApiModelProperty(value = "合同来源")
	private String contractSoure;
	/**
	 * 合同文件导出状态，是否导出
	 */
	@ApiModelProperty(value = "合同文件导出状态，是否导出")
	private Integer fileExportCategory;
	/**
	 * 合同一级分类
	 */
	@ApiModelProperty(value = "合同一级分类")
	private String contractBigCategory;
	/**
	 * 合同二级分类
	 */
	@ApiModelProperty(value = "合同二级分类")
	private String contractSmallCategory;

	/**
	 * 合同范本id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "合同二级分类")
	private Long contractTemplateId;

	/**
	 * json数据串
	 */
	@ApiModelProperty(value = "json数据串")
	private String json;
	/**
	 * 模板表id
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	@ApiModelProperty(value = "模板表id")
	private Long contractListId;

	/**
	 * 模板表id
	 */
	@ApiModelProperty(value = "范本名称")
	private String contractListName;
	/**
	 * 关联相对方信息
	 */
	@ApiModelProperty(value = "关联相对方信息")
	@TableField(exist = false)
	private List<ContractCounterpartEntity> counterpart;

	/**
	 * 关联依据信息
	 */
	@ApiModelProperty(value = "关联依据信息")
	@TableField(exist = false)
	private List<ContractAccordingEntity> according;

	@ApiModelProperty(value = "保证金集合")
	@TableField(exist = false)
	private List<ContractBondEntity> contractBond;

	@ApiModelProperty(value = "履约计划集合")
	@TableField(exist = false)
	private List<ContractPerformanceEntity> performanceList;

	@ApiModelProperty(value = "履约计划收付款集合")
	@TableField(exist = false)
	private List<ContractPerformanceColPayEntity> performanceColPayList;

	@ApiModelProperty(value = "原物料一对多")
	@TableField(exist = false)
	private List<ContractRawMaterialsEntity> rawMaterialsList;

	/**
	 * 关联合同评估信息
	 */
	@ApiModelProperty(value = "评估信息")
	@TableField(exist = false)
	private ContractAssessmentEntity assessmentEntity;

	/**
	 * 关联合同归档信息
	 */
	@ApiModelProperty(value = "归档信息")
	@TableField(exist = false)
	private ContractArchiveEntity archiveEntity;
	/**
	 * 关联合同未归档信息
	 */
	@ApiModelProperty(value = "未归档信息")
	@TableField(exist = false)
	private List<ContractArchiveNotEntity> archiveNotEntity;

	/**
	 * 合同文件导出次数统计
	 */
	@ApiModelProperty(value = "合同文件导出次数统计")
	private Integer fileExportCount;

	/**
	 * 关联合同用印信息
	 */
	@ApiModelProperty(value = "用印信息")
	@TableField(exist = false)
	private ContractSealUsingInfoEntity sealInfoEntity;

	/**
	 * 关联合同签订信息
	 */
	@ApiModelProperty(value = "签订信息")
	@TableField(exist = false)
	private ContractSigningEntity signingEntity;

	/**
	 * 关联合同解除信息
	 */
	@ApiModelProperty(value = "解除信息")
	@TableField(exist = false)
	private ContractRelieveEntity relieveEntity;

	/**
	 * 翻译
	 */
	@TableField(exist = false)
	private String dictValue;

	/**
	 * 占比金额比率
	 */
	@TableField(exist = false)
	private String amountRatio;
	/**
	 * 相对方名称字符串
	 */
	@TableField(exist = false)
	private String counterpartName;
	/**
	 * 月份
	 */
	@TableField(exist = false)
	private String mouth;
	/**
	 * 一年内每月合同數量信息
	 */
	@TableField(exist = false)
	private List<MonthTypeSelect> monthTypeSelects;
	/**
	 * 統計查詢類型(公司)
	 */
	@ApiModelProperty(value="統計查詢類型(公司)")
	@TableField(exist = false)
	private String company;
	/**
	 * 分类合同数量
	 */
	@TableField(exist = false)
	private Integer count;
	/**
	 * 返回页面的付款金额数据
	 */
	@TableField(exist = false)
	private BigDecimal payAmountVoidData;
	/**
	 * 返回页面的金额数据
	 */
	@TableField(exist = false)
	private String amountVoidData;
	/**
	 * 返回页面的收款金额数据
	 */
	@TableField(exist = false)
	private BigDecimal receiveAmountVoidData;
	/**
	 * 返回页面的用戶名
	 */
	@TableField(exist = false)
	private String createUserName;
	/**
	 * 返回页面的部門
	 */
	@TableField(exist = false)
	private String createDeptName;

	/**
	 * 范本起草的pdf文件本地地址
	 */
	@TableField(exist = false)
	private String filePDF;
	/**
	 * 归档日期
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "归档日期")
	private Date fileTime;

	/**
	 * 甲方名称
	 */
	@JsonProperty(value = "aName")
	@ApiModelProperty(value = "甲方名称")
	private String aName;
	/**
	 * 甲方联系人
	 */
	@JsonProperty(value = "aPerson")
	@ApiModelProperty(value = "甲方联系人")
	private String aPerson;
	/**
	 * 甲方地址
	 */
	@JsonProperty(value = "aAddress")
	@ApiModelProperty(value = "甲方地址")
	private String aAddress;
	/**
	 * 甲方联系方式
	 */
	@JsonProperty(value = "aTelephone")
	@ApiModelProperty(value = "甲方联系方式")
	private String aTelephone;
	/**
	 * 甲方指定邮箱
	 */
	@JsonProperty(value = "aEmail")
	@ApiModelProperty(value = "甲方指定邮箱")
	private String aEmail;
	/**
	 * 甲方银行账号
	 */
	@JsonProperty(value = "aBankNumber")
	@ApiModelProperty(value = "甲方银行账号")
	private Long aBankNumber;
	/**
	 * 甲方开户行名称
	 */
	@JsonProperty(value = "aBankName")
	@ApiModelProperty(value = "甲方开户行名称")
	private String aBankName;
	/**
	 * 甲方账号名称
	 */
	@JsonProperty(value = "aAccountName")
	@ApiModelProperty(value = "甲方账号名称")
	private String aAccountName;
	/**
	 * 起订量及说明
	 */
	@ApiModelProperty(value = "起订量及说明")
	private String ywlMinimum;
	/**
	 * 甲方指定邮箱
	 */
	@ApiModelProperty(value = "甲方指定邮箱")
	private String ywlMailbox;
	/**
	 * 质量其他约定
	 */
	@ApiModelProperty(value = "质量其他约定")
	private String ywlAgreements;
	/**
	 * 包装其他约定
	 */
	@ApiModelProperty(value = "包装其他约定")
	private String ywlPacking;
	/**
	 * 交货方式
	 */
	@ApiModelProperty(value = "交货方式")
	private String ywlMode;
	/**
	 * 验收约定
	 */
	@ApiModelProperty(value = "验收约定")
	private String ywlAcceptance;
	/**
	 * 付款方式
	 */
	@ApiModelProperty(value = "付款方式")
	private String ywlPaymentMethod;
	/**
	 * 相对方开户行名称
	 */
	@ApiModelProperty(value = "相对方开户行名称")
	private String ywlNameBank;
	/**
	 * 相对方账号
	 */
	@ApiModelProperty(value = "相对方账号")
	private String ywlAccountNumber;
	/**
	 * 年度未按约定交货次数(可解约)
	 */
	@ApiModelProperty(value = "年度未按约定交货次数(可解约)")
	private String ywlDeliveryTimes;
	/**
	 * 其他违约行为违约金比例
	 */
	@ApiModelProperty(value = "其他违约行为违约金比例")
	private String ywlDamages;
	/**
	 * 违约责任其他约定
	 */
	@ApiModelProperty(value = "违约责任其他约定")
	private String ywlBreachOfContract;
	/**
	 * 解决争议其他约定
	 */
	@ApiModelProperty(value = "解决争议其他约定")
	private String ywlSettlement;
	/**
	 * 合同起草方（字典）
	 */
	@ApiModelProperty(value = "合同起草方（字典）")
	private String contractRoles;
}
