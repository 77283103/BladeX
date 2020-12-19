package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ActAppDatabasechangeloglockEntity;
import org.springblade.contract.service.IActAppDatabasechangeloglockService;
import org.springblade.contract.vo.ActAppDatabasechangeloglockRequestVO;
import org.springblade.contract.vo.ActAppDatabasechangeloglockResponseVO;
import org.springblade.contract.wrapper.ActAppDatabasechangeloglockWrapper;
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
 * 售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1) 控制器
 *
 * @author : 售货机类
 * @date : 2020-12-18 16:13:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/actAppDatabasechangeloglock")
@Api(value = "售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1)", tags = "售货机类：2019.12.04修-售货机设备租赁合同—通用版（不可销售自选产品版本）补充协议（20191129）(1)(1)")
public class ActAppDatabasechangeloglockController extends BladeController {

	private IActAppDatabasechangeloglockService actAppDatabasechangeloglockService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入actAppDatabasechangeloglock")
	@PreAuth("hasPermission('actAppDatabasechangeloglock:actAppDatabasechangeloglock:detail')")
	public R<ActAppDatabasechangeloglockResponseVO> detail(@RequestParam Long id) {
		ActAppDatabasechangeloglockEntity detail = actAppDatabasechangeloglockService.getById(id);
		return R.data(ActAppDatabasechangeloglockWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入actAppDatabasechangeloglock")
	@PreAuth("hasPermission('actAppDatabasechangeloglock:actAppDatabasechangeloglock:page')")
	public R<IPage<ActAppDatabasechangeloglockResponseVO>> list(ActAppDatabasechangeloglockRequestVO actAppDatabasechangeloglock, Query query) {
		IPage<ActAppDatabasechangeloglockEntity> pages = actAppDatabasechangeloglockService.pageList(Condition.getPage(query), actAppDatabasechangeloglock);
		return R.data(ActAppDatabasechangeloglockWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入actAppDatabasechangeloglock")
	@PreAuth("hasPermission('actAppDatabasechangeloglock:actAppDatabasechangeloglock:add')")
	public R save(@Valid @RequestBody ActAppDatabasechangeloglockResponseVO actAppDatabasechangeloglock) {
		return R.status(actAppDatabasechangeloglockService.save(ActAppDatabasechangeloglockWrapper.build().PVEntity(actAppDatabasechangeloglock)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入actAppDatabasechangeloglock")
	@PreAuth("hasPermission('actAppDatabasechangeloglock:actAppDatabasechangeloglock:update')")
	public R update(@Valid @RequestBody ActAppDatabasechangeloglockResponseVO actAppDatabasechangeloglock) {
	    if (Func.isEmpty(actAppDatabasechangeloglock.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(actAppDatabasechangeloglockService.updateById(ActAppDatabasechangeloglockWrapper.build().PVEntity(actAppDatabasechangeloglock)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('actAppDatabasechangeloglock:actAppDatabasechangeloglock:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(actAppDatabasechangeloglockService.deleteLogic(Func.toLongList(ids)));
	}

}
