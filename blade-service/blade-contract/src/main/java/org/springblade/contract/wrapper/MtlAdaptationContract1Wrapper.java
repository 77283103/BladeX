package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtlAdaptationContract1RequestVO;
import org.springblade.contract.vo.MtlAdaptationContract1ResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.MtlAdaptationContract1Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：视频广告改编合同关联表 包装类,返回视图层所需的字段
 *
 * @author 媒体类：视频广告改编合同关联表
 * @date : 2020-12-11 08:36:16
 */
@Component
public class MtlAdaptationContract1Wrapper implements IEntityWrapper<MtlAdaptationContract1Entity, MtlAdaptationContract1RequestVO, MtlAdaptationContract1ResponseVO> {

	public static MtlAdaptationContract1Wrapper build() {
		return new MtlAdaptationContract1Wrapper();
 	}

    @Override
	public MtlAdaptationContract1Entity createEntity() {
		return new MtlAdaptationContract1Entity();
	}

	@Override
	public MtlAdaptationContract1RequestVO createQV() {
		return new MtlAdaptationContract1RequestVO();
	}

	@Override
	public MtlAdaptationContract1ResponseVO createPV() {
		return new MtlAdaptationContract1ResponseVO();
	}

    @Override
    public void selectUserName(MtlAdaptationContract1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
