package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.vo.ContractBondRequestVO;

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

}
