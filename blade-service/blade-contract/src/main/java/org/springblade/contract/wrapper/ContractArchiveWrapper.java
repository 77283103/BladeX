package org.springblade.contract.wrapper;

import java.util.Optional;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.vo.ContractArchiveResponseVO;
import org.springblade.contract.vo.ContractArchiveRequestVO;
import org.springblade.contract.entity.ContractArchiveEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 合同归档 包装类,返回视图层所需的字段
 *
 * @author 合同归档
 * @date : 2020-11-05 09:41:39
 */
@Component
public class ContractArchiveWrapper implements IEntityWrapper<ContractArchiveEntity, ContractArchiveRequestVO, ContractArchiveResponseVO> {

	public static ContractArchiveWrapper build() {
		return new ContractArchiveWrapper();
 	}

    @Override
	public ContractArchiveEntity createEntity() {
		return new ContractArchiveEntity();
	}

	@Override
	public ContractArchiveRequestVO createQV() {
		return new ContractArchiveRequestVO();
	}

	@Override
	public ContractArchiveResponseVO createPV() {
		return new ContractArchiveResponseVO();
	}

    @Override
    public void selectUserName(ContractArchiveResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
