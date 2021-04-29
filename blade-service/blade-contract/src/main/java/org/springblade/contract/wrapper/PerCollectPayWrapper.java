package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.PerCollectPayRequestVO;
import org.springblade.contract.vo.PerCollectPayResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.PerCollectPayEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 履约收付款 包装类,返回视图层所需的字段
 *
 * @author chenzy
 * @date : 2021-04-25 10:32:30
 */
@Component
public class PerCollectPayWrapper implements IEntityWrapper<PerCollectPayEntity, PerCollectPayRequestVO, PerCollectPayResponseVO> {

	public static PerCollectPayWrapper build() {
		return new PerCollectPayWrapper();
 	}

    @Override
	public PerCollectPayEntity createEntity() {
		return new PerCollectPayEntity();
	}

	@Override
	public PerCollectPayRequestVO createQV() {
		return new PerCollectPayRequestVO();
	}

	@Override
	public PerCollectPayResponseVO createPV() {
		return new PerCollectPayResponseVO();
	}

    @Override
    public void selectUserName(PerCollectPayResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
