package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractDestructionRequestVO;
import org.springblade.contract.vo.ContractDestructionResponseVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractDestructionEntity;

/**
 * 合同销毁 服务类
 *
 * @author szw
 * @date : 2020-11-11 16:37:01
 */
public interface IContractDestructionService extends BaseService<ContractDestructionEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractDestruction
	 * @return
	 */
	IPage<ContractDestructionEntity> pageList(IPage<ContractDestructionEntity> page, ContractDestructionRequestVO contractDestruction);

	Boolean add(ContractDestructionResponseVO contractDestructionResponseVO);
}
