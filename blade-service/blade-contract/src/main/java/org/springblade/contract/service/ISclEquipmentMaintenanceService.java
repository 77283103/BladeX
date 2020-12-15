package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.SclEquipmentMaintenanceRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.SclEquipmentMaintenanceEntity;

/**
 * 生产类：设备维修保养合同 服务类
 *
 * @author kx
 * @date : 2020-12-11 10:56:44
 */
public interface ISclEquipmentMaintenanceService extends BaseService<SclEquipmentMaintenanceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclEquipmentMaintenance
	 * @return
	 */
	IPage<SclEquipmentMaintenanceEntity> pageList(IPage<SclEquipmentMaintenanceEntity> page, SclEquipmentMaintenanceRequestVO sclEquipmentMaintenance);
}
