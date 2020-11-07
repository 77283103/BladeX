package org.springblade.contract.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
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
 *  实体类
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
	 * 收付款期限
	 */
	@ApiModelProperty(value ="收付款期限")
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
	private Double contractAmount;
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
	 * 关联相对方信息
	 */
	@ApiModelProperty(value="关联相对方信息")
	@TableField(exist = false)
	private List<ContractCounterpartEntity> counterpartList;

	/**
	 * 关联依据信息
	 */
	@ApiModelProperty(value="关联依据信息")
	@TableField(exist = false)
	private List<ContractAccordingEntity> accordingList;

	/**
	 * 关联履约信息
	 */
	@ApiModelProperty(value="关联履约信息")
	@TableField(exist = false)
	private List<ContractPerformanceEntity> performanceList;


	/**
	 * 关联合同评估信息
	 */
	@ApiModelProperty(value="评估信息")
	@TableField(exist = false)
	private ContractAssessmentEntity assessmentEntity;

	/**
	 * 关联合同归档信息
	 */
	@ApiModelProperty(value="归档信息")
	@TableField(exist = false)
	private ContractArchiveEntity archiveEntity;

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
}
