package org.springblade.system.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.UserDepartEntity;
import org.springblade.system.vo.UserDepartVO;

/**
 *  包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class UserDepartWrapper extends BaseEntityWrapper<UserDepartEntity, UserDepartVO>  {

	public static UserDepartWrapper build() {
		return new UserDepartWrapper();
 	}

	@Override
	public UserDepartVO entityVO(UserDepartEntity user_depart) {
		UserDepartVO user_departVO = BeanUtil.copy(user_depart, UserDepartVO.class);

		//User createUser = UserCache.getUser(user_depart.getCreateUser());
		//User updateUser = UserCache.getUser(user_depart.getUpdateUser());
		//user_departVO.setCreateUserName(createUser.getName());
		//user_departVO.setUpdateUserName(updateUser.getName());

		return user_departVO;
	}

}
