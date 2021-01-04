package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtbProductionContract1Entity;
import org.springblade.contract.service.IMtbProductionContract1Service;
import org.springblade.contract.vo.MtbProductionContract1RequestVO;
import org.springblade.contract.vo.MtbProductionContract1ResponseVO;
import org.springblade.contract.wrapper.MtbProductionContract1Wrapper;
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
 * 媒体类：平面广告拍摄制作合同（关联表1） 控制器
 *
 * @author : 张文武
 * @date : 2021-01-04 11:27:45
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtbProductionContract1")
@Api(value = "媒体类：平面广告拍摄制作合同（关联表1）", tags = "媒体类：平面广告拍摄制作合同（关联表1）")
public class MtbProductionContract1Controller extends BladeController {

	private IMtbProductionContract1Service mtbProductionContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtbProductionContract1")
	@PreAuth("hasPermission('mtbProductionContract1:mtbProductionContract1:detail')")
	public R<MtbProductionContract1ResponseVO> detail(@RequestParam Long id) {
		MtbProductionContract1Entity detail = mtbProductionContract1Service.getById(id);
		return R.data(MtbProductionContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtbProductionContract1")
	@PreAuth("hasPermission('mtbProductionContract1:mtbProductionContract1:page')")
	public R<IPage<MtbProductionContract1ResponseVO>> list(MtbProductionContract1RequestVO mtbProductionContract1, Query query) {
		IPage<MtbProductionContract1Entity> pages = mtbProductionContract1Service.pageList(Condition.getPage(query), mtbProductionContract1);
		return R.data(MtbProductionContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtbProductionContract1")
	@PreAuth("hasPermission('mtbProductionContract1:mtbProductionContract1:add')")
	public R save(@Valid @RequestBody MtbProductionContract1ResponseVO mtbProductionContract1) {
		return R.status(mtbProductionContract1Service.save(MtbProductionContract1Wrapper.build().PVEntity(mtbProductionContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtbProductionContract1")
	@PreAuth("hasPermission('mtbProductionContract1:mtbProductionContract1:update')")
	public R update(@Valid @RequestBody MtbProductionContract1ResponseVO mtbProductionContract1) {
	    if (Func.isEmpty(mtbProductionContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtbProductionContract1Service.updateById(MtbProductionContract1Wrapper.build().PVEntity(mtbProductionContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtbProductionContract1:mtbProductionContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtbProductionContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
