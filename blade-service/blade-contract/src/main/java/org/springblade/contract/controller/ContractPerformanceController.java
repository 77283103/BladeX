package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.contract.vo.ContractPerformanceRequestVO;
import org.springblade.contract.vo.ContractPerformanceResponseVO;
import org.springblade.core.tool.utils.BeanUtil;
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
 * 合同履约计划 控制器
 *
 * @author : liyj
 * @date : 2020-09-23 19:26:28
 */
@RestController
@AllArgsConstructor
@RequestMapping("/performance")
@Api(value = "合同履约计划", tags = "合同履约计划")
public class ContractPerformanceController extends BladeController {

	private IContractPerformanceService performanceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入performance")
	@PreAuth("hasPermission('contract:performance:detail')")
	public R<ContractPerformanceResponseVO> detail(@RequestParam Long id) {
		ContractPerformanceEntity detail = performanceService.getById(id);
		return R.data(ContractPerformanceWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入performance")
	@PreAuth("hasPermission('contract:performance:list')")
	public R<IPage<ContractPerformanceResponseVO>> list(ContractPerformanceEntity performance, Query query) {
		IPage<ContractPerformanceEntity> pages = performanceService.pageList(Condition.getPage(query), performance);
		return R.data(ContractPerformanceWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入performance")
	@PreAuth("hasPermission('contract:performance:add')")
	public R save(@Valid @RequestBody ContractPerformanceRequestVO performance) {
        ContractPerformanceEntity entity = new ContractPerformanceEntity();
        BeanUtil.copy(performance,entity);
		return R.status(performanceService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入performance")
	@PreAuth("hasPermission('contract:performance:update')")
	public R update(@Valid @RequestBody ContractPerformanceRequestVO performance) {
	    if (Func.isEmpty(performance.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractPerformanceEntity entity = new ContractPerformanceEntity();
        BeanUtil.copy(performance,entity);
		return R.status(performanceService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:performance:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(performanceService.deleteLogic(Func.toLongList(ids)));
	}

}
