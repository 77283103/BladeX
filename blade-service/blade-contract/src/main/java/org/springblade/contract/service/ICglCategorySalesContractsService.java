package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglCategorySalesContractsRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglCategorySalesContractsEntity;

/**
 * 采购类：买卖合同（行销品） 服务类
 *
 * @author 王策
 * @date : 2020-12-10 18:52:46
 */
public interface ICglCategorySalesContractsService extends BaseService<CglCategorySalesContractsEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglCategorySalesContracts
	 * @return
	 */
	IPage<CglCategorySalesContractsEntity> pageList(IPage<CglCategorySalesContractsEntity> page, CglCategorySalesContractsRequestVO cglCategorySalesContracts);
}
