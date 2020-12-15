package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtlVideoProductionContract2Entity;
import org.springblade.contract.vo.MtlVideoProductionContract2RequestVO;
import org.springblade.contract.vo.MtlVideoProductionContract2ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 媒体类：视频制作合同关联表2 包装类,返回视图层所需的字段
 *
 * @author 媒体类：视频制作合同关联表2
 * @date : 2020-12-11 08:48:37
 */
@Component
public class MtlVideoProductionContract2Wrapper implements IEntityWrapper<MtlVideoProductionContract2Entity, MtlVideoProductionContract2RequestVO, MtlVideoProductionContract2ResponseVO> {

	public static MtlVideoProductionContract2Wrapper build() {
		return new MtlVideoProductionContract2Wrapper();
 	}

    @Override
	public MtlVideoProductionContract2Entity createEntity() {
		return new MtlVideoProductionContract2Entity();
	}

	@Override
	public MtlVideoProductionContract2RequestVO createQV() {
		return new MtlVideoProductionContract2RequestVO();
	}

	@Override
	public MtlVideoProductionContract2ResponseVO createPV() {
		return new MtlVideoProductionContract2ResponseVO();
	}

    @Override
    public void selectUserName(MtlVideoProductionContract2ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
