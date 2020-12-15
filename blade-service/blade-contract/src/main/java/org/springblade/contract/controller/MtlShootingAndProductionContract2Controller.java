package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.MtlShootingAndProductionContract2RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract2ResponseVO;
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

import org.springblade.contract.entity.MtlShootingAndProductionContract2Entity;
import org.springblade.contract.wrapper.MtlShootingAndProductionContract2Wrapper;
import org.springblade.contract.service.IMtlShootingAndProductionContract2Service;


/**
 * 媒体类：视频广告拍摄制作合同关联表2 控制器
 *
 * @author : 媒体类：视频广告拍摄制作合同关联表2
 * @date : 2020-12-11 05:31:03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlShootingAndProductionContract2")
@Api(value = "媒体类：视频广告拍摄制作合同关联表2", tags = "媒体类：视频广告拍摄制作合同关联表2")
public class MtlShootingAndProductionContract2Controller extends BladeController {

	private IMtlShootingAndProductionContract2Service mtlShootingAndProductionContract2Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlShootingAndProductionContract2")
	@PreAuth("hasPermission('mtlShootingAndProductionContract2:mtlShootingAndProductionContract2:detail')")
	public R<MtlShootingAndProductionContract2ResponseVO> detail(@RequestParam Long id) {
		MtlShootingAndProductionContract2Entity detail = mtlShootingAndProductionContract2Service.getById(id);
		return R.data(MtlShootingAndProductionContract2Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlShootingAndProductionContract2")
	@PreAuth("hasPermission('mtlShootingAndProductionContract2:mtlShootingAndProductionContract2:page')")
	public R<IPage<MtlShootingAndProductionContract2ResponseVO>> list(MtlShootingAndProductionContract2RequestVO mtlShootingAndProductionContract2, Query query) {
		IPage<MtlShootingAndProductionContract2Entity> pages = mtlShootingAndProductionContract2Service.pageList(Condition.getPage(query), mtlShootingAndProductionContract2);
		return R.data(MtlShootingAndProductionContract2Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlShootingAndProductionContract2")
	@PreAuth("hasPermission('mtlShootingAndProductionContract2:mtlShootingAndProductionContract2:add')")
	public R save(@Valid @RequestBody MtlShootingAndProductionContract2ResponseVO mtlShootingAndProductionContract2) {
		return R.status(mtlShootingAndProductionContract2Service.save(MtlShootingAndProductionContract2Wrapper.build().PVEntity(mtlShootingAndProductionContract2)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlShootingAndProductionContract2")
	@PreAuth("hasPermission('mtlShootingAndProductionContract2:mtlShootingAndProductionContract2:update')")
	public R update(@Valid @RequestBody MtlShootingAndProductionContract2ResponseVO mtlShootingAndProductionContract2) {
	    if (Func.isEmpty(mtlShootingAndProductionContract2.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlShootingAndProductionContract2Service.updateById(MtlShootingAndProductionContract2Wrapper.build().PVEntity(mtlShootingAndProductionContract2)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlShootingAndProductionContract2:mtlShootingAndProductionContract2:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlShootingAndProductionContract2Service.deleteLogic(Func.toLongList(ids)));
	}

}
