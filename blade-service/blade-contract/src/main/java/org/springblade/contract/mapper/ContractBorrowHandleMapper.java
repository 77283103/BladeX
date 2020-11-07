package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.vo.ContractBorrowHandleRequestVO;

/**
 * 借阅处理 Mapper 接口
 *
 * @author xhb
 * @date : 2020-10-30 09:28:19
 */
public interface ContractBorrowHandleMapper extends BaseMapper<ContractBorrowHandleEntity> {

    /**
     * 分页查询
     *
     * @param page
     * @param contractBorrowHandle
     * @return
     */
    IPage<ContractBorrowHandleEntity> pageList(IPage<ContractBorrowHandleEntity> page, ContractBorrowHandleRequestVO contractBorrowHandle);

    /**
     * 根据处理id查询处理信息
     *
     * @param id
     * @return
     */
    ContractBorrowHandleEntity selectById(Long id);
}
