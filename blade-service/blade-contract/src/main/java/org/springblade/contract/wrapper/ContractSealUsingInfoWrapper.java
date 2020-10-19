package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import org.springblade.contract.vo.ContractSealUsingInfoResponseVO;

/**
 * 用印名称 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
public class ContractSealUsingInfoWrapper extends BaseEntityWrapper<ContractSealUsingInfoEntity, ContractSealUsingInfoResponseVO>  {

	public static ContractSealUsingInfoWrapper build() {
		return new ContractSealUsingInfoWrapper();
 	}

	@Override
	public ContractSealUsingInfoResponseVO entityVO(ContractSealUsingInfoEntity sealInfo) {
		ContractSealUsingInfoResponseVO sealInfoResponseVO = BeanUtil.copy(sealInfo, ContractSealUsingInfoResponseVO.class);

		//User createUser = UserCache.getUser(sealInfo.getCreateUser());
		//User updateUser = UserCache.getUser(sealInfo.getUpdateUser());
		//sealInfoResponseVO.setCreateUserName(createUser.getName());
		//sealInfoResponseVO.setUpdateUserName(updateUser.getName());

		return sealInfoResponseVO;
	}

}
