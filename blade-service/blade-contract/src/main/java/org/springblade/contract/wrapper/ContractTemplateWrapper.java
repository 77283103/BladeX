package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractTemplateRequestVO;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;

import java.util.Optional;

/**
 * 范本管理 包装类,返回视图层所需的字段
 *
 * @author XHB
 * @date : 2020-09-24 13:57:38
 */
public class ContractTemplateWrapper  implements IEntityWrapper<ContractTemplateEntity, ContractTemplateRequestVO,ContractTemplateResponseVO> {

	public static ContractTemplateWrapper build() {
		return new ContractTemplateWrapper();
 	}

	@Override
	public ContractTemplateEntity createEntity() {
		return new  ContractTemplateEntity();
	}

	@Override
	public ContractTemplateRequestVO createQV() {
		return new ContractTemplateRequestVO();
	}

	@Override
	public ContractTemplateResponseVO createPV() {
		return new ContractTemplateResponseVO();
	}

	@Override
	public void selectUserName(ContractTemplateResponseVO responseVO) {
			responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
			responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
			responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
	}
}
