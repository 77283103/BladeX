package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractBondRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractBondEntity;

/**
 * 保证金 服务类
 *
 * @author szw
 * @date : 2020-11-04 18:28:11
 */
public interface IContractBondService extends BaseService<ContractBondEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<ContractBondEntity> pageList(IPage<ContractBondEntity> page, ContractBondRequestVO contractBond);
}
