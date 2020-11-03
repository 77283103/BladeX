package org.springblade.cases.controller;

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

import org.springblade.cases.entity.ContractCaseRegistrationEntity;
import org.springblade.cases.wrapper.ContractCaseRegistrationWrapper;
import org.springblade.cases.service.IContractCaseRegistrationService;
import org.springblade.cases.vo.ContractCaseRegistrationRequestVO;
import org.springblade.cases.vo.ContractCaseRegistrationResponseVO;


/**
 * 案件登记表 控制器
 *
 * @author : xhb
 * @date : 2020-10-30 10:04:47
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractCaseRegistration")
@Api(value = "案件登记表", tags = "案件登记表")
public class ContractCaseRegistrationController extends BladeController {

	private IContractCaseRegistrationService contractCaseRegistrationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractCaseRegistration")
	@PreAuth("hasPermission('contractCaseRegistation:contractCaseRegistration:detail')")
	public R<ContractCaseRegistrationResponseVO> detail(@RequestParam Long id) {
		ContractCaseRegistrationEntity detail = contractCaseRegistrationService.getById(id);
		return R.data(ContractCaseRegistrationWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractCaseRegistration")
	@PreAuth("hasPermission('contractCaseRegistation:contractCaseRegistration:page')")
	public R<IPage<ContractCaseRegistrationResponseVO>> list(ContractCaseRegistrationRequestVO contractCaseRegistration, Query query) {
		IPage<ContractCaseRegistrationEntity> pages = contractCaseRegistrationService.pageList(Condition.getPage(query), contractCaseRegistration);
		return R.data(ContractCaseRegistrationWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractCaseRegistration")
	@PreAuth("hasPermission('contractCaseRegistation:contractCaseRegistration:add')")
	public R save(@Valid @RequestBody ContractCaseRegistrationResponseVO contractCaseRegistration) {
		return R.status(contractCaseRegistrationService.save(ContractCaseRegistrationWrapper.build().PVEntity(contractCaseRegistration)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractCaseRegistration")
	@PreAuth("hasPermission('contractCaseRegistation:contractCaseRegistration:update')")
	public R update(@Valid @RequestBody ContractCaseRegistrationResponseVO contractCaseRegistration) {
	    if (Func.isEmpty(contractCaseRegistration.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractCaseRegistrationService.updateById(ContractCaseRegistrationWrapper.build().PVEntity(contractCaseRegistration)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractCaseRegistation:contractCaseRegistration:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractCaseRegistrationService.deleteLogic(Func.toLongList(ids)));
	}

}
