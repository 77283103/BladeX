package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractSealUsingInfoResponseVO;
import org.springblade.contract.wrapper.ContractSealUsingInfoWrapper;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.contract.vo.ContractSigningRequestVO;
import org.springblade.contract.vo.ContractSigningResponseVO;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.cache.SysCache;
import org.springblade.system.user.cache.UserCache;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractSigningEntity;
import org.springblade.contract.wrapper.ContractSigningWrapper;
import org.springblade.contract.service.IContractSigningService;

import java.util.Date;


/**
 * 合同签订表 控制器
 *
 * @author : liyj
 * @date : 2020-09-23 19:27:05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/signing")
@Api(value = "合同签订表", tags = "合同签订表")
public class ContractSigningController extends BladeController {

	private IContractSigningService contractSigningService;
	private IContractFormInfoService contractFormInfoService;
	private static final String CONTRACT_SIGNING_SAVE_STATUS="60";
	private static final String CONTRACT_CONTRACT_FORM_VALUE="1";
	private static final String CONTRACT_ARCHIVE_STATUS="110";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractSigning")
	@PreAuth("hasPermission('contract:signing:detail')")
	public R<ContractSigningResponseVO> detail(@RequestParam Long id) {
		ContractSigningEntity detail = contractSigningService.getById(id);
		return R.data(ContractSigningWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractSigning")
	@PreAuth("hasPermission('contract:signing:page')")
	public R<IPage<ContractSigningResponseVO>> list(ContractSigningRequestVO contractSigning, Query query) {
		IPage<ContractSigningEntity> pages = contractSigningService.pageList(Condition.getPage(query), contractSigning);
		return R.data(ContractSigningWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractSigning")
	@PreAuth("hasPermission('contract:signing:add')")
	public R save(@Valid @RequestBody ContractSigningResponseVO contractSigning) {
		String contractForm=contractFormInfoService.getById(contractSigning.getContractId()).getContractForm();
		if (CONTRACT_CONTRACT_FORM_VALUE.equals(contractForm)){
			String contractStatus=CONTRACT_ARCHIVE_STATUS;
			contractFormInfoService.updateExportStatus(contractStatus,contractSigning.getContractId());
		}else {
			String contractStatus = CONTRACT_SIGNING_SAVE_STATUS;
			contractFormInfoService.updateExportStatus(contractStatus, contractSigning.getContractId());
		}
		return R.status(contractSigningService.save(ContractSigningWrapper.build().PVEntity(contractSigning)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractSigning")
	@PreAuth("hasPermission('contract:signing:update')")
	public R update(@Valid @RequestBody ContractSigningResponseVO contractSigning) {
		if (Func.isEmpty(contractSigning.getId())){
			throw new ServiceException("id不能为空");
		}
		return R.status(contractSigningService.updateById(ContractSigningWrapper.build().PVEntity(contractSigning)));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:signing:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractSigningService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 获取当前用户登录信息
	 * @return
	 */
	@GetMapping("/logInfo")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询登当前录用户信息")
	@PreAuth("hasPermission('contract:signing:logInfo')")
	public R<ContractSigningResponseVO> logInfo() {
		ContractSigningResponseVO responseVO = ContractSigningWrapper.build().createPV();
		BladeUser user = AuthUtil.getUser();
		Long userId = Long.valueOf(user.getUserId());
		Long deptId = Long.valueOf(AuthUtil.getDeptId());
		Date now = new Date();
		responseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
		responseVO.setCreateDeptName(SysCache.getDeptName(deptId));
		return R.data(responseVO);
	}
}
