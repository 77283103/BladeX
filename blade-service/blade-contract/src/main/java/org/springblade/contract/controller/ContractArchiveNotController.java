package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractArchiveNotEntity;
import org.springblade.contract.service.IContractArchiveNotService;
import org.springblade.contract.vo.ContractArchiveNotRequestVO;
import org.springblade.contract.vo.ContractArchiveNotResponseVO;
import org.springblade.contract.wrapper.ContractArchiveNotWrapper;
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
 * 未归档原因 控制器
 *
 * @author : 未归档原因
 * @date : 2020-11-09 15:19:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/archiveNot")
@Api(value = "未归档原因", tags = "未归档原因")
public class ContractArchiveNotController extends BladeController {

	private final IContractArchiveNotService contractArchiveNotService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractArchiveNot")
	@PreAuth("hasPermission('contract:archiveNot:detail')")
	public R<ContractArchiveNotResponseVO> detail(@RequestParam Long id) {
		ContractArchiveNotEntity detail = contractArchiveNotService.getById(id);
		return R.data(ContractArchiveNotWrapper.build().entityPV(detail));
	}
	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractArchiveNot")
	@PreAuth("hasPermission('contract:archiveNot:page')")
	public R<IPage<ContractArchiveNotResponseVO>> list(ContractArchiveNotRequestVO contractArchiveNot, Query query) {
		IPage<ContractArchiveNotEntity> pages = contractArchiveNotService.pageList(Condition.getPage(query), contractArchiveNot);
		return R.data(ContractArchiveNotWrapper.build().entityPVPage(pages));
	}
	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractArchiveNot")
	@PreAuth("hasPermission('contract:archiveNot:add')")
	public R save(@Valid @RequestBody ContractArchiveNotResponseVO contractArchiveNot) {
		return R.status(contractArchiveNotService.save(ContractArchiveNotWrapper.build().PVEntity(contractArchiveNot)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractArchiveNot")
	@PreAuth("hasPermission('contract:archiveNot:update')")
	public R update(@Valid @RequestBody ContractArchiveNotResponseVO contractArchiveNot) {
	    if (Func.isEmpty(contractArchiveNot.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractArchiveNotService.updateById(ContractArchiveNotWrapper.build().PVEntity(contractArchiveNot)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:archiveNot:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractArchiveNotService.deleteLogic(Func.toLongList(ids)));
	}

}
