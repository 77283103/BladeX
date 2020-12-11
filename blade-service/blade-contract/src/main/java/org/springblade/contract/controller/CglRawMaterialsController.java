package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.CglRawMaterialsRequestVO;
import org.springblade.contract.vo.CglRawMaterialsResponseVO;
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

import org.springblade.contract.entity.CglRawMaterialsEntity;
import org.springblade.contract.wrapper.CglRawMaterialsWrapper;
import org.springblade.contract.service.ICglRawMaterialsService;


/**
 * 采购类：原物料-买卖合同 控制器
 *
 * @author : 王策
 * @date : 2020-12-10 19:17:20
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglRawMaterials")
@Api(value = "采购类：原物料-买卖合同", tags = "采购类：原物料-买卖合同")
public class CglRawMaterialsController extends BladeController {

	private ICglRawMaterialsService cglRawMaterialsService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglRawMaterials")
	@PreAuth("hasPermission('cglRawMaterials:cglRawMaterials:detail')")
	public R<CglRawMaterialsResponseVO> detail(@RequestParam Long id) {
		CglRawMaterialsEntity detail = cglRawMaterialsService.getById(id);
		return R.data(CglRawMaterialsWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglRawMaterials")
	@PreAuth("hasPermission('cglRawMaterials:cglRawMaterials:page')")
	public R<IPage<CglRawMaterialsResponseVO>> list(CglRawMaterialsRequestVO cglRawMaterials, Query query) {
		IPage<CglRawMaterialsEntity> pages = cglRawMaterialsService.pageList(Condition.getPage(query), cglRawMaterials);
		return R.data(CglRawMaterialsWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglRawMaterials")
	@PreAuth("hasPermission('cglRawMaterials:cglRawMaterials:add')")
	public R save(@Valid @RequestBody CglRawMaterialsResponseVO cglRawMaterials) {
		return R.status(cglRawMaterialsService.save(CglRawMaterialsWrapper.build().PVEntity(cglRawMaterials)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglRawMaterials")
	@PreAuth("hasPermission('cglRawMaterials:cglRawMaterials:update')")
	public R update(@Valid @RequestBody CglRawMaterialsResponseVO cglRawMaterials) {
	    if (Func.isEmpty(cglRawMaterials.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglRawMaterialsService.updateById(CglRawMaterialsWrapper.build().PVEntity(cglRawMaterials)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglRawMaterials:cglRawMaterials:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglRawMaterialsService.deleteLogic(Func.toLongList(ids)));
	}

}
