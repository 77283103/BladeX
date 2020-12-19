package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ShjbGeneralVersionEntity;
import org.springblade.contract.vo.ShjbGeneralVersionRequestVO;
import org.springblade.contract.vo.ShjbGeneralVersionResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本）） 包装类,返回视图层所需的字段
 *
 * @author 售货机类：2020.2.24修 -售货机设备租赁合同—通用版（可销售自选产品版本））
 * @date : 2020-12-18 16:02:27
 */
@Component
public class ShjbGeneralVersionWrapper implements IEntityWrapper<ShjbGeneralVersionEntity, ShjbGeneralVersionRequestVO, ShjbGeneralVersionResponseVO> {

	public static ShjbGeneralVersionWrapper build() {
		return new ShjbGeneralVersionWrapper();
 	}

    @Override
	public ShjbGeneralVersionEntity createEntity() {
		return new ShjbGeneralVersionEntity();
	}

	@Override
	public ShjbGeneralVersionRequestVO createQV() {
		return new ShjbGeneralVersionRequestVO();
	}

	@Override
	public ShjbGeneralVersionResponseVO createPV() {
		return new ShjbGeneralVersionResponseVO();
	}

    @Override
    public void selectUserName(ShjbGeneralVersionResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
