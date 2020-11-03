package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractBorrowReturnEntity;
import org.springblade.contract.vo.ContractBorrowReturnRequestVO;

/**
 * 借阅归还 Mapper 接口
 *
 * @author xhb
 * @date : 2020-10-30 09:28:56
 */
public interface ContractBorrowReturnMapper extends BaseMapper<ContractBorrowReturnEntity> {

    /**
     * 分页查询
     *
     * @param page
     * @param contractBorrowReturn
     * @return
     */
    IPage<ContractBorrowReturnEntity> pageList(IPage<ContractBorrowReturnEntity> page, ContractBorrowReturnRequestVO contractBorrowReturn);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    ContractBorrowReturnEntity selectById(Long id);
}
