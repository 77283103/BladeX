package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclConstructionProject2Entity;
import org.springblade.contract.vo.SclConstructionProject2RequestVO;
import org.springblade.contract.vo.SclConstructionProject2ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：加工承揽合同（代工合同）关联表2 包装类,返回视图层所需的字段
 *
 * @author 生产类：加工承揽合同（代工合同）关联表2
 * @date : 2020-12-11 10:22:09
 */
@Component
public class SclConstructionProject2Wrapper implements IEntityWrapper<SclConstructionProject2Entity, SclConstructionProject2RequestVO, SclConstructionProject2ResponseVO> {

	public static SclConstructionProject2Wrapper build() {
		return new SclConstructionProject2Wrapper();
 	}

    @Override
	public SclConstructionProject2Entity createEntity() {
		return new SclConstructionProject2Entity();
	}

	@Override
	public SclConstructionProject2RequestVO createQV() {
		return new SclConstructionProject2RequestVO();
	}

	@Override
	public SclConstructionProject2ResponseVO createPV() {
		return new SclConstructionProject2ResponseVO();
	}

    @Override
    public void selectUserName(SclConstructionProject2ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
