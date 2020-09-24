package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.contract.entity.SealInfoEntity;
import org.springblade.contract.vo.SealInfoResponseVO;

/**
 * 用印名称 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-09-24 01:27:14
 */
public class SealInfoWrapper extends BaseEntityWrapper<SealInfoEntity, SealInfoResponseVO>  {

	public static SealInfoWrapper build() {
		return new SealInfoWrapper();
 	}

	@Override
	public SealInfoResponseVO entityVO(SealInfoEntity sealInfo) {
		SealInfoResponseVO sealInfoResponseVO = BeanUtil.copy(sealInfo, SealInfoResponseVO.class);

		//User createUser = UserCache.getUser(sealInfo.getCreateUser());
		//User updateUser = UserCache.getUser(sealInfo.getUpdateUser());
		//sealInfoResponseVO.setCreateUserName(createUser.getName());
		//sealInfoResponseVO.setUpdateUserName(updateUser.getName());

		return sealInfoResponseVO;
	}

}
