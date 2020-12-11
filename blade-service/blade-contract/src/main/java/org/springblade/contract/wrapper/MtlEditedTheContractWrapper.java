package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtlEditedTheContractRequestVO;
import org.springblade.contract.vo.MtlEditedTheContractResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.MtlEditedTheContractEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：修图合同 包装类,返回视图层所需的字段
 *
 * @author 媒体类：修图合同
 * @date : 2020-12-10 19:24:50
 */
@Component
public class MtlEditedTheContractWrapper implements IEntityWrapper<MtlEditedTheContractEntity, MtlEditedTheContractRequestVO, MtlEditedTheContractResponseVO> {

	public static MtlEditedTheContractWrapper build() {
		return new MtlEditedTheContractWrapper();
 	}

    @Override
	public MtlEditedTheContractEntity createEntity() {
		return new MtlEditedTheContractEntity();
	}

	@Override
	public MtlEditedTheContractRequestVO createQV() {
		return new MtlEditedTheContractRequestVO();
	}

	@Override
	public MtlEditedTheContractResponseVO createPV() {
		return new MtlEditedTheContractResponseVO();
	}

    @Override
    public void selectUserName(MtlEditedTheContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
