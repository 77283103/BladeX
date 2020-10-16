package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.CounterpartEntity;
import org.springblade.contract.vo.CounterpartResponseVO;

/**
 * 合同相对方的管理 包装类,返回视图层所需的字段
 *
 * @author XHB
 * @date : 2020-09-18 21:13:57
 */
public class CounterpartWrapper extends BaseEntityWrapper<CounterpartEntity, CounterpartResponseVO>  {

	public static CounterpartWrapper build() {
		return new CounterpartWrapper();
 	}

	@Override
	public CounterpartResponseVO entityVO(CounterpartEntity counterpart) {
		CounterpartResponseVO counterpartResponseVO = BeanUtil.copy(counterpart, CounterpartResponseVO.class);

		//User createUser = UserCache.getUser(counterpart.getCreateUser());
		//User updateUser = UserCache.getUser(counterpart.getUpdateUser());
		//counterpartResponseVO.setCreateUserName(createUser.getName());
		//counterpartResponseVO.setUpdateUserName(updateUser.getName());

		return counterpartResponseVO;
	}

}
