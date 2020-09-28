package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.contract.vo.ContractArchiveRequestVO;
import org.springblade.contract.vo.ContractArchiveResponseVO;
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


/**
 * 合同归档管理 控制器
 *
 * @author : XHB
 * @date : 2020-09-23 18:32:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/archive")
@Api(value = "合同归档管理", tags = "合同归档管理")
public class ContractArchiveController extends BladeController {

	private IContractArchiveService archiveService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入archive")
	@PreAuth("hasPermission('contract:archive:detail')")
	public R<ContractArchiveResponseVO> detail(@RequestParam Long id) {
		ContractArchiveEntity detail = archiveService.getById(id);
		return R.data(ContractArchiveWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入archive")
	@PreAuth("hasPermission('contract:archive:list')")
	public R<IPage<ContractArchiveResponseVO>> list(ContractArchiveEntity archive, Query query) {
		IPage<ContractArchiveEntity> pages = archiveService.pageList(Condition.getPage(query), archive);
		return R.data(ContractArchiveWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入archive")
	@PreAuth("hasPermission('contract:archive:add')")
	public R save(@Valid @RequestBody ContractArchiveRequestVO archive) {
        ContractArchiveEntity entity = new ContractArchiveEntity();
        BeanUtil.copy(archive,entity);
		return R.status(archiveService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入archive")
	@PreAuth("hasPermission('contract:archive:update')")
	public R update(@Valid @RequestBody ContractArchiveRequestVO archive) {
	    if (Func.isEmpty(archive.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractArchiveEntity entity = new ContractArchiveEntity();
        BeanUtil.copy(archive,entity);
		return R.status(archiveService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:archive:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(archiveService.deleteLogic(Func.toLongList(ids)));
	}

}
