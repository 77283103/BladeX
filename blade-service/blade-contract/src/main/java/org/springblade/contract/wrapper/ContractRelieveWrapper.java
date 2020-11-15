package org.springblade.contract.wrapper;

import java.util.Optional;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.vo.ContractRelieveResponseVO;
import org.springblade.contract.vo.ContractRelieveRequestVO;
import org.springblade.contract.entity.ContractRelieveEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 合同解除 包装类,返回视图层所需的字段
 *
 * @author 合同解除
 * @date : 2020-11-05 09:24:01
 */
@Component
public class ContractRelieveWrapper implements IEntityWrapper<ContractRelieveEntity, ContractRelieveRequestVO, ContractRelieveResponseVO> {

	public static ContractRelieveWrapper build() {
		return new ContractRelieveWrapper();
 	}

    @Override
	public ContractRelieveEntity createEntity() {
		return new ContractRelieveEntity();
	}

	@Override
	public ContractRelieveRequestVO createQV() {
		return new ContractRelieveRequestVO();
	}

	@Override
	public ContractRelieveResponseVO createPV() {
		return new ContractRelieveResponseVO();
	}

    @Override
    public void selectUserName(ContractRelieveResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
