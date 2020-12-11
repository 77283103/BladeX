package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtlAudioProductionContract2Entity;
import org.springblade.contract.service.IMtlAudioProductionContract2Service;
import org.springblade.contract.vo.MtlAudioProductionContract2RequestVO;
import org.springblade.contract.vo.MtlAudioProductionContract2ResponseVO;
import org.springblade.contract.wrapper.MtlAudioProductionContract2Wrapper;
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
 * 媒体类：音频制作合同关联表2 控制器
 *
 * @author : 媒体类：音频制作合同关联表2
 * @date : 2020-12-11 03:31:35
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlAudioProductionContract2")
@Api(value = "媒体类：音频制作合同关联表2", tags = "媒体类：音频制作合同关联表2")
public class MtlAudioProductionContract2Controller extends BladeController {

	private IMtlAudioProductionContract2Service mtlAudioProductionContract2Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlAudioProductionContract2")
	@PreAuth("hasPermission('mtlAudioProductionContract2:mtlAudioProductionContract2:detail')")
	public R<MtlAudioProductionContract2ResponseVO> detail(@RequestParam Long id) {
		MtlAudioProductionContract2Entity detail = mtlAudioProductionContract2Service.getById(id);
		return R.data(MtlAudioProductionContract2Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlAudioProductionContract2")
	@PreAuth("hasPermission('mtlAudioProductionContract2:mtlAudioProductionContract2:page')")
	public R<IPage<MtlAudioProductionContract2ResponseVO>> list(MtlAudioProductionContract2RequestVO mtlAudioProductionContract2, Query query) {
		IPage<MtlAudioProductionContract2Entity> pages = mtlAudioProductionContract2Service.pageList(Condition.getPage(query), mtlAudioProductionContract2);
		return R.data(MtlAudioProductionContract2Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlAudioProductionContract2")
	@PreAuth("hasPermission('mtlAudioProductionContract2:mtlAudioProductionContract2:add')")
	public R save(@Valid @RequestBody MtlAudioProductionContract2ResponseVO mtlAudioProductionContract2) {
		return R.status(mtlAudioProductionContract2Service.save(MtlAudioProductionContract2Wrapper.build().PVEntity(mtlAudioProductionContract2)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlAudioProductionContract2")
	@PreAuth("hasPermission('mtlAudioProductionContract2:mtlAudioProductionContract2:update')")
	public R update(@Valid @RequestBody MtlAudioProductionContract2ResponseVO mtlAudioProductionContract2) {
	    if (Func.isEmpty(mtlAudioProductionContract2.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlAudioProductionContract2Service.updateById(MtlAudioProductionContract2Wrapper.build().PVEntity(mtlAudioProductionContract2)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlAudioProductionContract2:mtlAudioProductionContract2:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlAudioProductionContract2Service.deleteLogic(Func.toLongList(ids)));
	}

}
