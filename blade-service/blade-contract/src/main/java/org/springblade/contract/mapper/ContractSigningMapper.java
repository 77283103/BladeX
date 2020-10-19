package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractSigningEntity;

/**
 * 合同签订表 Mapper 接口
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
public interface ContractSigningMapper extends BaseMapper<ContractSigningEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param signing
	 * @retur
	 */
	IPage<ContractSigningEntity> pageList(IPage<ContractSigningEntity> page, ContractSigningEntity signing);

	/**
	 * 根据合同id查询签订信息
	 * @param id
	 * @return
	 */
	ContractSigningEntity selectById(Long id);
}
