package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.entity.ContractAssessmentEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.service.IContractAssessmentService;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.vo.ContractAssessmentRequestVO;
import org.springblade.contract.vo.ContractAssessmentResponseVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.wrapper.ContractAssessmentWrapper;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
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



/**
 * 合同评估表 控制器
 *
 * @author : liyj
 * @date : 2020-09-24 10:41:33
 */
@RestController
@AllArgsConstructor
@RequestMapping("/assessment")
@Api(value = "合同评估表", tags = "合同评估表")
public class ContractAssessmentController extends BladeController {

	private IContractAssessmentService assessmentService;
	private IContractFormInfoService contractFormInfoService;
	private static final String CONTRACT_ASSESSMENTS_CONTRACT_STATUS="100";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractAssessment")
	@PreAuth("hasPermission('contractAssessment:contractAssessment:detail')")
	public R<ContractAssessmentResponseVO> detail(@RequestParam Long id) {
		ContractAssessmentEntity detail = assessmentService.getById(id);
		return R.data(ContractAssessmentWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractAssessment")
	@PreAuth("hasPermission('contractAssessment:contractAssessment:page')")
	public R<IPage<ContractAssessmentResponseVO>> list(ContractAssessmentRequestVO contractAssessment, Query query) {
		IPage<ContractAssessmentEntity> pages = assessmentService.pageList(Condition.getPage(query), contractAssessment);
		return R.data(ContractAssessmentWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractAssessment")
	@PreAuth("hasPermission('contract:assessment:add')")
	public R save(@Valid @RequestBody ContractAssessmentResponseVO contractAssessment) {
		String contractStatus=CONTRACT_ASSESSMENTS_CONTRACT_STATUS;
		contractFormInfoService.updateExportStatus(contractStatus,contractAssessment.getContractId());
		return R.status(assessmentService.save(ContractAssessmentWrapper.build().PVEntity(contractAssessment)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入assessment")
	@PreAuth("hasPermission('contract:assessment:update')")
	public R update(@Valid @RequestBody ContractAssessmentRequestVO assessment) {
	    if (Func.isEmpty(assessment.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractAssessmentEntity entity = new ContractAssessmentEntity();
        BeanUtil.copy(assessment,entity);
		return R.status(assessmentService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:assessment:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(assessmentService.deleteLogic(Func.toLongList(ids)));
	}
}
