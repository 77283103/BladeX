package org.springblade.cases.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.cases.entity.ContractCaseHandlingEntity;
import org.springblade.cases.mapper.ContractCaseHandlingMapper;
import org.springblade.cases.service.IContractCaseHandlingService;
import org.springframework.stereotype.Service;
import org.springblade.cases.vo.ContractCaseHandlingRequestVO;

/**
 * 案件处理 服务实现类
 *
 * @author xhb
 * @date : 2020-10-30 10:04:20
 */
@Service
public class ContractCaseHandlingServiceImpl extends BaseServiceImpl<ContractCaseHandlingMapper, ContractCaseHandlingEntity> implements IContractCaseHandlingService {

	@Override
	public IPage<ContractCaseHandlingEntity> pageList(IPage<ContractCaseHandlingEntity> page, ContractCaseHandlingRequestVO contractCaseHandling) {
		return baseMapper.pageList(page, contractCaseHandling);
	}
}
