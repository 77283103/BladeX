package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.MtlEditedTheContract1Entity;
import org.springblade.contract.service.IMtlEditedTheContract1Service;
import org.springblade.contract.vo.MtlEditedTheContract1RequestVO;
import org.springblade.contract.vo.MtlEditedTheContract1ResponseVO;
import org.springblade.contract.wrapper.MtlEditedTheContract1Wrapper;
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
 * 媒体类：修图合同关联表 控制器
 *
 * @author : 媒体类：修图合同关联表
 * @date : 2020-12-11 05:00:47
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mtlEditedTheContract1")
@Api(value = "媒体类：修图合同关联表", tags = "媒体类：修图合同关联表")
public class MtlEditedTheContract1Controller extends BladeController {

	private IMtlEditedTheContract1Service mtlEditedTheContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入mtlEditedTheContract1")
	@PreAuth("hasPermission('mtlEditedTheContract1:mtlEditedTheContract1:detail')")
	public R<MtlEditedTheContract1ResponseVO> detail(@RequestParam Long id) {
		MtlEditedTheContract1Entity detail = mtlEditedTheContract1Service.getById(id);
		return R.data(MtlEditedTheContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入mtlEditedTheContract1")
	@PreAuth("hasPermission('mtlEditedTheContract1:mtlEditedTheContract1:page')")
	public R<IPage<MtlEditedTheContract1ResponseVO>> list(MtlEditedTheContract1RequestVO mtlEditedTheContract1, Query query) {
		IPage<MtlEditedTheContract1Entity> pages = mtlEditedTheContract1Service.pageList(Condition.getPage(query), mtlEditedTheContract1);
		return R.data(MtlEditedTheContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入mtlEditedTheContract1")
	@PreAuth("hasPermission('mtlEditedTheContract1:mtlEditedTheContract1:add')")
	public R save(@Valid @RequestBody MtlEditedTheContract1ResponseVO mtlEditedTheContract1) {
		return R.status(mtlEditedTheContract1Service.save(MtlEditedTheContract1Wrapper.build().PVEntity(mtlEditedTheContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入mtlEditedTheContract1")
	@PreAuth("hasPermission('mtlEditedTheContract1:mtlEditedTheContract1:update')")
	public R update(@Valid @RequestBody MtlEditedTheContract1ResponseVO mtlEditedTheContract1) {
	    if (Func.isEmpty(mtlEditedTheContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(mtlEditedTheContract1Service.updateById(MtlEditedTheContract1Wrapper.build().PVEntity(mtlEditedTheContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('mtlEditedTheContract1:mtlEditedTheContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(mtlEditedTheContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
