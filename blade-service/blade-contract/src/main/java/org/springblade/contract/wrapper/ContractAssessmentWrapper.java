package org.springblade.contract.wrapper;

import java.util.Optional;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.vo.ContractAssessmentResponseVO;
import org.springblade.contract.vo.ContractAssessmentRequestVO;
import org.springblade.contract.entity.ContractAssessmentEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 合同评估表 包装类,返回视图层所需的字段
 *
 * @author 合同评估表
 * @date : 2020-11-05 09:37:40
 */
@Component
public class ContractAssessmentWrapper implements IEntityWrapper<ContractAssessmentEntity, ContractAssessmentRequestVO, ContractAssessmentResponseVO> {

	public static ContractAssessmentWrapper build() {
		return new ContractAssessmentWrapper();
 	}

    @Override
	public ContractAssessmentEntity createEntity() {
		return new ContractAssessmentEntity();
	}

	@Override
	public ContractAssessmentRequestVO createQV() {
		return new ContractAssessmentRequestVO();
	}

	@Override
	public ContractAssessmentResponseVO createPV() {
		return new ContractAssessmentResponseVO();
	}

    @Override
    public void selectUserName(ContractAssessmentResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
