package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.CglRawMaterials1RequestVO;
import org.springblade.contract.vo.CglRawMaterials1ResponseVO;
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

import org.springblade.contract.entity.CglRawMaterials1Entity;
import org.springblade.contract.wrapper.CglRawMaterials1Wrapper;
import org.springblade.contract.service.ICglRawMaterials1Service;


/**
 * 采购类：原物料-买卖合同 控制器
 *
 * @author : 采购类：原物料-买卖合同
 * @date : 2020-12-10 18:54:32
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglRawMaterials1")
@Api(value = "采购类：原物料-买卖合同", tags = "采购类：原物料-买卖合同")
public class CglRawMaterials1Controller extends BladeController {

	private ICglRawMaterials1Service cglRawMaterials1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglRawMaterials1")
	@PreAuth("hasPermission('cglRawMaterials1:cglRawMaterials1:detail')")
	public R<CglRawMaterials1ResponseVO> detail(@RequestParam Long id) {
		CglRawMaterials1Entity detail = cglRawMaterials1Service.getById(id);
		return R.data(CglRawMaterials1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglRawMaterials1")
	@PreAuth("hasPermission('cglRawMaterials1:cglRawMaterials1:page')")
	public R<IPage<CglRawMaterials1ResponseVO>> list(CglRawMaterials1RequestVO cglRawMaterials1, Query query) {
		IPage<CglRawMaterials1Entity> pages = cglRawMaterials1Service.pageList(Condition.getPage(query), cglRawMaterials1);
		return R.data(CglRawMaterials1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglRawMaterials1")
	@PreAuth("hasPermission('cglRawMaterials1:cglRawMaterials1:add')")
	public R save(@Valid @RequestBody CglRawMaterials1ResponseVO cglRawMaterials1) {
		return R.status(cglRawMaterials1Service.save(CglRawMaterials1Wrapper.build().PVEntity(cglRawMaterials1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglRawMaterials1")
	@PreAuth("hasPermission('cglRawMaterials1:cglRawMaterials1:update')")
	public R update(@Valid @RequestBody CglRawMaterials1ResponseVO cglRawMaterials1) {
	    if (Func.isEmpty(cglRawMaterials1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglRawMaterials1Service.updateById(CglRawMaterials1Wrapper.build().PVEntity(cglRawMaterials1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglRawMaterials1:cglRawMaterials1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglRawMaterials1Service.deleteLogic(Func.toLongList(ids)));
	}

}
