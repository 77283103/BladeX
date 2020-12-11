package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.CglCategorySalesContractsRequestVO;
import org.springblade.contract.vo.CglCategorySalesContractsResponseVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.CglCategorySalesContractsEntity;
import org.springblade.contract.wrapper.CglCategorySalesContractsWrapper;
import org.springblade.contract.service.ICglCategorySalesContractsService;


/**
 * 采购类：买卖合同（行销品） 控制器
 *
 * @author : 王策
 * @date : 2020-12-10 18:52:43
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglCategorySalesContracts")
@Api(value = "采购类：买卖合同（行销品）", tags = "采购类：买卖合同（行销品）")
public class CglCategorySalesContractsController extends BladeController {

	private ICglCategorySalesContractsService cglCategorySalesContractsService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglCategorySalesContracts")
	@PreAuth("hasPermission('cglCategorySalesContracts:cglCategorySalesContracts:detail')")
	public R<CglCategorySalesContractsResponseVO> detail(@RequestParam Long id) {
		CglCategorySalesContractsEntity detail = cglCategorySalesContractsService.getById(id);
		return R.data(CglCategorySalesContractsWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglCategorySalesContracts")
	@PreAuth("hasPermission('cglCategorySalesContracts:cglCategorySalesContracts:page')")
	public R<IPage<CglCategorySalesContractsResponseVO>> list(CglCategorySalesContractsRequestVO cglCategorySalesContracts, Query query) {
		IPage<CglCategorySalesContractsEntity> pages = cglCategorySalesContractsService.pageList(Condition.getPage(query), cglCategorySalesContracts);
		return R.data(CglCategorySalesContractsWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglCategorySalesContracts")
	@PreAuth("hasPermission('cglCategorySalesContracts:cglCategorySalesContracts:add')")
	public R save(@Valid @RequestBody CglCategorySalesContractsResponseVO cglCategorySalesContracts) {
		return R.status(cglCategorySalesContractsService.save(CglCategorySalesContractsWrapper.build().PVEntity(cglCategorySalesContracts)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglCategorySalesContracts")
	@PreAuth("hasPermission('cglCategorySalesContracts:cglCategorySalesContracts:update')")
	public R update(@Valid @RequestBody CglCategorySalesContractsResponseVO cglCategorySalesContracts) {
	    if (Func.isEmpty(cglCategorySalesContracts.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglCategorySalesContractsService.updateById(CglCategorySalesContractsWrapper.build().PVEntity(cglCategorySalesContracts)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglCategorySalesContracts:cglCategorySalesContracts:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglCategorySalesContractsService.deleteLogic(Func.toLongList(ids)));
	}

}
