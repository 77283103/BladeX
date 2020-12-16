package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.YwlANewDisplay1RequestVO;
import org.springblade.contract.vo.YwlANewDisplay1ResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.YwlANewDisplay1Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 业务类：21.新陈列协议书关联表 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2020-12-16 16:42:40
 */
@Component
public class YwlANewDisplay1Wrapper implements IEntityWrapper<YwlANewDisplay1Entity, YwlANewDisplay1RequestVO, YwlANewDisplay1ResponseVO> {

	public static YwlANewDisplay1Wrapper build() {
		return new YwlANewDisplay1Wrapper();
 	}

    @Override
	public YwlANewDisplay1Entity createEntity() {
		return new YwlANewDisplay1Entity();
	}

	@Override
	public YwlANewDisplay1RequestVO createQV() {
		return new YwlANewDisplay1RequestVO();
	}

	@Override
	public YwlANewDisplay1ResponseVO createPV() {
		return new YwlANewDisplay1ResponseVO();
	}

    @Override
    public void selectUserName(YwlANewDisplay1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
