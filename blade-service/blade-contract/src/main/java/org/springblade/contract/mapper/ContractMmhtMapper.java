package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractMmhtEntity;

/**
 * 国内设备买卖合同 Mapper 接口
 *
 * @author kx
 * @date : 2021-05-10 13:39:23
 */
public interface ContractMmhtMapper extends BaseMapper<ContractMmhtEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractMmht
	 * @return
	 */
	IPage<ContractMmhtEntity> pageList(IPage<ContractMmhtEntity> page, ContractMmhtEntity contractMmht);

}
