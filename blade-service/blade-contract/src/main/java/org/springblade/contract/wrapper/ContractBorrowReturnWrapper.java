package org.springblade.contract.wrapper;

import org.springblade.contract.entity.ContractBorrowReturnEntity;
import org.springblade.contract.vo.ContractBorrowReturnRequestVO;
import org.springblade.contract.vo.ContractBorrowReturnResponseVO;
import org.springblade.core.mp.support.IEntityWrapper;
import org.springframework.stereotype.Component;


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
        //responseVO.setCreateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        //responseVO.setUpdateUserName(UserCache.getUser(responseVO.getCreateUser()).getRealName());
        //responseVO.setCreateDeptName(SysCache.getDeptName(responseVO.getCreateDept()));
    }
}
