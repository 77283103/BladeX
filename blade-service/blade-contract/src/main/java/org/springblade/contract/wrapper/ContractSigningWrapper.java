package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractSigningEntity;
import org.springblade.contract.vo.ContractSigningResponseVO;

/**
 * 合同签订表 包装类,返回视图层所需的字段
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
public class ContractSigningWrapper extends BaseEntityWrapper<ContractSigningEntity, ContractSigningResponseVO>  {

	public static ContractSigningWrapper build() {
		return new ContractSigningWrapper();
 	}

	@Override
	public ContractSigningResponseVO entityVO(ContractSigningEntity signing) {
		ContractSigningResponseVO signingResponseVO = BeanUtil.copy(signing, ContractSigningResponseVO.class);

		//User createUser = UserCache.getUser(signing.getCreateUser());
		//User updateUser = UserCache.getUser(signing.getUpdateUser());
		//signingResponseVO.setCreateUserName(createUser.getName());
		//signingResponseVO.setUpdateUserName(updateUser.getName());

		return signingResponseVO;
	}

}
