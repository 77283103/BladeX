package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.mapper.ContractTemplateMapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.service.IContractTemplateService;
import org.springframework.stereotype.Service;

/**
 * 范本管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-24 13:57:38
 */
@Service
public class ContractTemplateServiceImpl extends BaseServiceImpl<ContractTemplateMapper, ContractTemplateEntity> implements IContractTemplateService {

	@Override
	public IPage<ContractTemplateEntity> pageList(IPage<ContractTemplateEntity> page, ContractTemplateEntity template) {
		return baseMapper.pageList(page, template);
	}
}
