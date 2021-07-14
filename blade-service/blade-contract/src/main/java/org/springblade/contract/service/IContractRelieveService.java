package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractRelieveEntity;
import org.springblade.contract.vo.ContractRelieveRequestVO;
import org.springblade.core.tool.api.R;

/**
 * 合同解除 服务类
 *
 * @author 合同解除
 * @date : 2020-11-05 09:24:01
 */
public interface IContractRelieveService extends BaseService<ContractRelieveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractRelieve
	 * @return
	 */
	IPage<ContractRelieveEntity> pageList(IPage<ContractRelieveEntity> page, ContractRelieveRequestVO contractRelieve);

	/**
	 * 提交送审
	 * @param contractId
	 * @return
	 */
	R submit(Long contractId);

}
