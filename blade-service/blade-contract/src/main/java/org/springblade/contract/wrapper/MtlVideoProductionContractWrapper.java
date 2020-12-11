package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtlVideoProductionContractRequestVO;
import org.springblade.contract.vo.MtlVideoProductionContractResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.MtlVideoProductionContractEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：视频制作合同 包装类,返回视图层所需的字段
 *
 * @author 媒体类：视频制作合同
 * @date : 2020-12-10 19:31:05
 */
@Component
public class MtlVideoProductionContractWrapper implements IEntityWrapper<MtlVideoProductionContractEntity, MtlVideoProductionContractRequestVO, MtlVideoProductionContractResponseVO> {

	public static MtlVideoProductionContractWrapper build() {
		return new MtlVideoProductionContractWrapper();
 	}

    @Override
	public MtlVideoProductionContractEntity createEntity() {
		return new MtlVideoProductionContractEntity();
	}

	@Override
	public MtlVideoProductionContractRequestVO createQV() {
		return new MtlVideoProductionContractRequestVO();
	}

	@Override
	public MtlVideoProductionContractResponseVO createPV() {
		return new MtlVideoProductionContractResponseVO();
	}

    @Override
    public void selectUserName(MtlVideoProductionContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
