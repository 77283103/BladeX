package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.vo.ContractAccordingResponseVO;

/**
 * 合同依据管理 包装类,返回视图层所需的字段
 *
 * @author XHB
 * @date : 2020-09-24 14:20:33
 */
public class ContractAccordingWrapper extends BaseEntityWrapper<ContractAccordingEntity, ContractAccordingResponseVO>  {

	public static ContractAccordingWrapper build() {
		return new ContractAccordingWrapper();
 	}

	@Override
	public ContractAccordingResponseVO entityVO(ContractAccordingEntity according) {
		ContractAccordingResponseVO accordingResponseVO = BeanUtil.copy(according, ContractAccordingResponseVO.class);

		//User createUser = UserCache.getUser(according.getCreateUser());
		//User updateUser = UserCache.getUser(according.getUpdateUser());
		//accordingResponseVO.setCreateUserName(createUser.getName());
		//accordingResponseVO.setUpdateUserName(updateUser.getName());

		return accordingResponseVO;
	}

}
