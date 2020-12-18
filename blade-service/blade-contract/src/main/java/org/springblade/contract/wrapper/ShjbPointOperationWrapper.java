package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ShjbPointOperationEntity;
import org.springblade.contract.vo.ShjbPointOperationRequestVO;
import org.springblade.contract.vo.ShjbPointOperationResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014） 包装类,返回视图层所需的字段
 *
 * @author 售货机类：0428修-（点位+运营）UP售货机合作合同（线上线下款相抵付款条款，20191014）
 * @date : 2020-12-18 16:05:29
 */
@Component
public class ShjbPointOperationWrapper implements IEntityWrapper<ShjbPointOperationEntity, ShjbPointOperationRequestVO, ShjbPointOperationResponseVO> {

	public static ShjbPointOperationWrapper build() {
		return new ShjbPointOperationWrapper();
 	}

    @Override
	public ShjbPointOperationEntity createEntity() {
		return new ShjbPointOperationEntity();
	}

	@Override
	public ShjbPointOperationRequestVO createQV() {
		return new ShjbPointOperationRequestVO();
	}

	@Override
	public ShjbPointOperationResponseVO createPV() {
		return new ShjbPointOperationResponseVO();
	}

    @Override
    public void selectUserName(ShjbPointOperationResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
