package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ProductOutServiceContractEntity;

/**
 * 生产项目外包服务合同 服务类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:20:06
 */
public interface IProductOutServiceContractService extends BaseService<ProductOutServiceContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param productOutServiceContract
	 * @return
	 */
	IPage<ProductOutServiceContractEntity> pageList(IPage<ProductOutServiceContractEntity> page, ProductOutServiceContractEntity productOutServiceContract);
}
