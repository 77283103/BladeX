package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.vo.ContractTemplateResponseVO;

/**
 * 范本管理 包装类,返回视图层所需的字段
 *
 * @author XHB
 * @date : 2020-09-24 13:57:38
 */
public class ContractTemplateWrapper extends BaseEntityWrapper<ContractTemplateEntity, ContractTemplateResponseVO>  {

	public static ContractTemplateWrapper build() {
		return new ContractTemplateWrapper();
 	}

	@Override
	public ContractTemplateResponseVO entityVO(ContractTemplateEntity template) {
		ContractTemplateResponseVO templateResponseVO = BeanUtil.copy(template, ContractTemplateResponseVO.class);

		//User createUser = UserCache.getUser(template.getCreateUser());
		//User updateUser = UserCache.getUser(template.getUpdateUser());
		//templateResponseVO.setCreateUserName(createUser.getName());
		//templateResponseVO.setUpdateUserName(updateUser.getName());

		return templateResponseVO;
	}

}
