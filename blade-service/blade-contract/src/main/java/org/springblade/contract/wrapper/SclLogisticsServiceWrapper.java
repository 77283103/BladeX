package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclLogisticsServiceEntity;
import org.springblade.contract.vo.SclLogisticsServiceRequestVO;
import org.springblade.contract.vo.SclLogisticsServiceResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：物流服务合同（二段配送） 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2020-12-18 17:17:44
 */
@Component
public class SclLogisticsServiceWrapper implements IEntityWrapper<SclLogisticsServiceEntity, SclLogisticsServiceRequestVO, SclLogisticsServiceResponseVO> {

	public static SclLogisticsServiceWrapper build() {
		return new SclLogisticsServiceWrapper();
 	}

    @Override
	public SclLogisticsServiceEntity createEntity() {
		return new SclLogisticsServiceEntity();
	}

	@Override
	public SclLogisticsServiceRequestVO createQV() {
		return new SclLogisticsServiceRequestVO();
	}

	@Override
	public SclLogisticsServiceResponseVO createPV() {
		return new SclLogisticsServiceResponseVO();
	}

    @Override
    public void selectUserName(SclLogisticsServiceResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
