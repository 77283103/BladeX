package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtbProductionContract2Entity;
import org.springblade.contract.service.IMtbProductionContract2Service;
import org.springblade.contract.vo.MtbProductionContract2RequestVO;
import org.springblade.contract.vo.MtbProductionContract2ResponseVO;
import org.springblade.contract.wrapper.MtbProductionContract2Wrapper;
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
 * 媒体类：平面广告拍摄制作合同（关联表2） 控制器
 *
 * @author : 张文武
 * @date : 2021-01-04 11:23:59
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtbProductionContract2")
@Api(value = "媒体类：平面广告拍摄制作合同（关联表2）", tags = "媒体类：平面广告拍摄制作合同（关联表2）")
public class MtbProductionContract2Controller extends BladeController {

	private IMtbProductionContract2Service mtbProductionContract2Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtbProductionContract2")
	@PreAuth("hasPermission('mtbProductionContract2:mtbProductionContract2:detail')")
	public R<MtbProductionContract2ResponseVO> detail(@RequestParam Long id) {
		MtbProductionContract2Entity detail = mtbProductionContract2Service.getById(id);
		return R.data(MtbProductionContract2Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtbProductionContract2")
	@PreAuth("hasPermission('mtbProductionContract2:mtbProductionContract2:page')")
	public R<IPage<MtbProductionContract2ResponseVO>> list(MtbProductionContract2RequestVO mtbProductionContract2, Query query) {
		IPage<MtbProductionContract2Entity> pages = mtbProductionContract2Service.pageList(Condition.getPage(query), mtbProductionContract2);
		return R.data(MtbProductionContract2Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtbProductionContract2")
	@PreAuth("hasPermission('mtbProductionContract2:mtbProductionContract2:add')")
	public R save(@Valid @RequestBody MtbProductionContract2ResponseVO mtbProductionContract2) {
		return R.status(mtbProductionContract2Service.save(MtbProductionContract2Wrapper.build().PVEntity(mtbProductionContract2)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtbProductionContract2")
	@PreAuth("hasPermission('mtbProductionContract2:mtbProductionContract2:update')")
	public R update(@Valid @RequestBody MtbProductionContract2ResponseVO mtbProductionContract2) {
	    if (Func.isEmpty(mtbProductionContract2.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtbProductionContract2Service.updateById(MtbProductionContract2Wrapper.build().PVEntity(mtbProductionContract2)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtbProductionContract2:mtbProductionContract2:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtbProductionContract2Service.deleteLogic(Func.toLongList(ids)));
	}

}
