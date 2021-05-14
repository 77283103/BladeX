package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractMmhtxxpfEntity;
import org.springblade.contract.mapper.ContractMmhtxxpfMapper;
import org.springblade.contract.service.IContractMmhtxxpfService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 行销品买卖合同 服务实现类
 *
 * @author kx
 * @date : 2021-05-10 13:37:43
 */
@Service
public class ContractMmhtxxpfServiceImpl extends BaseServiceImpl<ContractMmhtxxpfMapper, ContractMmhtxxpfEntity> implements IContractMmhtxxpfService {

	@Override
	public IPage<ContractMmhtxxpfEntity> pageList(IPage<ContractMmhtxxpfEntity> page, ContractMmhtxxpfEntity contractMmhtxxpf) {
		return baseMapper.pageList(page, contractMmhtxxpf);
	}
}
