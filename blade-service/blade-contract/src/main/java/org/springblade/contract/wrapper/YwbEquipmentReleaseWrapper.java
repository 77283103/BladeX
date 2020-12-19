package org.springblade.contract.wrapper;

import org.springblade.contract.entity.YwbEquipmentReleaseEntity;
import org.springblade.contract.vo.YwbEquipmentReleaseRequestVO;
import org.springblade.contract.vo.YwbEquipmentReleaseResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 业务类：19.设备投放使用协议 包装类,返回视图层所需的字段
 *
 * @author 业务类：19.设备投放使用协议
 * @date : 2020-12-18 16:07:48
 */
@Component
public class YwbEquipmentReleaseWrapper implements IEntityWrapper<YwbEquipmentReleaseEntity, YwbEquipmentReleaseRequestVO, YwbEquipmentReleaseResponseVO> {

	public static YwbEquipmentReleaseWrapper build() {
		return new YwbEquipmentReleaseWrapper();
 	}

    @Override
	public YwbEquipmentReleaseEntity createEntity() {
		return new YwbEquipmentReleaseEntity();
	}

	@Override
	public YwbEquipmentReleaseRequestVO createQV() {
		return new YwbEquipmentReleaseRequestVO();
	}

	@Override
	public YwbEquipmentReleaseResponseVO createPV() {
		return new YwbEquipmentReleaseResponseVO();
	}

    @Override
    public void selectUserName(YwbEquipmentReleaseResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
