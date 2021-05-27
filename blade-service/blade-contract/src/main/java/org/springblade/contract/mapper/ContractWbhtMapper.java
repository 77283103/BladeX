package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractWbhtEntity;

/**
 * 消防-维保合同 Mapper 接口
 *
 * @author kx
 * @date : 2021-05-10 13:41:05
 */
public interface ContractWbhtMapper extends BaseMapper<ContractWbhtEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractWbht
	 * @return
	 */
	IPage<ContractWbhtEntity> pageList(IPage<ContractWbhtEntity> page, ContractWbhtEntity contractWbht);

}
