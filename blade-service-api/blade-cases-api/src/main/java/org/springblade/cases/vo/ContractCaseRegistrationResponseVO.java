package org.springblade.cases.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.cases.entity.ContractCaseClosedEntity;
import org.springblade.cases.entity.ContractCaseHandlingEntity;
import org.springblade.cases.entity.ContractCaseRegistrationEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.tool.api.R;
import org.springblade.resource.vo.FileVO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 案件登记表 返回模型VO
 *
 * @author xhb
 * @date : 2020-10-30 10:05:06
 */
@Getter
@Setter
@ToString
@ApiModel(description = "案件登记表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractCaseRegistrationResponseVO extends ContractCaseRegistrationEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
	/**
	 *  合同信息
	 */
	private ContractFormInfoResponseVO infoEntity;

	/**
	 * 合同状态
	 */
	private String contractStatus;
	/**
	 * 合同币种
	 */
	private String  currencyCategory;
	/**
	 * 案件附件
	 */
	private List<FileVO> attachedFileVOList;
	/**
	 * 案件处理附件
	 */
	private List<FileVO> handleAttachedFileVOList;
	/**
	 * 案件处理
	 */
	private List<FileVO> handleReplyVOList;
	/**
	 * 案件结案文书
	 */
	private List<FileVO> closeCaseDocumentVOList;
}
