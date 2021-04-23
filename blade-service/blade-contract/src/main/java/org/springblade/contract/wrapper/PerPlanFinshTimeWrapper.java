package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.PerPlanFinshTimeRequestVO;
import org.springblade.contract.vo.PerPlanFinshTimeResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;

import org.springblade.contract.entity.PerPlanFinshTimeEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 履约计划完成时间 包装类,返回视图层所需的字段
 *
 * @author chenzy
 * @date : 2021-04-20 16:34:52
 */
@Component
public class PerPlanFinshTimeWrapper implements IEntityWrapper<PerPlanFinshTimeEntity, PerPlanFinshTimeRequestVO, PerPlanFinshTimeResponseVO> {

	public static PerPlanFinshTimeWrapper build() {
		return new PerPlanFinshTimeWrapper();
 	}

    @Override
	public PerPlanFinshTimeEntity createEntity() {
		return new PerPlanFinshTimeEntity();
	}

	@Override
	public PerPlanFinshTimeRequestVO createQV() {
		return new PerPlanFinshTimeRequestVO();
	}

	@Override
	public PerPlanFinshTimeResponseVO createPV() {
		return new PerPlanFinshTimeResponseVO();
	}

    @Override
    public void selectUserName(PerPlanFinshTimeResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
