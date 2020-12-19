package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclOutsourcingAgreementEntity;
import org.springblade.contract.service.ISclOutsourcingAgreementService;
import org.springblade.contract.vo.SclOutsourcingAgreementRequestVO;
import org.springblade.contract.vo.SclOutsourcingAgreementResponseVO;
import org.springblade.contract.wrapper.SclOutsourcingAgreementWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 生产类：作业外包协议 控制器
 *
 * @author : kx
 * @date : 2020-12-18 16:08:23
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclOutsourcingAgreement")
@Api(value = "生产类：作业外包协议", tags = "生产类：作业外包协议")
public class SclOutsourcingAgreementController extends BladeController {

	private ISclOutsourcingAgreementService sclOutsourcingAgreementService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclOutsourcingAgreement")
	@PreAuth("hasPermission('sclOutsourcingAgreement:sclOutsourcingAgreement:detail')")
	public R<SclOutsourcingAgreementResponseVO> detail(@RequestParam Long id) {
		SclOutsourcingAgreementEntity detail = sclOutsourcingAgreementService.getById(id);
		return R.data(SclOutsourcingAgreementWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclOutsourcingAgreement")
	@PreAuth("hasPermission('sclOutsourcingAgreement:sclOutsourcingAgreement:page')")
	public R<IPage<SclOutsourcingAgreementResponseVO>> list(SclOutsourcingAgreementRequestVO sclOutsourcingAgreement, Query query) {
		IPage<SclOutsourcingAgreementEntity> pages = sclOutsourcingAgreementService.pageList(Condition.getPage(query), sclOutsourcingAgreement);
		return R.data(SclOutsourcingAgreementWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclOutsourcingAgreement")
	@PreAuth("hasPermission('sclOutsourcingAgreement:sclOutsourcingAgreement:add')")
	public R save(@Valid @RequestBody SclOutsourcingAgreementResponseVO sclOutsourcingAgreement) {
		return R.status(sclOutsourcingAgreementService.save(SclOutsourcingAgreementWrapper.build().PVEntity(sclOutsourcingAgreement)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclOutsourcingAgreement")
	@PreAuth("hasPermission('sclOutsourcingAgreement:sclOutsourcingAgreement:update')")
	public R update(@Valid @RequestBody SclOutsourcingAgreementResponseVO sclOutsourcingAgreement) {
	    if (Func.isEmpty(sclOutsourcingAgreement.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclOutsourcingAgreementService.updateById(SclOutsourcingAgreementWrapper.build().PVEntity(sclOutsourcingAgreement)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclOutsourcingAgreement:sclOutsourcingAgreement:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclOutsourcingAgreementService.deleteLogic(Func.toLongList(ids)));
	}

}
