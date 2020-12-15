package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtlVideoProductionContract2Entity;
import org.springblade.contract.service.IMtlVideoProductionContract2Service;
import org.springblade.contract.vo.MtlVideoProductionContract2RequestVO;
import org.springblade.contract.vo.MtlVideoProductionContract2ResponseVO;
import org.springblade.contract.wrapper.MtlVideoProductionContract2Wrapper;
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
 * 媒体类：视频制作合同关联表2 控制器
 *
 * @author : 媒体类：视频制作合同关联表2
 * @date : 2020-12-11 08:48:35
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlVideoProductionContract2")
@Api(value = "媒体类：视频制作合同关联表2", tags = "媒体类：视频制作合同关联表2")
public class MtlVideoProductionContract2Controller extends BladeController {

	private IMtlVideoProductionContract2Service mtlVideoProductionContract2Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlVideoProductionContract2")
	@PreAuth("hasPermission('mtlVideoProductionContract2:mtlVideoProductionContract2:detail')")
	public R<MtlVideoProductionContract2ResponseVO> detail(@RequestParam Long id) {
		MtlVideoProductionContract2Entity detail = mtlVideoProductionContract2Service.getById(id);
		return R.data(MtlVideoProductionContract2Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlVideoProductionContract2")
	@PreAuth("hasPermission('mtlVideoProductionContract2:mtlVideoProductionContract2:page')")
	public R<IPage<MtlVideoProductionContract2ResponseVO>> list(MtlVideoProductionContract2RequestVO mtlVideoProductionContract2, Query query) {
		IPage<MtlVideoProductionContract2Entity> pages = mtlVideoProductionContract2Service.pageList(Condition.getPage(query), mtlVideoProductionContract2);
		return R.data(MtlVideoProductionContract2Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlVideoProductionContract2")
	@PreAuth("hasPermission('mtlVideoProductionContract2:mtlVideoProductionContract2:add')")
	public R save(@Valid @RequestBody MtlVideoProductionContract2ResponseVO mtlVideoProductionContract2) {
		return R.status(mtlVideoProductionContract2Service.save(MtlVideoProductionContract2Wrapper.build().PVEntity(mtlVideoProductionContract2)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlVideoProductionContract2")
	@PreAuth("hasPermission('mtlVideoProductionContract2:mtlVideoProductionContract2:update')")
	public R update(@Valid @RequestBody MtlVideoProductionContract2ResponseVO mtlVideoProductionContract2) {
	    if (Func.isEmpty(mtlVideoProductionContract2.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlVideoProductionContract2Service.updateById(MtlVideoProductionContract2Wrapper.build().PVEntity(mtlVideoProductionContract2)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlVideoProductionContract2:mtlVideoProductionContract2:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlVideoProductionContract2Service.deleteLogic(Func.toLongList(ids)));
	}

}
