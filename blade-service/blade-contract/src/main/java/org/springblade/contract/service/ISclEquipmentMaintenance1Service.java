package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.SclEquipmentMaintenance1RequestVO;
import org.springblade.contract.vo.SclEquipmentMaintenance1ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.SclEquipmentMaintenance1Entity;

import java.util.List;

/**
 * 生产类：设备维修保养合同(关联表） 服务类
 *
 * @author kx
 * @date : 2020-12-11 10:59:51
 */
public interface ISclEquipmentMaintenance1Service extends BaseService<SclEquipmentMaintenance1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclEquipmentMaintenance1
	 * @return
	 */
	IPage<SclEquipmentMaintenance1Entity> pageList(IPage<SclEquipmentMaintenance1Entity> page, SclEquipmentMaintenance1RequestVO sclEquipmentMaintenance1);

	void saveBatchByRefId(Long refId, List<SclEquipmentMaintenance1ResponseVO> responseVOList);

	List<SclEquipmentMaintenance1ResponseVO> selectRefList(Long refId);
}
