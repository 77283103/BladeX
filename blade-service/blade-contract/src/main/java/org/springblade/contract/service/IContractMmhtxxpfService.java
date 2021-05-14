package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractMmhtxxpfEntity;
import org.springblade.core.mp.base.BaseService;

/**
 * 行销品买卖合同 服务类
 *
 * @author kx
 * @date : 2021-05-10 13:37:42
 */
public interface IContractMmhtxxpfService extends BaseService<ContractMmhtxxpfEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractMmhtxxpf
	 * @return
	 */
	IPage<ContractMmhtxxpfEntity> pageList(IPage<ContractMmhtxxpfEntity> page, ContractMmhtxxpfEntity contractMmhtxxpf);
}
