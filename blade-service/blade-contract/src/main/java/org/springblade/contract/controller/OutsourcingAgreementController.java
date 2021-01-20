package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.OutsourcingAgreementRequestVO;
import org.springblade.contract.vo.OutsourcingAgreementResponseVO;
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

import org.springblade.contract.entity.OutsourcingAgreementEntity;
import org.springblade.contract.wrapper.OutsourcingAgreementWrapper;
import org.springblade.contract.service.IOutsourcingAgreementService;


/**
 * 作 业 外 包 协 议 控制器
 *
 * @author : 王策
 * @date : 2021-01-20 13:42:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/outsourcingAgreement")
@Api(value = "作 业 外 包 协 议", tags = "作 业 外 包 协 议")
public class OutsourcingAgreementController extends BladeController {

	private IOutsourcingAgreementService outsourcingAgreementService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入outsourcingAgreement")
	@PreAuth("hasPermission('outsourcingAgreement:outsourcingAgreement:detail')")
	public R<OutsourcingAgreementResponseVO> detail(@RequestParam Long id) {
		OutsourcingAgreementEntity detail = outsourcingAgreementService.getById(id);
		return R.data(OutsourcingAgreementWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入outsourcingAgreement")
	@PreAuth("hasPermission('outsourcingAgreement:outsourcingAgreement:page')")
	public R<IPage<OutsourcingAgreementResponseVO>> list(OutsourcingAgreementEntity outsourcingAgreement, Query query) {
		IPage<OutsourcingAgreementEntity> pages = outsourcingAgreementService.pageList(Condition.getPage(query), outsourcingAgreement);
		return R.data(OutsourcingAgreementWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入outsourcingAgreement")
	@PreAuth("hasPermission('outsourcingAgreement:outsourcingAgreement:add')")
	public R save(@Valid @RequestBody OutsourcingAgreementRequestVO outsourcingAgreement) {
		return R.status(outsourcingAgreementService.save(OutsourcingAgreementWrapper.build().QVEntity(outsourcingAgreement)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入outsourcingAgreement")
	@PreAuth("hasPermission('outsourcingAgreement:outsourcingAgreement:update')")
	public R update(@Valid @RequestBody OutsourcingAgreementRequestVO outsourcingAgreement) {
	    if (Func.isEmpty(outsourcingAgreement.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(outsourcingAgreementService.updateById(OutsourcingAgreementWrapper.build().QVEntity(outsourcingAgreement)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('outsourcingAgreement:outsourcingAgreement:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(outsourcingAgreementService.deleteLogic(Func.toLongList(ids)));
	}

}
