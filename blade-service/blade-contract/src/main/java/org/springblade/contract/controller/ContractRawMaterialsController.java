package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.ContractRawMaterialsRequestVO;
import org.springblade.contract.vo.ContractRawMaterialsResponseVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractRawMaterialsEntity;
import org.springblade.contract.wrapper.ContractRawMaterialsWrapper;
import org.springblade.contract.service.IContractRawMaterialsService;


/**
 * 原物料1v多 控制器
 *
 * @author : szw
 * @date : 2020-11-22 16:42:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractRawMaterials")
@Api(value = "原物料1v多", tags = "原物料1v多")
public class ContractRawMaterialsController extends BladeController {

	private IContractRawMaterialsService contractRawMaterialsService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractRawMaterials")
	@PreAuth("hasPermission('contractRawMaterials:contractRawMaterials:detail')")
	public R<ContractRawMaterialsResponseVO> detail(@RequestParam Long id) {
		ContractRawMaterialsEntity detail = contractRawMaterialsService.getById(id);
		return R.data(ContractRawMaterialsWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractRawMaterials")
	@PreAuth("hasPermission('contractRawMaterials:contractRawMaterials:page')")
	public R<IPage<ContractRawMaterialsResponseVO>> list(ContractRawMaterialsRequestVO contractRawMaterials, Query query) {
		IPage<ContractRawMaterialsEntity> pages = contractRawMaterialsService.pageList(Condition.getPage(query), contractRawMaterials);
		return R.data(ContractRawMaterialsWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractRawMaterials")
	@PreAuth("hasPermission('contractRawMaterials:contractRawMaterials:add')")
	public R save(@Valid @RequestBody ContractRawMaterialsResponseVO contractRawMaterials) {
		return R.status(contractRawMaterialsService.save(ContractRawMaterialsWrapper.build().PVEntity(contractRawMaterials)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractRawMaterials")
	@PreAuth("hasPermission('contractRawMaterials:contractRawMaterials:update')")
	public R update(@Valid @RequestBody ContractRawMaterialsResponseVO contractRawMaterials) {
	    if (Func.isEmpty(contractRawMaterials.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractRawMaterialsService.updateById(ContractRawMaterialsWrapper.build().PVEntity(contractRawMaterials)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractRawMaterials:contractRawMaterials:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractRawMaterialsService.deleteLogic(Func.toLongList(ids)));
	}

}
