package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractBondPlanEntity;
import org.springblade.contract.vo.ContractBondPlanRequestVO;
import org.springblade.contract.vo.ContractBondPlanResponseVO;
import org.springblade.contract.vo.ContractBondRequestVO;
import org.springblade.contract.vo.ContractBondResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 保证金 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-11-04 18:28:11
 */
@Component
public class ContractBondPlanWrapper implements IEntityWrapper<ContractBondPlanEntity, ContractBondPlanRequestVO, ContractBondPlanResponseVO> {

	public static ContractBondPlanWrapper build() {
		return new ContractBondPlanWrapper();
 	}

    @Override
	public ContractBondPlanEntity createEntity() {
		return new ContractBondPlanEntity();
	}

	@Override
	public ContractBondPlanRequestVO createQV() {
		return new ContractBondPlanRequestVO();
	}

	@Override
	public ContractBondPlanResponseVO createPV() {
		return new ContractBondPlanResponseVO();
	}

    @Override
    public void selectUserName(ContractBondPlanResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
