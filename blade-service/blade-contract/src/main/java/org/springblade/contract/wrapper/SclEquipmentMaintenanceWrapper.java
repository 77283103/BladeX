package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.SclEquipmentMaintenanceRequestVO;
import org.springblade.contract.vo.SclEquipmentMaintenanceResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.SclEquipmentMaintenanceEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 生产类：设备维修保养合同 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2020-12-11 10:56:47
 */
@Component
public class SclEquipmentMaintenanceWrapper implements IEntityWrapper<SclEquipmentMaintenanceEntity, SclEquipmentMaintenanceRequestVO, SclEquipmentMaintenanceResponseVO> {

	public static SclEquipmentMaintenanceWrapper build() {
		return new SclEquipmentMaintenanceWrapper();
 	}

    @Override
	public SclEquipmentMaintenanceEntity createEntity() {
		return new SclEquipmentMaintenanceEntity();
	}

	@Override
	public SclEquipmentMaintenanceRequestVO createQV() {
		return new SclEquipmentMaintenanceRequestVO();
	}

	@Override
	public SclEquipmentMaintenanceResponseVO createPV() {
		return new SclEquipmentMaintenanceResponseVO();
	}

    @Override
    public void selectUserName(SclEquipmentMaintenanceResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
