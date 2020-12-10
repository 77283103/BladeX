package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.CglActivityExecutionContractRequestVO;
import org.springblade.contract.vo.CglActivityExecutionContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.user.entity.User;
import org.springblade.contract.entity.CglActivityExecutionContractEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 采购类：活动执行合同 包装类,返回视图层所需的字段
 *
 * @author 采购类：活动执行合同
 * @date : 2020-12-10 18:29:51
 */
@Component
public class CglActivityExecutionContractWrapper implements IEntityWrapper<CglActivityExecutionContractEntity, CglActivityExecutionContractRequestVO, CglActivityExecutionContractResponseVO> {

	public static CglActivityExecutionContractWrapper build() {
		return new CglActivityExecutionContractWrapper();
 	}

    @Override
	public CglActivityExecutionContractEntity createEntity() {
		return new CglActivityExecutionContractEntity();
	}

	@Override
	public CglActivityExecutionContractRequestVO createQV() {
		return new CglActivityExecutionContractRequestVO();
	}

	@Override
	public CglActivityExecutionContractResponseVO createPV() {
		return new CglActivityExecutionContractResponseVO();
	}

    @Override
    public void selectUserName(CglActivityExecutionContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
