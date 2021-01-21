package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.BusServiceContractEntity;
import org.springblade.contract.mapper.BusServiceContractMapper;
import org.springblade.contract.service.IBusServiceContractService;
import org.springframework.stereotype.Service;

/**
 * 班车服务合同 服务实现类
 *
 * @author Wang Pengfei
 * @date : 2021-01-19 10:25:18
 */
@Service
public class BusServiceContractServiceImpl extends BaseServiceImpl<BusServiceContractMapper, BusServiceContractEntity> implements IBusServiceContractService {

	@Override
	public IPage<BusServiceContractEntity> pageList(IPage<BusServiceContractEntity> page, BusServiceContractEntity busServiceContract) {
		return baseMapper.pageList(page, busServiceContract);
	}
}
