package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglCategorySalesContractsRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.CglCategorySalesContractsEntity;
import org.springblade.contract.mapper.CglCategorySalesContractsMapper;
import org.springblade.contract.service.ICglCategorySalesContractsService;
import org.springframework.stereotype.Service;

/**
 * 采购类：买卖合同（行销品） 服务实现类
 *
 * @author 王策
 * @date : 2020-12-10 18:52:46
 */
@Service
public class CglCategorySalesContractsServiceImpl extends BaseServiceImpl<CglCategorySalesContractsMapper, CglCategorySalesContractsEntity> implements ICglCategorySalesContractsService {

	@Override
	public IPage<CglCategorySalesContractsEntity> pageList(IPage<CglCategorySalesContractsEntity> page, CglCategorySalesContractsRequestVO cglCategorySalesContracts) {
		return baseMapper.pageList(page, cglCategorySalesContracts);
	}
}
