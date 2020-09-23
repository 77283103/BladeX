package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.AssessmentEntity;
import org.springblade.contract.service.IAssessmentService;
import org.springblade.contract.vo.AssessmentRequestVO;
import org.springblade.contract.vo.AssessmentResponseVO;
import org.springblade.contract.wrapper.AssessmentWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 合同评估表 控制器
 *
 * @author : liyj
 * @date : 2020-09-23 15:50:00
 */
@RestController
@AllArgsConstructor
@RequestMapping("/assessment")
@Api(value = "合同评估表", tags = "合同评估表")
public class AssessmentController extends BladeController {

	private IAssessmentService assessmentService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入assessment")
	@PreAuth("hasPermission('contractAssessment:assessment:detail')")
	public R<AssessmentResponseVO> detail(@RequestParam Long id) {
		AssessmentEntity detail = assessmentService.getById(id);
		return R.data(AssessmentWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入assessment")
	@PreAuth("hasPermission('contractAssessment:assessment:list')")
	public R<IPage<AssessmentResponseVO>> list(AssessmentEntity assessment, Query query) {
		IPage<AssessmentEntity> pages = assessmentService.pageList(Condition.getPage(query), assessment);
		return R.data(AssessmentWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入assessment")
	@PreAuth("hasPermission('contractAssessment:assessment:add')")
	public R save(@Valid @RequestBody AssessmentRequestVO assessment) {
        AssessmentEntity entity = new AssessmentEntity();
        BeanUtil.copy(assessment,entity);
		return R.status(assessmentService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入assessment")
	@PreAuth("hasPermission('contractAssessment:assessment:update')")
	public R update(@Valid @RequestBody AssessmentRequestVO assessment) {
	    if (Func.isEmpty(assessment.getId())){
            throw new ServiceException("id不能为空");
        }
	    AssessmentEntity entity = new AssessmentEntity();
        BeanUtil.copy(assessment,entity);
		return R.status(assessmentService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractAssessment:assessment:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(assessmentService.deleteLogic(Func.toLongList(ids)));
	}

}
