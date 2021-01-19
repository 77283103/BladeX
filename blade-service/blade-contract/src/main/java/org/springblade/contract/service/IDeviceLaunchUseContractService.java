package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.DeviceLaunchUseContractEntity;

/**
 * 设备投放使用协议 服务类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:18:12
 */
public interface IDeviceLaunchUseContractService extends BaseService<DeviceLaunchUseContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param deviceLaunchUseContract
	 * @return
	 */
	IPage<DeviceLaunchUseContractEntity> pageList(IPage<DeviceLaunchUseContractEntity> page, DeviceLaunchUseContractEntity deviceLaunchUseContract);
}
