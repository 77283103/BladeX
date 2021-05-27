package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractWbhtEntity;
import org.springblade.contract.mapper.ContractWbhtMapper;
import org.springblade.contract.service.IContractWbhtService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 消防-维保合同 服务实现类
 *
 * @author kx
 * @date : 2021-05-10 13:41:08
 */
@Service
public class ContractWbhtServiceImpl extends BaseServiceImpl<ContractWbhtMapper, ContractWbhtEntity> implements IContractWbhtService {

	@Override
	public IPage<ContractWbhtEntity> pageList(IPage<ContractWbhtEntity> page, ContractWbhtEntity contractWbht) {
		return baseMapper.pageList(page, contractWbht);
	}
}
