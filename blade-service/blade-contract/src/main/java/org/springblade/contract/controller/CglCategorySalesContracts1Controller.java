package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.CglCategorySalesContracts1RequestVO;
import org.springblade.contract.vo.CglCategorySalesContracts1ResponseVO;
import org.springblade.contract.wrapper.CglCategorySalesContracts1Wrapper;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.CglCategorySalesContracts1Entity;
import org.springblade.contract.service.ICglCategorySalesContracts1Service;


/**
 * 采购类：买卖合同（行销品） 控制器
 *
 * @author : 采购类：买卖合同（行销品）
 * @date : 2020-12-10 18:58:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglCategorySalesContracts1")
@Api(value = "采购类：买卖合同（行销品）", tags = "采购类：买卖合同（行销品）")
public class CglCategorySalesContracts1Controller extends BladeController {

	private ICglCategorySalesContracts1Service cglCategorySalesContracts1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglCategorySalesContracts1")
	@PreAuth("hasPermission('cglCategorySalesContracts1:cglCategorySalesContracts1:detail')")
	public R<CglCategorySalesContracts1ResponseVO> detail(@RequestParam Long id) {
		CglCategorySalesContracts1Entity detail = cglCategorySalesContracts1Service.getById(id);
		return R.data(CglCategorySalesContracts1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglCategorySalesContracts1")
	@PreAuth("hasPermission('cglCategorySalesContracts1:cglCategorySalesContracts1:page')")
	public R<IPage<CglCategorySalesContracts1ResponseVO>> list(CglCategorySalesContracts1RequestVO cglCategorySalesContracts1, Query query) {
		IPage<CglCategorySalesContracts1Entity> pages = cglCategorySalesContracts1Service.pageList(Condition.getPage(query), cglCategorySalesContracts1);
		return R.data(CglCategorySalesContracts1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglCategorySalesContracts1")
	@PreAuth("hasPermission('cglCategorySalesContracts1:cglCategorySalesContracts1:add')")
	public R save(@Valid @RequestBody CglCategorySalesContracts1ResponseVO cglCategorySalesContracts1) {
		return R.status(cglCategorySalesContracts1Service.save(CglCategorySalesContracts1Wrapper.build().PVEntity(cglCategorySalesContracts1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglCategorySalesContracts1")
	@PreAuth("hasPermission('cglCategorySalesContracts1:cglCategorySalesContracts1:update')")
	public R update(@Valid @RequestBody CglCategorySalesContracts1ResponseVO cglCategorySalesContracts1) {
	    if (Func.isEmpty(cglCategorySalesContracts1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglCategorySalesContracts1Service.updateById(CglCategorySalesContracts1Wrapper.build().PVEntity(cglCategorySalesContracts1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglCategorySalesContracts1:cglCategorySalesContracts1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglCategorySalesContracts1Service.deleteLogic(Func.toLongList(ids)));
	}

}
