package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclProductionCategoryEntity;
import org.springblade.contract.vo.SclProductionCategoryRequestVO;
import org.springblade.contract.vo.SclProductionCategoryResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：物流服务合同（一段+调拨运输） 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2020-12-18 17:18:05
 */
@Component
public class SclProductionCategoryWrapper implements IEntityWrapper<SclProductionCategoryEntity, SclProductionCategoryRequestVO, SclProductionCategoryResponseVO> {

	public static SclProductionCategoryWrapper build() {
		return new SclProductionCategoryWrapper();
 	}

    @Override
	public SclProductionCategoryEntity createEntity() {
		return new SclProductionCategoryEntity();
	}

	@Override
	public SclProductionCategoryRequestVO createQV() {
		return new SclProductionCategoryRequestVO();
	}

	@Override
	public SclProductionCategoryResponseVO createPV() {
		return new SclProductionCategoryResponseVO();
	}

    @Override
    public void selectUserName(SclProductionCategoryResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
