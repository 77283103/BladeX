package org.springblade.cases.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.cases.entity.ContractCaseClosedEntity;
import org.springblade.cases.mapper.ContractCaseClosedMapper;
import org.springblade.cases.service.IContractCaseClosedService;
import org.springframework.stereotype.Service;
import org.springblade.cases.vo.ContractCaseClosedRequestVO;

/**
 * 案件结案 服务实现类
 *
 * @author xhb
 * @date : 2020-10-30 10:03:21
 */
@Service
public class ContractCaseClosedServiceImpl extends BaseServiceImpl<ContractCaseClosedMapper, ContractCaseClosedEntity> implements IContractCaseClosedService {

	@Override
	public IPage<ContractCaseClosedEntity> pageList(IPage<ContractCaseClosedEntity> page, ContractCaseClosedRequestVO contractCaseClosed) {
		return baseMapper.pageList(page, contractCaseClosed);
	}
}
