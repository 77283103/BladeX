package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ShjbOnlinePaymentOffsetEntity;
import org.springblade.contract.vo.ShjbOnlinePaymentOffsetRequestVO;
import org.springblade.contract.vo.ShjbOnlinePaymentOffsetResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014） 包装类,返回视图层所需的字段
 *
 * @author 售货机类：0428修-（仅运营）UP售货机运营维护服务合同（线上线上款相抵付款条款，20191014）
 * @date : 2020-12-18 16:04:46
 */
@Component
public class ShjbOnlinePaymentOffsetWrapper implements IEntityWrapper<ShjbOnlinePaymentOffsetEntity, ShjbOnlinePaymentOffsetRequestVO, ShjbOnlinePaymentOffsetResponseVO> {

	public static ShjbOnlinePaymentOffsetWrapper build() {
		return new ShjbOnlinePaymentOffsetWrapper();
 	}

    @Override
	public ShjbOnlinePaymentOffsetEntity createEntity() {
		return new ShjbOnlinePaymentOffsetEntity();
	}

	@Override
	public ShjbOnlinePaymentOffsetRequestVO createQV() {
		return new ShjbOnlinePaymentOffsetRequestVO();
	}

	@Override
	public ShjbOnlinePaymentOffsetResponseVO createPV() {
		return new ShjbOnlinePaymentOffsetResponseVO();
	}

    @Override
    public void selectUserName(ShjbOnlinePaymentOffsetResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
