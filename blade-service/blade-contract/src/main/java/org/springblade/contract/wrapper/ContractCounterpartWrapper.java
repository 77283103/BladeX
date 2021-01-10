package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.vo.ContractCounterpartRequestVO;
import org.springblade.contract.vo.ContractCounterpartResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;

import java.util.Optional;

/**
 * 相对方管理 包装类,返回视图层所需的字段
 *
 * @author XHB
 * @date : 2020-09-23 19:35:07
 */
public class ContractCounterpartWrapper implements IEntityWrapper<ContractCounterpartEntity, ContractCounterpartRequestVO, ContractCounterpartResponseVO> {

	public static ContractCounterpartWrapper build() {
		return new ContractCounterpartWrapper();
 	}

	@Override
	public ContractCounterpartEntity createEntity() {
		return new ContractCounterpartEntity();
	}

	@Override
	public ContractCounterpartResponseVO createPV() {
		return new ContractCounterpartResponseVO();
	}

	@Override
	public void selectUserName(ContractCounterpartResponseVO responseVO) {
		responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
		responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
		responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
	}

	@Override
	public ContractCounterpartRequestVO createQV() {
		return new ContractCounterpartRequestVO();
	}
}
