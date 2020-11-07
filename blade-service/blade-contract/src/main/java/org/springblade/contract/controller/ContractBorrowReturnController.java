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

import org.springblade.contract.entity.ContractBorrowReturnEntity;
import org.springblade.contract.wrapper.ContractBorrowReturnWrapper;
import org.springblade.contract.service.IContractBorrowReturnService;
import org.springblade.contract.vo.ContractBorrowReturnRequestVO;
import org.springblade.contract.vo.ContractBorrowReturnResponseVO;


/**
 * 借阅归还 控制器
 *
 * @author : xhb
 * @date : 2020-10-30 09:28:53
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractBorrowReturn")
@Api(value = "借阅归还", tags = "借阅归还")
public class ContractBorrowReturnController extends BladeController {

    private IContractBorrowReturnService contractBorrowReturnService;
    private IContractBorrowApplicationService contractBorrowApplicationService;
    private static final String BORROW_RETURN_STATUS = "30";

    /**
     * 详情
     */
    @GetMapping("/detail")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入contractBorrowReturn")
    @PreAuth("hasPermission('return:contractBorrowReturn:detail')")
    public R<ContractBorrowReturnResponseVO> detail(@RequestParam Long id) {
        ContractBorrowReturnEntity detail = contractBorrowReturnService.getById(id);
        return R.data(ContractBorrowReturnWrapper.build().entityPV(detail));
    }

    /**
     * 分页
     */
    @GetMapping("/page")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入contractBorrowReturn")
    @PreAuth("hasPermission('return:contractBorrowReturn:page')")
    public R<IPage<ContractBorrowReturnResponseVO>> list(ContractBorrowReturnRequestVO contractBorrowReturn, Query query) {
        IPage<ContractBorrowReturnEntity> pages = contractBorrowReturnService.pageList(Condition.getPage(query), contractBorrowReturn);
        return R.data(ContractBorrowReturnWrapper.build().entityPVPage(pages));
    }

    /**
     * 新增
     */
    @PostMapping("/add")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增", notes = "传入contractBorrowReturn")
    @PreAuth("hasPermission('return:contractBorrowReturn:add')")
    public R save(@Valid @RequestBody ContractBorrowReturnResponseVO contractBorrowReturn) {
        String borrowStatus = BORROW_RETURN_STATUS;
        contractBorrowApplicationService.updateBorrowStatusById(Long.valueOf(contractBorrowReturn.getReturnId()), borrowStatus);
        return R.status(contractBorrowReturnService.save(ContractBorrowReturnWrapper.build().PVEntity(contractBorrowReturn)));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改", notes = "传入contractBorrowReturn")
    @PreAuth("hasPermission('return:contractBorrowReturn:update')")
    public R update(@Valid @RequestBody ContractBorrowReturnResponseVO contractBorrowReturn) {
        if (Func.isEmpty(contractBorrowReturn.getId())) {
            throw new ServiceException("id不能为空");
        }
        return R.status(contractBorrowReturnService.updateById(ContractBorrowReturnWrapper.build().PVEntity(contractBorrowReturn)));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "逻辑删除", notes = "传入ids")
    @PreAuth("hasPermission('return:contractBorrowReturn:remove')")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(contractBorrowReturnService.deleteLogic(Func.toLongList(ids)));
    }

}
