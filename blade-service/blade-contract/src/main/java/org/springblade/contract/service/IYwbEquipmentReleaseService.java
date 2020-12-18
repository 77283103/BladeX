package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.YwbEquipmentReleaseEntity;
import org.springblade.contract.vo.YwbEquipmentReleaseRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 业务类：19.设备投放使用协议 服务类
 *
 * @author 业务类：19.设备投放使用协议
 * @date : 2020-12-18 16:07:47
 */
public interface IYwbEquipmentReleaseService extends BaseService<YwbEquipmentReleaseEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param ywbEquipmentRelease
	 * @return
	 */
	IPage<YwbEquipmentReleaseEntity> pageList(IPage<YwbEquipmentReleaseEntity> page, YwbEquipmentReleaseRequestVO ywbEquipmentRelease);
}
