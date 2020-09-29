package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractChangeEntity;
import org.springblade.contract.mapper.ContractChangeMapper;
import org.springblade.contract.service.IContractChangeService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 合同变更 服务实现类
 *
 * @author szw
 * @date : 2020-09-23 19:24:50
 */
@Service
public class ContractChangeServiceImpl extends BaseServiceImpl<ContractChangeMapper, ContractChangeEntity> implements IContractChangeService {

	@Override
	public IPage<ContractChangeEntity> pageList(IPage<ContractChangeEntity> page, ContractChangeEntity change) {
		return baseMapper.pageList(page, change);
	}
}
