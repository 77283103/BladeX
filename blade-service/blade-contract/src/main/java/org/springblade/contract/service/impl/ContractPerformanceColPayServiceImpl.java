package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractPerformanceColPayRequestVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import org.springblade.contract.mapper.ContractPerformanceColPayMapper;
import org.springblade.contract.service.IContractPerformanceColPayService;
import org.springframework.stereotype.Service;

/**
 * 收付款计划清单-收付款 服务实现类
 *
 * @author szw
 * @date : 2020-11-05 17:07:02
 */
@Service
public class ContractPerformanceColPayServiceImpl extends BaseServiceImpl<ContractPerformanceColPayMapper, ContractPerformanceColPayEntity> implements IContractPerformanceColPayService {

	@Override
	public IPage<ContractPerformanceColPayEntity> pageList(IPage<ContractPerformanceColPayEntity> page, ContractPerformanceColPayRequestVO contractPerformanceColPay) {
		return baseMapper.pageList(page, contractPerformanceColPay);
	}
}
