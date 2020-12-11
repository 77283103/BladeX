package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.CglTheSalesContractRequestVO;
import org.springblade.contract.vo.CglTheSalesContractResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.CglTheSalesContractEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 采购类：新增原物料补充协议--买卖合同 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-10 19:07:50
 */
@Component
public class CglTheSalesContractWrapper implements IEntityWrapper<CglTheSalesContractEntity, CglTheSalesContractRequestVO, CglTheSalesContractResponseVO> {

	public static CglTheSalesContractWrapper build() {
		return new CglTheSalesContractWrapper();
 	}

    @Override
	public CglTheSalesContractEntity createEntity() {
		return new CglTheSalesContractEntity();
	}

	@Override
	public CglTheSalesContractRequestVO createQV() {
		return new CglTheSalesContractRequestVO();
	}

	@Override
	public CglTheSalesContractResponseVO createPV() {
		return new CglTheSalesContractResponseVO();
	}

    @Override
    public void selectUserName(CglTheSalesContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
