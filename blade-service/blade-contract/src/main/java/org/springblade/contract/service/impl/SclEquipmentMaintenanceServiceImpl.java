package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.SclEquipmentMaintenanceRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.SclEquipmentMaintenanceEntity;
import org.springblade.contract.mapper.SclEquipmentMaintenanceMapper;
import org.springblade.contract.service.ISclEquipmentMaintenanceService;
import org.springframework.stereotype.Service;

/**
 * 生产类：设备维修保养合同 服务实现类
 *
 * @author kx
 * @date : 2020-12-11 10:56:45
 */
@Service
public class SclEquipmentMaintenanceServiceImpl extends BaseServiceImpl<SclEquipmentMaintenanceMapper, SclEquipmentMaintenanceEntity> implements ISclEquipmentMaintenanceService {

	@Override
	public IPage<SclEquipmentMaintenanceEntity> pageList(IPage<SclEquipmentMaintenanceEntity> page, SclEquipmentMaintenanceRequestVO sclEquipmentMaintenance) {
		return baseMapper.pageList(page, sclEquipmentMaintenance);
	}
}
