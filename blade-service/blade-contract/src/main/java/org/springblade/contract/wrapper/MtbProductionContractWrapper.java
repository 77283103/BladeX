package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtbProductionContractRequestVO;
import org.springblade.contract.vo.MtbProductionContractResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.MtbProductionContractEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：平面广告拍摄制作合同 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-10 19:30:56
 */
@Component
public class MtbProductionContractWrapper implements IEntityWrapper<MtbProductionContractEntity, MtbProductionContractRequestVO, MtbProductionContractResponseVO> {

	public static MtbProductionContractWrapper build() {
		return new MtbProductionContractWrapper();
 	}

    @Override
	public MtbProductionContractEntity createEntity() {
		return new MtbProductionContractEntity();
	}

	@Override
	public MtbProductionContractRequestVO createQV() {
		return new MtbProductionContractRequestVO();
	}

	@Override
	public MtbProductionContractResponseVO createPV() {
		return new MtbProductionContractResponseVO();
	}

    @Override
    public void selectUserName(MtbProductionContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
