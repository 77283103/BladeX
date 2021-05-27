package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractWbht1Entity;

/**
 * 消防-维保合同子表 Mapper 接口
 *
 * @author kx
 * @date : 2021-05-10 13:40:19
 */
public interface ContractWbht1Mapper extends BaseMapper<ContractWbht1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractWbht1
	 * @return
	 */
	IPage<ContractWbht1Entity> pageList(IPage<ContractWbht1Entity> page, ContractWbht1Entity contractWbht1);

}
