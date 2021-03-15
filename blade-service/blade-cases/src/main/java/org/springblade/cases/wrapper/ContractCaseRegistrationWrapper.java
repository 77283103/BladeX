package org.springblade.cases.wrapper;

import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.cases.vo.ContractCaseRegistrationResponseVO;
import org.springblade.cases.vo.ContractCaseRegistrationRequestVO;
import org.springblade.cases.entity.ContractCaseRegistrationEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 案件登记表 包装类,返回视图层所需的字段
 *
 * @author xhb
 * @date : 2020-10-30 10:04:58
 */
@Component
public class ContractCaseRegistrationWrapper implements IEntityWrapper<ContractCaseRegistrationEntity, ContractCaseRegistrationRequestVO, ContractCaseRegistrationResponseVO> {

	public static ContractCaseRegistrationWrapper build() {
		return new ContractCaseRegistrationWrapper();
 	}

    @Override
	public ContractCaseRegistrationEntity createEntity() {
		return new ContractCaseRegistrationEntity();
	}

	@Override
	public ContractCaseRegistrationRequestVO createQV() {
		return new ContractCaseRegistrationRequestVO();
	}

	@Override
	public ContractCaseRegistrationResponseVO createPV() {
		return new ContractCaseRegistrationResponseVO();
	}

    @Override
    public void selectUserName(ContractCaseRegistrationResponseVO responseVO) {
        /*responseVO.setCreateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setUpdateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));*/
    }
}
