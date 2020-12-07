package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.YwlShopRecruitment1RequestVO;
import org.springblade.contract.vo.YwlShopRecruitment1ResponseVO;
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

import org.springblade.contract.entity.YwlShopRecruitment1Entity;
import org.springblade.contract.wrapper.YwlShopRecruitment1Wrapper;
import org.springblade.contract.service.IYwlShopRecruitment1Service;


/**
 * 业务类：21.新陈列协议书关联表 控制器
 *
 * @author : szw
 * @date : 2020-12-06 13:51:39
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ywlShopRecruitment1")
@Api(value = "业务类：21.新陈列协议书关联表", tags = "业务类：21.新陈列协议书关联表")
public class YwlShopRecruitment1Controller extends BladeController {

	private IYwlShopRecruitment1Service ywlShopRecruitment1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ywlShopRecruitment1")
	@PreAuth("hasPermission('ywlShopRecruitment1:ywlShopRecruitment1:detail')")
	public R<YwlShopRecruitment1ResponseVO> detail(@RequestParam Long id) {
		YwlShopRecruitment1Entity detail = ywlShopRecruitment1Service.getById(id);
		return R.data(YwlShopRecruitment1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ywlShopRecruitment1")
	@PreAuth("hasPermission('ywlShopRecruitment1:ywlShopRecruitment1:page')")
	public R<IPage<YwlShopRecruitment1ResponseVO>> list(YwlShopRecruitment1RequestVO ywlShopRecruitment1, Query query) {
		IPage<YwlShopRecruitment1Entity> pages = ywlShopRecruitment1Service.pageList(Condition.getPage(query), ywlShopRecruitment1);
		return R.data(YwlShopRecruitment1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入ywlShopRecruitment1")
	@PreAuth("hasPermission('ywlShopRecruitment1:ywlShopRecruitment1:add')")
	public R save(@Valid @RequestBody YwlShopRecruitment1ResponseVO ywlShopRecruitment1) {
		return R.status(ywlShopRecruitment1Service.save(YwlShopRecruitment1Wrapper.build().PVEntity(ywlShopRecruitment1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入ywlShopRecruitment1")
	@PreAuth("hasPermission('ywlShopRecruitment1:ywlShopRecruitment1:update')")
	public R update(@Valid @RequestBody YwlShopRecruitment1ResponseVO ywlShopRecruitment1) {
	    if (Func.isEmpty(ywlShopRecruitment1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(ywlShopRecruitment1Service.updateById(YwlShopRecruitment1Wrapper.build().PVEntity(ywlShopRecruitment1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('ywlShopRecruitment1:ywlShopRecruitment1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ywlShopRecruitment1Service.deleteLogic(Func.toLongList(ids)));
	}

}
