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

import org.springblade.cases.entity.ContractCaseClosedEntity;
import org.springblade.cases.wrapper.ContractCaseClosedWrapper;
import org.springblade.cases.service.IContractCaseClosedService;
import org.springblade.cases.vo.ContractCaseClosedRequestVO;
import org.springblade.cases.vo.ContractCaseClosedResponseVO;


/**
 * 案件结案 控制器
 *
 * @author : xhb
 * @date : 2020-10-30 10:03:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractCaseClosed")
@Api(value = "案件结案", tags = "案件结案")
public class ContractCaseClosedController extends BladeController {

	private IContractCaseClosedService contractCaseClosedService;
	private IContractCaseRegistrationService registrationService;
	private static final String CONTRACT_CASE_CLOSED_SAVE="40";
	private static final String CONTRACT_CASE_CLOSED_SUBMIT="50";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractCaseClosed")
	@PreAuth("hasPermission('contractCaseClose:contractCaseClosed:detail')")
	public R<ContractCaseClosedResponseVO> detail(@RequestParam Long id) {
		ContractCaseClosedEntity detail = contractCaseClosedService.getById(id);
		return R.data(ContractCaseClosedWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractCaseClosed")
	@PreAuth("hasPermission('contractCaseClose:contractCaseClosed:page')")
	public R<IPage<ContractCaseClosedResponseVO>> list(ContractCaseClosedRequestVO contractCaseClosed, Query query) {
		IPage<ContractCaseClosedEntity> pages = contractCaseClosedService.pageList(Condition.getPage(query), contractCaseClosed);
		return R.data(ContractCaseClosedWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractCaseClosed")
	@PreAuth("hasPermission('contractCaseClose:contractCaseClosed:add')")
	public R save(@Valid @RequestBody ContractCaseClosedResponseVO contractCaseClosed) {
		String caseStatus=CONTRACT_CASE_CLOSED_SAVE;
		registrationService.updateCaseStatusById(Long.valueOf(contractCaseClosed.getCloseCaseId()),caseStatus);
		return R.status(contractCaseClosedService.save(ContractCaseClosedWrapper.build().PVEntity(contractCaseClosed)));
	}

	/**
	 * 提交
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "提交", notes = "传入contractCaseClosed")
	@PreAuth("hasPermission('contractCaseClose:contractCaseClosed:submit')")
	public R submit(@Valid @RequestBody ContractCaseClosedResponseVO contractCaseClosed) {
		String caseStatus=CONTRACT_CASE_CLOSED_SUBMIT;
		registrationService.updateCaseStatusById(Long.valueOf(contractCaseClosed.getCloseCaseId()),caseStatus);
		return R.status(contractCaseClosedService.save(ContractCaseClosedWrapper.build().PVEntity(contractCaseClosed)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractCaseClosed")
	@PreAuth("hasPermission('contractCaseClose:contractCaseClosed:update')")
	public R update(@Valid @RequestBody ContractCaseClosedResponseVO contractCaseClosed) {
	    if (Func.isEmpty(contractCaseClosed.getId())){
            throw new ServiceException("id不能为空");
        }
		String caseStatus=CONTRACT_CASE_CLOSED_SUBMIT;
		registrationService.updateCaseStatusById(Long.valueOf(contractCaseClosed.getCloseCaseId()),caseStatus);
		return R.status(contractCaseClosedService.updateById(ContractCaseClosedWrapper.build().PVEntity(contractCaseClosed)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractCaseClose:contractCaseClosed:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractCaseClosedService.deleteLogic(Func.toLongList(ids)));
	}

}
