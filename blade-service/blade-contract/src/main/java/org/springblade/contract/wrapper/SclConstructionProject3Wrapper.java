package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclConstructionProject3Entity;
import org.springblade.contract.vo.SclConstructionProject3RequestVO;
import org.springblade.contract.vo.SclConstructionProject3ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：加工承揽合同（代工合同）关联表3 包装类,返回视图层所需的字段
 *
 * @author 生产类：加工承揽合同（代工合同）关联表3
 * @date : 2020-12-11 10:36:44
 */
@Component
public class SclConstructionProject3Wrapper implements IEntityWrapper<SclConstructionProject3Entity, SclConstructionProject3RequestVO, SclConstructionProject3ResponseVO> {

	public static SclConstructionProject3Wrapper build() {
		return new SclConstructionProject3Wrapper();
 	}

    @Override
	public SclConstructionProject3Entity createEntity() {
		return new SclConstructionProject3Entity();
	}

	@Override
	public SclConstructionProject3RequestVO createQV() {
		return new SclConstructionProject3RequestVO();
	}

	@Override
	public SclConstructionProject3ResponseVO createPV() {
		return new SclConstructionProject3ResponseVO();
	}

    @Override
    public void selectUserName(SclConstructionProject3ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
