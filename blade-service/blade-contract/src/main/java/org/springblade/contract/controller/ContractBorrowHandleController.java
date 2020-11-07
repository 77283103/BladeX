package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.springblade.contract.service.IContractBorrowApplicationService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractBorrowHandleEntity;
import org.springblade.contract.wrapper.ContractBorrowHandleWrapper;
import org.springblade.contract.service.IContractBorrowHandleService;
import org.springblade.contract.vo.ContractBorrowHandleRequestVO;
import org.springblade.contract.vo.ContractBorrowHandleResponseVO;


/**
 * 借阅处理 控制器
 *
 * @author : xhb
 * @date : 2020-10-30 09:28:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractBorrowHandle")
@Api(value = "借阅处理", tags = "借阅处理")
public class ContractBorrowHandleController extends BladeController {

    private IContractBorrowHandleService contractBorrowHandleService;
    private IContractBorrowApplicationService contractBorrowApplicationService;
    private static final String BORROW_HANDLE_STATUS = "20";

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入contractBorrowHandle")
    @PreAuth("hasPermission('handling:contractBorrowHandle:detail')")
    public R<ContractBorrowHandleResponseVO> detail(@RequestParam Long id) {
        ContractBorrowHandleEntity detail = contractBorrowHandleService.getById(id);
        return R.data(ContractBorrowHandleWrapper.build().entityPV(detail));
    }

    /**
     * 分页
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入contractBorrowHandle")
    @PreAuth("hasPermission('handling:contractBorrowHandle:page')")
    public R<IPage<ContractBorrowHandleResponseVO>> list(ContractBorrowHandleRequestVO contractBorrowHandle, Query query) {
        IPage<ContractBorrowHandleEntity> pages = contractBorrowHandleService.pageList(Condition.getPage(query), contractBorrowHandle);
        return R.data(ContractBorrowHandleWrapper.build().entityPVPage(pages));
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入contractBorrowHandle")
    @PreAuth("hasPermission('handling:contractBorrowHandle:add')")
    public R save(@Valid @RequestBody ContractBorrowHandleResponseVO contractBorrowHandle) {
        String borrowStatus = BORROW_HANDLE_STATUS;
        contractBorrowApplicationService.updateBorrowStatusById(Long.valueOf(contractBorrowHandle.getHandleId()), borrowStatus);
        return R.status(contractBorrowHandleService.save(ContractBorrowHandleWrapper.build().PVEntity(contractBorrowHandle)));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入contractBorrowHandle")
    @PreAuth("hasPermission('handling:contractBorrowHandle:update')")
    public R update(@Valid @RequestBody ContractBorrowHandleResponseVO contractBorrowHandle) {
        if (Func.isEmpty(contractBorrowHandle.getId())) {
            throw new ServiceException("id不能为空");
        }
        return R.status(contractBorrowHandleService.updateById(ContractBorrowHandleWrapper.build().PVEntity(contractBorrowHandle)));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @PreAuth("hasPermission('handling:contractBorrowHandle:remove')")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(contractBorrowHandleService.deleteLogic(Func.toLongList(ids)));
    }

}
