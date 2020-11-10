package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import org.springblade.contract.vo.ContractSealUsingInfoRequestVO;

/**
 * 合同用印 服务类
 *
 * @author 合同用印
 * @date : 2020-11-05 09:29:26
 */
public interface IContractSealUsingInfoService extends BaseService<ContractSealUsingInfoEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractSealUsingInfo
	 * @return
	 */
	IPage<ContractSealUsingInfoEntity> pageList(IPage<ContractSealUsingInfoEntity> page, ContractSealUsingInfoRequestVO contractSealUsingInfo);
}
