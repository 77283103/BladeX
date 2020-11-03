package org.springblade.cases.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.cases.entity.ContractCaseRegistrationEntity;
import org.springblade.cases.mapper.ContractCaseRegistrationMapper;
import org.springblade.cases.service.IContractCaseRegistrationService;
import org.springframework.stereotype.Service;
import org.springblade.cases.vo.ContractCaseRegistrationRequestVO;

/**
 * 案件登记表 服务实现类
 *
 * @author xhb
 * @date : 2020-10-30 10:04:57
 */
@Service
public class ContractCaseRegistrationServiceImpl extends BaseServiceImpl<ContractCaseRegistrationMapper, ContractCaseRegistrationEntity> implements IContractCaseRegistrationService {

	@Override
	public IPage<ContractCaseRegistrationEntity> pageList(IPage<ContractCaseRegistrationEntity> page, ContractCaseRegistrationRequestVO contractCaseRegistration) {
		return baseMapper.pageList(page, contractCaseRegistration);
	}
}
