package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglProofingContractEntity;

/**
 * 采购类_打样合同书 服务类
 *
 * @author 采购类_打样合同书
 * @date : 2021-01-12 13:24:35
 */
public interface ICglProofingContractService extends BaseService<CglProofingContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglProofingContract
	 * @return
	 */
	IPage<CglProofingContractEntity> pageList(IPage<CglProofingContractEntity> page, CglProofingContractEntity cglProofingContract);
}
