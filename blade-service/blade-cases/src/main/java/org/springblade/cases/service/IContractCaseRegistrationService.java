package org.springblade.cases.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.cases.vo.ContractCaseRegistrationResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.cases.entity.ContractCaseRegistrationEntity;
import org.springblade.cases.vo.ContractCaseRegistrationRequestVO;

/**
 * 案件登记表 服务类
 *
 * @author xhb
 * @date : 2020-10-30 10:04:56
 */
public interface IContractCaseRegistrationService extends BaseService<ContractCaseRegistrationEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param contractCaseRegistration
	 * @return
	 */
	IPage<ContractCaseRegistrationEntity> pageList(IPage<ContractCaseRegistrationEntity> page, ContractCaseRegistrationRequestVO contractCaseRegistration);

	/**
	 * 案件
	 * @param id
	 * @return
	 */
	ContractCaseRegistrationResponseVO getById(Long id);
	/**
	 * 修改案件信息状态
	 *
	 * @param id
	 * @param caseStatus
	 */
	void updateCaseStatusById(Long id, String caseStatus);
}
