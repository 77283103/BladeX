package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractSigningArchiveEntity;
import org.springblade.contract.vo.ContractSigningArchiveRequestVO;
import org.springblade.core.mp.base.BaseService;

import java.util.List;

/**
 * 合同签订表 服务类
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
public interface IContractSigningArchiveService extends BaseService<ContractSigningArchiveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param signing
	 * @return
	 */
	IPage<ContractSigningArchiveEntity> pageList(IPage<ContractSigningArchiveEntity> page, ContractSigningArchiveRequestVO signing);


	List<ContractSigningArchiveEntity> getByContractId(Long id);
}
