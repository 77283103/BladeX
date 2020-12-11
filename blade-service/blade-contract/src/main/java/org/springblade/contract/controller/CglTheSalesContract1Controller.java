package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.vo.CglTheSalesContract1RequestVO;
import org.springblade.contract.vo.CglTheSalesContract1ResponseVO;
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

import org.springblade.contract.entity.CglTheSalesContract1Entity;
import org.springblade.contract.wrapper.CglTheSalesContract1Wrapper;
import org.springblade.contract.service.ICglTheSalesContract1Service;


/**
 * 采购类：新增原物料补充协议--买卖合同 控制器
 *
 * @author : 采购类：新增原物料补充协议--买卖合同
 * @date : 2020-12-10 18:50:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglTheSalesContract1")
@Api(value = "采购类：新增原物料补充协议--买卖合同", tags = "采购类：新增原物料补充协议--买卖合同")
public class CglTheSalesContract1Controller extends BladeController {

	private ICglTheSalesContract1Service cglTheSalesContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglTheSalesContract1")
	@PreAuth("hasPermission('cglTheSalesContract1:cglTheSalesContract1:detail')")
	public R<CglTheSalesContract1ResponseVO> detail(@RequestParam Long id) {
		CglTheSalesContract1Entity detail = cglTheSalesContract1Service.getById(id);
		return R.data(CglTheSalesContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglTheSalesContract1")
	@PreAuth("hasPermission('cglTheSalesContract1:cglTheSalesContract1:page')")
	public R<IPage<CglTheSalesContract1ResponseVO>> list(CglTheSalesContract1RequestVO cglTheSalesContract1, Query query) {
		IPage<CglTheSalesContract1Entity> pages = cglTheSalesContract1Service.pageList(Condition.getPage(query), cglTheSalesContract1);
		return R.data(CglTheSalesContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglTheSalesContract1")
	@PreAuth("hasPermission('cglTheSalesContract1:cglTheSalesContract1:add')")
	public R save(@Valid @RequestBody CglTheSalesContract1ResponseVO cglTheSalesContract1) {
		return R.status(cglTheSalesContract1Service.save(CglTheSalesContract1Wrapper.build().PVEntity(cglTheSalesContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglTheSalesContract1")
	@PreAuth("hasPermission('cglTheSalesContract1:cglTheSalesContract1:update')")
	public R update(@Valid @RequestBody CglTheSalesContract1ResponseVO cglTheSalesContract1) {
	    if (Func.isEmpty(cglTheSalesContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglTheSalesContract1Service.updateById(CglTheSalesContract1Wrapper.build().PVEntity(cglTheSalesContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglTheSalesContract1:cglTheSalesContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglTheSalesContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
