package org.springblade.contract.wrapper;

import org.springblade.contract.entity.AssessmentEntity;
import org.springblade.contract.vo.AssessmentResponseVO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;

/**
 * 合同评估表 包装类,返回视图层所需的字段
 *
 * @author liyj
 * @date : 2020-09-23 15:50:00
 */
public class AssessmentWrapper extends BaseEntityWrapper<AssessmentEntity, AssessmentResponseVO>  {

	public static AssessmentWrapper build() {
		return new AssessmentWrapper();
 	}

	@Override
	public AssessmentResponseVO entityVO(AssessmentEntity assessment) {
		AssessmentResponseVO assessmentResponseVO = BeanUtil.copy(assessment, AssessmentResponseVO.class);

		//User createUser = UserCache.getUser(assessment.getCreateUser());
		//User updateUser = UserCache.getUser(assessment.getUpdateUser());
		//assessmentResponseVO.setCreateUserName(createUser.getName());
		//assessmentResponseVO.setUpdateUserName(updateUser.getName());

		return assessmentResponseVO;
	}

}
