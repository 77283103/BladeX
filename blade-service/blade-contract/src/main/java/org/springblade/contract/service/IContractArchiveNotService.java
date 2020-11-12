package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import org.springblade.contract.vo.ContractArchiveNotRequestVO;

/**
 * 未归档原因 服务类
 *
 * @author 未归档原因
 * @date : 2020-11-09 15:19:18
 */
public interface IContractArchiveNotService extends BaseService<ContractArchiveNotEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractArchiveNot
	 * @return
	 */
	IPage<ContractArchiveNotEntity> pageList(IPage<ContractArchiveNotEntity> page, ContractArchiveNotRequestVO contractArchiveNot);
}
