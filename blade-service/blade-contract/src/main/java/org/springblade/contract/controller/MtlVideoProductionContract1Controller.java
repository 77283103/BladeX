package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtlVideoProductionContract1Entity;
import org.springblade.contract.service.IMtlVideoProductionContract1Service;
import org.springblade.contract.vo.MtlVideoProductionContract1RequestVO;
import org.springblade.contract.vo.MtlVideoProductionContract1ResponseVO;
import org.springblade.contract.wrapper.MtlVideoProductionContract1Wrapper;
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
 * 媒体类：视频制作合同关联表 控制器
 *
 * @author : 媒体类：视频制作合同关联表
 * @date : 2020-12-11 08:47:24
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlVideoProductionContract1")
@Api(value = "媒体类：视频制作合同关联表", tags = "媒体类：视频制作合同关联表")
public class MtlVideoProductionContract1Controller extends BladeController {

	private IMtlVideoProductionContract1Service mtlVideoProductionContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlVideoProductionContract1")
	@PreAuth("hasPermission('mtlVideoProductionContract1:mtlVideoProductionContract1:detail')")
	public R<MtlVideoProductionContract1ResponseVO> detail(@RequestParam Long id) {
		MtlVideoProductionContract1Entity detail = mtlVideoProductionContract1Service.getById(id);
		return R.data(MtlVideoProductionContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlVideoProductionContract1")
	@PreAuth("hasPermission('mtlVideoProductionContract1:mtlVideoProductionContract1:page')")
	public R<IPage<MtlVideoProductionContract1ResponseVO>> list(MtlVideoProductionContract1RequestVO mtlVideoProductionContract1, Query query) {
		IPage<MtlVideoProductionContract1Entity> pages = mtlVideoProductionContract1Service.pageList(Condition.getPage(query), mtlVideoProductionContract1);
		return R.data(MtlVideoProductionContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlVideoProductionContract1")
	@PreAuth("hasPermission('mtlVideoProductionContract1:mtlVideoProductionContract1:add')")
	public R save(@Valid @RequestBody MtlVideoProductionContract1ResponseVO mtlVideoProductionContract1) {
		return R.status(mtlVideoProductionContract1Service.save(MtlVideoProductionContract1Wrapper.build().PVEntity(mtlVideoProductionContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlVideoProductionContract1")
	@PreAuth("hasPermission('mtlVideoProductionContract1:mtlVideoProductionContract1:update')")
	public R update(@Valid @RequestBody MtlVideoProductionContract1ResponseVO mtlVideoProductionContract1) {
	    if (Func.isEmpty(mtlVideoProductionContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlVideoProductionContract1Service.updateById(MtlVideoProductionContract1Wrapper.build().PVEntity(mtlVideoProductionContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlVideoProductionContract1:mtlVideoProductionContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlVideoProductionContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
