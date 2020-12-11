package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtlAudioProductionContract2Entity;
import org.springblade.contract.vo.MtlAudioProductionContract2RequestVO;
import org.springblade.contract.vo.MtlAudioProductionContract2ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 媒体类：音频制作合同关联表2 包装类,返回视图层所需的字段
 *
 * @author 媒体类：音频制作合同关联表2
 * @date : 2020-12-11 03:31:37
 */
@Component
public class MtlAudioProductionContract2Wrapper implements IEntityWrapper<MtlAudioProductionContract2Entity, MtlAudioProductionContract2RequestVO, MtlAudioProductionContract2ResponseVO> {

	public static MtlAudioProductionContract2Wrapper build() {
		return new MtlAudioProductionContract2Wrapper();
 	}

    @Override
	public MtlAudioProductionContract2Entity createEntity() {
		return new MtlAudioProductionContract2Entity();
	}

	@Override
	public MtlAudioProductionContract2RequestVO createQV() {
		return new MtlAudioProductionContract2RequestVO();
	}

	@Override
	public MtlAudioProductionContract2ResponseVO createPV() {
		return new MtlAudioProductionContract2ResponseVO();
	}

    @Override
    public void selectUserName(MtlAudioProductionContract2ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
