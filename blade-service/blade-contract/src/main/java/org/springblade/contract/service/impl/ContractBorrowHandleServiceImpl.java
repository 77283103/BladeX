package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springblade.contract.vo.ContractBorrowHandleResponseVO;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.mapper.ContractBorrowHandleMapper;
import org.springblade.contract.service.IContractBorrowHandleService;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractBorrowHandleRequestVO;

/**
 * 借阅处理 服务实现类
 *
 * @author xhb
 * @date : 2020-10-30 09:28:22
 */
@Service
public class ContractBorrowHandleServiceImpl extends BaseServiceImpl<ContractBorrowHandleMapper, ContractBorrowHandleEntity> implements IContractBorrowHandleService {

    @Override
    public IPage<ContractBorrowHandleEntity> pageList(IPage<ContractBorrowHandleEntity> page, ContractBorrowHandleRequestVO contractBorrowHandle) {
        return baseMapper.pageList(page, contractBorrowHandle);
    }

}
