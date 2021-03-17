package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.vo.ContractBorrowHandleRequestVO;
import org.springblade.contract.vo.ContractBorrowHandleResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


/**
 * 借阅处理 包装类,返回视图层所需的字段
 *
 * @author xhb
 * @date : 2020-10-30 09:28:23
 */
@Component
public class ContractBorrowHandleWrapper implements IEntityWrapper<ContractBorrowHandleEntity, ContractBorrowHandleRequestVO, ContractBorrowHandleResponseVO> {

    public static ContractBorrowHandleWrapper build() {
        return new ContractBorrowHandleWrapper();
    }

    @Override
    public ContractBorrowHandleEntity createEntity() {
        return new ContractBorrowHandleEntity();
    }

    @Override
    public ContractBorrowHandleRequestVO createQV() {
        return new ContractBorrowHandleRequestVO();
    }

    @Override
    public ContractBorrowHandleResponseVO createPV() {
        return new ContractBorrowHandleResponseVO();
    }

    @Override
    public void selectUserName(ContractBorrowHandleResponseVO responseVO) {
        //responseVO.setCreateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        //responseVO.setUpdateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        //responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
