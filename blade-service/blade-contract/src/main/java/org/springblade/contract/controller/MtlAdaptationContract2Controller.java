package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.MtlAdaptationContract2RequestVO;
import org.springblade.contract.vo.MtlAdaptationContract2ResponseVO;
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

import org.springblade.contract.entity.MtlAdaptationContract2Entity;
import org.springblade.contract.wrapper.MtlAdaptationContract2Wrapper;
import org.springblade.contract.service.IMtlAdaptationContract2Service;


/**
 * 媒体类：视频广告改编合同关联表2 控制器
 *
 * @author : 媒体类：视频广告改编合同关联表2
 * @date : 2020-12-11 08:36:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlAdaptationContract2")
@Api(value = "媒体类：视频广告改编合同关联表2", tags = "媒体类：视频广告改编合同关联表2")
public class MtlAdaptationContract2Controller extends BladeController {

	private IMtlAdaptationContract2Service mtlAdaptationContract2Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlAdaptationContract2")
	@PreAuth("hasPermission('mtlAdaptationContract2:mtlAdaptationContract2:detail')")
	public R<MtlAdaptationContract2ResponseVO> detail(@RequestParam Long id) {
		MtlAdaptationContract2Entity detail = mtlAdaptationContract2Service.getById(id);
		return R.data(MtlAdaptationContract2Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlAdaptationContract2")
	@PreAuth("hasPermission('mtlAdaptationContract2:mtlAdaptationContract2:page')")
	public R<IPage<MtlAdaptationContract2ResponseVO>> list(MtlAdaptationContract2RequestVO mtlAdaptationContract2, Query query) {
		IPage<MtlAdaptationContract2Entity> pages = mtlAdaptationContract2Service.pageList(Condition.getPage(query), mtlAdaptationContract2);
		return R.data(MtlAdaptationContract2Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlAdaptationContract2")
	@PreAuth("hasPermission('mtlAdaptationContract2:mtlAdaptationContract2:add')")
	public R save(@Valid @RequestBody MtlAdaptationContract2ResponseVO mtlAdaptationContract2) {
		return R.status(mtlAdaptationContract2Service.save(MtlAdaptationContract2Wrapper.build().PVEntity(mtlAdaptationContract2)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlAdaptationContract2")
	@PreAuth("hasPermission('mtlAdaptationContract2:mtlAdaptationContract2:update')")
	public R update(@Valid @RequestBody MtlAdaptationContract2ResponseVO mtlAdaptationContract2) {
	    if (Func.isEmpty(mtlAdaptationContract2.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlAdaptationContract2Service.updateById(MtlAdaptationContract2Wrapper.build().PVEntity(mtlAdaptationContract2)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlAdaptationContract2:mtlAdaptationContract2:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlAdaptationContract2Service.deleteLogic(Func.toLongList(ids)));
	}

}
