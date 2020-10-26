package org.springblade.system.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.vo.TemplateFieldResponseVO;

/**
 *  包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-10-20 14:45:05
 */
public class TemplateFieldWrapper extends BaseEntityWrapper<TemplateFieldEntity, TemplateFieldResponseVO>  {

	public static TemplateFieldWrapper build() {
		return new TemplateFieldWrapper();
 	}

	@Override
	public TemplateFieldResponseVO entityVO(TemplateFieldEntity TemplateField) {
		TemplateFieldResponseVO TemplateFieldResponseVO = BeanUtil.copy(TemplateField, TemplateFieldResponseVO.class);

		//User createUser = UserCache.getUser(TemplateField.getCreateUser());
		//User updateUser = UserCache.getUser(TemplateField.getUpdateUser());
		//TemplateFieldResponseVO.setCreateUserName(createUser.getName());
		//TemplateFieldResponseVO.setUpdateUserName(updateUser.getName());

		return TemplateFieldResponseVO;
	}

}
