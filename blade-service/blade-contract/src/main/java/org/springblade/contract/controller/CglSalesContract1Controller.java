package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

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

import org.springblade.contract.entity.CglSalesContract1Entity;
import org.springblade.contract.wrapper.CglSalesContract1Wrapper;
import org.springblade.contract.service.ICglSalesContract1Service;
import org.springblade.contract.requestVO.CglSalesContract1RequestVO;
import org.springblade.contract.responseVO.CglSalesContract1ResponseVO;


/**
 * 采购类：买卖合同（国内设备购买）附表 控制器
 *
 * @author : 王策
 * @date : 2020-12-18 16:12:23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglSalesContract1")
@Api(value = "采购类：买卖合同（国内设备购买）附表", tags = "采购类：买卖合同（国内设备购买）附表")
public class CglSalesContract1Controller extends BladeController {

	private ICglSalesContract1Service cglSalesContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglSalesContract1")
	@PreAuth("hasPermission('cglSalesContract1:cglSalesContract1:detail')")
	public R<CglSalesContract1ResponseVO> detail(@RequestParam Long id) {
		CglSalesContract1Entity detail = cglSalesContract1Service.getById(id);
		return R.data(CglSalesContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglSalesContract1")
	@PreAuth("hasPermission('cglSalesContract1:cglSalesContract1:page')")
	public R<IPage<CglSalesContract1ResponseVO>> list(CglSalesContract1RequestVO cglSalesContract1, Query query) {
		IPage<CglSalesContract1Entity> pages = cglSalesContract1Service.pageList(Condition.getPage(query), cglSalesContract1);
		return R.data(CglSalesContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglSalesContract1")
	@PreAuth("hasPermission('cglSalesContract1:cglSalesContract1:add')")
	public R save(@Valid @RequestBody CglSalesContract1ResponseVO cglSalesContract1) {
		return R.status(cglSalesContract1Service.save(CglSalesContract1Wrapper.build().PVEntity(cglSalesContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglSalesContract1")
	@PreAuth("hasPermission('cglSalesContract1:cglSalesContract1:update')")
	public R update(@Valid @RequestBody CglSalesContract1ResponseVO cglSalesContract1) {
	    if (Func.isEmpty(cglSalesContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglSalesContract1Service.updateById(CglSalesContract1Wrapper.build().PVEntity(cglSalesContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglSalesContract1:cglSalesContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglSalesContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
