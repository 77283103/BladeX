package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.DeviceLaunchUseContractEntity;
import org.springblade.contract.mapper.DeviceLaunchUseContractMapper;
import org.springblade.contract.service.IDeviceLaunchUseContractService;
import org.springframework.stereotype.Service;

/**
 * 设备投放使用协议 服务实现类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:18:12
 */
@Service
public class DeviceLaunchUseContractServiceImpl extends BaseServiceImpl<DeviceLaunchUseContractMapper, DeviceLaunchUseContractEntity> implements IDeviceLaunchUseContractService {

	@Override
	public IPage<DeviceLaunchUseContractEntity> pageList(IPage<DeviceLaunchUseContractEntity> page, DeviceLaunchUseContractEntity deviceLaunchUseContract) {
		return baseMapper.pageList(page, deviceLaunchUseContract);
	}
}
