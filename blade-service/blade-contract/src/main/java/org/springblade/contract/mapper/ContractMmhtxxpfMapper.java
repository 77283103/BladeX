package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractMmhtxxpfEntity;

/**
 * 行销品买卖合同 Mapper 接口
 *
 * @author kx
 * @date : 2021-05-10 13:37:40
 */
public interface ContractMmhtxxpfMapper extends BaseMapper<ContractMmhtxxpfEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractMmhtxxpf
	 * @return
	 */
	IPage<ContractMmhtxxpfEntity> pageList(IPage<ContractMmhtxxpfEntity> page, ContractMmhtxxpfEntity contractMmhtxxpf);

}
