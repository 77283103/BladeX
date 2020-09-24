package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.TemplateEntity;
import org.springblade.contract.mapper.TemplateMapper;
import org.springblade.contract.service.ITemplateService;
import org.springframework.stereotype.Service;

/**
 * 范本管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-24 13:57:38
 */
@Service
public class TemplateServiceImpl extends BaseServiceImpl<TemplateMapper, TemplateEntity> implements ITemplateService {

	@Override
	public IPage<TemplateEntity> pageList(IPage<TemplateEntity> page, TemplateEntity template) {
		return baseMapper.pageList(page, template);
	}
}
