package org.springblade.contract.wrapper;

import org.springblade.contract.entity.YwbSurveyProjectContractEntity;
import org.springblade.contract.vo.YwbSurveyProjectContractRequestVO;
import org.springblade.contract.vo.YwbSurveyProjectContractResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 业务类：20.售点普查项目合同 包装类,返回视图层所需的字段
 *
 * @author 业务类：20.售点普查项目合同
 * @date : 2020-12-18 16:06:59
 */
@Component
public class YwbSurveyProjectContractWrapper implements IEntityWrapper<YwbSurveyProjectContractEntity, YwbSurveyProjectContractRequestVO, YwbSurveyProjectContractResponseVO> {

	public static YwbSurveyProjectContractWrapper build() {
		return new YwbSurveyProjectContractWrapper();
 	}

    @Override
	public YwbSurveyProjectContractEntity createEntity() {
		return new YwbSurveyProjectContractEntity();
	}

	@Override
	public YwbSurveyProjectContractRequestVO createQV() {
		return new YwbSurveyProjectContractRequestVO();
	}

	@Override
	public YwbSurveyProjectContractResponseVO createPV() {
		return new YwbSurveyProjectContractResponseVO();
	}

    @Override
    public void selectUserName(YwbSurveyProjectContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
