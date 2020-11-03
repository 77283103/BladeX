package org.springblade.cases.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.cases.entity.ContractCaseHandlingEntity;
import org.springblade.cases.vo.ContractCaseHandlingRequestVO;

/**
 * 案件处理 服务类
 *
 * @author xhb
 * @date : 2020-10-30 10:04:19
 */
public interface IContractCaseHandlingService extends BaseService<ContractCaseHandlingEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractCaseHandling
	 * @return
	 */
	IPage<ContractCaseHandlingEntity> pageList(IPage<ContractCaseHandlingEntity> page, ContractCaseHandlingRequestVO contractCaseHandling);
}
