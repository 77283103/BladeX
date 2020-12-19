package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.YwbUnifiedAgreementEntity;
import org.springblade.contract.service.IYwbUnifiedAgreementService;
import org.springblade.contract.vo.YwbUnifiedAgreementRequestVO;
import org.springblade.contract.vo.YwbUnifiedAgreementResponseVO;
import org.springblade.contract.wrapper.YwbUnifiedAgreementWrapper;
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
 * 2021年统一e商城平台入驻服务协议（统一经销商） 控制器
 *
 * @author : 2021年统一e商城平台入驻服务协议（统一经销商）
 * @date : 2020-12-18 16:03:27
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ywbUnifiedAgreement")
@Api(value = "2021年统一e商城平台入驻服务协议（统一经销商）", tags = "2021年统一e商城平台入驻服务协议（统一经销商）")
public class YwbUnifiedAgreementController extends BladeController {

	private IYwbUnifiedAgreementService ywbUnifiedAgreementService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ywbUnifiedAgreement")
	@PreAuth("hasPermission('ywbUnifiedAgreement:ywbUnifiedAgreement:detail')")
	public R<YwbUnifiedAgreementResponseVO> detail(@RequestParam Long id) {
		YwbUnifiedAgreementEntity detail = ywbUnifiedAgreementService.getById(id);
		return R.data(YwbUnifiedAgreementWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ywbUnifiedAgreement")
	@PreAuth("hasPermission('ywbUnifiedAgreement:ywbUnifiedAgreement:page')")
	public R<IPage<YwbUnifiedAgreementResponseVO>> list(YwbUnifiedAgreementRequestVO ywbUnifiedAgreement, Query query) {
		IPage<YwbUnifiedAgreementEntity> pages = ywbUnifiedAgreementService.pageList(Condition.getPage(query), ywbUnifiedAgreement);
		return R.data(YwbUnifiedAgreementWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入ywbUnifiedAgreement")
	@PreAuth("hasPermission('ywbUnifiedAgreement:ywbUnifiedAgreement:add')")
	public R save(@Valid @RequestBody YwbUnifiedAgreementResponseVO ywbUnifiedAgreement) {
		return R.status(ywbUnifiedAgreementService.save(YwbUnifiedAgreementWrapper.build().PVEntity(ywbUnifiedAgreement)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入ywbUnifiedAgreement")
	@PreAuth("hasPermission('ywbUnifiedAgreement:ywbUnifiedAgreement:update')")
	public R update(@Valid @RequestBody YwbUnifiedAgreementResponseVO ywbUnifiedAgreement) {
	    if (Func.isEmpty(ywbUnifiedAgreement.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(ywbUnifiedAgreementService.updateById(YwbUnifiedAgreementWrapper.build().PVEntity(ywbUnifiedAgreement)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('ywbUnifiedAgreement:ywbUnifiedAgreement:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ywbUnifiedAgreementService.deleteLogic(Func.toLongList(ids)));
	}

}
