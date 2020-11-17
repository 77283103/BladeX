package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.ContractPerformanceRequestVO;
import org.springblade.contract.vo.ContractPerformanceResponseVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractPerformanceEntity;
import org.springblade.contract.wrapper.ContractPerformanceWrapper;
import org.springblade.contract.service.IContractPerformanceService;


/**
 * 接收/提供服务计划清单 控制器
 *
 * @author : szw
 * @date : 2020-11-05 17:06:55
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractPerformance")
@Api(value = "接收/提供服务计划清单", tags = "接收/提供服务计划清单")
public class ContractPerformanceController extends BladeController {

	private IContractPerformanceService contractPerformanceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractPerformance")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:detail')")
	public R<ContractPerformanceResponseVO> detail(@RequestParam Long id) {
		ContractPerformanceEntity detail = contractPerformanceService.getById(id);
		return R.data(ContractPerformanceWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractPerformance")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:page')")
	public R<IPage<ContractPerformanceResponseVO>> list(ContractPerformanceRequestVO contractPerformance, Query query) {
		IPage<ContractPerformanceResponseVO> pages = contractPerformanceService.pageList(Condition.getPage(query), contractPerformance);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractPerformance")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:add')")
	public R save(@Valid @RequestBody ContractPerformanceResponseVO contractPerformance) {
		return R.status(contractPerformanceService.save(ContractPerformanceWrapper.build().PVEntity(contractPerformance)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractPerformance")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:update')")
	public R update(@Valid @RequestBody ContractPerformanceResponseVO contractPerformance) {
	    if (Func.isEmpty(contractPerformance.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractPerformanceService.updateById(ContractPerformanceWrapper.build().PVEntity(contractPerformance)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractPerformance:contractPerformance:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractPerformanceService.deleteLogic(Func.toLongList(ids)));
	}

}
