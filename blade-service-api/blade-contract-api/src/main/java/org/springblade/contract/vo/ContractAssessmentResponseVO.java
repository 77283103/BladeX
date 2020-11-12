package org.springblade.contract.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.springblade.contract.entity.ContractAssessmentEntity;
import io.swagger.annotations.ApiModel;
import org.springblade.resource.vo.FileVO;

import java.util.Date;
import java.util.List;

/**
 * 合同评估表 返回模型VO
 *
 * @author 合同评估表
 * @date : 2020-11-05 09:37:40
 */
@Getter
@Setter
@ToString
@ApiModel(description = "合同评估表返回对象")
@EqualsAndHashCode(callSuper = true)
public class ContractAssessmentResponseVO extends ContractAssessmentEntity {

	private static final long serialVersionUID = 1L;

	private String createUserName;

	private String createDeptName;

	private String updateUserName;
	/**
	 * 评估相关附件
	 */
	private List<FileVO> assessmentAttachedVOList;
}
