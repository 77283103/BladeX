package org.springblade.contract.wrapper;

import org.springblade.contract.entity.YwbDirectSalesCustomersEntity;
import org.springblade.contract.vo.YwbDirectSalesCustomersRequestVO;
import org.springblade.contract.vo.YwbDirectSalesCustomersResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 业务类：22.直营客户促销执行通知函 包装类,返回视图层所需的字段
 *
 * @author 业务类：22.直营客户促销执行通知函
 * @date : 2020-12-18 16:06:13
 */
@Component
public class YwbDirectSalesCustomersWrapper implements IEntityWrapper<YwbDirectSalesCustomersEntity, YwbDirectSalesCustomersRequestVO, YwbDirectSalesCustomersResponseVO> {

	public static YwbDirectSalesCustomersWrapper build() {
		return new YwbDirectSalesCustomersWrapper();
 	}

    @Override
	public YwbDirectSalesCustomersEntity createEntity() {
		return new YwbDirectSalesCustomersEntity();
	}

	@Override
	public YwbDirectSalesCustomersRequestVO createQV() {
		return new YwbDirectSalesCustomersRequestVO();
	}

	@Override
	public YwbDirectSalesCustomersResponseVO createPV() {
		return new YwbDirectSalesCustomersResponseVO();
	}

    @Override
    public void selectUserName(YwbDirectSalesCustomersResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
