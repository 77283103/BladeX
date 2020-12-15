package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclEquipmentMaintenance1Entity;
import org.springblade.contract.vo.SclEquipmentMaintenance1RequestVO;

/**
 * 生产类：设备维修保养合同(关联表） Mapper 接口
 *
 * @author kx
 * @date : 2020-12-11 10:59:50
 */
public interface SclEquipmentMaintenance1Mapper extends BaseMapper<SclEquipmentMaintenance1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclEquipmentMaintenance1
	 * @return
	 */
	IPage<SclEquipmentMaintenance1Entity> pageList(IPage<SclEquipmentMaintenance1Entity> page, SclEquipmentMaintenance1RequestVO sclEquipmentMaintenance1);

}
