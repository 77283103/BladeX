package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractBondPlanEntity;
import org.springblade.contract.vo.ContractBondPlanRequestVO;
import org.springblade.contract.vo.ContractBondPlanResponseVO;
import org.springblade.contract.vo.ContractBondRequestVO;
import org.springblade.contract.vo.ContractBondResponseVO;
import org.springblade.core.mp.base.BaseService;

import java.util.Date;
import java.util.List;

/**
 * 保证金 服务类
 *
 * @author szw
 * @date : 2020-11-04 18:28:11
 */
public interface IContractBondPlanService extends BaseService<ContractBondPlanEntity> {

	/**
	 * 分页查询
	 * 一般超期
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<ContractBondPlanResponseVO> pageList(IPage<ContractBondPlanEntity> page, ContractBondPlanRequestVO contractBond);

	/**
	 * 分页查询
	 * 严重超期
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<ContractBondPlanEntity> pageListSerious(IPage<ContractBondPlanEntity> page, ContractBondPlanRequestVO contractBond);

	/**
	 * 分页查询
	 * 超长超期
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<ContractBondPlanEntity> pageListLong(IPage<ContractBondPlanEntity> page, ContractBondPlanRequestVO contractBond);

	/**
	 * 保证金存方法
	 * @param ids 保证金id
	 * @param id 合同id
	 * @return
	 */
	void saveBond(List<Long> ids, Long id);
}
