package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.CglPaymentDaysSupplementaryRequestVO;
import org.springblade.contract.vo.CglPaymentDaysSupplementaryResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.CglPaymentDaysSupplementaryEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 采购类：账期补充协议--买卖合同 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-10 19:21:47
 */
@Component
public class CglPaymentDaysSupplementaryWrapper implements IEntityWrapper<CglPaymentDaysSupplementaryEntity, CglPaymentDaysSupplementaryRequestVO, CglPaymentDaysSupplementaryResponseVO> {

	public static CglPaymentDaysSupplementaryWrapper build() {
		return new CglPaymentDaysSupplementaryWrapper();
 	}

    @Override
	public CglPaymentDaysSupplementaryEntity createEntity() {
		return new CglPaymentDaysSupplementaryEntity();
	}

	@Override
	public CglPaymentDaysSupplementaryRequestVO createQV() {
		return new CglPaymentDaysSupplementaryRequestVO();
	}

	@Override
	public CglPaymentDaysSupplementaryResponseVO createPV() {
		return new CglPaymentDaysSupplementaryResponseVO();
	}

    @Override
    public void selectUserName(CglPaymentDaysSupplementaryResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
