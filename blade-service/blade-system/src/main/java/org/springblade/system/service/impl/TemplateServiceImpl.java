package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.mapper.TemplateMapper;
import org.springblade.system.service.ITemplateService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * 表单模板 服务实现类
 *
 * @author szw
 * @date : 2020-10-19 20:00:56
 */
@Service
@Validated
@AllArgsConstructor
public class TemplateServiceImpl extends BaseServiceImpl<TemplateMapper, TemplateEntity> implements ITemplateService {

	@Override
	public IPage<TemplateEntity> pageList(IPage<TemplateEntity> page, TemplateEntity template) {
		return baseMapper.pageList(page, template);
	}
}
