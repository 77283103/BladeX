package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractPerformanceRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.mapper.ContractPerformanceMapper;
import org.springblade.contract.service.IContractPerformanceService;
import org.springframework.stereotype.Service;

/**
 * 接收/提供服务计划清单 服务实现类
 *
 * @author szw
 * @date : 2020-11-05 17:06:56
 */
@Service
public class ContractPerformanceServiceImpl extends BaseServiceImpl<ContractPerformanceMapper, ContractPerformanceEntity> implements IContractPerformanceService {

	@Override
	public IPage<ContractPerformanceEntity> pageList(IPage<ContractPerformanceEntity> page, ContractPerformanceRequestVO contractPerformance) {
		return baseMapper.pageList(page, contractPerformance);
	}
}
