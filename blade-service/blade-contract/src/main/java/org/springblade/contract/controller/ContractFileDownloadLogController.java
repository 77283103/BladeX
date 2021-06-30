package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractFileDownloadLogEntity;
import org.springblade.contract.service.IContractFileDownloadLogService;
import org.springblade.contract.vo.ContractFileDownloadLogRequestVO;
import org.springblade.contract.vo.ContractFileDownloadLogResponseVO;
import org.springblade.contract.wrapper.ContractFileDownloadLogWrapper;
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
 * 合同文件日志 控制器
 *
 * @author : wpf
 * @date : 2021-06-23 10:30:35
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractFileDownloadLog")
@Api(value = "合同文件日志", tags = "合同文件日志")
public class ContractFileDownloadLogController extends BladeController {

	private final IContractFileDownloadLogService contractFileDownloadLogService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractFileDownloadLog")
	public R<ContractFileDownloadLogResponseVO> detail(@RequestParam Long id) {
		ContractFileDownloadLogEntity detail = contractFileDownloadLogService.getById(id);
		return R.data(ContractFileDownloadLogWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractFileDownloadLog")
	public R<IPage<ContractFileDownloadLogResponseVO>> list(ContractFileDownloadLogRequestVO contractFileDownloadLog, Query query) {
		IPage<ContractFileDownloadLogEntity> pages = contractFileDownloadLogService.pageList(Condition.getPage(query), contractFileDownloadLog);
		return R.data(ContractFileDownloadLogWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractFileDownloadLog")
	@PreAuth("hasPermission('contractFileDownloadLog:contractFileDownloadLog:add')")
	public R save(@Valid @RequestBody ContractFileDownloadLogResponseVO contractFileDownloadLog) {
		return R.status(contractFileDownloadLogService.save(ContractFileDownloadLogWrapper.build().PVEntity(contractFileDownloadLog)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractFileDownloadLog")
	@PreAuth("hasPermission('contractFileDownloadLog:contractFileDownloadLog:update')")
	public R update(@Valid @RequestBody ContractFileDownloadLogResponseVO contractFileDownloadLog) {
	    if (Func.isEmpty(contractFileDownloadLog.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractFileDownloadLogService.updateById(ContractFileDownloadLogWrapper.build().PVEntity(contractFileDownloadLog)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractFileDownloadLog:contractFileDownloadLog:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractFileDownloadLogService.deleteLogic(Func.toLongList(ids)));
	}

}
