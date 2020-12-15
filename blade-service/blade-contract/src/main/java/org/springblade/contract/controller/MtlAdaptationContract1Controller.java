package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.MtlAdaptationContract1RequestVO;
import org.springblade.contract.vo.MtlAdaptationContract1ResponseVO;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.MtlAdaptationContract1Entity;
import org.springblade.contract.wrapper.MtlAdaptationContract1Wrapper;
import org.springblade.contract.service.IMtlAdaptationContract1Service;


/**
 * 媒体类：视频广告改编合同关联表 控制器
 *
 * @author : 媒体类：视频广告改编合同关联表
 * @date : 2020-12-11 08:36:13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlAdaptationContract1")
@Api(value = "媒体类：视频广告改编合同关联表", tags = "媒体类：视频广告改编合同关联表")
public class MtlAdaptationContract1Controller extends BladeController {

	private IMtlAdaptationContract1Service mtlAdaptationContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlAdaptationContract1")
	@PreAuth("hasPermission('mtlAdaptationContract1:mtlAdaptationContract1:detail')")
	public R<MtlAdaptationContract1ResponseVO> detail(@RequestParam Long id) {
		MtlAdaptationContract1Entity detail = mtlAdaptationContract1Service.getById(id);
		return R.data(MtlAdaptationContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlAdaptationContract1")
	@PreAuth("hasPermission('mtlAdaptationContract1:mtlAdaptationContract1:page')")
	public R<IPage<MtlAdaptationContract1ResponseVO>> list(MtlAdaptationContract1RequestVO mtlAdaptationContract1, Query query) {
		IPage<MtlAdaptationContract1Entity> pages = mtlAdaptationContract1Service.pageList(Condition.getPage(query), mtlAdaptationContract1);
		return R.data(MtlAdaptationContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlAdaptationContract1")
	@PreAuth("hasPermission('mtlAdaptationContract1:mtlAdaptationContract1:add')")
	public R save(@Valid @RequestBody MtlAdaptationContract1ResponseVO mtlAdaptationContract1) {
		return R.status(mtlAdaptationContract1Service.save(MtlAdaptationContract1Wrapper.build().PVEntity(mtlAdaptationContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlAdaptationContract1")
	@PreAuth("hasPermission('mtlAdaptationContract1:mtlAdaptationContract1:update')")
	public R update(@Valid @RequestBody MtlAdaptationContract1ResponseVO mtlAdaptationContract1) {
	    if (Func.isEmpty(mtlAdaptationContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlAdaptationContract1Service.updateById(MtlAdaptationContract1Wrapper.build().PVEntity(mtlAdaptationContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlAdaptationContract1:mtlAdaptationContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlAdaptationContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
