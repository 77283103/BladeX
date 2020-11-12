package org.springblade.contract.wrapper;

import java.util.Optional;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.vo.ContractSealUsingInfoResponseVO;
import org.springblade.contract.vo.ContractSealUsingInfoRequestVO;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 合同用印 包装类,返回视图层所需的字段
 *
 * @author 合同用印
 * @date : 2020-11-05 09:29:27
 */
@Component
public class ContractSealUsingInfoWrapper implements IEntityWrapper<ContractSealUsingInfoEntity, ContractSealUsingInfoRequestVO, ContractSealUsingInfoResponseVO> {

	public static ContractSealUsingInfoWrapper build() {
		return new ContractSealUsingInfoWrapper();
 	}

    @Override
	public ContractSealUsingInfoEntity createEntity() {
		return new ContractSealUsingInfoEntity();
	}

	@Override
	public ContractSealUsingInfoRequestVO createQV() {
		return new ContractSealUsingInfoRequestVO();
	}

	@Override
	public ContractSealUsingInfoResponseVO createPV() {
		return new ContractSealUsingInfoResponseVO();
	}

    @Override
    public void selectUserName(ContractSealUsingInfoResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
