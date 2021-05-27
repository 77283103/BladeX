package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractWbhtEntity;
import org.springblade.core.mp.base.BaseService;

/**
 * 消防-维保合同 服务类
 *
 * @author kx
 * @date : 2021-05-10 13:41:07
 */
public interface IContractWbhtService extends BaseService<ContractWbhtEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractWbht
	 * @return
	 */
	IPage<ContractWbhtEntity> pageList(IPage<ContractWbhtEntity> page, ContractWbhtEntity contractWbht);
}
