package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractMmhtxxpf1Entity;

/**
 * 行销品买卖合同子表 Mapper 接口
 *
 * @author kx
 * @date : 2021-05-10 13:36:59
 */
public interface ContractMmhtxxpf1Mapper extends BaseMapper<ContractMmhtxxpf1Entity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractMmhtxxpf1
	 * @return
	 */
	IPage<ContractMmhtxxpf1Entity> pageList(IPage<ContractMmhtxxpf1Entity> page, ContractMmhtxxpf1Entity contractMmhtxxpf1);

}
