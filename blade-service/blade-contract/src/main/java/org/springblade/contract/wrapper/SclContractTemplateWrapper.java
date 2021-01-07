package org.springblade.contract.wrapper;

import org.springblade.contract.entity.SclContractTemplateEntity;
import org.springblade.contract.vo.SclContractTemplateRequestVO;
import org.springblade.contract.vo.SclContractTemplateResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.Optional;


/**
 * 生产类：下脚品买卖合同模版 包装类,返回视图层所需的字段
 *
 * @author 张文武
 * @date : 2021-01-04 15:17:31
 */
@Component
public class SclContractTemplateWrapper implements IEntityWrapper<SclContractTemplateEntity, SclContractTemplateRequestVO, SclContractTemplateResponseVO> {

	public static SclContractTemplateWrapper build() {
		return new SclContractTemplateWrapper();
 	}

    @Override
	public SclContractTemplateEntity createEntity() {
		return new SclContractTemplateEntity();
	}

	@Override
	public SclContractTemplateRequestVO createQV() {
		return new SclContractTemplateRequestVO();
	}

	@Override
	public SclContractTemplateResponseVO createPV() {
		return new SclContractTemplateResponseVO();
	}

    @Override
    public void selectUserName(SclContractTemplateResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
