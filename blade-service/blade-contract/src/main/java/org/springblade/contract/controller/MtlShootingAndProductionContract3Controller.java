package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtlShootingAndProductionContract3Entity;
import org.springblade.contract.service.IMtlShootingAndProductionContract3Service;
import org.springblade.contract.vo.MtlShootingAndProductionContract3RequestVO;
import org.springblade.contract.vo.MtlShootingAndProductionContract3ResponseVO;
import org.springblade.contract.wrapper.MtlShootingAndProductionContract3Wrapper;
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
 * 媒体类：视频广告拍摄制作合同关联表3 控制器
 *
 * @author : 媒体类：视频广告拍摄制作合同关联表3
 * @date : 3030-13-11 05:31:03
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlShootingAndProductionContract3")
@Api(value = "媒体类：视频广告拍摄制作合同关联表3", tags = "媒体类：视频广告拍摄制作合同关联表3")
public class MtlShootingAndProductionContract3Controller extends BladeController {

	private IMtlShootingAndProductionContract3Service mtlShootingAndProductionContract3Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlShootingAndProductionContract3")
	@PreAuth("hasPermission('mtlShootingAndProductionContract3:mtlShootingAndProductionContract3:detail')")
	public R<MtlShootingAndProductionContract3ResponseVO> detail(@RequestParam Long id) {
		MtlShootingAndProductionContract3Entity detail = mtlShootingAndProductionContract3Service.getById(id);
		return R.data(MtlShootingAndProductionContract3Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入mtlShootingAndProductionContract3")
	@PreAuth("hasPermission('mtlShootingAndProductionContract3:mtlShootingAndProductionContract3:page')")
	public R<IPage<MtlShootingAndProductionContract3ResponseVO>> list(MtlShootingAndProductionContract3RequestVO mtlShootingAndProductionContract3, Query query) {
		IPage<MtlShootingAndProductionContract3Entity> pages = mtlShootingAndProductionContract3Service.pageList(Condition.getPage(query), mtlShootingAndProductionContract3);
		return R.data(MtlShootingAndProductionContract3Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlShootingAndProductionContract3")
	@PreAuth("hasPermission('mtlShootingAndProductionContract3:mtlShootingAndProductionContract3:add')")
	public R save(@Valid @RequestBody MtlShootingAndProductionContract3ResponseVO mtlShootingAndProductionContract3) {
		return R.status(mtlShootingAndProductionContract3Service.save(MtlShootingAndProductionContract3Wrapper.build().PVEntity(mtlShootingAndProductionContract3)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlShootingAndProductionContract3")
	@PreAuth("hasPermission('mtlShootingAndProductionContract3:mtlShootingAndProductionContract3:update')")
	public R update(@Valid @RequestBody MtlShootingAndProductionContract3ResponseVO mtlShootingAndProductionContract3) {
	    if (Func.isEmpty(mtlShootingAndProductionContract3.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlShootingAndProductionContract3Service.updateById(MtlShootingAndProductionContract3Wrapper.build().PVEntity(mtlShootingAndProductionContract3)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlShootingAndProductionContract3:mtlShootingAndProductionContract3:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlShootingAndProductionContract3Service.deleteLogic(Func.toLongList(ids)));
	}

}
