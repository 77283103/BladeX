package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.MtlShootingAndProductionContractRequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContractResponseVO;
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

import org.springblade.contract.entity.MtlShootingAndProductionContractEntity;
import org.springblade.contract.wrapper.MtlShootingAndProductionContractWrapper;
import org.springblade.contract.service.IMtlShootingAndProductionContractService;


/**
 * 媒体类：视频广告拍摄制作合同 控制器
 *
 * @author : 媒体类：视频广告拍摄制作合同
 * @date : 2020-12-10 19:36:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlShootingAndProductionContract")
@Api(value = "媒体类：视频广告拍摄制作合同", tags = "媒体类：视频广告拍摄制作合同")
public class MtlShootingAndProductionContractController extends BladeController {

	private IMtlShootingAndProductionContractService mtlShootingAndProductionContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlShootingAndProductionContract")
	@PreAuth("hasPermission('mtlShootingAndProductionContract:mtlShootingAndProductionContract:detail')")
	public R<MtlShootingAndProductionContractResponseVO> detail(@RequestParam Long id) {
		MtlShootingAndProductionContractEntity detail = mtlShootingAndProductionContractService.getById(id);
		return R.data(MtlShootingAndProductionContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlShootingAndProductionContract")
	@PreAuth("hasPermission('mtlShootingAndProductionContract:mtlShootingAndProductionContract:page')")
	public R<IPage<MtlShootingAndProductionContractResponseVO>> list(MtlShootingAndProductionContractRequestVO mtlShootingAndProductionContract, Query query) {
		IPage<MtlShootingAndProductionContractEntity> pages = mtlShootingAndProductionContractService.pageList(Condition.getPage(query), mtlShootingAndProductionContract);
		return R.data(MtlShootingAndProductionContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlShootingAndProductionContract")
	@PreAuth("hasPermission('mtlShootingAndProductionContract:mtlShootingAndProductionContract:add')")
	public R save(@Valid @RequestBody MtlShootingAndProductionContractResponseVO mtlShootingAndProductionContract) {
		return R.status(mtlShootingAndProductionContractService.save(MtlShootingAndProductionContractWrapper.build().PVEntity(mtlShootingAndProductionContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlShootingAndProductionContract")
	@PreAuth("hasPermission('mtlShootingAndProductionContract:mtlShootingAndProductionContract:update')")
	public R update(@Valid @RequestBody MtlShootingAndProductionContractResponseVO mtlShootingAndProductionContract) {
	    if (Func.isEmpty(mtlShootingAndProductionContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlShootingAndProductionContractService.updateById(MtlShootingAndProductionContractWrapper.build().PVEntity(mtlShootingAndProductionContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlShootingAndProductionContract:mtlShootingAndProductionContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlShootingAndProductionContractService.deleteLogic(Func.toLongList(ids)));
	}

}
