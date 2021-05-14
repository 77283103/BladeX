package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractMmhtEntity;
import org.springblade.contract.mapper.ContractMmhtMapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 国内设备买卖合同 服务实现类
 *
 * @author kx
 * @date : 2021-05-10 13:39:28
 */
@Service
public class ContractMmhtServiceImpl extends BaseServiceImpl<ContractMmhtMapper, ContractMmhtEntity> implements IContractMmhtService {

	@Override
	public IPage<ContractMmhtEntity> pageList(IPage<ContractMmhtEntity> page, ContractMmhtEntity contractMmht) {
		return baseMapper.pageList(page, contractMmht);
	}
}
