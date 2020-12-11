package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtlAudioProductionContractEntity;
import org.springblade.contract.vo.MtlAudioProductionContractRequestVO;
import org.springblade.contract.vo.MtlAudioProductionContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 媒体类：音频制作合同 包装类,返回视图层所需的字段
 *
 * @author 媒体类：音频制作合同
 * @date : 2020-12-10 19:21:38
 */
@Component
public class MtlAudioProductionContractWrapper implements IEntityWrapper<MtlAudioProductionContractEntity, MtlAudioProductionContractRequestVO, MtlAudioProductionContractResponseVO> {

	public static MtlAudioProductionContractWrapper build() {
		return new MtlAudioProductionContractWrapper();
 	}

    @Override
	public MtlAudioProductionContractEntity createEntity() {
		return new MtlAudioProductionContractEntity();
	}

	@Override
	public MtlAudioProductionContractRequestVO createQV() {
		return new MtlAudioProductionContractRequestVO();
	}

	@Override
	public MtlAudioProductionContractResponseVO createPV() {
		return new MtlAudioProductionContractResponseVO();
	}

    @Override
    public void selectUserName(MtlAudioProductionContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
