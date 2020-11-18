package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractBondPlanEntity;
import org.springblade.contract.service.IContractBondPlanService;
import org.springblade.contract.service.IContractBondService;
import org.springblade.contract.vo.ContractBondPlanRequestVO;
import org.springblade.contract.vo.ContractBondPlanResponseVO;
import org.springblade.contract.vo.ContractBondRequestVO;
import org.springblade.contract.vo.ContractBondResponseVO;
import org.springblade.contract.wrapper.ContractBondPlanWrapper;
import org.springblade.contract.wrapper.ContractBondWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 保证金 控制器
 *
 * @author : szw
 * @date : 2020-11-04 18:28:10
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractBondPlan")
@Api(value = "保证金", tags = "保证金")
public class ContractBondPlanController extends BladeController {

	private IContractBondPlanService contractBondService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractBond")
	@PreAuth("hasPermission('contractBond:contractBondPlan:detail')")
	public R<ContractBondPlanResponseVO> detail(@RequestParam Long id) {
		ContractBondPlanEntity detail = contractBondService.getById(id);
		return R.data(ContractBondPlanWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractBond")
	@PreAuth("hasPermission('contractBond:contractBondPlan:page')")
	public R<IPage<ContractBondPlanResponseVO>> list(ContractBondPlanRequestVO contractBond, Query query) {
		IPage<ContractBondPlanResponseVO> pages = contractBondService.pageList(Condition.getPage(query), contractBond);
		return R.data(pages);
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractBond")
	@PreAuth("hasPermission('contractBond:contractBondPlan:add')")
	public R save(@Valid @RequestBody ContractBondPlanResponseVO contractBond) {
		return R.status(contractBondService.save(ContractBondPlanWrapper.build().PVEntity(contractBond)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractBond")
	@PreAuth("hasPermission('contractBond:contractBondPlan:update')")
	public R update(@Valid @RequestBody ContractBondPlanResponseVO contractBond) {
	    if (Func.isEmpty(contractBond.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractBondService.updateById(ContractBondPlanWrapper.build().PVEntity(contractBond)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractBond:contractBondPlan:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractBondService.deleteLogic(Func.toLongList(ids)));
	}

}
