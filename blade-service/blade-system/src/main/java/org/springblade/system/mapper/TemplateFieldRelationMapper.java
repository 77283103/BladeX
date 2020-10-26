package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.TemplateFieldRelationEntity;

/**
 * 关联表字段属性配置表 Mapper 接口
 *
 * @author szw
 * @date : 2020-10-23 17:24:42
 */
public interface TemplateFieldRelationMapper extends BaseMapper<TemplateFieldRelationEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param templateFieldRelation
	 * @return
	 */
	IPage<TemplateFieldRelationEntity> pageList(IPage<TemplateFieldRelationEntity> page, TemplateFieldRelationEntity templateFieldRelation);

}
