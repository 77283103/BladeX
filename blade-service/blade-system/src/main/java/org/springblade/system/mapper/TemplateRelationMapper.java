package org.springblade.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.system.entity.TemplateRelationEntity;

/**
 * 关联表单 Mapper 接口
 *
 * @author szw
 * @date : 2020-10-23 17:24:27
 */
public interface TemplateRelationMapper extends BaseMapper<TemplateRelationEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param templateRelation
	 * @return
	 */
	IPage<TemplateRelationEntity> pageList(IPage<TemplateRelationEntity> page, TemplateRelationEntity templateRelation);

}
