package org.springblade.contract.wrapper;

import org.springblade.contract.entity.CglSalesContract1Entity;
import org.springblade.contract.vo.CglSalesContract1RequestVO;
import org.springblade.contract.vo.CglSalesContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 采购类：买卖合同（国内设备购买）附表 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-18 16:12:25
 */
@Component
public class CglSalesContract1Wrapper implements IEntityWrapper<CglSalesContract1Entity, CglSalesContract1RequestVO, CglSalesContract1ResponseVO> {

	public static CglSalesContract1Wrapper build() {
		return new CglSalesContract1Wrapper();
 	}

    @Override
	public CglSalesContract1Entity createEntity() {
		return new CglSalesContract1Entity();
	}

	@Override
	public CglSalesContract1RequestVO createQV() {
		return new CglSalesContract1RequestVO();
	}

	@Override
	public CglSalesContract1ResponseVO createPV() {
		return new CglSalesContract1ResponseVO();
	}

    @Override
    public void selectUserName(CglSalesContract1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
