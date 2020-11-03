package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.vo.ContractBorrowApplicationResponseVO;
import org.springblade.contract.vo.ContractBorrowApplicationRequestVO;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 借阅申请 包装类,返回视图层所需的字段
 *
 * @author xhb
 * @date : 2020-10-30 09:27:08
 */
@Component
public class ContractBorrowApplicationWrapper implements IEntityWrapper<ContractBorrowApplicationEntity, ContractBorrowApplicationRequestVO, ContractBorrowApplicationResponseVO> {

    public static ContractBorrowApplicationWrapper build() {
        return new ContractBorrowApplicationWrapper();
    }

    @Override
    public ContractBorrowApplicationEntity createEntity() {
        return new ContractBorrowApplicationEntity();
    }

    @Override
    public ContractBorrowApplicationRequestVO createQV() {
        return new ContractBorrowApplicationRequestVO();
    }

    @Override
    public ContractBorrowApplicationResponseVO createPV() {
        return new ContractBorrowApplicationResponseVO();
    }

    @Override
    public void selectUserName(ContractBorrowApplicationResponseVO responseVO) {
        responseVO.setCreateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setUpdateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
