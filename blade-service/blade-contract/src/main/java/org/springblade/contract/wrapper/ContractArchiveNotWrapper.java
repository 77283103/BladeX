package org.springblade.contract.wrapper;

import java.util.Optional;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.vo.ContractArchiveNotResponseVO;
import org.springblade.contract.vo.ContractArchiveNotRequestVO;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 未归档原因 包装类,返回视图层所需的字段
 *
 * @author 未归档原因
 * @date : 2020-11-09 15:19:18
 */
@Component
public class ContractArchiveNotWrapper implements IEntityWrapper<ContractArchiveNotEntity, ContractArchiveNotRequestVO, ContractArchiveNotResponseVO> {

	public static ContractArchiveNotWrapper build() {
		return new ContractArchiveNotWrapper();
 	}

    @Override
	public ContractArchiveNotEntity createEntity() {
		return new ContractArchiveNotEntity();
	}

	@Override
	public ContractArchiveNotRequestVO createQV() {
		return new ContractArchiveNotRequestVO();
	}

	@Override
	public ContractArchiveNotResponseVO createPV() {
		return new ContractArchiveNotResponseVO();
	}

    @Override
    public void selectUserName(ContractArchiveNotResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
