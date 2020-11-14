package org.springblade.contract.wrapper;

import java.util.Optional;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.vo.ContractSigningResponseVO;
import org.springblade.contract.vo.ContractSigningRequestVO;
import org.springblade.contract.entity.ContractSigningEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 合同签订表 包装类,返回视图层所需的字段
 *
 * @author 合同签订
 * @date : 2020-11-05 09:34:33
 */
@Component
public class ContractSigningWrapper implements IEntityWrapper<ContractSigningEntity, ContractSigningRequestVO, ContractSigningResponseVO> {

	public static ContractSigningWrapper build() {
		return new ContractSigningWrapper();
 	}

    @Override
	public ContractSigningEntity createEntity() {
		return new ContractSigningEntity();
	}

	@Override
	public ContractSigningRequestVO createQV() {
		return new ContractSigningRequestVO();
	}

	@Override
	public ContractSigningResponseVO createPV() {
		return new ContractSigningResponseVO();
	}

    @Override
    public void selectUserName(ContractSigningResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
