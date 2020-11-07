package org.springblade.contract.wrapper;

import org.springblade.core.mp.support.IEntityWrapper;
import org.springblade.contract.vo.ContractBorrowReturnResponseVO;
import org.springblade.contract.vo.ContractBorrowReturnRequestVO;
import org.springblade.contract.entity.ContractBorrowReturnEntity;
import org.springframework.stereotype.Component;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;


/**
 * 借阅归还 包装类,返回视图层所需的字段
 *
 * @author xhb
 * @date : 2020-10-30 09:28:59
 */
@Component
public class ContractBorrowReturnWrapper implements IEntityWrapper<ContractBorrowReturnEntity, ContractBorrowReturnRequestVO, ContractBorrowReturnResponseVO> {

    public static ContractBorrowReturnWrapper build() {
        return new ContractBorrowReturnWrapper();
    }

    @Override
    public ContractBorrowReturnEntity createEntity() {
        return new ContractBorrowReturnEntity();
    }

    @Override
    public ContractBorrowReturnRequestVO createQV() {
        return new ContractBorrowReturnRequestVO();
    }

    @Override
    public ContractBorrowReturnResponseVO createPV() {
        return new ContractBorrowReturnResponseVO();
    }

    @Override
    public void selectUserName(ContractBorrowReturnResponseVO responseVO) {
        responseVO.setCreateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setUpdateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
