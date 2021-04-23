package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.PerPlanFinshContentRequestVO;
import org.springblade.contract.vo.PerPlanFinshContentResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.PerPlanFinshContentEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 履约计划完成内容 包装类,返回视图层所需的字段
 *
 * @author chenzy
 * @date : 2021-04-20 16:28:58
 */
@Component
public class PerPlanFinshContentWrapper implements IEntityWrapper<PerPlanFinshContentEntity, PerPlanFinshContentRequestVO, PerPlanFinshContentResponseVO> {

	public static PerPlanFinshContentWrapper build() {
		return new PerPlanFinshContentWrapper();
 	}

    @Override
	public PerPlanFinshContentEntity createEntity() {
		return new PerPlanFinshContentEntity();
	}

	@Override
	public PerPlanFinshContentRequestVO createQV() {
		return new PerPlanFinshContentRequestVO();
	}

	@Override
	public PerPlanFinshContentResponseVO createPV() {
		return new PerPlanFinshContentResponseVO();
	}

    @Override
    public void selectUserName(PerPlanFinshContentResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
