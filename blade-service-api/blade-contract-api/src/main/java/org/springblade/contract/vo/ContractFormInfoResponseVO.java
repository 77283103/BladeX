package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springblade.contract.entity.*;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.resource.vo.FileVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 *  返回模型VO
 *
 * @author 史智伟
 * @date : 2020-09-23 18:04:39
 */
@Getter
@Setter
@ToString
@ApiModel(description = "返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractFormInfoResponseVO extends ContractFormInfoEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 依据集合
	 */
	private String[] sealNameList;

	/**
	 * 合同文本列表
	 */
	private List<FileVO> testFileVOList;

	/**
	 * 合同附件列表
	 */
	private List<FileVO> attachedFileVOList;

	/**
	 * 创建者真实姓名
	 */
	private String userRealName;

	/**
	 * 创建者所在组织
	 */
	private String userDepartName;


	/**
	 * 依据集合
	 */
	private List<ContractAccordingEntity> according;

	/**
	 * 保证金集合
	 */
	private List<ContractBondEntity> contractBond;

	/**
	 * 履约计划集合
	 */
	private List<ContractPerformanceEntity> performanceList;

	/**
	 * 履约计划收付款集合
	 */
	private List<ContractPerformanceColPayEntity> performanceColPayList;

	/**
	 * 签订文件扫描件列表
	 */
	private List<FileVO> signingTextFileVOList;
	/**
	 * 签订附件扫描件列表
	 */
	private List<FileVO> signingAttachedFileVOList;
	/**
	 * 评估相关附件
	 */
	private List<FileVO> assessmentAttachedVOList;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
	/**
	 * 归档月份
	 */
	private String archiveMonth;

	/**
	 * 合同期限起始时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date contractStartingTime;
	/**
	 * 合同期限截止时间
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date contractEndTime;
	/**
	 * 邮寄日期
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date signDate;
	/**
	 * 用印日期
	 */
	@DateTimeFormat(pattern = DateUtil.PATTERN_DATE)
	@JsonFormat(pattern = DateUtil.PATTERN_DATE)
	private Date signTime;
	/**
	 * 用印申请人
	 */
	private String printApplicant;
	/**
	 * 用印申请单位
	 */
	private String contractPrintInitDept;
	/**
	 * 用印公司
	 */
	private String printCompany;

}
