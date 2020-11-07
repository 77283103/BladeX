package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.ContractPerformanceRequestVO;
import org.springblade.contract.vo.ContractPerformanceResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.user.entity.User;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 接收/提供服务计划清单 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-11-05 17:06:56
 */
@Component
public class ContractPerformanceWrapper implements IEntityWrapper<ContractPerformanceEntity, ContractPerformanceRequestVO, ContractPerformanceResponseVO> {

	public static ContractPerformanceWrapper build() {
		return new ContractPerformanceWrapper();
 	}

    @Override
	public ContractPerformanceEntity createEntity() {
		return new ContractPerformanceEntity();
	}

	@Override
	public ContractPerformanceRequestVO createQV() {
		return new ContractPerformanceRequestVO();
	}

	@Override
	public ContractPerformanceResponseVO createPV() {
		return new ContractPerformanceResponseVO();
	}

    @Override
    public void selectUserName(ContractPerformanceResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
