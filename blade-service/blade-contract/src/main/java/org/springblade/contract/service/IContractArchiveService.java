package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractArchiveEntity;

/**
 * 合同归档管理 服务类
 *
 * @author XHB
 * @date : 2020-09-23 18:32:14
 */
public interface IContractArchiveService extends BaseService<ContractArchiveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param archive
	 * @return
	 */
	IPage<ContractArchiveEntity> pageList(IPage<ContractArchiveEntity> page, ContractArchiveEntity archive);
}
