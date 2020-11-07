package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.ContractPerformanceColPayRequestVO;
import org.springblade.contract.vo.ContractPerformanceColPayResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 收付款计划清单-收付款 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-11-05 17:07:02
 */
@Component
public class ContractPerformanceColPayWrapper implements IEntityWrapper<ContractPerformanceColPayEntity, ContractPerformanceColPayRequestVO, ContractPerformanceColPayResponseVO> {

	public static ContractPerformanceColPayWrapper build() {
		return new ContractPerformanceColPayWrapper();
 	}

    @Override
	public ContractPerformanceColPayEntity createEntity() {
		return new ContractPerformanceColPayEntity();
	}

	@Override
	public ContractPerformanceColPayRequestVO createQV() {
		return new ContractPerformanceColPayRequestVO();
	}

	@Override
	public ContractPerformanceColPayResponseVO createPV() {
		return new ContractPerformanceColPayResponseVO();
	}

    @Override
    public void selectUserName(ContractPerformanceColPayResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
