package org.springblade.abutment.wrapper;

import java.util.Optional;

import org.springblade.abutment.vo.EkpSynDataRequestVO;
import org.springblade.abutment.vo.EkpSynDataResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.abutment.entity.EkpSynDataEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * ekp同步数据 包装类,返回视图层所需的字段
 *
 * @author chenzy
 * @date : 2021-07-27 15:42:12
 */
@Component
public class EkpSynDataWrapper implements IEntityWrapper<EkpSynDataEntity, EkpSynDataRequestVO, EkpSynDataResponseVO> {

	public static EkpSynDataWrapper build() {
		return new EkpSynDataWrapper();
 	}

    @Override
	public EkpSynDataEntity createEntity() {
		return new EkpSynDataEntity();
	}

	@Override
	public EkpSynDataRequestVO createQV() {
		return new EkpSynDataRequestVO();
	}

	@Override
	public EkpSynDataResponseVO createPV() {
		return new EkpSynDataResponseVO();
	}

    @Override
    public void selectUserName(EkpSynDataResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
