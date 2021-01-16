package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.ConfidentialityAgreementRequestVO;
import org.springblade.contract.vo.ConfidentialityAgreementResponseVO;
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

import org.springblade.contract.entity.ConfidentialityAgreementEntity;
import org.springblade.contract.wrapper.ConfidentialityAgreementWrapper;
import org.springblade.contract.service.IConfidentialityAgreementService;


/**
 * 梁艳-保密协议（三方） 控制器
 *
 * @author : 王策
 * @date : 2021-01-15 15:36:26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/confidentialityAgreement")
@Api(value = "梁艳-保密协议（三方）", tags = "梁艳-保密协议（三方）")
public class ConfidentialityAgreementController extends BladeController {

	private IConfidentialityAgreementService confidentialityAgreementService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入confidentialityAgreement")
	@PreAuth("hasPermission('confidentialityAgreement:confidentialityAgreement:detail')")
	public R<ConfidentialityAgreementResponseVO> detail(@RequestParam Long id) {
		ConfidentialityAgreementEntity detail = confidentialityAgreementService.getById(id);
		return R.data(ConfidentialityAgreementWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入confidentialityAgreement")
	@PreAuth("hasPermission('confidentialityAgreement:confidentialityAgreement:page')")
	public R<IPage<ConfidentialityAgreementResponseVO>> list(ConfidentialityAgreementEntity confidentialityAgreement, Query query) {
		IPage<ConfidentialityAgreementEntity> pages = confidentialityAgreementService.pageList(Condition.getPage(query), confidentialityAgreement);
		return R.data(ConfidentialityAgreementWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入confidentialityAgreement")
	@PreAuth("hasPermission('confidentialityAgreement:confidentialityAgreement:add')")
	public R save(@Valid @RequestBody ConfidentialityAgreementRequestVO confidentialityAgreement) {
		return R.status(confidentialityAgreementService.save(ConfidentialityAgreementWrapper.build().QVEntity(confidentialityAgreement)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入confidentialityAgreement")
	@PreAuth("hasPermission('confidentialityAgreement:confidentialityAgreement:update')")
	public R update(@Valid @RequestBody ConfidentialityAgreementRequestVO confidentialityAgreement) {
	    if (Func.isEmpty(confidentialityAgreement.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(confidentialityAgreementService.updateById(ConfidentialityAgreementWrapper.build().QVEntity(confidentialityAgreement)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('confidentialityAgreement:confidentialityAgreement:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(confidentialityAgreementService.deleteLogic(Func.toLongList(ids)));
	}

}
