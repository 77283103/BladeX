package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.SclEquipmentMaintenance1RequestVO;
import org.springblade.contract.vo.SclEquipmentMaintenance1ResponseVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.SclEquipmentMaintenance1Entity;
import org.springblade.contract.wrapper.SclEquipmentMaintenance1Wrapper;
import org.springblade.contract.service.ISclEquipmentMaintenance1Service;


/**
 * 生产类：设备维修保养合同(关联表） 控制器
 *
 * @author : kx
 * @date : 2020-12-11 10:59:48
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclEquipmentMaintenance1")
@Api(value = "生产类：设备维修保养合同(关联表）", tags = "生产类：设备维修保养合同(关联表）")
public class SclEquipmentMaintenance1Controller extends BladeController {

	private ISclEquipmentMaintenance1Service sclEquipmentMaintenance1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclEquipmentMaintenance1")
	@PreAuth("hasPermission('sclEquipmentMaintenance1:sclEquipmentMaintenance1:detail')")
	public R<SclEquipmentMaintenance1ResponseVO> detail(@RequestParam Long id) {
		SclEquipmentMaintenance1Entity detail = sclEquipmentMaintenance1Service.getById(id);
		return R.data(SclEquipmentMaintenance1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclEquipmentMaintenance1")
	@PreAuth("hasPermission('sclEquipmentMaintenance1:sclEquipmentMaintenance1:page')")
	public R<IPage<SclEquipmentMaintenance1ResponseVO>> list(SclEquipmentMaintenance1RequestVO sclEquipmentMaintenance1, Query query) {
		IPage<SclEquipmentMaintenance1Entity> pages = sclEquipmentMaintenance1Service.pageList(Condition.getPage(query), sclEquipmentMaintenance1);
		return R.data(SclEquipmentMaintenance1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclEquipmentMaintenance1")
	@PreAuth("hasPermission('sclEquipmentMaintenance1:sclEquipmentMaintenance1:add')")
	public R save(@Valid @RequestBody SclEquipmentMaintenance1ResponseVO sclEquipmentMaintenance1) {
		return R.status(sclEquipmentMaintenance1Service.save(SclEquipmentMaintenance1Wrapper.build().PVEntity(sclEquipmentMaintenance1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclEquipmentMaintenance1")
	@PreAuth("hasPermission('sclEquipmentMaintenance1:sclEquipmentMaintenance1:update')")
	public R update(@Valid @RequestBody SclEquipmentMaintenance1ResponseVO sclEquipmentMaintenance1) {
	    if (Func.isEmpty(sclEquipmentMaintenance1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclEquipmentMaintenance1Service.updateById(SclEquipmentMaintenance1Wrapper.build().PVEntity(sclEquipmentMaintenance1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclEquipmentMaintenance1:sclEquipmentMaintenance1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclEquipmentMaintenance1Service.deleteLogic(Func.toLongList(ids)));
	}

}
