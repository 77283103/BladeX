package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.CglRawMaterials1RequestVO;
import org.springblade.contract.vo.CglRawMaterials1ResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.CglRawMaterials1Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 采购类：原物料-买卖合同 包装类,返回视图层所需的字段
 *
 * @author 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:36
 */
@Component
public class CglRawMaterials1Wrapper implements IEntityWrapper<CglRawMaterials1Entity, CglRawMaterials1RequestVO, CglRawMaterials1ResponseVO> {

	public static CglRawMaterials1Wrapper build() {
		return new CglRawMaterials1Wrapper();
 	}

    @Override
	public CglRawMaterials1Entity createEntity() {
		return new CglRawMaterials1Entity();
	}

	@Override
	public CglRawMaterials1RequestVO createQV() {
		return new CglRawMaterials1RequestVO();
	}

	@Override
	public CglRawMaterials1ResponseVO createPV() {
		return new CglRawMaterials1ResponseVO();
	}

    @Override
    public void selectUserName(CglRawMaterials1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
