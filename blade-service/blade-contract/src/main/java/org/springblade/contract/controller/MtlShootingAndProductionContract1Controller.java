package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.MtlShootingAndProductionContract1RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract1ResponseVO;
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

import org.springblade.contract.entity.MtlShootingAndProductionContract1Entity;
import org.springblade.contract.wrapper.MtlShootingAndProductionContract1Wrapper;
import org.springblade.contract.service.IMtlShootingAndProductionContract1Service;


/**
 * 媒体类：视频广告拍摄制作合同关联表 控制器
 *
 * @author : 媒体类：视频广告拍摄制作合同关联表
 * @date : 2020-12-11 05:30:02
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlShootingAndProductionContract1")
@Api(value = "媒体类：视频广告拍摄制作合同关联表", tags = "媒体类：视频广告拍摄制作合同关联表")
public class MtlShootingAndProductionContract1Controller extends BladeController {

	private IMtlShootingAndProductionContract1Service mtlShootingAndProductionContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlShootingAndProductionContract1")
	@PreAuth("hasPermission('mtlShootingAndProductionContract1:mtlShootingAndProductionContract1:detail')")
	public R<MtlShootingAndProductionContract1ResponseVO> detail(@RequestParam Long id) {
		MtlShootingAndProductionContract1Entity detail = mtlShootingAndProductionContract1Service.getById(id);
		return R.data(MtlShootingAndProductionContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlShootingAndProductionContract1")
	@PreAuth("hasPermission('mtlShootingAndProductionContract1:mtlShootingAndProductionContract1:page')")
	public R<IPage<MtlShootingAndProductionContract1ResponseVO>> list(MtlShootingAndProductionContract1RequestVO mtlShootingAndProductionContract1, Query query) {
		IPage<MtlShootingAndProductionContract1Entity> pages = mtlShootingAndProductionContract1Service.pageList(Condition.getPage(query), mtlShootingAndProductionContract1);
		return R.data(MtlShootingAndProductionContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlShootingAndProductionContract1")
	@PreAuth("hasPermission('mtlShootingAndProductionContract1:mtlShootingAndProductionContract1:add')")
	public R save(@Valid @RequestBody MtlShootingAndProductionContract1ResponseVO mtlShootingAndProductionContract1) {
		return R.status(mtlShootingAndProductionContract1Service.save(MtlShootingAndProductionContract1Wrapper.build().PVEntity(mtlShootingAndProductionContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlShootingAndProductionContract1")
	@PreAuth("hasPermission('mtlShootingAndProductionContract1:mtlShootingAndProductionContract1:update')")
	public R update(@Valid @RequestBody MtlShootingAndProductionContract1ResponseVO mtlShootingAndProductionContract1) {
	    if (Func.isEmpty(mtlShootingAndProductionContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlShootingAndProductionContract1Service.updateById(MtlShootingAndProductionContract1Wrapper.build().PVEntity(mtlShootingAndProductionContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlShootingAndProductionContract1:mtlShootingAndProductionContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlShootingAndProductionContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
