package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtbProductionContract1Entity;
import org.springblade.contract.vo.MtbProductionContract1RequestVO;
import org.springblade.contract.vo.MtbProductionContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 媒体类：平面广告拍摄制作合同（关联表1） 包装类,返回视图层所需的字段
 *
 * @author 张文武
 * @date : 2021-01-04 11:27:47
 */
@Component
public class MtbProductionContract1Wrapper implements IEntityWrapper<MtbProductionContract1Entity, MtbProductionContract1RequestVO, MtbProductionContract1ResponseVO> {

	public static MtbProductionContract1Wrapper build() {
		return new MtbProductionContract1Wrapper();
 	}

    @Override
	public MtbProductionContract1Entity createEntity() {
		return new MtbProductionContract1Entity();
	}

	@Override
	public MtbProductionContract1RequestVO createQV() {
		return new MtbProductionContract1RequestVO();
	}

	@Override
	public MtbProductionContract1ResponseVO createPV() {
		return new MtbProductionContract1ResponseVO();
	}

    @Override
    public void selectUserName(MtbProductionContract1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
