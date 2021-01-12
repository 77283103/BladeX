package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.service.IYwbBusContracteTemplateService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.YwbBusinessContractTemplateEntity;
import org.springblade.contract.mapper.YwbBusContractTemplateMapper;
import org.springframework.stereotype.Service;

/**
 * 业务类：15.房屋租赁合同模板 服务实现类
 *
 * @author 王策
 * @date : 2021-01-12 17:30:30
 */
@Service
public class YwbBusContracteTemplateServiceImpl extends BaseServiceImpl<YwbBusContractTemplateMapper, YwbBusinessContractTemplateEntity> implements IYwbBusContracteTemplateService {

	@Override
	public IPage<YwbBusinessContractTemplateEntity> pageList(IPage<YwbBusinessContractTemplateEntity> page, YwbBusinessContractTemplateEntity ywbBusinessContractTemplate) {
		return baseMapper.pageList(page, ywbBusinessContractTemplate);
	}
}
