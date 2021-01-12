package org.springblade.contract.wrapper;

import org.springblade.contract.entity.YwlAnewDisplayEntity;
import org.springblade.contract.vo.YwlANewDisplayRequestVO;
import org.springblade.contract.vo.YwlAnewDisplayResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 业务类：21.新陈列协议书 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-12-07 15:37:43
 */
@Component
public class YwlANewDisplayWrapper implements IEntityWrapper<YwlAnewDisplayEntity, YwlANewDisplayRequestVO, YwlAnewDisplayResponseVO> {

	public static YwlANewDisplayWrapper build() {
		return new YwlANewDisplayWrapper();
 	}

    @Override
	public YwlAnewDisplayEntity createEntity() {
		return new YwlAnewDisplayEntity();
	}

	@Override
	public YwlANewDisplayRequestVO createQV() {
		return new YwlANewDisplayRequestVO();
	}

	@Override
	public YwlAnewDisplayResponseVO createPV() {
		return new YwlAnewDisplayResponseVO();
	}

    @Override
    public void selectUserName(YwlAnewDisplayResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
