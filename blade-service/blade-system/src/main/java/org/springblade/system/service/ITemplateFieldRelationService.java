package org.springblade.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.core.mp.base.BaseService;
import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.entity.TemplateFieldRelationEntity;
import org.springblade.system.entity.TemplateRelationEntity;

import java.util.List;

/**
 * 关联表字段属性配置表 服务类
 *
 * @author szw
 * @date : 2020-10-23 17:24:43
 */
public interface ITemplateFieldRelationService extends BaseService<TemplateFieldRelationEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param templateFieldRelation
	 * @return
	 */
	IPage<TemplateFieldRelationEntity> pageList(IPage<TemplateFieldRelationEntity> page, TemplateFieldRelationEntity templateFieldRelation);

	/**
	 * 配置
	 * @param list
	 * @param bean
	 * @return
	 */
	List<TemplateFieldRelationEntity> selectField(List<TemplateFieldRelationEntity> list, TemplateRelationEntity bean);
}
