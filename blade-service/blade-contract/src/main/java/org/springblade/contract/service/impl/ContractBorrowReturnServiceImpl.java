package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.mapper.ContractBorrowHandleMapper;
import org.springblade.contract.vo.ContractBorrowReturnResponseVO;
import org.springblade.contract.vo.ContractBorrowReturnRequestVO;
import org.springblade.contract.wrapper.ContractBorrowReturnWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractBorrowReturnEntity;
import org.springblade.contract.mapper.ContractBorrowReturnMapper;
import org.springblade.contract.service.IContractBorrowReturnService;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

/**
 * 借阅归还 服务实现类
 *
 * @author xhb
 * @date : 2020-10-30 09:28:58
 */
@Service
@AllArgsConstructor
public class ContractBorrowReturnServiceImpl extends BaseServiceImpl<ContractBorrowReturnMapper, ContractBorrowReturnEntity> implements IContractBorrowReturnService {

    private ContractBorrowHandleMapper handleMapper;

    @Override
    public IPage<ContractBorrowReturnEntity> pageList(IPage<ContractBorrowReturnEntity> page, ContractBorrowReturnRequestVO contractBorrowReturn) {
        return baseMapper.pageList(page, contractBorrowReturn);
    }
}
