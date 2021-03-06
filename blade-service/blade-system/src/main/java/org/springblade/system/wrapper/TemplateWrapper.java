package org.springblade.system.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.TemplateEntity;
import org.springblade.system.vo.TemplateResponseVO;

/**
 * 表单模板 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-10-19 20:00:56
 */
public class TemplateWrapper extends BaseEntityWrapper<TemplateEntity, TemplateResponseVO>  {

	public static TemplateWrapper build() {
		return new TemplateWrapper();
 	}

	@Override
	public TemplateResponseVO entityVO(TemplateEntity template) {
		TemplateResponseVO templateResponseVO = BeanUtil.copy(template, TemplateResponseVO.class);

		//User createUser = UserCache.getUser(template.getCreateUser());
		//User updateUser = UserCache.getUser(template.getUpdateUser());
		//templateResponseVO.setCreateUserName(createUser.getName());
		//templateResponseVO.setUpdateUserName(updateUser.getName());

		return templateResponseVO;
	}

}
