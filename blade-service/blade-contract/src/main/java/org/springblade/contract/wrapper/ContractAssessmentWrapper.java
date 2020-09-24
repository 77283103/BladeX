package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractAssessmentEntity;
import org.springblade.contract.vo.ContractAssessmentResponseVO;

/**
 * 合同评估表 包装类,返回视图层所需的字段
 *
 * @author liyj
 * @date : 2020-09-24 10:41:34
 */
public class ContractAssessmentWrapper extends BaseEntityWrapper<ContractAssessmentEntity, ContractAssessmentResponseVO>  {

	public static ContractAssessmentWrapper build() {
		return new ContractAssessmentWrapper();
 	}

	@Override
	public ContractAssessmentResponseVO entityVO(ContractAssessmentEntity assessment) {
		ContractAssessmentResponseVO assessmentResponseVO = BeanUtil.copy(assessment, ContractAssessmentResponseVO.class);

		//User createUser = UserCache.getUser(assessment.getCreateUser());
		//User updateUser = UserCache.getUser(assessment.getUpdateUser());
		//assessmentResponseVO.setCreateUserName(createUser.getName());
		//assessmentResponseVO.setUpdateUserName(updateUser.getName());

		return assessmentResponseVO;
	}

}
