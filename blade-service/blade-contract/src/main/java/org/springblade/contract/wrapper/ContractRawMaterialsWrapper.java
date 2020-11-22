package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.ContractRawMaterialsRequestVO;
import org.springblade.contract.vo.ContractRawMaterialsResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.user.entity.User;
import org.springblade.contract.entity.ContractRawMaterialsEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 原物料1v多 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-11-22 16:42:03
 */
@Component
public class ContractRawMaterialsWrapper implements IEntityWrapper<ContractRawMaterialsEntity, ContractRawMaterialsRequestVO, ContractRawMaterialsResponseVO> {

	public static ContractRawMaterialsWrapper build() {
		return new ContractRawMaterialsWrapper();
 	}

    @Override
	public ContractRawMaterialsEntity createEntity() {
		return new ContractRawMaterialsEntity();
	}

	@Override
	public ContractRawMaterialsRequestVO createQV() {
		return new ContractRawMaterialsRequestVO();
	}

	@Override
	public ContractRawMaterialsResponseVO createPV() {
		return new ContractRawMaterialsResponseVO();
	}

    @Override
    public void selectUserName(ContractRawMaterialsResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
