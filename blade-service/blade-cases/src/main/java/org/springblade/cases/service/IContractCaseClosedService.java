package org.springblade.cases.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.cases.entity.ContractCaseClosedEntity;
import org.springblade.cases.vo.ContractCaseClosedRequestVO;

/**
 * 案件结案 服务类
 *
 * @author xhb
 * @date : 2020-10-30 10:03:19
 */
public interface IContractCaseClosedService extends BaseService<ContractCaseClosedEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractCaseClosed
	 * @return
	 */
	IPage<ContractCaseClosedEntity> pageList(IPage<ContractCaseClosedEntity> page, ContractCaseClosedRequestVO contractCaseClosed);
}
