package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractRelieveEntity;
import org.springblade.contract.mapper.ContractRelieveMapper;
import org.springblade.contract.service.IContractRelieveService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
@Service
public class ContractRelieveServiceImpl extends BaseServiceImpl<ContractRelieveMapper, ContractRelieveEntity> implements IContractRelieveService {

	@Override
	public IPage<ContractRelieveEntity> pageList(IPage<ContractRelieveEntity> page, ContractRelieveEntity relieve) {
		return baseMapper.pageList(page, relieve);
	}
}
