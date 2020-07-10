package org.springblade.system.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.system.entity.TopMenu;
import org.springblade.system.vo.TopMenuVO;


/**
 * 顶部菜单表 包装类,返回视图层所需的字段
 *
 * @author Chill
 */
public class TopMenuWrapper extends BaseEntityWrapper<TopMenu, TopMenuVO>  {

	public static TopMenuWrapper build() {
		return new TopMenuWrapper();
 	}

	@Override
	public TopMenuVO entityVO(TopMenu topmenu) {
		TopMenuVO topmenuVO = BeanUtil.copy(topmenu, TopMenuVO.class);

		//User createUser = UserCache.getUser(topmenu.getCreateUser());
		//User updateUser = UserCache.getUser(topmenu.getUpdateUser());
		//topmenuVO.setCreateUserName(createUser.getName());
		//topmenuVO.setUpdateUserName(updateUser.getName());

		return topmenuVO;
	}

}
