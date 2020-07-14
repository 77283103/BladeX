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
	public UserDepartVO entityVO(UserDepartEntity userDepart) {
		return BeanUtil.copy(userDepart, UserDepartVO.class);
	}

}
