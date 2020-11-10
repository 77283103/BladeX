package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.vo.ContractBondRequestVO;

import java.util.List;

/**
 * 保证金 Mapper 接口
 *
 * @author szw
 * @date : 2020-11-04 18:28:10
 */
public interface ContractBondMapper extends BaseMapper<ContractBondEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractBond
	 * @return
	 */
	IPage<ContractBondEntity> pageList(IPage<ContractBondEntity> page, ContractBondRequestVO contractBond);


	/**
	 * 保存合同保证金关联表
	 * @param  ids 保证金id
	 * @param id 合同id
	 */
	void saveBond(List<Long> ids,Long  id);


	/**
	 * 删除合同相保证金关联
	 * @param id 合同id
	 */
	void deleteBond(Long  id);

	/**
	 * 根据合同id查询保证金
	 *
	 * @param id
	 * @return
	 */
	List<ContractBondEntity> selectByIds(Long id);

}
