package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.YwlANewDisplay1RequestVO;
import org.springblade.contract.vo.YwlANewDisplay1ResponseVO;
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

import org.springblade.contract.entity.YwlANewDisplay1Entity;
import org.springblade.contract.wrapper.YwlANewDisplay1Wrapper;
import org.springblade.contract.service.IYwlANewDisplay1Service;


/**
 * 业务类：21.新陈列协议书关联表 控制器
 *
 * @author : kx
 * @date : 2020-12-16 16:42:38
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ywlANewDisplay1")
@Api(value = "业务类：21.新陈列协议书关联表", tags = "业务类：21.新陈列协议书关联表")
public class YwlANewDisplay1Controller extends BladeController {

	private IYwlANewDisplay1Service ywlANewDisplay1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ywlANewDisplay1")
	@PreAuth("hasPermission('ywlANewDisplay1:ywlANewDisplay1:detail')")
	public R<YwlANewDisplay1ResponseVO> detail(@RequestParam Long id) {
		YwlANewDisplay1Entity detail = ywlANewDisplay1Service.getById(id);
		return R.data(YwlANewDisplay1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ywlANewDisplay1")
	@PreAuth("hasPermission('ywlANewDisplay1:ywlANewDisplay1:page')")
	public R<IPage<YwlANewDisplay1ResponseVO>> list(YwlANewDisplay1RequestVO ywlANewDisplay1, Query query) {
		IPage<YwlANewDisplay1Entity> pages = ywlANewDisplay1Service.pageList(Condition.getPage(query), ywlANewDisplay1);
		return R.data(YwlANewDisplay1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入ywlANewDisplay1")
	@PreAuth("hasPermission('ywlANewDisplay1:ywlANewDisplay1:add')")
	public R save(@Valid @RequestBody YwlANewDisplay1ResponseVO ywlANewDisplay1) {
		return R.status(ywlANewDisplay1Service.save(YwlANewDisplay1Wrapper.build().PVEntity(ywlANewDisplay1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入ywlANewDisplay1")
	@PreAuth("hasPermission('ywlANewDisplay1:ywlANewDisplay1:update')")
	public R update(@Valid @RequestBody YwlANewDisplay1ResponseVO ywlANewDisplay1) {
	    if (Func.isEmpty(ywlANewDisplay1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(ywlANewDisplay1Service.updateById(YwlANewDisplay1Wrapper.build().PVEntity(ywlANewDisplay1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('ywlANewDisplay1:ywlANewDisplay1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ywlANewDisplay1Service.deleteLogic(Func.toLongList(ids)));
	}

}
