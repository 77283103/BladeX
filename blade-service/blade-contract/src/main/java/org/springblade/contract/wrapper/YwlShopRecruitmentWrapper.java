package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.YwlShopRecruitmentRequestVO;
import org.springblade.contract.vo.YwlShopRecruitmentResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.user.entity.User;
import org.springblade.contract.entity.YwlShopRecruitmentEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 业务类：14.店招合同 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-12-04 19:04:56
 */
@Component
public class YwlShopRecruitmentWrapper implements IEntityWrapper<YwlShopRecruitmentEntity, YwlShopRecruitmentRequestVO, YwlShopRecruitmentResponseVO> {

	public static YwlShopRecruitmentWrapper build() {
		return new YwlShopRecruitmentWrapper();
 	}

    @Override
	public YwlShopRecruitmentEntity createEntity() {
		return new YwlShopRecruitmentEntity();
	}

	@Override
	public YwlShopRecruitmentRequestVO createQV() {
		return new YwlShopRecruitmentRequestVO();
	}

	@Override
	public YwlShopRecruitmentResponseVO createPV() {
		return new YwlShopRecruitmentResponseVO();
	}

    @Override
    public void selectUserName(YwlShopRecruitmentResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
