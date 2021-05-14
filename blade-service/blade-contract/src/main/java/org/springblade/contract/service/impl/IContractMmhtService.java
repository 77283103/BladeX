package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractMmhtEntity;
import org.springblade.core.mp.base.BaseService;

/**
 * 国内设备买卖合同 服务类
 *
 * @author kx
 * @date : 2021-05-10 13:39:26
 */
public interface IContractMmhtService extends BaseService<ContractMmhtEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractMmht
	 * @return
	 */
	IPage<ContractMmhtEntity> pageList(IPage<ContractMmhtEntity> page, ContractMmhtEntity contractMmht);
}
