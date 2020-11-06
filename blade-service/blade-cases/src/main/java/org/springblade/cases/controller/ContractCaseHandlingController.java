package org.springblade.cases.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.cases.service.IContractCaseRegistrationService;
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

import org.springblade.cases.entity.ContractCaseHandlingEntity;
import org.springblade.cases.wrapper.ContractCaseHandlingWrapper;
import org.springblade.cases.service.IContractCaseHandlingService;
import org.springblade.cases.vo.ContractCaseHandlingRequestVO;
import org.springblade.cases.vo.ContractCaseHandlingResponseVO;


/**
 * 案件处理 控制器
 *
 * @author : xhb
 * @date : 2020-10-30 10:03:46
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractCaseHandling")
@Api(value = "案件处理", tags = "案件处理")
public class ContractCaseHandlingController extends BladeController {

	private IContractCaseHandlingService contractCaseHandlingService;
	private IContractCaseRegistrationService registrationService;
	private static final String CONTRACT_CASE_HANDLE_SAVE="30";
	private static final String CONTRACT_CASE_HANDLE_SUBMIT="40";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractCaseHandling")
	@PreAuth("hasPermission('contractCaseHanding:contractCaseHandling:detail')")
	public R<ContractCaseHandlingResponseVO> detail(@RequestParam Long id) {
		ContractCaseHandlingEntity detail = contractCaseHandlingService.getById(id);
		return R.data(ContractCaseHandlingWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractCaseHandling")
	@PreAuth("hasPermission('contractCaseHanding:contractCaseHandling:page')")
	public R<IPage<ContractCaseHandlingResponseVO>> list(ContractCaseHandlingRequestVO contractCaseHandling, Query query) {
		IPage<ContractCaseHandlingEntity> pages = contractCaseHandlingService.pageList(Condition.getPage(query), contractCaseHandling);
		return R.data(ContractCaseHandlingWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractCaseHandling")
	@PreAuth("hasPermission('contractCaseHanding:contractCaseHandling:add')")
	public R save(@Valid @RequestBody ContractCaseHandlingResponseVO contractCaseHandling) {
		String caseStatus=CONTRACT_CASE_HANDLE_SAVE;
		registrationService.updateCaseStatusById(Long.valueOf(contractCaseHandling.getHandlingCaseId()),caseStatus);
		return R.status(contractCaseHandlingService.save(ContractCaseHandlingWrapper.build().PVEntity(contractCaseHandling)));
	}


	/**
	 * 提交
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "提交", notes = "传入contractCaseHandling")
	@PreAuth("hasPermission('contractCaseHanding:contractCaseHandling:submit')")
	public R submit(@Valid @RequestBody ContractCaseHandlingResponseVO contractCaseHandling) {
		String caseStatus=CONTRACT_CASE_HANDLE_SUBMIT;
		registrationService.updateCaseStatusById(Long.valueOf(contractCaseHandling.getHandlingCaseId()),caseStatus);
		return R.status(contractCaseHandlingService.save(ContractCaseHandlingWrapper.build().PVEntity(contractCaseHandling)));
	}
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractCaseHandling")
	@PreAuth("hasPermission('contractCaseHanding:contractCaseHandling:update')")
	public R update(@Valid @RequestBody ContractCaseHandlingResponseVO contractCaseHandling) {
	    if (Func.isEmpty(contractCaseHandling.getId())){
            throw new ServiceException("id不能为空");
        }
		String caseStatus=CONTRACT_CASE_HANDLE_SUBMIT;
		registrationService.updateCaseStatusById(Long.valueOf(contractCaseHandling.getHandlingCaseId()),caseStatus);
		return R.status(contractCaseHandlingService.updateById(ContractCaseHandlingWrapper.build().PVEntity(contractCaseHandling)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractCaseHanding:contractCaseHandling:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractCaseHandlingService.deleteLogic(Func.toLongList(ids)));
	}

}
