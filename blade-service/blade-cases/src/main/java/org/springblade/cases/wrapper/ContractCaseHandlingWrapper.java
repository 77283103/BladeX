package org.springblade.cases.wrapper;

import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.cases.vo.ContractCaseHandlingResponseVO;
import org.springblade.cases.vo.ContractCaseHandlingRequestVO;
import org.springblade.cases.entity.ContractCaseHandlingEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 案件处理 包装类,返回视图层所需的字段
 *
 * @author xhb
 * @date : 2020-10-30 10:04:22
 */
@Component
public class ContractCaseHandlingWrapper implements IEntityWrapper<ContractCaseHandlingEntity, ContractCaseHandlingRequestVO, ContractCaseHandlingResponseVO> {

	public static ContractCaseHandlingWrapper build() {
		return new ContractCaseHandlingWrapper();
 	}

    @Override
	public ContractCaseHandlingEntity createEntity() {
		return new ContractCaseHandlingEntity();
	}

	@Override
	public ContractCaseHandlingRequestVO createQV() {
		return new ContractCaseHandlingRequestVO();
	}

	@Override
	public ContractCaseHandlingResponseVO createPV() {
		return new ContractCaseHandlingResponseVO();
	}

    @Override
    public void selectUserName(ContractCaseHandlingResponseVO responseVO) {
        responseVO.setCreateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setUpdateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
