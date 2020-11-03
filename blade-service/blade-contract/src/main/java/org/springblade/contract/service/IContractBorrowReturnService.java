package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractBorrowReturnResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractBorrowReturnEntity;
import org.springblade.contract.vo.ContractBorrowReturnRequestVO;

/**
 * 借阅归还 服务类
 *
 * @author xhb
 * @date : 2020-10-30 09:28:58
 */
public interface IContractBorrowReturnService extends BaseService<ContractBorrowReturnEntity> {

    /**
     * 分页查询
     *
     * @param page
     * @param contractBorrowReturn
     * @return
     */
    IPage<ContractBorrowReturnEntity> pageList(IPage<ContractBorrowReturnEntity> page, ContractBorrowReturnRequestVO contractBorrowReturn);

}
