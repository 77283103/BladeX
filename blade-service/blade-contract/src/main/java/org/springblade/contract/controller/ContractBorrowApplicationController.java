package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.cache.SysCache;
import org.springblade.system.entity.Dept;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.user.entity.User;
import org.springblade.system.user.entity.UserDepart;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractBorrowApplicationEntity;
import org.springblade.contract.wrapper.ContractBorrowApplicationWrapper;
import org.springblade.contract.service.IContractBorrowApplicationService;
import org.springblade.contract.vo.ContractBorrowApplicationRequestVO;
import org.springblade.contract.vo.ContractBorrowApplicationResponseVO;

import java.util.Date;


/**
 * 借阅申请 控制器
 *
 * @author : xhb
 * @date : 2020-10-30 09:27:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractBorrowApplication")
@Api(value = "借阅申请", tags = "借阅申请")
public class ContractBorrowApplicationController extends BladeController {

    private IContractBorrowApplicationService contractBorrowApplicationService;
    private static final String BORROW_APPLICATION_STATUS = "10";

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入contractBorrowApplication")
    @PreAuth("hasPermission('application:contractBorrowApplication:detail')")
    public R<ContractBorrowApplicationResponseVO> detail(@RequestParam Long id) {
        ContractBorrowApplicationEntity detail = contractBorrowApplicationService.getById(id);
        return R.data(ContractBorrowApplicationWrapper.build().entityPV(detail));
    }

    /**
     * 分页
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入contractBorrowApplication")
    @PreAuth("hasPermission('application:contractBorrowApplication:page')")
    public R<IPage<ContractBorrowApplicationResponseVO>> list(ContractBorrowApplicationRequestVO contractBorrowApplication, Query query) {
        IPage<ContractBorrowApplicationEntity> pages = contractBorrowApplicationService.pageList(Condition.getPage(query), contractBorrowApplication);
        return R.data(ContractBorrowApplicationWrapper.build().entityPVPage(pages));
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入contractBorrowApplication")
    @PreAuth("hasPermission('application:contractBorrowApplication:add')")
    public R save(@Valid @RequestBody ContractBorrowApplicationResponseVO contractBorrowApplication) {
        String borrowStatus = BORROW_APPLICATION_STATUS;
        contractBorrowApplication.setBorrowStatus(borrowStatus);
        return R.status(contractBorrowApplicationService.save(ContractBorrowApplicationWrapper.build().PVEntity(contractBorrowApplication)));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入contractBorrowApplication")
    @PreAuth("hasPermission('application:contractBorrowApplication:update')")
    public R update(@Valid @RequestBody ContractBorrowApplicationResponseVO contractBorrowApplication) {
        if (Func.isEmpty(contractBorrowApplication.getId())) {
            throw new ServiceException("id不能为空");
        }
        return R.status(contractBorrowApplicationService.updateById(ContractBorrowApplicationWrapper.build().PVEntity(contractBorrowApplication)));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @PreAuth("hasPermission('application:contractBorrowApplication:remove')")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(contractBorrowApplicationService.deleteLogic(Func.toLongList(ids)));
    }


    /**
     * 当前登录用户信息
     */
    @GetMapping("/logInfo")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "插叙登当前录用户信息")
    @PreAuth("hasPermission('application:contractBorrowApplication:logInfo')")
    public R<ContractBorrowApplicationResponseVO> logInfo() {
        ContractBorrowApplicationResponseVO responseVO = ContractBorrowApplicationWrapper.build().createPV();
        BladeUser user = AuthUtil.getUser();
        Long userId = Long.valueOf(user.getUserId());
        Long deptId = Long.valueOf(AuthUtil.getDeptId());
        Date now = new Date();
        responseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
        responseVO.setCreateDeptName(SysCache.getDeptName(deptId));
        responseVO.setCreateSystemTime(now);
        return R.data(responseVO);
    }
}
