package org.springblade.contract.wrapper;

import org.springblade.contract.entity.MtlEditedTheContract1Entity;
import org.springblade.contract.vo.MtlEditedTheContract1RequestVO;
import org.springblade.contract.vo.MtlEditedTheContract1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 媒体类：修图合同关联表 包装类,返回视图层所需的字段
 *
 * @author 媒体类：修图合同关联表
 * @date : 2020-12-11 05:00:49
 */
@Component
public class MtlEditedTheContract1Wrapper implements IEntityWrapper<MtlEditedTheContract1Entity, MtlEditedTheContract1RequestVO, MtlEditedTheContract1ResponseVO> {

	public static MtlEditedTheContract1Wrapper build() {
		return new MtlEditedTheContract1Wrapper();
 	}

    @Override
	public MtlEditedTheContract1Entity createEntity() {
		return new MtlEditedTheContract1Entity();
	}

	@Override
	public MtlEditedTheContract1RequestVO createQV() {
		return new MtlEditedTheContract1RequestVO();
	}

	@Override
	public MtlEditedTheContract1ResponseVO createPV() {
		return new MtlEditedTheContract1ResponseVO();
	}

    @Override
    public void selectUserName(MtlEditedTheContract1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
