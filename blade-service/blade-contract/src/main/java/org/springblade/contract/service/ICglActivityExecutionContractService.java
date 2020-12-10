package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.CglActivityExecutionContractRequestVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.CglActivityExecutionContractEntity;

/**
 * 采购类：活动执行合同 服务类
 *
 * @author 采购类：活动执行合同
 * @date : 2020-12-10 18:29:51
 */
public interface ICglActivityExecutionContractService extends BaseService<CglActivityExecutionContractEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param cglActivityExecutionContract
	 * @return
	 */
	IPage<CglActivityExecutionContractEntity> pageList(IPage<CglActivityExecutionContractEntity> page, CglActivityExecutionContractRequestVO cglActivityExecutionContract);
}
