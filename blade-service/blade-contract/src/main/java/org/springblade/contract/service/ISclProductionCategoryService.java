package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.SclProductionCategoryEntity;
import org.springblade.contract.vo.SclProductionCategoryRequestVO;
import org.springblade.core.mp.base.BaseService;

/**
 * 生产类：物流服务合同（一段+调拨运输） 服务类
 *
 * @author kx
 * @date : 2020-12-18 17:18:04
 */
public interface ISclProductionCategoryService extends BaseService<SclProductionCategoryEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param sclProductionCategory
	 * @return
	 */
	IPage<SclProductionCategoryEntity> pageList(IPage<SclProductionCategoryEntity> page, SclProductionCategoryRequestVO sclProductionCategory);
}
