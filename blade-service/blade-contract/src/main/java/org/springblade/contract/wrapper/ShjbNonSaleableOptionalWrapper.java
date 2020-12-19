package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ShjbNonSaleableOptionalEntity;
import org.springblade.contract.vo.ShjbNonSaleableOptionalRequestVO;
import org.springblade.contract.vo.ShjbNonSaleableOptionalResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本） 包装类,返回视图层所需的字段
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（不可销售自选产品版本）
 * @date : 2020-12-18 16:01:19
 */
@Component
public class ShjbNonSaleableOptionalWrapper implements IEntityWrapper<ShjbNonSaleableOptionalEntity, ShjbNonSaleableOptionalRequestVO, ShjbNonSaleableOptionalResponseVO> {

	public static ShjbNonSaleableOptionalWrapper build() {
		return new ShjbNonSaleableOptionalWrapper();
 	}

    @Override
	public ShjbNonSaleableOptionalEntity createEntity() {
		return new ShjbNonSaleableOptionalEntity();
	}

	@Override
	public ShjbNonSaleableOptionalRequestVO createQV() {
		return new ShjbNonSaleableOptionalRequestVO();
	}

	@Override
	public ShjbNonSaleableOptionalResponseVO createPV() {
		return new ShjbNonSaleableOptionalResponseVO();
	}

    @Override
    public void selectUserName(ShjbNonSaleableOptionalResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
