package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractRelieveEntity;

/**
 *  服务类
 *
 * @author szw
 * @date : 2020-09-23 20:10:29
 */
public interface IContractRelieveService extends BaseService<ContractRelieveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param relieve
	 * @return
	 */
	IPage<ContractRelieveEntity> pageList(IPage<ContractRelieveEntity> page, ContractRelieveEntity relieve);
}
