package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclEquipmentMaintenanceEntity;
import org.springblade.contract.vo.SclEquipmentMaintenanceRequestVO;

/**
 * 生产类：设备维修保养合同 Mapper 接口
 *
 * @author kx
 * @date : 2020-12-11 10:56:42
 */
public interface SclEquipmentMaintenanceMapper extends BaseMapper<SclEquipmentMaintenanceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclEquipmentMaintenance
	 * @return
	 */
	IPage<SclEquipmentMaintenanceEntity> pageList(IPage<SclEquipmentMaintenanceEntity> page, SclEquipmentMaintenanceRequestVO sclEquipmentMaintenance);

}
