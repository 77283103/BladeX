package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

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

import org.springblade.contract.entity.ContractArchiveEntity;
import org.springblade.contract.wrapper.ContractArchiveWrapper;
import org.springblade.contract.service.IContractArchiveService;
import org.springblade.contract.vo.ContractArchiveRequestVO;
import org.springblade.contract.vo.ContractArchiveResponseVO;


/**
 * 合同归档 控制器
 *
 * @author : 合同归档
 * @date : 2020-11-05 09:41:38
 */
@RestController
@AllArgsConstructor
@RequestMapping("/archive")
@Api(value = "合同归档", tags = "合同归档")
public class ContractArchiveController extends BladeController {

	private IContractArchiveService contractArchiveService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractArchive")
	@PreAuth("hasPermission('contract:archive:detail')")
	public R<ContractArchiveResponseVO> detail(@RequestParam Long id) {
		ContractArchiveEntity detail = contractArchiveService.getById(id);
		return R.data(ContractArchiveWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractArchive")
	@PreAuth("hasPermission('contract:archive:page')")
	public R<IPage<ContractArchiveResponseVO>> list(ContractArchiveRequestVO contractArchive, Query query) {
		IPage<ContractArchiveEntity> pages = contractArchiveService.pageList(Condition.getPage(query), contractArchive);
		return R.data(ContractArchiveWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractArchive")
	@PreAuth("hasPermission('contract:archive:add')")
	public R save(@Valid @RequestBody ContractArchiveResponseVO contractArchive) {
		return R.status(contractArchiveService.save(ContractArchiveWrapper.build().PVEntity(contractArchive)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractArchive")
	@PreAuth("hasPermission('contract:archive:update')")
	public R update(@Valid @RequestBody ContractArchiveResponseVO contractArchive) {
	    if (Func.isEmpty(contractArchive.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractArchiveService.updateById(ContractArchiveWrapper.build().PVEntity(contractArchive)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:archive:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractArchiveService.deleteLogic(Func.toLongList(ids)));
	}

}
