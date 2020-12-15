package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclConstructionProject1Entity;
import org.springblade.contract.vo.SclConstructionProject1RequestVO;
import org.springblade.contract.vo.SclConstructionProject1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：加工承揽合同（代工合同）关联表 包装类,返回视图层所需的字段
 *
 * @author 生产类：加工承揽合同（代工合同）关联表
 * @date : 2020-12-11 10:10:14
 */
@Component
public class SclConstructionProject1Wrapper implements IEntityWrapper<SclConstructionProject1Entity, SclConstructionProject1RequestVO, SclConstructionProject1ResponseVO> {

	public static SclConstructionProject1Wrapper build() {
		return new SclConstructionProject1Wrapper();
 	}

    @Override
	public SclConstructionProject1Entity createEntity() {
		return new SclConstructionProject1Entity();
	}

	@Override
	public SclConstructionProject1RequestVO createQV() {
		return new SclConstructionProject1RequestVO();
	}

	@Override
	public SclConstructionProject1ResponseVO createPV() {
		return new SclConstructionProject1ResponseVO();
	}

    @Override
    public void selectUserName(SclConstructionProject1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
