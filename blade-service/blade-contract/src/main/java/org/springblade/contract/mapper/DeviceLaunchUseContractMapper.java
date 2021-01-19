package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.DeviceLaunchUseContractEntity;

/**
 * 设备投放使用协议 Mapper 接口
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:18:11
 */
public interface DeviceLaunchUseContractMapper extends BaseMapper<DeviceLaunchUseContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param deviceLaunchUseContract
	 * @return
	 */
	IPage<DeviceLaunchUseContractEntity> pageList(IPage<DeviceLaunchUseContractEntity> page, DeviceLaunchUseContractEntity deviceLaunchUseContract);

}
