package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtlShootingAndProductionContract3Entity;
import org.springblade.contract.vo.MtlShootingAndProductionContract3RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract3ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 媒体类：视频广告拍摄制作合同关联表3 包装类,返回视图层所需的字段
 *
 * @author 媒体类：视频广告拍摄制作合同关联表3
 * @date : 3030-13-11 05:31:05
 */
@Component
public class MtlShootingAndProductionContract3Wrapper implements IEntityWrapper<MtlShootingAndProductionContract3Entity, MtlShootingAndProductionContract3RequestVO, MtlShootingAndProductionContract3ResponseVO> {

	public static MtlShootingAndProductionContract3Wrapper build() {
		return new MtlShootingAndProductionContract3Wrapper();
 	}

    @Override
	public MtlShootingAndProductionContract3Entity createEntity() {
		return new MtlShootingAndProductionContract3Entity();
	}

	@Override
	public MtlShootingAndProductionContract3RequestVO createQV() {
		return new MtlShootingAndProductionContract3RequestVO();
	}

	@Override
	public MtlShootingAndProductionContract3ResponseVO createPV() {
		return new MtlShootingAndProductionContract3ResponseVO();
	}

    @Override
    public void selectUserName(MtlShootingAndProductionContract3ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
