package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclProjectOutsourcing1Entity;
import org.springblade.contract.vo.SclProjectOutsourcing1RequestVO;
import org.springblade.contract.vo.SclProjectOutsourcing1ResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：生产项目外包服务合同 包装类,返回视图层所需的字段
 *
 * @author kx
 * @date : 2020-12-11 11:05:06
 */
@Component
public class SclProjectOutsourcing1Wrapper implements IEntityWrapper<SclProjectOutsourcing1Entity, SclProjectOutsourcing1RequestVO, SclProjectOutsourcing1ResponseVO> {

	public static SclProjectOutsourcing1Wrapper build() {
		return new SclProjectOutsourcing1Wrapper();
 	}

    @Override
	public SclProjectOutsourcing1Entity createEntity() {
		return new SclProjectOutsourcing1Entity();
	}

	@Override
	public SclProjectOutsourcing1RequestVO createQV() {
		return new SclProjectOutsourcing1RequestVO();
	}

	@Override
	public SclProjectOutsourcing1ResponseVO createPV() {
		return new SclProjectOutsourcing1ResponseVO();
	}

    @Override
    public void selectUserName(SclProjectOutsourcing1ResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
