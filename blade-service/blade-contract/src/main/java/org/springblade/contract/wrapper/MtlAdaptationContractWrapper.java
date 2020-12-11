package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtlAdaptationContractRequestVO;
import org.springblade.contract.vo.MtlAdaptationContractResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.MtlAdaptationContractEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：视频广告改编合同 包装类,返回视图层所需的字段
 *
 * @author  媒体类：视频广告改编合同
 * @date : 2020-12-10 19:40:36
 */
@Component
public class MtlAdaptationContractWrapper implements IEntityWrapper<MtlAdaptationContractEntity, MtlAdaptationContractRequestVO, MtlAdaptationContractResponseVO> {

	public static MtlAdaptationContractWrapper build() {
		return new MtlAdaptationContractWrapper();
 	}

    @Override
	public MtlAdaptationContractEntity createEntity() {
		return new MtlAdaptationContractEntity();
	}

	@Override
	public MtlAdaptationContractRequestVO createQV() {
		return new MtlAdaptationContractRequestVO();
	}

	@Override
	public MtlAdaptationContractResponseVO createPV() {
		return new MtlAdaptationContractResponseVO();
	}

    @Override
    public void selectUserName(MtlAdaptationContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
