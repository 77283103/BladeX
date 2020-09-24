package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractAssessmentEntity;

/**
 * 合同评估表 服务类
 *
 * @author liyj
 * @date : 2020-09-23 23:28:31
 */
public interface IContractAssessmentService extends BaseService<ContractAssessmentEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param assessment
	 * @return
	 */
	IPage<ContractAssessmentEntity> pageList(IPage<ContractAssessmentEntity> page, ContractAssessmentEntity assessment);
}
