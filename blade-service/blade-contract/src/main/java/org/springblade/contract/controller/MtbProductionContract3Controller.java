package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.service.IMtbProductionContract3Service;
import org.springblade.contract.vo.MtbProductionContract3RequestVO;
import org.springblade.contract.vo.MtbProductionContract3ResponseVO;
import org.springblade.contract.wrapper.MtbProductionContract3Wrapper;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.MtbProductionContract3Entity;


/**
 * 媒体类：平面广告拍摄制作合同（关联表2） 控制器
 *
 * @author : 韩杨
 * @date : 2021-01-21 11:26:59
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtbProductionContract3")
@Api(value = "媒体类：平面广告拍摄制作合同（关联表2）", tags = "媒体类：平面广告拍摄制作合同（关联表2）")
public class MtbProductionContract3Controller extends BladeController {

	private IMtbProductionContract3Service mtbProductionContract3Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtbProductionContract3")
	@PreAuth("hasPermission('mtbProductionContract3:mtbProductionContract3:detail')")
	public R<MtbProductionContract3ResponseVO> detail(@RequestParam Long id) {
		MtbProductionContract3Entity detail = mtbProductionContract3Service.getById(id);
		return R.data(MtbProductionContract3Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtbProductionContract3")
	@PreAuth("hasPermission('mtbProductionContract3:mtbProductionContract3:page')")
	public R<IPage<MtbProductionContract3ResponseVO>> list(MtbProductionContract3Entity mtbProductionContract3, Query query) {
		IPage<MtbProductionContract3Entity> pages = mtbProductionContract3Service.pageList(Condition.getPage(query), mtbProductionContract3);
		return R.data(MtbProductionContract3Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入mtbProductionContract3")
	@PreAuth("hasPermission('mtbProductionContract3:mtbProductionContract3:add')")
	public R save(@Valid @RequestBody MtbProductionContract3RequestVO mtbProductionContract3) {
		return R.status(mtbProductionContract3Service.save(MtbProductionContract3Wrapper.build().QVEntity(mtbProductionContract3)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入mtbProductionContract3")
	@PreAuth("hasPermission('mtbProductionContract3:mtbProductionContract3:update')")
	public R update(@Valid @RequestBody MtbProductionContract3RequestVO mtbProductionContract3) {
	    if (Func.isEmpty(mtbProductionContract3.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtbProductionContract3Service.updateById(MtbProductionContract3Wrapper.build().QVEntity(mtbProductionContract3)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtbProductionContract3:mtbProductionContract3:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtbProductionContract3Service.deleteLogic(Func.toLongList(ids)));
	}

}
