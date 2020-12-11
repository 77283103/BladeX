package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclProjectOutsourcingEntity;
import org.springblade.contract.vo.SclProjectOutsourcingRequestVO;
import org.springblade.contract.vo.SclProjectOutsourcingResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：生产项目外包服务合同 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2020-12-11 11:03:56
 */
@Component
public class SclProjectOutsourcingWrapper implements IEntityWrapper<SclProjectOutsourcingEntity, SclProjectOutsourcingRequestVO, SclProjectOutsourcingResponseVO> {

	public static SclProjectOutsourcingWrapper build() {
		return new SclProjectOutsourcingWrapper();
 	}

    @Override
	public SclProjectOutsourcingEntity createEntity() {
		return new SclProjectOutsourcingEntity();
	}

	@Override
	public SclProjectOutsourcingRequestVO createQV() {
		return new SclProjectOutsourcingRequestVO();
	}

	@Override
	public SclProjectOutsourcingResponseVO createPV() {
		return new SclProjectOutsourcingResponseVO();
	}

    @Override
    public void selectUserName(SclProjectOutsourcingResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
