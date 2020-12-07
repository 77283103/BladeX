package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.YwlShopRecruitment1RequestVO;
import org.springblade.contract.vo.YwlShopRecruitment1ResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.YwlShopRecruitment1Entity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 业务类：21.新陈列协议书关联表 包装类,返回视图层所需的字段
 *
 * @author szw
 * @date : 2020-12-06 13:51:41
 */
@Component
public class YwlShopRecruitment1Wrapper implements IEntityWrapper<YwlShopRecruitment1Entity, YwlShopRecruitment1RequestVO, YwlShopRecruitment1ResponseVO> {

	public static YwlShopRecruitment1Wrapper build() {
		return new YwlShopRecruitment1Wrapper();
 	}

    @Override
	public YwlShopRecruitment1Entity createEntity() {
		return new YwlShopRecruitment1Entity();
	}

	@Override
	public YwlShopRecruitment1RequestVO createQV() {
		return new YwlShopRecruitment1RequestVO();
	}

	@Override
	public YwlShopRecruitment1ResponseVO createPV() {
		return new YwlShopRecruitment1ResponseVO();
	}

    @Override
    public void selectUserName(YwlShopRecruitment1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
