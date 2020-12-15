package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtlVideoProductionContract1Entity;
import org.springblade.contract.vo.MtlVideoProductionContract1RequestVO;
import org.springblade.contract.vo.MtlVideoProductionContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 媒体类：视频制作合同关联表 包装类,返回视图层所需的字段
 *
 * @author 媒体类：视频制作合同关联表
 * @date : 2020-12-11 08:47:27
 */
@Component
public class MtlVideoProductionContract1Wrapper implements IEntityWrapper<MtlVideoProductionContract1Entity, MtlVideoProductionContract1RequestVO, MtlVideoProductionContract1ResponseVO> {

	public static MtlVideoProductionContract1Wrapper build() {
		return new MtlVideoProductionContract1Wrapper();
 	}

    @Override
	public MtlVideoProductionContract1Entity createEntity() {
		return new MtlVideoProductionContract1Entity();
	}

	@Override
	public MtlVideoProductionContract1RequestVO createQV() {
		return new MtlVideoProductionContract1RequestVO();
	}

	@Override
	public MtlVideoProductionContract1ResponseVO createPV() {
		return new MtlVideoProductionContract1ResponseVO();
	}

    @Override
    public void selectUserName(MtlVideoProductionContract1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
