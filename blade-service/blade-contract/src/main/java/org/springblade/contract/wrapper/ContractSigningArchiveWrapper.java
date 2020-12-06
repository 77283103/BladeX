package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractSigningArchiveEntity;
import org.springblade.contract.vo.ContractSigningArchiveRequestVO;
import org.springblade.contract.vo.ContractSigningArchiveResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 合同签订表 包装类,返回视图层所需的字段
 *
 * @author 合同签订
 * @date : 2020-11-05 09:34:33
 */
@Component
public class ContractSigningArchiveWrapper implements IEntityWrapper<ContractSigningArchiveEntity, ContractSigningArchiveRequestVO, ContractSigningArchiveResponseVO> {

	public static ContractSigningArchiveWrapper build() {
		return new ContractSigningArchiveWrapper();
 	}

    @Override
	public ContractSigningArchiveEntity createEntity() {
		return new ContractSigningArchiveEntity();
	}

	@Override
	public ContractSigningArchiveRequestVO createQV() {
		return new ContractSigningArchiveRequestVO();
	}

	@Override
	public ContractSigningArchiveResponseVO createPV() {
		return new ContractSigningArchiveResponseVO();
	}

    @Override
    public void selectUserName(ContractSigningArchiveResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
