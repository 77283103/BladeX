package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtlShootingAndProductionContract2RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract2ResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.MtlShootingAndProductionContract2Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：视频广告拍摄制作合同关联表2 包装类,返回视图层所需的字段
 *
 * @author 媒体类：视频广告拍摄制作合同关联表2
 * @date : 2020-12-11 05:31:05
 */
@Component
public class MtlShootingAndProductionContract2Wrapper implements IEntityWrapper<MtlShootingAndProductionContract2Entity, MtlShootingAndProductionContract2RequestVO, MtlShootingAndProductionContract2ResponseVO> {

	public static MtlShootingAndProductionContract2Wrapper build() {
		return new MtlShootingAndProductionContract2Wrapper();
 	}

    @Override
	public MtlShootingAndProductionContract2Entity createEntity() {
		return new MtlShootingAndProductionContract2Entity();
	}

	@Override
	public MtlShootingAndProductionContract2RequestVO createQV() {
		return new MtlShootingAndProductionContract2RequestVO();
	}

	@Override
	public MtlShootingAndProductionContract2ResponseVO createPV() {
		return new MtlShootingAndProductionContract2ResponseVO();
	}

    @Override
    public void selectUserName(MtlShootingAndProductionContract2ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
