package org.springblade.contract.wrapper;

import org.springblade.contract.entity.DeviceLaunchUseContractEntity;
import org.springblade.contract.vo.DeviceLaunchUseContractRequestVO;
import org.springblade.contract.vo.DeviceLaunchUseContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 设备投放使用协议 包装类,返回视图层所需的字段
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:18:13
 */
@Component
public class DeviceLaunchUseContractWrapper implements IEntityWrapper<DeviceLaunchUseContractEntity, DeviceLaunchUseContractRequestVO, DeviceLaunchUseContractResponseVO> {

	public static DeviceLaunchUseContractWrapper build() {
		return new DeviceLaunchUseContractWrapper();
 	}

    @Override
	public DeviceLaunchUseContractEntity createEntity() {
		return new DeviceLaunchUseContractEntity();
	}

	@Override
	public DeviceLaunchUseContractRequestVO createQV() {
		return new DeviceLaunchUseContractRequestVO();
	}

	@Override
	public DeviceLaunchUseContractResponseVO createPV() {
		return new DeviceLaunchUseContractResponseVO();
	}

	@Override
	public void selectUserName(DeviceLaunchUseContractResponseVO responseVO) {

	}
}
