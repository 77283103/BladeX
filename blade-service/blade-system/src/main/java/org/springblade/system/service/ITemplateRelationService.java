package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.system.entity.TemplateRelationEntity;

/**
 * 关联表单 服务类
 *
 * @author szw
 * @date : 2020-10-23 17:24:28
 */
public interface ITemplateRelationService extends BaseService<TemplateRelationEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param templateRelation
	 * @return
	 */
	IPage<TemplateRelationEntity> pageList(IPage<TemplateRelationEntity> page, TemplateRelationEntity templateRelation);
}
