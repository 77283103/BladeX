package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtlAudioProductionContract1Entity;
import org.springblade.contract.vo.MtlAudioProductionContract1RequestVO;
import org.springblade.contract.vo.MtlAudioProductionContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 媒体类：音频制作合同关联表 包装类,返回视图层所需的字段
 *
 * @author 媒体类：音频制作合同关联表
 * @date : 2020-12-11 03:30:25
 */
@Component
public class MtlAudioProductionContract1Wrapper implements IEntityWrapper<MtlAudioProductionContract1Entity, MtlAudioProductionContract1RequestVO, MtlAudioProductionContract1ResponseVO> {

	public static MtlAudioProductionContract1Wrapper build() {
		return new MtlAudioProductionContract1Wrapper();
 	}

    @Override
	public MtlAudioProductionContract1Entity createEntity() {
		return new MtlAudioProductionContract1Entity();
	}

	@Override
	public MtlAudioProductionContract1RequestVO createQV() {
		return new MtlAudioProductionContract1RequestVO();
	}

	@Override
	public MtlAudioProductionContract1ResponseVO createPV() {
		return new MtlAudioProductionContract1ResponseVO();
	}

    @Override
    public void selectUserName(MtlAudioProductionContract1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
