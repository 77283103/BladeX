package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.entity.ContractBorrowReturnEntity;
import org.springblade.contract.mapper.ContractBorrowHandleMapper;
import org.springblade.contract.mapper.ContractBorrowReturnMapper;
import org.springblade.contract.vo.ContractBorrowApplicationResponseVO;
import org.springblade.contract.vo.ContractBorrowHandleResponseVO;
import org.springblade.contract.vo.ContractBorrowReturnResponseVO;
import org.springblade.contract.wrapper.ContractBorrowApplicationWrapper;
import org.springblade.contract.wrapper.ContractBorrowHandleWrapper;
import org.springblade.contract.wrapper.ContractBorrowReturnWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springblade.contract.mapper.ContractBorrowApplicationMapper;
import org.springblade.contract.service.IContractBorrowApplicationService;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springframework.stereotype.Service;
import org.springblade.contract.vo.ContractBorrowApplicationRequestVO;

/**
 * 借阅申请 服务实现类
 *
 * @author xhb
 * @date : 2020-10-30 09:27:07
 */
@Service
@AllArgsConstructor
public class ContractBorrowApplicationServiceImpl extends BaseServiceImpl<ContractBorrowApplicationMapper, ContractBorrowApplicationEntity> implements IContractBorrowApplicationService {

    private ContractBorrowHandleMapper handleMapper;

    private ContractBorrowReturnMapper returnMapper;

    private ContractBorrowApplicationMapper applicationMapper;

    @Override
    public IPage<ContractBorrowApplicationResponseVO> pageList(IPage<ContractBorrowApplicationEntity> page, ContractBorrowApplicationRequestVO contractBorrowApplication) {
        return baseMapper.pageList(page, contractBorrowApplication);
    }

    /**
     * 查询借阅信息
     *
     * @param id
     * @return
     */
    @Override
    public ContractBorrowApplicationResponseVO getById(Long id) {
        ContractBorrowApplicationEntity applicationEntity = baseMapper.selectById(id);
        ContractBorrowApplicationResponseVO applicationResponseVO = ContractBorrowApplicationWrapper.build().entityPV(applicationEntity);
        //将处理信息返回vo
        if (Func.isEmpty(applicationResponseVO.getHandleEntity())) {
            ContractBorrowHandleEntity handleEntity = handleMapper.selectById(id);
            if (Func.isNotEmpty(handleEntity)) {
                ContractBorrowHandleResponseVO handleResponseVO = ContractBorrowHandleWrapper.build().entityPV(handleEntity);
                BladeUser user = AuthUtil.getUser();
                Long userId = Long.valueOf(user.getUserId());
                Long deptId = Long.valueOf(AuthUtil.getDeptId());
                handleResponseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
                handleResponseVO.setCreateDeptName(SysCache.getDeptName(deptId));
                applicationResponseVO.setHandleEntity(handleResponseVO);
            }
        }
        //将归还信息返回vo
        if (Func.isEmpty(applicationResponseVO.getReturnEntity())) {
            ContractBorrowReturnEntity returnEntity = returnMapper.selectById(id);
            if (Func.isNotEmpty(returnEntity)) {
                ContractBorrowReturnResponseVO returnResponseVO = ContractBorrowReturnWrapper.build().entityPV(returnEntity);
                BladeUser user = AuthUtil.getUser();
                Long userId = Long.valueOf(user.getUserId());
                Long deptId = Long.valueOf(AuthUtil.getDeptId());
                returnResponseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
                returnResponseVO.setCreateDeptName(SysCache.getDeptName(deptId));
                applicationResponseVO.setReturnEntity(returnResponseVO);
            }
        }
        return applicationResponseVO;
    }

    @Override
    public void updateBorrowStatusById(Long id, String borrowStatus) {
        applicationMapper.updateBorrowStatusById(id, borrowStatus);
    }
}
