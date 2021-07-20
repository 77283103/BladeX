package org.springblade.contract.wrapper;

import org.springblade.contract.entity.CglLowCostHardware1Entity;
import org.springblade.contract.vo.CglLowCostHardware1RequestVO;
import org.springblade.contract.vo.CglLowCostHardware1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 采购类：原物料-买卖合同 包装类,返回视图层所需的字段
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:36
 */
@Component
public class CglLowCostHardware1Wrapper implements IEntityWrapper<CglLowCostHardware1Entity, CglLowCostHardware1RequestVO, CglLowCostHardware1ResponseVO> {

	public static CglLowCostHardware1Wrapper build() {
		return new CglLowCostHardware1Wrapper();
 	}

    @Override
	public CglLowCostHardware1Entity createEntity() {
		return new CglLowCostHardware1Entity();
	}

	@Override
	public CglLowCostHardware1RequestVO createQV() {
		return new CglLowCostHardware1RequestVO();
	}

	@Override
	public CglLowCostHardware1ResponseVO createPV() {
		return new CglLowCostHardware1ResponseVO();
	}

    @Override
    public void selectUserName(CglLowCostHardware1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
