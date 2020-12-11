package org.springblade.contract.wrapper;

import java.util.Optional;

import org.springblade.contract.vo.MtbMarketResearchContractRequestVO;
import org.springblade.contract.vo.MtbMarketResearchContractResponseVO;
import org.springblade.system.user.entity.User;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.entity.MtbMarketResearchContractEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 媒体类：市调合同（定性+定量) 包装类,返回视图层所需的字段
 *
 * @author 王策
 * @date : 2020-12-10 19:37:20
 */
@Component
public class MtbMarketResearchContractWrapper implements IEntityWrapper<MtbMarketResearchContractEntity, MtbMarketResearchContractRequestVO, MtbMarketResearchContractResponseVO> {

	public static MtbMarketResearchContractWrapper build() {
		return new MtbMarketResearchContractWrapper();
 	}

    @Override
	public MtbMarketResearchContractEntity createEntity() {
		return new MtbMarketResearchContractEntity();
	}

	@Override
	public MtbMarketResearchContractRequestVO createQV() {
		return new MtbMarketResearchContractRequestVO();
	}

	@Override
	public MtbMarketResearchContractResponseVO createPV() {
		return new MtbMarketResearchContractResponseVO();
	}

    @Override
    public void selectUserName(MtbMarketResearchContractResponseVO responseVO) {
        responseVO.setCreateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getCreateUser())).orElse(new User()).getRealName());
        responseVO.setUpdateUserName(Optional.ofNullable(UserCache.getUser(responseVO.getUpdateUser())).orElse(new User()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
