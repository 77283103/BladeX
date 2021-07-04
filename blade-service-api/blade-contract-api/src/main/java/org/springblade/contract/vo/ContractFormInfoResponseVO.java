package org.springblade.contract.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springblade.contract.entity.*;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.resource.vo.FileVO;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
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
	 * 合同文本列表
	 */
	private List<FileVO> testFileVOListPDF;
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
	 * 相对方名称表达式
	 */
	private String counterpartNames;


	/**
	 * 依据集合
	 */
	private List<ContractAccordingEntity> according;

	/**
	 * 相对方集合
	 */
	private List<ContractCounterpartEntity> counterpart;
	/**
	 * 保证金库集合
	 */
	private List<ContractBondEntity> contractBond;
	/**
	 * 履约保证金集合
	 */
	private List<ContractBondPlanEntity> bondPlanEntityList;

	/**
	 * 履约计划集合
	 */
	private List<ContractPerformanceEntity> performanceList;

	/**
	 * 履约计划收付款集合
	 */
	private List<ContractPerformanceColPayEntity> performanceColPayList;
	/**
	 * 合同变更信息
	 */
	private ContractChangeEntity changeEntity;
	/**
	 * 合同变更补充附件
	 */
	private List<FileVO> suppleAgreementFileVOList;
	/**
	 * 签订文件扫描件列表
	 */
	private List<FileVO> signingTextFileVOList;
	/**
	 * 签订附件扫描件列表
	 */
	private List<FileVO> signingAttachedFileVOList;
	/**
	 * 合同模板
	 */
	private List<ContractTemplateEntity> templateEntity;
	/**
	 *  根据合同id查询变更原合同历史版本列表
	 */
	private List<ContractFormInfoEntity> formInfosEntityOldVOList;
	/**
	 *  根据合同id查询变更原合同新版本合同
	 */
	private List<ContractFormInfoEntity> formInfosEntityNewVOList;
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

	/**
	 * 相对方名称字符串
	 */
	private String counterpartName;

	/**
	 * 履约进度
	 */
	private String planSchedule;
	/**
	 * 金额sum
	 */
	private BigDecimal contractAmountSum;
	/**
	 * 签订数量
	 */
	private Integer signingCount;
	/**
	 * 統計查詢類型
	 */
	private String statisticsType;
	/**
	 * 合同负责人的部门
	 */
	private String personContractDept;
	/**
	 * 履约计划-接收\提供信息集合
	 */
	private List<PerServiceContentResponseVO> perServiceContentList;

	/**
	 * 履约信息-收付款
	 */
	private List<PerCollectPayResponseVO> perCollectPayList;

	/**
	 * 合同文本下载记录
	 */
	private List<ContractFileDownloadLogEntity> fileDownloadLogEntities;
	/**
	 * 多方起草关联的签章单位子公司信息
	 */
	private List<ContractSealEntity> contractSeal;


//	public String getCounterpartNames(){
//		StringBuffer stringBuffer = new StringBuffer("");
//		if(Func.isNotEmpty(this.counterpart)){
//			this.counterpart.forEach(contractCounterpartEntity -> {
//				if(stringBuffer.length() > 0){
//					stringBuffer.append(",");
//				}
//				stringBuffer.append(contractCounterpartEntity.getName());
//			});
//		}
//		return stringBuffer.toString();
//	}
}
