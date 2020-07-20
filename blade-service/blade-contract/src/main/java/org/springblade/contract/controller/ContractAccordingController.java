package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.common.constant.CommonConstant;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.vo.ContractAccordingVO;
import org.springblade.contract.wrapper.ContractAccordingWrapper;
import org.springblade.contract.service.IContractAccordingService;


/**
 *  合同依据管理控制器
 *
 * @author Feng
 */
@RestController
@AllArgsConstructor
@RequestMapping("/blade-contract/according")
@Api(value = "合同依据管理", tags = "合同依据管理")
public class ContractAccordingController extends BladeController {

	private final IContractAccordingService accordingService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入according")
	public R<ContractAccordingVO> detail(ContractAccordingEntity according) {
		ContractAccordingEntity detail = accordingService.getOne(Condition.getQueryWrapper(according));
		return R.data(ContractAccordingWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入according")
	public R<IPage<ContractAccordingVO>> list(ContractAccordingEntity according, Query query) {
		IPage<ContractAccordingEntity> pages = accordingService.pageList(Condition.getPage(query), according);
		return R.data(ContractAccordingWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入according")
	public R save(@Valid @RequestBody ContractAccordingEntity according) {
		return R.status(accordingService.save(according));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入according")
	public R update(@Valid @RequestBody ContractAccordingEntity according) {
		return R.status(accordingService.updateById(according));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(accordingService.deleteLogic(Func.toLongList(ids)));
	}

}
