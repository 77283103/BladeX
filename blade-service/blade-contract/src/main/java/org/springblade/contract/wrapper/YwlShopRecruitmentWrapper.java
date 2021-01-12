package org.springblade.contract.wrapper;

import org.springblade.contract.entity.YwiShopRecruitmentEntity;
import org.springblade.contract.vo.YwiShopRecruitmentResponseVO;
import org.springblade.contract.vo.YwlShopRecruitmentRequestVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 业务类：14.店招合同 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-12-04 19:04:56
 */
@Component
public class YwlShopRecruitmentWrapper implements IEntityWrapper<YwiShopRecruitmentEntity, YwlShopRecruitmentRequestVO, YwiShopRecruitmentResponseVO> {

	public static YwlShopRecruitmentWrapper build() {
		return new YwlShopRecruitmentWrapper();
 	}

    @Override
	public YwiShopRecruitmentEntity createEntity() {
		return new YwiShopRecruitmentEntity();
	}

	@Override
	public YwlShopRecruitmentRequestVO createQV() {
		return new YwlShopRecruitmentRequestVO();
	}

	@Override
	public YwiShopRecruitmentResponseVO createPV() {
		return new YwiShopRecruitmentResponseVO();
	}

    @Override
    public void selectUserName(YwiShopRecruitmentResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
