package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.YwlANewDisplayRequestVO;
import org.springblade.contract.vo.YwlANewDisplayResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.YwlANewDisplayEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 业务类：21.新陈列协议书 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-12-07 15:37:43
 */
@Component
public class YwlANewDisplayWrapper implements IEntityWrapper<YwlANewDisplayEntity, YwlANewDisplayRequestVO, YwlANewDisplayResponseVO> {

	public static YwlANewDisplayWrapper build() {
		return new YwlANewDisplayWrapper();
 	}

    @Override
	public YwlANewDisplayEntity createEntity() {
		return new YwlANewDisplayEntity();
	}

	@Override
	public YwlANewDisplayRequestVO createQV() {
		return new YwlANewDisplayRequestVO();
	}

	@Override
	public YwlANewDisplayResponseVO createPV() {
		return new YwlANewDisplayResponseVO();
	}

    @Override
    public void selectUserName(YwlANewDisplayResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
