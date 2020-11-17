package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractBondRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractBondEntity;

import java.util.List;

/**
 * 保证金 服务类
 *
 * @author szw
 * @date : 2020-11-04 18:28:11
 */
public interface IContractBondService extends BaseService<ContractBondEntity> {

	/**
	 * 分页查询
	 * 一般超期
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<ContractBondEntity> pageList(IPage<ContractBondEntity> page, ContractBondRequestVO contractBond);

	/**
	 * 分页查询
	 * 严重超期
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<ContractBondEntity> pageListSerious(IPage<ContractBondEntity> page, ContractBondRequestVO contractBond);

	/**
	 * 分页查询
	 * 超长超期
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<ContractBondEntity> pageListLong(IPage<ContractBondEntity> page, ContractBondRequestVO contractBond);

	/**
	 * 保证金存方法
	 * @param ids 保证金id
	 * @param id 合同id
	 * @return
	 */
	void saveBond(List<Long> ids, Long id);
}
