package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.TemplateEntity;
import org.springblade.contract.vo.TemplateResponseVO;

/**
 * 范本管理 包装类,返回视图层所需的字段
 *
 * @author XHB
 * @date : 2020-09-24 13:57:38
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
