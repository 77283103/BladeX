package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.SclEquipmentMaintenanceRequestVO;
import org.springblade.contract.vo.SclEquipmentMaintenanceResponseVO;
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

import org.springblade.contract.entity.SclEquipmentMaintenanceEntity;
import org.springblade.contract.wrapper.SclEquipmentMaintenanceWrapper;
import org.springblade.contract.service.ISclEquipmentMaintenanceService;


/**
 * 生产类：设备维修保养合同 控制器
 *
 * @author : kx
 * @date : 2020-12-11 10:56:35
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclEquipmentMaintenance")
@Api(value = "生产类：设备维修保养合同", tags = "生产类：设备维修保养合同")
public class SclEquipmentMaintenanceController extends BladeController {

	private ISclEquipmentMaintenanceService sclEquipmentMaintenanceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclEquipmentMaintenance")
	@PreAuth("hasPermission('sclEquipmentMaintenance:sclEquipmentMaintenance:detail')")
	public R<SclEquipmentMaintenanceResponseVO> detail(@RequestParam Long id) {
		SclEquipmentMaintenanceEntity detail = sclEquipmentMaintenanceService.getById(id);
		return R.data(SclEquipmentMaintenanceWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclEquipmentMaintenance")
	@PreAuth("hasPermission('sclEquipmentMaintenance:sclEquipmentMaintenance:page')")
	public R<IPage<SclEquipmentMaintenanceResponseVO>> list(SclEquipmentMaintenanceRequestVO sclEquipmentMaintenance, Query query) {
		IPage<SclEquipmentMaintenanceEntity> pages = sclEquipmentMaintenanceService.pageList(Condition.getPage(query), sclEquipmentMaintenance);
		return R.data(SclEquipmentMaintenanceWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclEquipmentMaintenance")
	@PreAuth("hasPermission('sclEquipmentMaintenance:sclEquipmentMaintenance:add')")
	public R save(@Valid @RequestBody SclEquipmentMaintenanceResponseVO sclEquipmentMaintenance) {
		return R.status(sclEquipmentMaintenanceService.save(SclEquipmentMaintenanceWrapper.build().PVEntity(sclEquipmentMaintenance)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclEquipmentMaintenance")
	@PreAuth("hasPermission('sclEquipmentMaintenance:sclEquipmentMaintenance:update')")
	public R update(@Valid @RequestBody SclEquipmentMaintenanceResponseVO sclEquipmentMaintenance) {
	    if (Func.isEmpty(sclEquipmentMaintenance.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclEquipmentMaintenanceService.updateById(SclEquipmentMaintenanceWrapper.build().PVEntity(sclEquipmentMaintenance)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclEquipmentMaintenance:sclEquipmentMaintenance:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclEquipmentMaintenanceService.deleteLogic(Func.toLongList(ids)));
	}

}
