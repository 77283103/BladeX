package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclOutsourcingAgreementEntity;
import org.springblade.contract.vo.SclOutsourcingAgreementRequestVO;
import org.springblade.contract.vo.SclOutsourcingAgreementResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：作业外包协议 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2020-12-18 16:08:28
 */
@Component
public class SclOutsourcingAgreementWrapper implements IEntityWrapper<SclOutsourcingAgreementEntity, SclOutsourcingAgreementRequestVO, SclOutsourcingAgreementResponseVO> {

	public static SclOutsourcingAgreementWrapper build() {
		return new SclOutsourcingAgreementWrapper();
 	}

    @Override
	public SclOutsourcingAgreementEntity createEntity() {
		return new SclOutsourcingAgreementEntity();
	}

	@Override
	public SclOutsourcingAgreementRequestVO createQV() {
		return new SclOutsourcingAgreementRequestVO();
	}

	@Override
	public SclOutsourcingAgreementResponseVO createPV() {
		return new SclOutsourcingAgreementResponseVO();
	}

    @Override
    public void selectUserName(SclOutsourcingAgreementResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
