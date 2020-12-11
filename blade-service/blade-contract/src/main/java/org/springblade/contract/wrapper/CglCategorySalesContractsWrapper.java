package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.CglCategorySalesContractsRequestVO;
import org.springblade.contract.vo.CglCategorySalesContractsResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.user.entity.User;
import org.springblade.contract.entity.CglCategorySalesContractsEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 采购类：买卖合同（行销品） 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-10 18:52:47
 */
@Component
public class CglCategorySalesContractsWrapper implements IEntityWrapper<CglCategorySalesContractsEntity, CglCategorySalesContractsRequestVO, CglCategorySalesContractsResponseVO> {

	public static CglCategorySalesContractsWrapper build() {
		return new CglCategorySalesContractsWrapper();
 	}

    @Override
	public CglCategorySalesContractsEntity createEntity() {
		return new CglCategorySalesContractsEntity();
	}

	@Override
	public CglCategorySalesContractsRequestVO createQV() {
		return new CglCategorySalesContractsRequestVO();
	}

	@Override
	public CglCategorySalesContractsResponseVO createPV() {
		return new CglCategorySalesContractsResponseVO();
	}

    @Override
    public void selectUserName(CglCategorySalesContractsResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
