package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.CglLowCostHardwareRequestVO;
import org.springblade.contract.vo.CglLowCostHardwareResponseVO;
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

import org.springblade.contract.entity.CglLowCostHardwareEntity;
import org.springblade.contract.wrapper.CglLowCostHardwareWrapper;
import org.springblade.contract.service.ICglLowCostHardwareService;


/**
 * 采购类：买卖合同（五金低耗类） 控制器
 *
 * @author : 王策
 * @date : 2020-12-10 19:01:53
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglLowCostHardware")
@Api(value = "采购类：买卖合同（五金低耗类）", tags = "采购类：买卖合同（五金低耗类）")
public class CglLowCostHardwareController extends BladeController {

	private ICglLowCostHardwareService cglLowCostHardwareService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglLowCostHardware")
	@PreAuth("hasPermission('cglLowCostHardware:cglLowCostHardware:detail')")
	public R<CglLowCostHardwareResponseVO> detail(@RequestParam Long id) {
		CglLowCostHardwareEntity detail = cglLowCostHardwareService.getById(id);
		return R.data(CglLowCostHardwareWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglLowCostHardware")
	@PreAuth("hasPermission('cglLowCostHardware:cglLowCostHardware:page')")
	public R<IPage<CglLowCostHardwareResponseVO>> list(CglLowCostHardwareRequestVO cglLowCostHardware, Query query) {
		IPage<CglLowCostHardwareEntity> pages = cglLowCostHardwareService.pageList(Condition.getPage(query), cglLowCostHardware);
		return R.data(CglLowCostHardwareWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglLowCostHardware")
	@PreAuth("hasPermission('cglLowCostHardware:cglLowCostHardware:add')")
	public R save(@Valid @RequestBody CglLowCostHardwareResponseVO cglLowCostHardware) {
		return R.status(cglLowCostHardwareService.save(CglLowCostHardwareWrapper.build().PVEntity(cglLowCostHardware)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglLowCostHardware")
	@PreAuth("hasPermission('cglLowCostHardware:cglLowCostHardware:update')")
	public R update(@Valid @RequestBody CglLowCostHardwareResponseVO cglLowCostHardware) {
	    if (Func.isEmpty(cglLowCostHardware.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglLowCostHardwareService.updateById(CglLowCostHardwareWrapper.build().PVEntity(cglLowCostHardware)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglLowCostHardware:cglLowCostHardware:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglLowCostHardwareService.deleteLogic(Func.toLongList(ids)));
	}

}
