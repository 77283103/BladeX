package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtbProductionContract2Entity;
import org.springblade.contract.vo.MtbProductionContract2RequestVO;
import org.springblade.contract.vo.MtbProductionContract2ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 包装类,返回视图层所需的字段
 *
 * @author 张文武
 * @date : 2021-01-04 11:24:01
 */
@Component
public class MtbProductionContract2Wrapper implements IEntityWrapper<MtbProductionContract2Entity, MtbProductionContract2RequestVO, MtbProductionContract2ResponseVO> {

	public static MtbProductionContract2Wrapper build() {
		return new MtbProductionContract2Wrapper();
 	}

    @Override
	public MtbProductionContract2Entity createEntity() {
		return new MtbProductionContract2Entity();
	}

	@Override
	public MtbProductionContract2RequestVO createQV() {
		return new MtbProductionContract2RequestVO();
	}

	@Override
	public MtbProductionContract2ResponseVO createPV() {
		return new MtbProductionContract2ResponseVO();
	}

    @Override
    public void selectUserName(MtbProductionContract2ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
