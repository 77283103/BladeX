package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglAdvertisementProductionEntity;

/**
 * 采购类：广告制作安装合同模板 服务类
 *
 * @author 采购类：广告制作安装合同模板
 * @date : 2021-01-11 15:05:51
 */
public interface ICglAdvertisementProductionService extends BaseService<CglAdvertisementProductionEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglAdvertisementProduction
	 * @return
	 */
	IPage<CglAdvertisementProductionEntity> pageList(IPage<CglAdvertisementProductionEntity> page, CglAdvertisementProductionEntity cglAdvertisementProduction);
}
