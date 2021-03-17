package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.entity.SclServiceContractRefrigerationEntity;
import org.springblade.contract.vo.SclServiceContractRefrigerationRequestVO;
import org.springblade.contract.vo.SclServiceContractRefrigerationResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * kx 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2021-03-15 13:48:21
 */
@Component
public class SclServiceContractRefrigerationWrapper implements IEntityWrapper<SclServiceContractRefrigerationEntity, SclServiceContractRefrigerationRequestVO, SclServiceContractRefrigerationResponseVO> {

	public static SclServiceContractRefrigerationWrapper build() {
		return new SclServiceContractRefrigerationWrapper();
 	}

    @Override
	public SclServiceContractRefrigerationEntity createEntity() {
		return new SclServiceContractRefrigerationEntity();
	}

	@Override
	public SclServiceContractRefrigerationRequestVO createQV() {
		return new SclServiceContractRefrigerationRequestVO();
	}

	@Override
	public SclServiceContractRefrigerationResponseVO createPV() {
		return new SclServiceContractRefrigerationResponseVO();
	}

    @Override
    public void selectUserName(SclServiceContractRefrigerationResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
