package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclConstructionProjectEntity;
import org.springblade.contract.vo.SclConstructionProjectRequestVO;
import org.springblade.contract.vo.SclConstructionProjectResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：加工承揽合同（代工合同） 包装类,返回视图层所需的字段
 *
 * @author 生产类：加工承揽合同（代工合同）
 * @date : 2020-12-11 09:52:24
 */
@Component
public class SclConstructionProjectWrapper implements IEntityWrapper<SclConstructionProjectEntity, SclConstructionProjectRequestVO, SclConstructionProjectResponseVO> {

	public static SclConstructionProjectWrapper build() {
		return new SclConstructionProjectWrapper();
 	}

    @Override
	public SclConstructionProjectEntity createEntity() {
		return new SclConstructionProjectEntity();
	}

	@Override
	public SclConstructionProjectRequestVO createQV() {
		return new SclConstructionProjectRequestVO();
	}

	@Override
	public SclConstructionProjectResponseVO createPV() {
		return new SclConstructionProjectResponseVO();
	}

    @Override
    public void selectUserName(SclConstructionProjectResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
