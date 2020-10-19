package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.AccordingEntity;
import org.springblade.contract.vo.AccordingResponseVO;

/**
 * 合同依据表 包装类,返回视图层所需的字段
 *
 * @author XHB
 * @date : 2020-09-19 17:54:53
 */
public class AccordingWrapper extends BaseEntityWrapper<AccordingEntity, AccordingResponseVO>  {

	public static AccordingWrapper build() {
		return new AccordingWrapper();
 	}

	@Override
	public AccordingResponseVO entityVO(AccordingEntity according) {
		AccordingResponseVO accordingResponseVO = BeanUtil.copy(according, AccordingResponseVO.class);

		//User createUser = UserCache.getUser(according.getCreateUser());
		//User updateUser = UserCache.getUser(according.getUpdateUser());
		//accordingResponseVO.setCreateUserName(createUser.getName());
		//accordingResponseVO.setUpdateUserName(updateUser.getName());

		return accordingResponseVO;
	}

}
