package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.YwlShopRecruitmentRequestVO;
import org.springblade.contract.vo.YwlShopRecruitmentResponseVO;
import org.springblade.contract.wrapper.YwlShopRecruitmentWrapper;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.YwlShopRecruitmentEntity;
import org.springblade.contract.service.IYwlShopRecruitmentService;


/**
 * 业务类：14.店招合同 控制器
 *
 * @author : szw
 * @date : 2020-12-04 19:04:54
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ywlShopRecruitment")
@Api(value = "业务类：14.店招合同", tags = "业务类：14.店招合同")
public class YwlShopRecruitmentController extends BladeController {

	private IYwlShopRecruitmentService ywlShopRecruitmentService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ywlShopRecruitment")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:detail')")
	public R<YwlShopRecruitmentResponseVO> detail(@RequestParam Long id) {
		YwlShopRecruitmentEntity detail = ywlShopRecruitmentService.getById(id);
		return R.data(YwlShopRecruitmentWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ywlShopRecruitment")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:page')")
	public R<IPage<YwlShopRecruitmentResponseVO>> list(YwlShopRecruitmentRequestVO ywlShopRecruitment, Query query) {
		IPage<YwlShopRecruitmentEntity> pages = ywlShopRecruitmentService.pageList(Condition.getPage(query), ywlShopRecruitment);
		return R.data(YwlShopRecruitmentWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入ywlShopRecruitment")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:add')")
	public R save(@Valid @RequestBody YwlShopRecruitmentResponseVO ywlShopRecruitment) {
		return R.status(ywlShopRecruitmentService.save(YwlShopRecruitmentWrapper.build().PVEntity(ywlShopRecruitment)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入ywlShopRecruitment")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:update')")
	public R update(@Valid @RequestBody YwlShopRecruitmentResponseVO ywlShopRecruitment) {
	    if (Func.isEmpty(ywlShopRecruitment.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(ywlShopRecruitmentService.updateById(YwlShopRecruitmentWrapper.build().PVEntity(ywlShopRecruitment)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('ywlShopRecruitment:ywlShopRecruitment:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ywlShopRecruitmentService.deleteLogic(Func.toLongList(ids)));
	}

}
