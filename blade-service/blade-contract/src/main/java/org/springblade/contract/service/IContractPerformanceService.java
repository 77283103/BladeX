package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractPerformanceEntity;

/**
 * 合同履约计划 服务类
 *
 * @author liyj
 * @date : 2020-09-23 19:26:28
 */
public interface IContractPerformanceService extends BaseService<ContractPerformanceEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param performance
	 * @return
	 */
	IPage<ContractPerformanceEntity> pageList(IPage<ContractPerformanceEntity> page, ContractPerformanceEntity performance);
}
