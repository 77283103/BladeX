package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.entity.SclServiceContractRefrigerationEntity;
import org.springblade.contract.service.ISclServiceContractRefrigerationService;
import org.springblade.contract.vo.SclServiceContractRefrigerationRequestVO;
import org.springblade.contract.vo.SclServiceContractRefrigerationResponseVO;
import org.springblade.contract.wrapper.SclServiceContractRefrigerationWrapper;
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



/**
 * kx 控制器
 *
 * @author : kx
 * @date : 2021-03-15 13:48:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/SclServiceContractRefrigeration")
@Api(value = "kx", tags = "kx")
public class SclServiceContractRefrigerationController extends BladeController {

	private ISclServiceContractRefrigerationService SclServiceContractRefrigerationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入SclServiceContractRefrigeration")
	@PreAuth("hasPermission('contractRawMaterials:SclServiceContractRefrigeration:detail')")
	public R<SclServiceContractRefrigerationResponseVO> detail(@RequestParam Long id) {
		SclServiceContractRefrigerationEntity detail = SclServiceContractRefrigerationService.getById(id);
		return R.data(SclServiceContractRefrigerationWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入SclServiceContractRefrigeration")
	@PreAuth("hasPermission('contractRawMaterials:SclServiceContractRefrigeration:page')")
	public R<IPage<SclServiceContractRefrigerationResponseVO>> list(SclServiceContractRefrigerationRequestVO SclServiceContractRefrigeration, Query query) {
		IPage<SclServiceContractRefrigerationEntity> pages = SclServiceContractRefrigerationService.pageList(Condition.getPage(query), SclServiceContractRefrigeration);
		return R.data(SclServiceContractRefrigerationWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入SclServiceContractRefrigeration")
	@PreAuth("hasPermission('contractRawMaterials:SclServiceContractRefrigeration:add')")
	public R save(@Valid @RequestBody SclServiceContractRefrigerationResponseVO SclServiceContractRefrigeration) {
		return R.status(SclServiceContractRefrigerationService.save(SclServiceContractRefrigerationWrapper.build().PVEntity(SclServiceContractRefrigeration)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入SclServiceContractRefrigeration")
	@PreAuth("hasPermission('contractRawMaterials:SclServiceContractRefrigeration:update')")
	public R update(@Valid @RequestBody SclServiceContractRefrigerationResponseVO SclServiceContractRefrigeration) {
	    if (Func.isEmpty(SclServiceContractRefrigeration.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(SclServiceContractRefrigerationService.updateById(SclServiceContractRefrigerationWrapper.build().PVEntity(SclServiceContractRefrigeration)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractRawMaterials:SclServiceContractRefrigeration:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(SclServiceContractRefrigerationService.deleteLogic(Func.toLongList(ids)));
	}

}
