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

	private ContractTemplateMapper templateMapper;
	@Override
	public IPage<ContractTemplateEntity> pageList(IPage<ContractTemplateEntity> page, ContractTemplateEntity template) {
		return baseMapper.pageList(page, template);
	}

	/**
	 * 废弃范本后修改范本状态
	 * @param status,id
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateTemplateStatus(String status, String  id) {
		return templateMapper.updateTemplateStatus(status, id);
	}

	/**
	 * 新增范本，规范生成范本编号
	 * @param entity
	 * @return
	 */
	@Override
	public boolean save(ContractTemplateEntity entity) {

		return super.save(entity);
	}

}
