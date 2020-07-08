package org.springblade.system.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.Param;
import org.springblade.system.vo.ParamVO;

/**
 * 参数管理 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class ParamWrapper extends BaseEntityWrapper<Param, ParamVO>  {

	public static ParamWrapper build() {
		return new ParamWrapper();
 	}

	@Override
	public ParamVO entityVO(Param param) {

		//User createUser = UserCache.getUser(param.getCreateUser());
		//User updateUser = UserCache.getUser(param.getUpdateUser());
		//paramVO.setCreateUserName(createUser.getName());
		//paramVO.setUpdateUserName(updateUser.getName());

		return BeanUtil.copy(param, ParamVO.class);
	}

}
