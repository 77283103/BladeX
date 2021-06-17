package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractSealUsingInfoEntity;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.service.IContractSealUsingInfoService;
import org.springblade.contract.vo.ContractSealUsingInfoRequestVO;
import org.springblade.contract.vo.ContractSealUsingInfoResponseVO;
import org.springblade.contract.wrapper.ContractSealUsingInfoWrapper;
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
 * 用印名称 控制器
 *
 * @author : szw
 * @date : 2020-09-24 01:27:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sealInfo")
@Api(value = "用印名称", tags = "用印名称")
public class ContractSealUsingInfoController extends BladeController {

	private final IContractSealUsingInfoService contractSealUsingInfoService;
	private final IContractFormInfoService contractFormInfoService;
	private static final String CONTRACT_SEAL_INFO_SAVE_STATUS="45";
	private static final String CONTRACT_SEAL_INFO_SUBMIT_STATUS="50";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractSealUsingInfo")
	@PreAuth("hasPermission('signInfo:sealInfo:detail')")
	public R<ContractSealUsingInfoResponseVO> detail(@RequestParam Long id) {
		ContractSealUsingInfoEntity detail = contractSealUsingInfoService.getById(id);
		return R.data(ContractSealUsingInfoWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractSealUsingInfo")
	@PreAuth("hasPermission('signInfo:sealInfo:page')")
	public R<IPage<ContractSealUsingInfoResponseVO>> list(ContractSealUsingInfoRequestVO contractSealUsingInfo, Query query) {
		IPage<ContractSealUsingInfoEntity> pages = contractSealUsingInfoService.pageList(Condition.getPage(query), contractSealUsingInfo);
		return R.data(ContractSealUsingInfoWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractSealUsingInfo")
	@PreAuth("hasPermission('signInfo:sealInfo:add')")
	public R save(@Valid @RequestBody ContractSealUsingInfoResponseVO contractSealUsingInfo) {
		contractFormInfoService.updateExportStatus(CONTRACT_SEAL_INFO_SAVE_STATUS,contractSealUsingInfo.getRefContractId());
		return R.status(contractSealUsingInfoService.save(ContractSealUsingInfoWrapper.build().PVEntity(contractSealUsingInfo)));
	}

	/**
	 * 提交
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractSealUsingInfo")
	@PreAuth("hasPermission('signInfo:sealInfo:submit')")
	public R submit(@Valid @RequestBody ContractSealUsingInfoResponseVO contractSealUsingInfo) {
		contractFormInfoService.updateExportStatus(CONTRACT_SEAL_INFO_SUBMIT_STATUS,contractSealUsingInfo.getRefContractId());
		return R.status(contractSealUsingInfoService.save(ContractSealUsingInfoWrapper.build().PVEntity(contractSealUsingInfo)));
	}
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractSealUsingInfo")
	@PreAuth("hasPermission('signInfo:sealInfo:update')")
	public R update(@Valid @RequestBody ContractSealUsingInfoResponseVO contractSealUsingInfo) {
		if (Func.isEmpty(contractSealUsingInfo.getId())){
			throw new ServiceException("id不能为空");
		}
		contractFormInfoService.updateExportStatus(CONTRACT_SEAL_INFO_SUBMIT_STATUS,contractSealUsingInfo.getRefContractId());
		return R.status(contractSealUsingInfoService.updateById(ContractSealUsingInfoWrapper.build().PVEntity(contractSealUsingInfo)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('signInfo:sealInfo:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractSealUsingInfoService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 获取当前用户登录信息
	 * @return
	 */
	@GetMapping("/logInfo")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询登当前录用户信息")
	@PreAuth("hasPermission('signInfo:sealInfo:logInfo')")
	public R<ContractSealUsingInfoResponseVO> logInfo() {
		ContractSealUsingInfoResponseVO responseVO = ContractSealUsingInfoWrapper.build().createPV();
		BladeUser user = AuthUtil.getUser();
		Long userId = Long.valueOf(user.getUserId());
		Long deptId = Long.valueOf(AuthUtil.getDeptId());
		Date now = new Date();
		responseVO.setCreateUserName(UserCache.getUser(userId).getRealName());
		responseVO.setCreateDeptName(SysCache.getDeptName(deptId));
		responseVO.setCreateSystemTime(now);
		return R.data(responseVO);
	}
}
