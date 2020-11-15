package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.ContractDestructionRequestVO;
import org.springblade.contract.vo.ContractDestructionResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.ContractDestructionEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 合同销毁 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-11-11 16:37:02
 */
@Component
public class ContractDestructionWrapper implements IEntityWrapper<ContractDestructionEntity, ContractDestructionRequestVO, ContractDestructionResponseVO> {

	public static ContractDestructionWrapper build() {
		return new ContractDestructionWrapper();
 	}

    @Override
	public ContractDestructionEntity createEntity() {
		return new ContractDestructionEntity();
	}

	@Override
	public ContractDestructionRequestVO createQV() {
		return new ContractDestructionRequestVO();
	}

	@Override
	public ContractDestructionResponseVO createPV() {
		return new ContractDestructionResponseVO();
	}

    @Override
    public void selectUserName(ContractDestructionResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
