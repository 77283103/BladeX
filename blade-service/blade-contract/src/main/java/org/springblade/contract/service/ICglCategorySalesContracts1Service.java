package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglCategorySalesContracts1RequestVO;
import org.springblade.contract.vo.CglCategorySalesContracts1ResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglCategorySalesContracts1Entity;

import java.util.List;

/**
 * 采购类：买卖合同（行销品） 服务类
 *
 * @author 采购类：买卖合同（行销品）
 * @date : 2020-12-10 18:58:22
 */
public interface ICglCategorySalesContracts1Service extends BaseService<CglCategorySalesContracts1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglCategorySalesContracts1
	 * @return
	 */
	IPage<CglCategorySalesContracts1Entity> pageList(IPage<CglCategorySalesContracts1Entity> page, CglCategorySalesContracts1RequestVO cglCategorySalesContracts1);

	void saveBatchByRefId(Long refId, List<CglCategorySalesContracts1ResponseVO> responseVOList);

	List<CglCategorySalesContracts1ResponseVO> selectRefList(Long refId);
}
