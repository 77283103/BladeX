package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.MtbProductionContractRequestVO;
import org.springblade.contract.vo.MtbProductionContractResponseVO;
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

import org.springblade.contract.entity.MtbProductionContractEntity;
import org.springblade.contract.wrapper.MtbProductionContractWrapper;
import org.springblade.contract.service.IMtbProductionContractService;


/**
 * 媒体类：平面广告拍摄制作合同 控制器
 *
 * @author : 王策
 * @date : 2020-12-10 19:30:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtbProductionContract")
@Api(value = "媒体类：平面广告拍摄制作合同", tags = "媒体类：平面广告拍摄制作合同")
public class MtbProductionContractController extends BladeController {

	private IMtbProductionContractService mtbProductionContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtbProductionContract")
	@PreAuth("hasPermission('mtbProductionContract:mtbProductionContract:detail')")
	public R<MtbProductionContractResponseVO> detail(@RequestParam Long id) {
		MtbProductionContractEntity detail = mtbProductionContractService.getById(id);
		return R.data(MtbProductionContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtbProductionContract")
	@PreAuth("hasPermission('mtbProductionContract:mtbProductionContract:page')")
	public R<IPage<MtbProductionContractResponseVO>> list(MtbProductionContractRequestVO mtbProductionContract, Query query) {
		IPage<MtbProductionContractEntity> pages = mtbProductionContractService.pageList(Condition.getPage(query), mtbProductionContract);
		return R.data(MtbProductionContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtbProductionContract")
	@PreAuth("hasPermission('mtbProductionContract:mtbProductionContract:add')")
	public R save(@Valid @RequestBody MtbProductionContractResponseVO mtbProductionContract) {
		return R.status(mtbProductionContractService.save(MtbProductionContractWrapper.build().PVEntity(mtbProductionContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtbProductionContract")
	@PreAuth("hasPermission('mtbProductionContract:mtbProductionContract:update')")
	public R update(@Valid @RequestBody MtbProductionContractResponseVO mtbProductionContract) {
	    if (Func.isEmpty(mtbProductionContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtbProductionContractService.updateById(MtbProductionContractWrapper.build().PVEntity(mtbProductionContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtbProductionContract:mtbProductionContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtbProductionContractService.deleteLogic(Func.toLongList(ids)));
	}

}
