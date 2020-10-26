package org.springblade.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.system.entity.TemplateRelationEntity;
import org.springblade.system.mapper.TemplateRelationMapper;
import org.springblade.system.service.ITemplateRelationService;
import org.springframework.stereotype.Service;

/**
 * 关联表单 服务实现类
 *
 * @author szw
 * @date : 2020-10-23 17:24:28
 */
@Service
public class TemplateRelationServiceImpl extends BaseServiceImpl<TemplateRelationMapper, TemplateRelationEntity> implements ITemplateRelationService {

	@Override
	public IPage<TemplateRelationEntity> pageList(IPage<TemplateRelationEntity> page, TemplateRelationEntity templateRelation) {
		return baseMapper.pageList(page, templateRelation);
	}
}
