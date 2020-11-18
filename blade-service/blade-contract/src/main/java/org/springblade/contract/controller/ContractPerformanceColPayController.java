package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.ContractPerformanceColPayRequestVO;
import org.springblade.contract.vo.ContractPerformanceColPayResponseVO;
import org.springblade.contract.wrapper.ContractPerformanceColPayWrapper;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractPerformanceColPayEntity;
import org.springblade.contract.service.IContractPerformanceColPayService;


/**
 * 收付款计划清单-收付款 控制器
 *
 * @author : szw
 * @date : 2020-11-05 17:07:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractPerformanceColPay")
@Api(value = "收付款计划清单-收付款", tags = "收付款计划清单-收付款")
public class ContractPerformanceColPayController extends BladeController {

	private IContractPerformanceColPayService contractPerformanceColPayService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractPerformanceColPay")
	@PreAuth("hasPermission('contractPerformanceColPay:contractPerformanceColPay:detail')")
	public R<ContractPerformanceColPayResponseVO> detail(@RequestParam Long id) {
		ContractPerformanceColPayEntity detail = contractPerformanceColPayService.getById(id);
		return R.data(ContractPerformanceColPayWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractPerformanceColPay")
	@PreAuth("hasPermission('contractPerformanceColPay:contractPerformanceColPay:page')")
	public R<IPage<ContractPerformanceColPayResponseVO>> list(ContractPerformanceColPayRequestVO contractPerformanceColPay, Query query) {
		IPage<ContractPerformanceColPayResponseVO> pages = contractPerformanceColPayService.pageList(Condition.getPage(query), contractPerformanceColPay);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractPerformanceColPay")
	@PreAuth("hasPermission('contractPerformanceColPay:contractPerformanceColPay:add')")
	public R save(@Valid @RequestBody ContractPerformanceColPayResponseVO contractPerformanceColPay) {
		return R.status(contractPerformanceColPayService.save(ContractPerformanceColPayWrapper.build().PVEntity(contractPerformanceColPay)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractPerformanceColPay")
	@PreAuth("hasPermission('contractPerformanceColPay:contractPerformanceColPay:update')")
	public R update(@Valid @RequestBody ContractPerformanceColPayResponseVO contractPerformanceColPay) {
	    if (Func.isEmpty(contractPerformanceColPay.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractPerformanceColPayService.updateById(ContractPerformanceColPayWrapper.build().PVEntity(contractPerformanceColPay)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractPerformanceColPay:contractPerformanceColPay:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractPerformanceColPayService.deleteLogic(Func.toLongList(ids)));
	}

}
