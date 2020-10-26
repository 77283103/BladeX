package org.springblade.system.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.TemplateRelationEntity;
import org.springblade.system.vo.TemplateRelationResponseVO;

/**
 * 关联表单 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-10-23 17:24:28
 */
public class TemplateRelationWrapper extends BaseEntityWrapper<TemplateRelationEntity, TemplateRelationResponseVO>  {

	public static TemplateRelationWrapper build() {
		return new TemplateRelationWrapper();
 	}

	@Override
	public TemplateRelationResponseVO entityVO(TemplateRelationEntity templateRelation) {
		TemplateRelationResponseVO templateRelationResponseVO = BeanUtil.copy(templateRelation, TemplateRelationResponseVO.class);
		return templateRelationResponseVO;
	}

}
