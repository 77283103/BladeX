package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.PerBondRequestVO;
import org.springblade.contract.vo.PerBondResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.PerBondEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 履约计划保证金 包装类,返回视图层所需的字段
 *
 * @author chenzy
 * @date : 2021-04-27 17:06:22
 */
@Component
public class PerBondWrapper implements IEntityWrapper<PerBondEntity, PerBondRequestVO, PerBondResponseVO> {

	public static PerBondWrapper build() {
		return new PerBondWrapper();
 	}

    @Override
	public PerBondEntity createEntity() {
		return new PerBondEntity();
	}

	@Override
	public PerBondRequestVO createQV() {
		return new PerBondRequestVO();
	}

	@Override
	public PerBondResponseVO createPV() {
		return new PerBondResponseVO();
	}

    @Override
    public void selectUserName(PerBondResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
