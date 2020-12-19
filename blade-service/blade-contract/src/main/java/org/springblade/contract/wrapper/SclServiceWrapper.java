package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclServiceEntity;
import org.springblade.contract.vo.SclServiceRequestVO;
import org.springblade.contract.vo.SclServiceResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：物流服务合同（二段仓储+配送） 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2020-12-18 17:08:09
 */
@Component
public class SclServiceWrapper implements IEntityWrapper<SclServiceEntity, SclServiceRequestVO, SclServiceResponseVO> {

	public static SclServiceWrapper build() {
		return new SclServiceWrapper();
 	}

    @Override
	public SclServiceEntity createEntity() {
		return new SclServiceEntity();
	}

	@Override
	public SclServiceRequestVO createQV() {
		return new SclServiceRequestVO();
	}

	@Override
	public SclServiceResponseVO createPV() {
		return new SclServiceResponseVO();
	}

    @Override
    public void selectUserName(SclServiceResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
