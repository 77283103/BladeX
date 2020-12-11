package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.CglCategorySalesContracts1RequestVO;
import org.springblade.contract.vo.CglCategorySalesContracts1ResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.CglCategorySalesContracts1Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 采购类：买卖合同（行销品） 包装类,返回视图层所需的字段
 *
 * @author 采购类：买卖合同（行销品）
 * @date : 2020-12-10 18:58:22
 */
@Component
public class CglCategorySalesContracts1Wrapper implements IEntityWrapper<CglCategorySalesContracts1Entity, CglCategorySalesContracts1RequestVO, CglCategorySalesContracts1ResponseVO> {

	public static CglCategorySalesContracts1Wrapper build() {
		return new CglCategorySalesContracts1Wrapper();
 	}

    @Override
	public CglCategorySalesContracts1Entity createEntity() {
		return new CglCategorySalesContracts1Entity();
	}

	@Override
	public CglCategorySalesContracts1RequestVO createQV() {
		return new CglCategorySalesContracts1RequestVO();
	}

	@Override
	public CglCategorySalesContracts1ResponseVO createPV() {
		return new CglCategorySalesContracts1ResponseVO();
	}

    @Override
    public void selectUserName(CglCategorySalesContracts1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
