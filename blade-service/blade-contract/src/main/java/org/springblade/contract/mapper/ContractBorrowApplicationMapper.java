package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springblade.contract.vo.ContractBorrowApplicationRequestVO;
import org.springblade.contract.vo.ContractBorrowApplicationResponseVO;

/**
 * 借阅申请 Mapper 接口
 *
 * @author xhb
 * @date : 2020-10-30 09:27:04
 */
public interface ContractBorrowApplicationMapper extends BaseMapper<ContractBorrowApplicationEntity> {

    /**
     * 分页查询
     *
     * @param page
     * @param contractBorrowApplication
     * @return
     */
    IPage<ContractBorrowApplicationResponseVO> pageList(IPage<ContractBorrowApplicationEntity> page, ContractBorrowApplicationRequestVO contractBorrowApplication);

    /**
     * 申请详情
     *
     * @param id
     * @return
     */
    ContractBorrowApplicationEntity selectById(Long id);

    /**
     * -修改借阅申请信息状态
     *
     * @param id
     * @param borrowStatus
     * @return
     */
    boolean updateBorrowStatusById(Long id, String borrowStatus);
}
