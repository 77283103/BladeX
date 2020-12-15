package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtlAudioProductionContract1Entity;
import org.springblade.contract.service.IMtlAudioProductionContract1Service;
import org.springblade.contract.vo.MtlAudioProductionContract1RequestVO;
import org.springblade.contract.vo.MtlAudioProductionContract1ResponseVO;
import org.springblade.contract.wrapper.MtlAudioProductionContract1Wrapper;
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
 * 媒体类：音频制作合同关联表 控制器
 *
 * @author : 媒体类：音频制作合同关联表
 * @date : 2020-12-11 03:30:22
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlAudioProductionContract1")
@Api(value = "媒体类：音频制作合同关联表", tags = "媒体类：音频制作合同关联表")
public class MtlAudioProductionContract1Controller extends BladeController {

	private IMtlAudioProductionContract1Service mtlAudioProductionContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlAudioProductionContract1")
	@PreAuth("hasPermission('mtlAudioProductionContract1:mtlAudioProductionContract1:detail')")
	public R<MtlAudioProductionContract1ResponseVO> detail(@RequestParam Long id) {
		MtlAudioProductionContract1Entity detail = mtlAudioProductionContract1Service.getById(id);
		return R.data(MtlAudioProductionContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlAudioProductionContract1")
	@PreAuth("hasPermission('mtlAudioProductionContract1:mtlAudioProductionContract1:page')")
	public R<IPage<MtlAudioProductionContract1ResponseVO>> list(MtlAudioProductionContract1RequestVO mtlAudioProductionContract1, Query query) {
		IPage<MtlAudioProductionContract1Entity> pages = mtlAudioProductionContract1Service.pageList(Condition.getPage(query), mtlAudioProductionContract1);
		return R.data(MtlAudioProductionContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlAudioProductionContract1")
	@PreAuth("hasPermission('mtlAudioProductionContract1:mtlAudioProductionContract1:add')")
	public R save(@Valid @RequestBody MtlAudioProductionContract1ResponseVO mtlAudioProductionContract1) {
		return R.status(mtlAudioProductionContract1Service.save(MtlAudioProductionContract1Wrapper.build().PVEntity(mtlAudioProductionContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlAudioProductionContract1")
	@PreAuth("hasPermission('mtlAudioProductionContract1:mtlAudioProductionContract1:update')")
	public R update(@Valid @RequestBody MtlAudioProductionContract1ResponseVO mtlAudioProductionContract1) {
	    if (Func.isEmpty(mtlAudioProductionContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlAudioProductionContract1Service.updateById(MtlAudioProductionContract1Wrapper.build().PVEntity(mtlAudioProductionContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlAudioProductionContract1:mtlAudioProductionContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlAudioProductionContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
