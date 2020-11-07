package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.ContractBondRequestVO;
import org.springblade.contract.vo.ContractBondResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.user.entity.User;
import org.springblade.contract.entity.ContractBondEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 保证金 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-11-04 18:28:11
 */
@Component
public class ContractBondWrapper implements IEntityWrapper<ContractBondEntity, ContractBondRequestVO, ContractBondResponseVO> {

	public static ContractBondWrapper build() {
		return new ContractBondWrapper();
 	}

    @Override
	public ContractBondEntity createEntity() {
		return new ContractBondEntity();
	}

	@Override
	public ContractBondRequestVO createQV() {
		return new ContractBondRequestVO();
	}

	@Override
	public ContractBondResponseVO createPV() {
		return new ContractBondResponseVO();
	}

    @Override
    public void selectUserName(ContractBondResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
