package org.springblade.cases.wrapper;

import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.cases.vo.ContractCaseClosedResponseVO;
import org.springblade.cases.vo.ContractCaseClosedRequestVO;
import org.springblade.cases.entity.ContractCaseClosedEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 案件结案 包装类,返回视图层所需的字段
 *
 * @author xhb
 * @date : 2020-10-30 10:03:21
 */
@Component
public class ContractCaseClosedWrapper implements IEntityWrapper<ContractCaseClosedEntity, ContractCaseClosedRequestVO, ContractCaseClosedResponseVO> {

	public static ContractCaseClosedWrapper build() {
		return new ContractCaseClosedWrapper();
 	}

    @Override
	public ContractCaseClosedEntity createEntity() {
		return new ContractCaseClosedEntity();
	}

	@Override
	public ContractCaseClosedRequestVO createQV() {
		return new ContractCaseClosedRequestVO();
	}

	@Override
	public ContractCaseClosedResponseVO createPV() {
		return new ContractCaseClosedResponseVO();
	}

    @Override
    public void selectUserName(ContractCaseClosedResponseVO responseVO) {
        /*responseVO.setCreateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setUpdateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));*/
    }
}
