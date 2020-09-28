package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractSigningEntity;
import org.springblade.contract.entity.SealInfoEntity;

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

}
