package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.mapper.ContractCounterpartMapper;
import org.springblade.contract.service.IContractCounterpartService;
import org.springframework.stereotype.Service;

/**
 * 相对方管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-23 19:35:06
 */
@Service
public class ContractCounterpartServiceImpl extends BaseServiceImpl<ContractCounterpartMapper, ContractCounterpartEntity> implements IContractCounterpartService {

	@Override
	public IPage<ContractCounterpartEntity> pageList(IPage<ContractCounterpartEntity> page, ContractCounterpartEntity counterpart) {
		return baseMapper.pageList(page, counterpart);
	}
}
