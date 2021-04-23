package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.PerServiceContentRequestVO;
import org.springblade.contract.vo.PerServiceContentResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.PerServiceContentEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 履约服务内容 包装类,返回视图层所需的字段
 *
 * @author chenzy
 * @date : 2021-04-20 16:02:06
 */
@Component
public class PerServiceContentWrapper implements IEntityWrapper<PerServiceContentEntity, PerServiceContentRequestVO, PerServiceContentResponseVO> {

	public static PerServiceContentWrapper build() {
		return new PerServiceContentWrapper();
 	}

    @Override
	public PerServiceContentEntity createEntity() {
		return new PerServiceContentEntity();
	}

	@Override
	public PerServiceContentRequestVO createQV() {
		return new PerServiceContentRequestVO();
	}

	@Override
	public PerServiceContentResponseVO createPV() {
		return new PerServiceContentResponseVO();
	}

    @Override
    public void selectUserName(PerServiceContentResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
