package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclConstructionProject1Entity;
import org.springblade.contract.service.ISclConstructionProject1Service;
import org.springblade.contract.vo.SclConstructionProject1RequestVO;
import org.springblade.contract.vo.SclConstructionProject1ResponseVO;
import org.springblade.contract.wrapper.SclConstructionProject1Wrapper;
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
 * 生产类：加工承揽合同（代工合同）关联表 控制器
 *
 * @author : 生产类：加工承揽合同（代工合同）关联表
 * @date : 2020-12-11 10:10:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclConstructionProject1")
@Api(value = "生产类：加工承揽合同（代工合同）关联表", tags = "生产类：加工承揽合同（代工合同）关联表")
public class SclConstructionProject1Controller extends BladeController {

	private ISclConstructionProject1Service sclConstructionProject1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclConstructionProject1")
	@PreAuth("hasPermission('sclConstructionProject1:sclConstructionProject1:detail')")
	public R<SclConstructionProject1ResponseVO> detail(@RequestParam Long id) {
		SclConstructionProject1Entity detail = sclConstructionProject1Service.getById(id);
		return R.data(SclConstructionProject1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclConstructionProject1")
	@PreAuth("hasPermission('sclConstructionProject1:sclConstructionProject1:page')")
	public R<IPage<SclConstructionProject1ResponseVO>> list(SclConstructionProject1RequestVO sclConstructionProject1, Query query) {
		IPage<SclConstructionProject1Entity> pages = sclConstructionProject1Service.pageList(Condition.getPage(query), sclConstructionProject1);
		return R.data(SclConstructionProject1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclConstructionProject1")
	@PreAuth("hasPermission('sclConstructionProject1:sclConstructionProject1:add')")
	public R save(@Valid @RequestBody SclConstructionProject1ResponseVO sclConstructionProject1) {
		return R.status(sclConstructionProject1Service.save(SclConstructionProject1Wrapper.build().PVEntity(sclConstructionProject1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclConstructionProject1")
	@PreAuth("hasPermission('sclConstructionProject1:sclConstructionProject1:update')")
	public R update(@Valid @RequestBody SclConstructionProject1ResponseVO sclConstructionProject1) {
	    if (Func.isEmpty(sclConstructionProject1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclConstructionProject1Service.updateById(SclConstructionProject1Wrapper.build().PVEntity(sclConstructionProject1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclConstructionProject1:sclConstructionProject1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclConstructionProject1Service.deleteLogic(Func.toLongList(ids)));
	}

}
