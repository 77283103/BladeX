package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ActAppDatabasechangeloglockEntity;
import org.springblade.contract.vo.ActAppDatabasechangeloglockRequestVO;
import org.springblade.contract.vo.ActAppDatabasechangeloglockResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1) 包装类,返回视图层所需的字段
 *
 * @author 售货机类
 * @date : 2020-12-18 16:13:50
 */
@Component
public class ActAppDatabasechangeloglockWrapper implements IEntityWrapper<ActAppDatabasechangeloglockEntity, ActAppDatabasechangeloglockRequestVO, ActAppDatabasechangeloglockResponseVO> {

	public static ActAppDatabasechangeloglockWrapper build() {
		return new ActAppDatabasechangeloglockWrapper();
 	}

    @Override
	public ActAppDatabasechangeloglockEntity createEntity() {
		return new ActAppDatabasechangeloglockEntity();
	}

	@Override
	public ActAppDatabasechangeloglockRequestVO createQV() {
		return new ActAppDatabasechangeloglockRequestVO();
	}

	@Override
	public ActAppDatabasechangeloglockResponseVO createPV() {
		return new ActAppDatabasechangeloglockResponseVO();
	}

    @Override
    public void selectUserName(ActAppDatabasechangeloglockResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
