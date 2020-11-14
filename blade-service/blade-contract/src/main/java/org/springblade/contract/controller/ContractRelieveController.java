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

import org.springblade.contract.entity.ContractRelieveEntity;
import org.springblade.contract.wrapper.ContractRelieveWrapper;
import org.springblade.contract.service.IContractRelieveService;
import org.springblade.contract.vo.ContractRelieveRequestVO;
import org.springblade.contract.vo.ContractRelieveResponseVO;

import java.util.Date;


/**
 * 合同解除 控制器
 *
 * @author : 合同解除
 * @date : 2020-11-05 09:24:00
 */
@RestController
@AllArgsConstructor
@RequestMapping("/relieve")
@Api(value = "合同解除", tags = "合同解除")
public class ContractRelieveController extends BladeController {

	private IContractRelieveService contractRelieveService;
	private IContractFormInfoService formInfoService;
	private static final String CONTRACT_RELIEVE_SAVE="10";
	private static final String CONTRACT_RELIEVE_REVIEW="20";
	private static final String CONTRACT_RELIEVE_SUBMIT="30";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractRelieve")
	@PreAuth("hasPermission('contractRelieve:relieve:detail')")
	public R<ContractRelieveResponseVO> detail(@RequestParam Long id) {
		ContractRelieveEntity detail = contractRelieveService.getById(id);
		return R.data(ContractRelieveWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractRelieve")
	@PreAuth("hasPermission('contractRelieve:relieve:page')")
	public R<IPage<ContractRelieveResponseVO>> list(ContractRelieveRequestVO contractRelieve, Query query) {
		IPage<ContractRelieveEntity> pages = contractRelieveService.pageList(Condition.getPage(query), contractRelieve);
		return R.data(ContractRelieveWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractRelieve")
	@PreAuth("hasPermission('contractRelieve:relieve:add')")
	public R save(@Valid @RequestBody ContractRelieveResponseVO contractRelieve) {
		return R.status(contractRelieveService.save(ContractRelieveWrapper.build().PVEntity(contractRelieve)));
	}

	/**
	 * 提交
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "提交", notes = "传入contractCaseRegistration")
	@PreAuth("hasPermission('contractCaseRegistation:contractCaseRegistration:submit')")
	public R submit(@Valid @RequestBody ContractRelieveResponseVO relieveResponseVO) {
		String contractStatus=CONTRACT_RELIEVE_SUBMIT;
		formInfoService.updateExportStatus(contractStatus,relieveResponseVO.getRefContractId());
		return R.status(contractRelieveService.save(ContractRelieveWrapper.build().PVEntity(relieveResponseVO)));
	}
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractRelieve")
	@PreAuth("hasPermission('contractRelieve:relieve:update')")
	public R update(@Valid @RequestBody ContractRelieveResponseVO contractRelieve) {
	    if (Func.isEmpty(contractRelieve.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractRelieveService.updateById(ContractRelieveWrapper.build().PVEntity(contractRelieve)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractRelieve:relieve:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractRelieveService.deleteLogic(Func.toLongList(ids)));
	}
	/**
	 * 获取当前用户登录信息
	 * @return
	 */
	@GetMapping("/logInfo")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "查询登当前录用户信息")
	@PreAuth("hasPermission('contractRelieve:relieve:logInfo')")
	public R<ContractRelieveResponseVO> logInfo() {
		ContractRelieveResponseVO responseVO = ContractRelieveWrapper.build().createPV();
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
