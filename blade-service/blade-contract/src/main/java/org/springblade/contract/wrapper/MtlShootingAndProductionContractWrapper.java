package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtlShootingAndProductionContractRequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContractResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.MtlShootingAndProductionContractEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：视频广告拍摄制作合同 包装类,返回视图层所需的字段
 *
 * @author 媒体类：视频广告拍摄制作合同
 * @date : 2020-12-10 19:36:06
 */
@Component
public class MtlShootingAndProductionContractWrapper implements IEntityWrapper<MtlShootingAndProductionContractEntity, MtlShootingAndProductionContractRequestVO, MtlShootingAndProductionContractResponseVO> {

	public static MtlShootingAndProductionContractWrapper build() {
		return new MtlShootingAndProductionContractWrapper();
 	}

    @Override
	public MtlShootingAndProductionContractEntity createEntity() {
		return new MtlShootingAndProductionContractEntity();
	}

	@Override
	public MtlShootingAndProductionContractRequestVO createQV() {
		return new MtlShootingAndProductionContractRequestVO();
	}

	@Override
	public MtlShootingAndProductionContractResponseVO createPV() {
		return new MtlShootingAndProductionContractResponseVO();
	}

    @Override
    public void selectUserName(MtlShootingAndProductionContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
