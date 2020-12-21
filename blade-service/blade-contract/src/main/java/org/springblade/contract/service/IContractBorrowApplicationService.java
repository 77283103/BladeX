package org.springblade.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.vo.ContractBorrowApplicationResponseVO;
import org.springblade.core.mp.base.BaseService;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springblade.contract.vo.ContractBorrowApplicationRequestVO;

/**
 * 借阅申请 服务类
 *
 * @author xhb
 * @date : 2020-10-30 09:27:05
 */
public interface IContractBorrowApplicationService extends BaseService<ContractBorrowApplicationEntity> {

    /**
     * 分页查询
     *
     * @param page
     * @param contractBorrowApplication
     * @return
     */
    IPage<ContractBorrowApplicationResponseVO> pageList(IPage<ContractBorrowApplicationEntity> page, ContractBorrowApplicationRequestVO contractBorrowApplication);

    /**
     * 借阅信息
     *
     * @param id
     * @return
     */
    ContractBorrowApplicationResponseVO getById(Long id);

    /**
     * 修改申请信息状态
     *
     * @param id
     * @param borrowStatus
     */
    void updateBorrowStatusById(Long id, String borrowStatus);
}
