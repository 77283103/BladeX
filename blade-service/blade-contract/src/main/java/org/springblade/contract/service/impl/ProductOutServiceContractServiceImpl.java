package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ProductOutServiceContractEntity;
import org.springblade.contract.mapper.ProductOutServiceContractMapper;
import org.springblade.contract.service.IProductOutServiceContractService;
import org.springframework.stereotype.Service;

/**
 * 生产项目外包服务合同 服务实现类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:20:07
 */
@Service
public class ProductOutServiceContractServiceImpl extends BaseServiceImpl<ProductOutServiceContractMapper, ProductOutServiceContractEntity> implements IProductOutServiceContractService {

	@Override
	public IPage<ProductOutServiceContractEntity> pageList(IPage<ProductOutServiceContractEntity> page, ProductOutServiceContractEntity productOutServiceContract) {
		return baseMapper.pageList(page, productOutServiceContract);
	}
}
