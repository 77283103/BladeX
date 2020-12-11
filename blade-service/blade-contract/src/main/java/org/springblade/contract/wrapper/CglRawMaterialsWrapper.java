package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.CglRawMaterialsRequestVO;
import org.springblade.contract.vo.CglRawMaterialsResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.CglRawMaterialsEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 采购类：原物料-买卖合同 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-10 19:17:24
 */
@Component
public class CglRawMaterialsWrapper implements IEntityWrapper<CglRawMaterialsEntity, CglRawMaterialsRequestVO, CglRawMaterialsResponseVO> {

	public static CglRawMaterialsWrapper build() {
		return new CglRawMaterialsWrapper();
 	}

    @Override
	public CglRawMaterialsEntity createEntity() {
		return new CglRawMaterialsEntity();
	}

	@Override
	public CglRawMaterialsRequestVO createQV() {
		return new CglRawMaterialsRequestVO();
	}

	@Override
	public CglRawMaterialsResponseVO createPV() {
		return new CglRawMaterialsResponseVO();
	}

    @Override
    public void selectUserName(CglRawMaterialsResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
