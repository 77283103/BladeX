package org.springblade.contract.wrapper;

import org.springblade.contract.entity.YwbUnifiedAgreementEntity;
import org.springblade.contract.vo.YwbUnifiedAgreementRequestVO;
import org.springblade.contract.vo.YwbUnifiedAgreementResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 2021年统一e商城平台入驻服务协议（统一经销商） 包装类,返回视图层所需的字段
 *
 * @author 2021年统一e商城平台入驻服务协议（统一经销商）
 * @date : 2020-12-18 16:03:32
 */
@Component
public class YwbUnifiedAgreementWrapper implements IEntityWrapper<YwbUnifiedAgreementEntity, YwbUnifiedAgreementRequestVO, YwbUnifiedAgreementResponseVO> {

	public static YwbUnifiedAgreementWrapper build() {
		return new YwbUnifiedAgreementWrapper();
 	}

    @Override
	public YwbUnifiedAgreementEntity createEntity() {
		return new YwbUnifiedAgreementEntity();
	}

	@Override
	public YwbUnifiedAgreementRequestVO createQV() {
		return new YwbUnifiedAgreementRequestVO();
	}

	@Override
	public YwbUnifiedAgreementResponseVO createPV() {
		return new YwbUnifiedAgreementResponseVO();
	}

    @Override
    public void selectUserName(YwbUnifiedAgreementResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
