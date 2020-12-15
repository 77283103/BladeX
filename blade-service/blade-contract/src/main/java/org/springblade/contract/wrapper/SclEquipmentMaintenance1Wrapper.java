package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.SclEquipmentMaintenance1RequestVO;
import org.springblade.contract.vo.SclEquipmentMaintenance1ResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.SclEquipmentMaintenance1Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 生产类：设备维修保养合同(关联表） 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2020-12-11 10:59:52
 */
@Component
public class SclEquipmentMaintenance1Wrapper implements IEntityWrapper<SclEquipmentMaintenance1Entity, SclEquipmentMaintenance1RequestVO, SclEquipmentMaintenance1ResponseVO> {

	public static SclEquipmentMaintenance1Wrapper build() {
		return new SclEquipmentMaintenance1Wrapper();
 	}

    @Override
	public SclEquipmentMaintenance1Entity createEntity() {
		return new SclEquipmentMaintenance1Entity();
	}

	@Override
	public SclEquipmentMaintenance1RequestVO createQV() {
		return new SclEquipmentMaintenance1RequestVO();
	}

	@Override
	public SclEquipmentMaintenance1ResponseVO createPV() {
		return new SclEquipmentMaintenance1ResponseVO();
	}

    @Override
    public void selectUserName(SclEquipmentMaintenance1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
