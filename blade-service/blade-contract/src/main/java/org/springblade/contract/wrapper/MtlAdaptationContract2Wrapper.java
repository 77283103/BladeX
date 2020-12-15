package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtlAdaptationContract2RequestVO;
import org.springblade.contract.vo.MtlAdaptationContract2ResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.MtlAdaptationContract2Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：视频广告改编合同关联表2 包装类,返回视图层所需的字段
 *
 * @author 媒体类：视频广告改编合同关联表2
 * @date : 2020-12-11 08:36:48
 */
@Component
public class MtlAdaptationContract2Wrapper implements IEntityWrapper<MtlAdaptationContract2Entity, MtlAdaptationContract2RequestVO, MtlAdaptationContract2ResponseVO> {

	public static MtlAdaptationContract2Wrapper build() {
		return new MtlAdaptationContract2Wrapper();
 	}

    @Override
	public MtlAdaptationContract2Entity createEntity() {
		return new MtlAdaptationContract2Entity();
	}

	@Override
	public MtlAdaptationContract2RequestVO createQV() {
		return new MtlAdaptationContract2RequestVO();
	}

	@Override
	public MtlAdaptationContract2ResponseVO createPV() {
		return new MtlAdaptationContract2ResponseVO();
	}

    @Override
    public void selectUserName(MtlAdaptationContract2ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
