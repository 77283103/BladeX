package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractBorrowHandleResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.vo.ContractBorrowHandleRequestVO;

/**
 * 借阅处理 服务类
 *
 * @author xhb
 * @date : 2020-10-30 09:28:21
 */
public interface IContractBorrowHandleService extends BaseService<ContractBorrowHandleEntity> {

    /**
     * 分页查询
     *
     * @param page
     * @param contractBorrowHandle
     * @return
     */
    IPage<ContractBorrowHandleEntity> pageList(IPage<ContractBorrowHandleEntity> page, ContractBorrowHandleRequestVO contractBorrowHandle);

}
