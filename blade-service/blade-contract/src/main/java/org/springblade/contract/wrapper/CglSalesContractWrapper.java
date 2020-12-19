package org.springblade.contract.wrapper;

import org.springblade.contract.entity.CglSalesContractEntity;
import org.springblade.contract.vo.CglSalesContractRequestVO;
import org.springblade.contract.vo.CglSalesContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 采购类：买卖合同（国内设备购买） 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-18 15:36:11
 */
@Component
public class CglSalesContractWrapper implements IEntityWrapper<CglSalesContractEntity, CglSalesContractRequestVO, CglSalesContractResponseVO> {

	public static CglSalesContractWrapper build() {
		return new CglSalesContractWrapper();
 	}

    @Override
	public CglSalesContractEntity createEntity() {
		return new CglSalesContractEntity();
	}

	@Override
	public CglSalesContractRequestVO createQV() {
		return new CglSalesContractRequestVO();
	}

	@Override
	public CglSalesContractResponseVO createPV() {
		return new CglSalesContractResponseVO();
	}

    @Override
    public void selectUserName(CglSalesContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
