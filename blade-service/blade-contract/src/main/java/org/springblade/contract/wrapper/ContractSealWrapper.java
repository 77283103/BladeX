package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractSealEntity;
import org.springblade.contract.vo.ContractSealRequestVO;
import org.springblade.contract.vo.ContractSealResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springframework.stereotype.Component;


/**
 * 统一子公司（签章申请单位） 包装类,返回视图层所需的字段
 *
 * @author xhb
 * @date : 2021-06-16 16:11:01
 */
@Component
public class ContractSealWrapper implements IEntityWrapper<ContractSealEntity, ContractSealRequestVO, ContractSealResponseVO> {

	public static ContractSealWrapper build() {
		return new ContractSealWrapper();
 	}

    @Override
	public ContractSealEntity createEntity() {
		return new ContractSealEntity();
	}

	@Override
	public ContractSealRequestVO createQV() {
		return new ContractSealRequestVO();
	}

	@Override
	public ContractSealResponseVO createPV() {
		return new ContractSealResponseVO();
	}

    @Override
    public void selectUserName(ContractSealResponseVO responseVO) {
        responseVO.setCreateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setUpdateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
