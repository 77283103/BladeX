package org.springblade.system.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.TemplateFieldRelationEntity;
import org.springblade.system.vo.TemplateFieldRelationResponseVO;

/**
 * 关联表字段属性配置表 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-10-23 17:24:44
 */
public class TemplateFieldRelationWrapper extends BaseEntityWrapper<TemplateFieldRelationEntity, TemplateFieldRelationResponseVO> {

	public static TemplateFieldRelationWrapper build() {
		return new TemplateFieldRelationWrapper();
 	}

	@Override
	public TemplateFieldRelationResponseVO entityVO(TemplateFieldRelationEntity templateFieldRelation) {
		TemplateFieldRelationResponseVO templateFieldRelationResponseVO = BeanUtil.copy(templateFieldRelation, TemplateFieldRelationResponseVO.class);
		return templateFieldRelationResponseVO;
	}

}
