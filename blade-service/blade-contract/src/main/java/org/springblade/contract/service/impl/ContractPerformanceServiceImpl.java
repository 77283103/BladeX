package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.mapper.ContractPerformanceMapper;
import org.springblade.contract.service.IContractPerformanceService;
import org.springframework.stereotype.Service;

/**
 * 合同履约计划 服务实现类
 *
 * @author liyj
 * @date : 2020-09-23 19:26:28
 */
@Service
public class ContractPerformanceServiceImpl extends BaseServiceImpl<ContractPerformanceMapper, ContractPerformanceEntity> implements IContractPerformanceService {

	@Override
	public IPage<ContractPerformanceEntity> pageList(IPage<ContractPerformanceEntity> page, ContractPerformanceEntity performance) {
		return baseMapper.pageList(page, performance);
	}
}
