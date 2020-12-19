package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclProductionCategoryEntity;
import org.springblade.contract.service.ISclProductionCategoryService;
import org.springblade.contract.vo.SclProductionCategoryRequestVO;
import org.springblade.contract.vo.SclProductionCategoryResponseVO;
import org.springblade.contract.wrapper.SclProductionCategoryWrapper;
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
 * 生产类：物流服务合同（一段+调拨运输） 控制器
 *
 * @author : kx
 * @date : 2020-12-18 17:18:02
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclProductionCategory")
@Api(value = "生产类：物流服务合同（一段+调拨运输）", tags = "生产类：物流服务合同（一段+调拨运输）")
public class SclProductionCategoryController extends BladeController {

	private ISclProductionCategoryService sclProductionCategoryService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclProductionCategory")
	@PreAuth("hasPermission('sclProductionCategory:sclProductionCategory:detail')")
	public R<SclProductionCategoryResponseVO> detail(@RequestParam Long id) {
		SclProductionCategoryEntity detail = sclProductionCategoryService.getById(id);
		return R.data(SclProductionCategoryWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclProductionCategory")
	@PreAuth("hasPermission('sclProductionCategory:sclProductionCategory:page')")
	public R<IPage<SclProductionCategoryResponseVO>> list(SclProductionCategoryRequestVO sclProductionCategory, Query query) {
		IPage<SclProductionCategoryEntity> pages = sclProductionCategoryService.pageList(Condition.getPage(query), sclProductionCategory);
		return R.data(SclProductionCategoryWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclProductionCategory")
	@PreAuth("hasPermission('sclProductionCategory:sclProductionCategory:add')")
	public R save(@Valid @RequestBody SclProductionCategoryResponseVO sclProductionCategory) {
		return R.status(sclProductionCategoryService.save(SclProductionCategoryWrapper.build().PVEntity(sclProductionCategory)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclProductionCategory")
	@PreAuth("hasPermission('sclProductionCategory:sclProductionCategory:update')")
	public R update(@Valid @RequestBody SclProductionCategoryResponseVO sclProductionCategory) {
	    if (Func.isEmpty(sclProductionCategory.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclProductionCategoryService.updateById(SclProductionCategoryWrapper.build().PVEntity(sclProductionCategory)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclProductionCategory:sclProductionCategory:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclProductionCategoryService.deleteLogic(Func.toLongList(ids)));
	}

}
