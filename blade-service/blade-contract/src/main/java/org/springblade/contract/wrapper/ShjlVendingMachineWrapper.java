package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ShjlVendingMachineEntity;
import org.springblade.contract.vo.ShjlVendingMachineRequestVO;
import org.springblade.contract.vo.ShjlVendingMachineResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1) 包装类,返回视图层所需的字段
 *
 * @author 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)
 * @date : 2020-12-18 16:14:41
 */
@Component
public class ShjlVendingMachineWrapper implements IEntityWrapper<ShjlVendingMachineEntity, ShjlVendingMachineRequestVO, ShjlVendingMachineResponseVO> {

	public static ShjlVendingMachineWrapper build() {
		return new ShjlVendingMachineWrapper();
 	}

    @Override
	public ShjlVendingMachineEntity createEntity() {
		return new ShjlVendingMachineEntity();
	}

	@Override
	public ShjlVendingMachineRequestVO createQV() {
		return new ShjlVendingMachineRequestVO();
	}

	@Override
	public ShjlVendingMachineResponseVO createPV() {
		return new ShjlVendingMachineResponseVO();
	}

    @Override
    public void selectUserName(ShjlVendingMachineResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
