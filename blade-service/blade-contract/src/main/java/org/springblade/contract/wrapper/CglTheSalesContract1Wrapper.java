package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.CglTheSalesContract1RequestVO;
import org.springblade.contract.vo.CglTheSalesContract1ResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.CglTheSalesContract1Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 采购类：新增原物料补充协议--买卖合同 包装类,返回视图层所需的字段
 *
 * @author 采购类：新增原物料补充协议--买卖合同
 * @date : 2020-12-10 18:50:22
 */
@Component
public class CglTheSalesContract1Wrapper implements IEntityWrapper<CglTheSalesContract1Entity, CglTheSalesContract1RequestVO, CglTheSalesContract1ResponseVO> {

	public static CglTheSalesContract1Wrapper build() {
		return new CglTheSalesContract1Wrapper();
 	}

    @Override
	public CglTheSalesContract1Entity createEntity() {
		return new CglTheSalesContract1Entity();
	}

	@Override
	public CglTheSalesContract1RequestVO createQV() {
		return new CglTheSalesContract1RequestVO();
	}

	@Override
	public CglTheSalesContract1ResponseVO createPV() {
		return new CglTheSalesContract1ResponseVO();
	}

    @Override
    public void selectUserName(CglTheSalesContract1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
