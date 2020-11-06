package org.springblade.cases.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.cases.entity.ContractCaseRegistrationEntity;
import org.springblade.cases.service.IContractCaseRegistrationService;
import org.springblade.cases.vo.ContractCaseRegistrationRequestVO;
import org.springblade.cases.vo.ContractCaseRegistrationResponseVO;
import org.springblade.cases.wrapper.ContractCaseRegistrationWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;


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
	private static final String CONTRACT_CASE_REGISTRATION_SAVE="10";
	private static final String CONTRACT_CASE_REGISTRATINO_REVIEW="20";
	private static final String CONTRACT_CASE_REGISTRATION_SUBMIT="30";


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
		String caseStatus=CONTRACT_CASE_REGISTRATION_SAVE;
		contractCaseRegistration.setCaseStatus(caseStatus);
		return R.status(contractCaseRegistrationService.save(ContractCaseRegistrationWrapper.build().PVEntity(contractCaseRegistration)));
	}

	/**
	 * 提交
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "提交", notes = "传入contractCaseRegistration")
	@PreAuth("hasPermission('contractCaseRegistation:contractCaseRegistration:submit')")
	public R submit(@Valid @RequestBody ContractCaseRegistrationResponseVO contractCaseRegistration) {
		String caseStatus=CONTRACT_CASE_REGISTRATION_SUBMIT;
		contractCaseRegistration.setCaseStatus(caseStatus);
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
		String caseStatus=CONTRACT_CASE_REGISTRATION_SUBMIT;
		contractCaseRegistration.setCaseStatus(caseStatus);
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

	/**
	 * 当前登录用户信息
	 */
	@GetMapping("/logInfo")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询登当前录用户信息")
	@PreAuth("hasPermission('contractCaseRegistation:contractCaseRegistration:logInfo')")
	public R<ContractCaseRegistrationResponseVO> logInfo() {
		ContractCaseRegistrationResponseVO responseVO = ContractCaseRegistrationWrapper.build().createPV();
		BladeUser user = AuthUtil.getUser();
		Long userId = Long.valueOf(user.getUserId());
		Long deptId = Long.valueOf(AuthUtil.getDeptId());
		Date now = new Date();
		responseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
		responseVO.setCreateDeptName(SysCache.getDeptName(deptId));
		return R.data(responseVO);
	}

}
