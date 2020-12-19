package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ShjlVendingMachineEntity;
import org.springblade.contract.service.IShjlVendingMachineService;
import org.springblade.contract.vo.ShjlVendingMachineRequestVO;
import org.springblade.contract.vo.ShjlVendingMachineResponseVO;
import org.springblade.contract.wrapper.ShjlVendingMachineWrapper;
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
 * 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1) 控制器
 *
 * @author : 售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)
 * @date : 2020-12-18 16:14:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/shjlVendingMachine")
@Api(value = "售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)", tags = "售货机类：电子公章-（点位+运营）UP售货机合作合同（20191016）法务(1)")
public class ShjlVendingMachineController extends BladeController {

	private IShjlVendingMachineService shjlVendingMachineService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shjlVendingMachine")
	@PreAuth("hasPermission('shjlVendingMachine:shjlVendingMachine:detail')")
	public R<ShjlVendingMachineResponseVO> detail(@RequestParam Long id) {
		ShjlVendingMachineEntity detail = shjlVendingMachineService.getById(id);
		return R.data(ShjlVendingMachineWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shjlVendingMachine")
	@PreAuth("hasPermission('shjlVendingMachine:shjlVendingMachine:page')")
	public R<IPage<ShjlVendingMachineResponseVO>> list(ShjlVendingMachineRequestVO shjlVendingMachine, Query query) {
		IPage<ShjlVendingMachineEntity> pages = shjlVendingMachineService.pageList(Condition.getPage(query), shjlVendingMachine);
		return R.data(ShjlVendingMachineWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入shjlVendingMachine")
	@PreAuth("hasPermission('shjlVendingMachine:shjlVendingMachine:add')")
	public R save(@Valid @RequestBody ShjlVendingMachineResponseVO shjlVendingMachine) {
		return R.status(shjlVendingMachineService.save(ShjlVendingMachineWrapper.build().PVEntity(shjlVendingMachine)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入shjlVendingMachine")
	@PreAuth("hasPermission('shjlVendingMachine:shjlVendingMachine:update')")
	public R update(@Valid @RequestBody ShjlVendingMachineResponseVO shjlVendingMachine) {
	    if (Func.isEmpty(shjlVendingMachine.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(shjlVendingMachineService.updateById(ShjlVendingMachineWrapper.build().PVEntity(shjlVendingMachine)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('shjlVendingMachine:shjlVendingMachine:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(shjlVendingMachineService.deleteLogic(Func.toLongList(ids)));
	}

}
