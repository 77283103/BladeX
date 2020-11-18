package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractBondPlanEntity;
import org.springblade.contract.vo.ContractBondPlanRequestVO;
import org.springblade.contract.vo.ContractBondRequestVO;

import java.util.List;

/**
 * 保证金 Mapper 接口
 *
 * @author szw
 * @date : 2020-11-04 18:28:10
 */
public interface ContractBondPlanMapper extends BaseMapper<ContractBondPlanEntity> {

	/**
	 * 分页查询
	 * 一般超期
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<ContractBondPlanEntity> pageList(IPage<ContractBondPlanEntity> page, ContractBondPlanRequestVO contractBond);

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
	 * 保存合同保证金关联表
	 * @param  ids 保证金id
	 * @param id 合同id
	 */
	void saveBond(List<Long> ids, Long id);


	/**
	 * 删除合同相保证金关联
	 * @param id 合同id
	 */
	void deleteBond(Long id);

	/**
	 * 根据合同id查询保证金
	 *
	 * @param id
	 * @return
	 */
	List<ContractBondPlanEntity> selectByIds(Long id);

}
