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
	private Integer extension;
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
	 * 合同金额
	 */
	@ApiModelProperty(value = "合同金额")
	private BigDecimal contractAmount;
	/**
	 * 变更状态
	 */
	@ApiModelProperty(value = "变更状态")
	private String changeCategory;
	/**
	 * 变更合同ID
	 */
	@ApiModelProperty(value = "变更合同ID")
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
	@ApiModelProperty(value = "合同二级分类")
	private Long contractTemplateId;

	/**
	 * json数据串
	 */
	@ApiModelProperty(value = "json数据串")
	private String json;
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
	 * 分类合同数量
	 */
	@TableField(exist = false)
	private Integer count;
	/**
	 * 返回页面的金额数据
	 */
	private String amountTYPE;
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
	@ApiModelProperty(value = "甲方名称")
	private String aName;
	/**
	 * 甲方联系人
	 */
	@ApiModelProperty(value = "甲方联系人")
	private String aPerson;
	/**
	 * 甲方地址
	 */
	@ApiModelProperty(value = "甲方地址")
	private String aAddress;
	/**
	 * 甲方联系方式
	 */
	@ApiModelProperty(value = "甲方联系方式")
	private String aTelephone;
	/**
	 * 甲方指定邮箱
	 */
	@ApiModelProperty(value = "甲方指定邮箱")
	private String aEmail;
	/**
	 * 甲方银行账号
	 */
	@ApiModelProperty(value = "甲方银行账号")
	private Long aBankNumber;
	/**
	 * 甲方开户行名称
	 */
	@ApiModelProperty(value = "甲方开户行名称")
	private String aBankName;
	/**
	 * 甲方账号名称
	 */
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
	 * 我方住所
	 */
	@ApiModelProperty(value = "我方住所")
	private String cxpResidence;
	/**
	 * 相对方住所
	 */
	@ApiModelProperty(value = "相对方住所")
	private String cxpResidenceOf;
	/**
	 * 收到订单后交货期
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "收到订单后交货期")
	private Date cxpDeliveryDate;
	/**
	 * 甲方指定邮箱
	 */
	@ApiModelProperty(value = "甲方指定邮箱")
	private String cxpEmailAddress;
	/**
	 * 付款方式
	 */
	@ApiModelProperty(value = "付款方式")
	private String cxpPaymentMethod;
	/**
	 * 相对方开户名称
	 */
	@ApiModelProperty(value = "相对方开户名称")
	private String cxpNameBank;
	/**
	 * 相对方账户名称
	 */
	@ApiModelProperty(value = "相对方账户名称")
	private String cxpTitleOfAccount;
	/**
	 * 相对方账号
	 */
	@ApiModelProperty(value = "相对方账号")
	private Integer cxpAccountNumber;
	/**
	 * 其他约定
	 */
	@ApiModelProperty(value = "其他约定")
	private String cxpOtherAgreements;
	/**
	 * 付款方式
	 */
	@ApiModelProperty(value = "付款方式")
	private String ctjxsPaymentMethod;
	/**
	 * 甲方收款账户名称
	 */
	@ApiModelProperty(value = "甲方收款账户名称")
	private String ctjxsTitleOfAccount;
	/**
	 * 甲方收款账户帐号
	 */
	@ApiModelProperty(value = "甲方收款账户帐号")
	private Integer ctjxsAccountNumber;
	/**
	 * 甲方收款开户行
	 */
	@ApiModelProperty(value = "甲方收款开户行")
	private String ctjxsNameBank;
	/**
	 * 乙方付款帐户名称
	 */
	@ApiModelProperty(value = "乙方付款帐户名称")
	private String ctjxsYifangOfAccount;
	/**
	 * 乙方付款帐户帐号
	 */
	@ApiModelProperty(value = "乙方付款帐户帐号")
	private Integer ctjxsYifangAccountNumber;
	/**
	 * 乙方付款开户行
	 */
	@ApiModelProperty(value = "乙方付款开户行")
	private String ctjxsYfnameBank;
	/**
	 * 发票类型
	 */
	@ApiModelProperty(value = "发票类型")
	private String ctjxsInvoiceType;
	/**
	 * 乙方发票名称
	 */
	@ApiModelProperty(value = "乙方发票名称")
	private String ctjxsInvoiceName;
	/**
	 * 乙方发票纳税人识别号
	 */
	@ApiModelProperty(value = "乙方发票纳税人识别号")
	private Integer ctjxsIdentificationNumber;
	/**
	 * 乙方发票地址电话
	 */
	@ApiModelProperty(value = "乙方发票地址电话")
	private Integer ctjxsYfphton;
	/**
	 * 乙方发票开户行及账号
	 */
	@ApiModelProperty(value = "乙方发票开户行及账号")
	private Integer ctjxsInvoiceBankNumber;
	/**
	 * 发票传递方式1
	 */
	@ApiModelProperty(value = "发票传递方式1")
	private String ctjxsTransmissionMode;
	/**
	 * 发票传递方式2
	 */
	@ApiModelProperty(value = "发票传递方式2")
	private String ctjxsRansmissionMode2;
	/**
	 * 未及时办理终止解除的违约金比例
	 */
	@ApiModelProperty(value = "未及时办理终止解除的违约金比例")
	private Double ctjxsLiquidatedDamages;
	/**
	 * 划款执行银行
	 */
	@ApiModelProperty(value = "划款执行银行")
	private String ctjxsTransferExecutionBank;
	/**
	 * 季度/年度折扣
	 * 达成率≥80%＜100%，折扣率
	 */
	@ApiModelProperty(value = "季度/年度折扣 达成率≥80 %＜100 %，折扣率")
	private Double ctjxsDiscountRate;
	/**
	 * 季度/年度折扣
	 * 达成率≥100%，折扣率
	 */
	@ApiModelProperty(value = "季度/年度折扣 达成率≥100 %，折扣率")
	private Double ctjxsDiscountRate2;
	/**
	 * 预收款激励
	 * 预收款率≥35%，折扣率
	 */
	@ApiModelProperty(value = "预收款激励 预收款率≥35 %，折扣率")
	private Double ctjxsDiscountRate3;
	/**
	 * 库存及不良品帐实折扣
	 * 差异＜500箱且库存率≤10%，则折扣
	 */
	@ApiModelProperty(value = "库存及不良品帐实折扣 差异＜500箱且库存率≤10 %，则折扣")
	private Double ctjxsDiscountRate4;
	/**
	 * 差异＜20箱，则折扣
	 */
	@ApiModelProperty(value = "差异＜20箱，则折扣")
	private Double ctjxsDiscountRate5;
	/**
	 * 计算周期
	 */
	@ApiModelProperty(value = "计算周期")
	private String ctjxsCalculationPeriod;
	/**
	 * 其他
	 * 考核标准
	 */
	@ApiModelProperty(value = "其他考核标准")
	private String ctjxsAssessmentCriteria;
	/**
	 * 其他激励办法
	 */
	@ApiModelProperty(value = "其他激励办法")
	private String ctjxsIncentives;
	/**
	 * 乙方配备专业业务人数
	 */
	@ApiModelProperty(value = "乙方配备专业业务人数")
	private Integer ctjxsNumberOfPeople;
	/**
	 * 乙方配备机动车台数
	 */
	@ApiModelProperty(value = "乙方配备机动车台数")
	private Integer ctjxsNumberOfSets;
	/**
	 * 乙方配备非机动辆数
	 */
	@ApiModelProperty(value = "乙方配备非机动辆数")
	private Integer ctjxsNumberOfVehicles;
	/**
	 * 乙方专属仓库平方数
	 */
	@ApiModelProperty(value = "乙方专属仓库平方数")
	private Integer ctjxsNumbe2;
	/**
	 * 达成季销售目标优惠政策
	 */
	@ApiModelProperty(value = "达成季销售目标优惠政策")
	private String ctjxsFavouredPolicy;
	/**
	 * 达成年销售目标优惠政策
	 */
	@ApiModelProperty(value = "达成年销售目标优惠政策")
	private String ctjxsFavouredPolicy2;
	/**
	 * 基金折扣系数
	 */
	@ApiModelProperty(value = "基金折扣系数")
	private Double ctjxsCoefficient;
	/**
	 * 改善通知书甲方联络人
	 */
	@ApiModelProperty(value = "改善通知书甲方联络人")
	private String ctjxsContactPerson;
	/**
	 * 改善通知书甲方联络电话
	 */
	@ApiModelProperty(value = "改善通知书甲方联络电话")
	private Integer ctjxsPhton2;
	/**
	 * 乙方销售产品区域
	 */
	@ApiModelProperty(value = "乙方销售产品区域")
	private String spgxProductArea;
	/**
	 * 通路
	 */
	@ApiModelProperty(value = "通路")
	private String spgxAccess;
	/**
	 * 客户
	 */
	@ApiModelProperty(value = "客户")
	private String spgxCustomer;
	/**
	 * 滞销产品置换比例
	 */
	@ApiModelProperty(value = "滞销产品置换比例")
	private Double spgxReplacementRatio;
	/**
	 * 向甲方提供当月销售流向明细及库存资料的时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "向甲方提供当月销售流向明细及库存资料的时间")
	private Date spgxInformation;
	/**
	 * 订单形式
	 */
	@ApiModelProperty(value = "订单形式")
	private String spgxOrderForm;
	/**
	 * 交货地点
	 */
	@ApiModelProperty(value = "交货地点")
	private String spgxPlaceOfDelivery;
	/**
	 * 交货方式
	 */
	@ApiModelProperty(value = "交货方式")
	private String spgxDelivery;
	/**
	 * 乙方指定收货人（签样）
	 */
	@ApiModelProperty(value = "乙方指定收货人（签样）")
	private String spgxConsignee;
	/**
	 * 联系方式
	 */
	@ApiModelProperty(value = "联系方式")
	private Integer spgxInformation1;
	/**
	 * 货款结算周期
	 */
	@ApiModelProperty(value = "货款结算周期")
	private Integer spgxCycle;
	/**
	 * 已方发票类型
	 */
	@ApiModelProperty(value = "已方发票类型  ")
	private String spgxInvoiceType;
	/**
	 * 乙方发票名称
	 */
	@ApiModelProperty(value = "乙方发票名称")
	private String spgxInvoiceName;
	/**
	 * 乙方发票纳税人识别号
	 */
	@ApiModelProperty(value = "乙方发票纳税人识别号")
	private String spgxIdentificationNumber;
	/**
	 * 乙方发票地址、电话
	 */
	@ApiModelProperty(value = "乙方发票地址、电话")
	private String spgxAddressAndTelephone;
	/**
	 * 发票传递方式
	 */
	@ApiModelProperty(value = "发票传递方式")
	private String spgxTransmissionMode;
	/**
	 * 乙方发票收取联络人
	 */
	@ApiModelProperty(value = "乙方发票收取联络人")
	private String spgxPerson;
	/**
	 * 乙方发票收取联络人电话号码
	 */
	@ApiModelProperty(value = "乙方发票收取联络人电话号码")
	private Integer spgxPhton;
	/**
	 * 乙方发票发票收取地址
	 */
	@ApiModelProperty(value = "乙方发票发票收取地址")
	private String spgxAddress;
	/**
	 * 甲方帐户帐号
	 */
	@ApiModelProperty(value = "甲方帐户帐号")
	private String spgxAccount;
	/**
	 * 甲方开户银行
	 */
	@ApiModelProperty(value = "甲方开户银行")
	private String spgxBank;
	/**
	 * 甲方开户银行名称
	 */
	@ApiModelProperty(value = "甲方开户银行名称")
	private String spgxBankName;
	/**
	 * 违约责任
	 */
	@ApiModelProperty(value = "违约责任")
	private String spgxBreachOfContract;
	/**
	 * 年度
	 */
	@ApiModelProperty(value = "年度")
	private String yjdsYears;
	/**
	 * 物流线路
	 */
	@ApiModelProperty(value = "物流线路")
	private String yjdsLine;
	/**
	 * 出货传票交接时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "出货传票交接时间")
	private Date yjdsTimes;
	/**
	 * 运输其他要求
	 */
	@ApiModelProperty(value = "运输其他要求")
	private String yjdsTransport;
	/**
	 * 每日配送运力
	 */
	@ApiModelProperty(value = "每日配送运力")
	private String yjdsDistribution;
	/**
	 * 运输价格
	 */
	@ApiModelProperty(value = "运输价格")
	private BigDecimal yjdsTransportationPrice;
	/**
	 * 运输价格
	 */
	@ApiModelProperty(value = "运输价格")
	private BigDecimal yjdsRansportationPrices;
	/**
	 * 签单核对
	 */
	@ApiModelProperty(value = "签单核对")
	private String yjdsSignAndCheck;
	/**
	 * 支付条件
	 */
	@ApiModelProperty(value = "支付条件")
	private String yjdsPaymentTerms;
	/**
	 * 付款方式
	 */
	@ApiModelProperty(value = "付款方式")
	private String yjdsPaymentMethod;
	/**
	 * 当日到货时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "当日到货时间")
	private Date yjdsDayTimes;
	/**
	 * 年度
	 */
	@ApiModelProperty(value = "年度")
	private String edpsYear;
	/**
	 * 物流线路
	 */
	@ApiModelProperty(value = "物流线路")
	private String edpsLogisticsLines;
	/**
	 * 代收货款缴交时间
	 * 代收货款缴交时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "代收货款缴交时间代收货款缴交时间")
	private Date edpsPayTime;
	/**
	 * 货物出仓
	 */
	@ApiModelProperty(value = "货物出仓")
	private String edpsWarehouse;
	/**
	 * 车辆配置
	 */
	@ApiModelProperty(value = "车辆配置")
	private String edpsVehicleDisposition;
	/**
	 * 车辆配置其他要求
	 */
	@ApiModelProperty(value = "车辆配置其他要求")
	private String edpsRequirements;
	/**
	 * 甲方权利（优先安排客户)
	 */
	@ApiModelProperty(value = "甲方权利（优先安排客户)")
	private String edpsPartyRights;
	/**
	 * 乙方义务加单需求
	 */
	@ApiModelProperty(value = "乙方义务加单需求")
	private String edpsPartyObligations;
	/**
	 * 每日配送运力
	 */
	@ApiModelProperty(value = "每日配送运力")
	private String edpsDailyDistributionCapacity;
	/**
	 * 核对出货传票信息
	 */
	@ApiModelProperty(value = "核对出货传票信息")
	private String edpsTicketInformation;
	/**
	 * 退货入仓
	 */
	@ApiModelProperty(value = "退货入仓")
	private String edpsReturnsALevite;
	/**
	 * 费用结算时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "费用结算时间")
	private Date edpsFeeSettlementTime;
	/**
	 * 甲方违约责任
	 */
	@ApiModelProperty(value = "甲方违约责任")
	private String edpsDuty;
	/**
	 * 违约行为次数
	 */
	@ApiModelProperty(value = "违约行为次数")
	private String edpsNumberOfBreaches;
	/**
	 * 违约行为违约金比例
	 */
	@ApiModelProperty(value = "违约行为违约金比例")
	private Double edpsRatio;
	/**
	 * 年度未按品质配送(可解约)
	 */
	@ApiModelProperty(value = "年度未按品质配送(可解约)")
	private String edpsDelivery;
	/**
	 * 合同执行期违约金
	 */
	@ApiModelProperty(value = "合同执行期违约金")
	private String edpsExecution;
	/**
	 * 特别约定
	 */
	@ApiModelProperty(value = "特别约定")
	private String edpsSpecialAgreement;
	/**
	 * 仓储地点
	 */
	@ApiModelProperty(value = "仓储地点")
	private String edpshccStorageLocation;
	/**
	 * 仓储面积
	 */
	@ApiModelProperty(value = "仓储面积")
	private String edpshccArea;
	/**
	 * 仓储其他条件
	 */
	@ApiModelProperty(value = "仓储其他条件")
	private String edpshccElse;
	/**
	 * 库存数量
	 */
	@ApiModelProperty(value = "库存数量")
	private Double edpshccQuantityInStock;
	/**
	 * 出货增值服务
	 */
	@ApiModelProperty(value = "出货增值服务")
	private String edpshccServices;
	/**
	 * 收货标准
	 */
	@ApiModelProperty(value = "收货标准 ")
	private String edpshccStandard;
	/**
	 * 车辆其他要求
	 */
	@ApiModelProperty(value = "车辆其他要求")
	private String edpshccRequirements;
	/**
	 * 每日配送运力
	 */
	@ApiModelProperty(value = "每日配送运力")
	private String edpshccCapacity;
	/**
	 * 出货区域
	 */
	@ApiModelProperty(value = "出货区域")
	private String edpshccDeliveryArea;
	/**
	 * 出货违约要求
	 */
	@ApiModelProperty(value = "出货违约要求")
	private String edpshccShipmentBreach;
	/**
	 * 出货工作时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "出货工作时间")
	private Date edpshccShippingWorkingTime;
	/**
	 * 票据签收日
	 */
	@ApiModelProperty(value = "票据签收日")
	private String edpshccBill;
	/**
	 * 信息反馈时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "信息反馈时间")
	private Date edpshccFeedbackTime;
	/**
	 * 仓储费用
	 */
	@ApiModelProperty(value = "仓储费用")
	private BigDecimal edpshccWarehouseCharges;
	/**
	 * 特殊配送时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "特殊配送时间")
	private Date edpshccSpecialDeliveryTime;
	/**
	 * 出货工作时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "出货工作时间")
	private Date edpshccWorkingTime;
	/**
	 * 签单核对
	 */
	@ApiModelProperty(value = "签单核对")
	private String edpshccSingleCore;
	/**
	 * 支付条件
	 */
	@ApiModelProperty(value = "支付条件")
	private String edpshccTermsOfPayment;
	/**
	 * 付款方式
	 */
	@ApiModelProperty(value = "付款方式")
	private String edpshccPaymentMethod;
	/**
	 * 保证金
	 */
	@ApiModelProperty(value = "保证金")
	private BigDecimal edpshccEarnestMoney;
	/**
	 * 违约行为次数
	 */
	@ApiModelProperty(value = "违约行为次数")
	private String edpshccNumberOfBreaches;
	/**
	 * 违约行为违约金比例
	 */
	@ApiModelProperty(value = "违约行为违约金比例")
	private String edpshccBreach;
	/**
	 * 特别约定办公条件
	 */
	@ApiModelProperty(value = "特别约定办公条件")
	private String edpshccConditions;
	/**
	 * 奖励时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	@ApiModelProperty(value = "奖励时间")
	private Date jxsgx500yxBonusTime;
	/**
	 * 奖励期限
	 */
	@ApiModelProperty(value = "奖励期限")
	private String jxsgx500yxRewardDeadline;
	/**
	 * 冰箱陈列我司产品100%纯度台数
	 */
	@ApiModelProperty(value = "冰箱陈列我司产品100%纯度台数")
	private Double jxsgx500yxOneHundred;
	/**
	 * 冰箱陈列我司产品80%纯度台数
	 */
	@ApiModelProperty(value = "冰箱陈列我司产品80%纯度台数")
	private Double jxsgx500yxEighty;
	/**
	 * 冰箱陈列我司产品60%纯度台数
	 */
	@ApiModelProperty(value = "冰箱陈列我司产品60%纯度台数")
	private Double jxsgx500yxSixty;
	/**
	 * 奖励金大写金额
	 */
	@ApiModelProperty(value = "奖励金大写金额")
	private String jxsgx500yxIncentive;
	/**
	 * 其他约定
	 */
	@ApiModelProperty(value = "其他约定")
	private String jxsgx500yxOtherPromises;
	/**
	 * 奖励期限
	 */
	@ApiModelProperty(value = "奖励期限")
	private String jxsgx500ysRewardDeadline;

	/**
	 * 资金成本奖励
	 */
	@ApiModelProperty(value = "资金成本奖励")
	private String jxsgx500ysCapitalCostIncentive;
	/**
	 * 购买成本奖励年度比例（第1年）
	 */
	@ApiModelProperty(value = "购买成本奖励年度比例（第1年）")
	private String jxsgx500ysPurOneYear;
	/**
	 * 购买成本奖励年度比例（第2年）
	 */
	@ApiModelProperty(value = "购买成本奖励年度比例（第2年）")
	private String jxsgx500ysPurTwoYear;
	/**
	 * 购买成本奖励年度比例（第3年）
	 */
	@ApiModelProperty(value = "购买成本奖励年度比例（第3年）")
	private String jxsgx500ysPurThreeYear;
	/**
	 * 购买成本奖励年度比例（第4年）
	 */
	@ApiModelProperty(value = "购买成本奖励年度比例（第4年）")
	private String jxsgx500ysPurFourYear;
	/**
	 * 购买成本奖励年度比例（第5年） 合同的主表我还要加一些字段  之后我会提交一些代码   完事之后我告诉你
	 */
	@ApiModelProperty(value = "购买成本奖励年度比例（第5年）")
	private String jxsgx500ysPurFiveYear;
	/**
	 * 奖励金大写金额
	 */
	@ApiModelProperty(value = "奖励金大写金额")
	private String jxsgx500ysIncentive;
	/**
	 * 其他约定
	 */
	@ApiModelProperty(value = "其他约定")
	private String jxsgx500ysOtherPromises;

}
