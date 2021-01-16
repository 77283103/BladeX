package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.LyConfidentialityAgreementRequestVO;
import org.springblade.contract.vo.LyConfidentialityAgreementResponseVO;
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

import org.springblade.contract.entity.LyConfidentialityAgreementEntity;
import org.springblade.contract.wrapper.LyConfidentialityAgreementWrapper;
import org.springblade.contract.service.ILyConfidentialityAgreementService;


/**
 * 梁艳-保密协议 控制器
 *
 * @author : wd
 * @date : 2021-01-15 14:57:38
 */
@RestController
@AllArgsConstructor
@RequestMapping("/lyConfidentialityAgreement")
@Api(value = "梁艳-保密协议", tags = "梁艳-保密协议")
public class LyConfidentialityAgreementController extends BladeController {

	private ILyConfidentialityAgreementService lyConfidentialityAgreementService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入lyConfidentialityAgreement")
	@PreAuth("hasPermission('lyConfidentialityAgreement:lyConfidentialityAgreement:detail')")
	public R<LyConfidentialityAgreementResponseVO> detail(@RequestParam Long id) {
		LyConfidentialityAgreementEntity detail = lyConfidentialityAgreementService.getById(id);
		return R.data(LyConfidentialityAgreementWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入lyConfidentialityAgreement")
	@PreAuth("hasPermission('lyConfidentialityAgreement:lyConfidentialityAgreement:page')")
	public R<IPage<LyConfidentialityAgreementResponseVO>> list(LyConfidentialityAgreementEntity lyConfidentialityAgreement, Query query) {
		IPage<LyConfidentialityAgreementEntity> pages = lyConfidentialityAgreementService.pageList(Condition.getPage(query), lyConfidentialityAgreement);
		return R.data(LyConfidentialityAgreementWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入lyConfidentialityAgreement")
	@PreAuth("hasPermission('lyConfidentialityAgreement:lyConfidentialityAgreement:add')")
	public R save(@Valid @RequestBody LyConfidentialityAgreementRequestVO lyConfidentialityAgreement) {
		return R.status(lyConfidentialityAgreementService.save(LyConfidentialityAgreementWrapper.build().QVEntity(lyConfidentialityAgreement)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入lyConfidentialityAgreement")
	@PreAuth("hasPermission('lyConfidentialityAgreement:lyConfidentialityAgreement:update')")
	public R update(@Valid @RequestBody LyConfidentialityAgreementRequestVO lyConfidentialityAgreement) {
	    if (Func.isEmpty(lyConfidentialityAgreement.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(lyConfidentialityAgreementService.updateById(LyConfidentialityAgreementWrapper.build().QVEntity(lyConfidentialityAgreement)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('lyConfidentialityAgreement:lyConfidentialityAgreement:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(lyConfidentialityAgreementService.deleteLogic(Func.toLongList(ids)));
	}

}
