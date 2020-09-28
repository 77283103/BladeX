package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.vo.ContractCounterpartResponseVO;

/**
 * 相对方管理 包装类,返回视图层所需的字段
 *
 * @author XHB
 * @date : 2020-09-23 19:35:07
 */
public class ContractCounterpartWrapper extends BaseEntityWrapper<ContractCounterpartEntity, ContractCounterpartResponseVO>  {

	public static ContractCounterpartWrapper build() {
		return new ContractCounterpartWrapper();
 	}

	@Override
	public ContractCounterpartResponseVO entityVO(ContractCounterpartEntity counterpart) {
		ContractCounterpartResponseVO counterpartResponseVO = BeanUtil.copy(counterpart, ContractCounterpartResponseVO.class);

		//User createUser = UserCache.getUser(counterpart.getCreateUser());
		//User updateUser = UserCache.getUser(counterpart.getUpdateUser());
		//counterpartResponseVO.setCreateUserName(createUser.getName());
		//counterpartResponseVO.setUpdateUserName(updateUser.getName());

		return counterpartResponseVO;
	}

}
