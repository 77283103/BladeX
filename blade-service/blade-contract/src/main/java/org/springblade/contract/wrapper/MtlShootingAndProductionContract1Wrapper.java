package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtlShootingAndProductionContract1RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.user.entity.User;
import org.springblade.contract.entity.MtlShootingAndProductionContract1Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：视频广告拍摄制作合同关联表 包装类,返回视图层所需的字段
 *
 * @author 媒体类：视频广告拍摄制作合同关联表
 * @date : 2020-12-11 05:30:04
 */
@Component
public class MtlShootingAndProductionContract1Wrapper implements IEntityWrapper<MtlShootingAndProductionContract1Entity, MtlShootingAndProductionContract1RequestVO, MtlShootingAndProductionContract1ResponseVO> {

	public static MtlShootingAndProductionContract1Wrapper build() {
		return new MtlShootingAndProductionContract1Wrapper();
 	}

    @Override
	public MtlShootingAndProductionContract1Entity createEntity() {
		return new MtlShootingAndProductionContract1Entity();
	}

	@Override
	public MtlShootingAndProductionContract1RequestVO createQV() {
		return new MtlShootingAndProductionContract1RequestVO();
	}

	@Override
	public MtlShootingAndProductionContract1ResponseVO createPV() {
		return new MtlShootingAndProductionContract1ResponseVO();
	}

    @Override
    public void selectUserName(MtlShootingAndProductionContract1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
