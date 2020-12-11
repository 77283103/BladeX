package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.CglPaymentDaysSupplementaryRequestVO;
import org.springblade.contract.vo.CglPaymentDaysSupplementaryResponseVO;
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

import org.springblade.contract.entity.CglPaymentDaysSupplementaryEntity;
import org.springblade.contract.wrapper.CglPaymentDaysSupplementaryWrapper;
import org.springblade.contract.service.ICglPaymentDaysSupplementaryService;


/**
 * 采购类：账期补充协议--买卖合同 控制器
 *
 * @author : 王策
 * @date : 2020-12-10 19:21:44
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglPaymentDaysSupplementary")
@Api(value = "采购类：账期补充协议--买卖合同", tags = "采购类：账期补充协议--买卖合同")
public class CglPaymentDaysSupplementaryController extends BladeController {

	private ICglPaymentDaysSupplementaryService cglPaymentDaysSupplementaryService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglPaymentDaysSupplementary")
	@PreAuth("hasPermission('cglPaymentDaysSupplementary:cglPaymentDaysSupplementary:detail')")
	public R<CglPaymentDaysSupplementaryResponseVO> detail(@RequestParam Long id) {
		CglPaymentDaysSupplementaryEntity detail = cglPaymentDaysSupplementaryService.getById(id);
		return R.data(CglPaymentDaysSupplementaryWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglPaymentDaysSupplementary")
	@PreAuth("hasPermission('cglPaymentDaysSupplementary:cglPaymentDaysSupplementary:page')")
	public R<IPage<CglPaymentDaysSupplementaryResponseVO>> list(CglPaymentDaysSupplementaryRequestVO cglPaymentDaysSupplementary, Query query) {
		IPage<CglPaymentDaysSupplementaryEntity> pages = cglPaymentDaysSupplementaryService.pageList(Condition.getPage(query), cglPaymentDaysSupplementary);
		return R.data(CglPaymentDaysSupplementaryWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglPaymentDaysSupplementary")
	@PreAuth("hasPermission('cglPaymentDaysSupplementary:cglPaymentDaysSupplementary:add')")
	public R save(@Valid @RequestBody CglPaymentDaysSupplementaryResponseVO cglPaymentDaysSupplementary) {
		return R.status(cglPaymentDaysSupplementaryService.save(CglPaymentDaysSupplementaryWrapper.build().PVEntity(cglPaymentDaysSupplementary)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglPaymentDaysSupplementary")
	@PreAuth("hasPermission('cglPaymentDaysSupplementary:cglPaymentDaysSupplementary:update')")
	public R update(@Valid @RequestBody CglPaymentDaysSupplementaryResponseVO cglPaymentDaysSupplementary) {
	    if (Func.isEmpty(cglPaymentDaysSupplementary.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglPaymentDaysSupplementaryService.updateById(CglPaymentDaysSupplementaryWrapper.build().PVEntity(cglPaymentDaysSupplementary)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglPaymentDaysSupplementary:cglPaymentDaysSupplementary:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglPaymentDaysSupplementaryService.deleteLogic(Func.toLongList(ids)));
	}

}
