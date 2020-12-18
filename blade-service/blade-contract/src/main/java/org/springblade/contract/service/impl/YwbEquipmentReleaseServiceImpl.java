package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbEquipmentReleaseEntity;
import org.springblade.contract.mapper.YwbEquipmentReleaseMapper;
import org.springblade.contract.service.IYwbEquipmentReleaseService;
import org.springblade.contract.vo.YwbEquipmentReleaseRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 业务类：19.设备投放使用协议 服务实现类
 *
 * @author 业务类：19.设备投放使用协议
 * @date : 2020-12-18 16:07:47
 */
@Service
public class YwbEquipmentReleaseServiceImpl extends BaseServiceImpl<YwbEquipmentReleaseMapper, YwbEquipmentReleaseEntity> implements IYwbEquipmentReleaseService {

	@Override
	public IPage<YwbEquipmentReleaseEntity> pageList(IPage<YwbEquipmentReleaseEntity> page, YwbEquipmentReleaseRequestVO ywbEquipmentRelease) {
		return baseMapper.pageList(page, ywbEquipmentRelease);
	}
}
