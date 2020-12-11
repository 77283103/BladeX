package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.CglLowCostHardwareRequestVO;
import org.springblade.contract.vo.CglLowCostHardwareResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.CglLowCostHardwareEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 采购类：买卖合同（五金低耗类） 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-10 19:01:57
 */
@Component
public class CglLowCostHardwareWrapper implements IEntityWrapper<CglLowCostHardwareEntity, CglLowCostHardwareRequestVO, CglLowCostHardwareResponseVO> {

	public static CglLowCostHardwareWrapper build() {
		return new CglLowCostHardwareWrapper();
 	}

    @Override
	public CglLowCostHardwareEntity createEntity() {
		return new CglLowCostHardwareEntity();
	}

	@Override
	public CglLowCostHardwareRequestVO createQV() {
		return new CglLowCostHardwareRequestVO();
	}

	@Override
	public CglLowCostHardwareResponseVO createPV() {
		return new CglLowCostHardwareResponseVO();
	}

    @Override
    public void selectUserName(CglLowCostHardwareResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
