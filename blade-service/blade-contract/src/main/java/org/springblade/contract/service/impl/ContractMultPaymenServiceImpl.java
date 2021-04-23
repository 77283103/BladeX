package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractMultPaymenEntity;
import org.springblade.contract.mapper.ContractMultPaymenMapper;
import org.springblade.contract.service.IContractMultPaymenService;
import org.springframework.stereotype.Service;

/**
 * 多方相对方收付款 服务实现类
 *
 * @author xhb
 * @date : 2021-04-23 17:30:31
 */
@Service
public class ContractMultPaymenServiceImpl extends BaseServiceImpl<ContractMultPaymenMapper, ContractMultPaymenEntity> implements IContractMultPaymenService {

	@Override
	public IPage<ContractMultPaymenEntity> pageList(IPage<ContractMultPaymenEntity> page, ContractMultPaymenEntity contractMultPaymen) {
		return baseMapper.pageList(page, contractMultPaymen);
	}
}
