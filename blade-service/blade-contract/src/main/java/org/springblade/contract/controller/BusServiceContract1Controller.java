package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.BusServiceContract1Entity;
import org.springblade.contract.service.IBusServiceContract1Service;
import org.springblade.contract.vo.BusServiceContract1RequestVO;
import org.springblade.contract.vo.BusServiceContract1ResponseVO;
import org.springblade.contract.wrapper.BusServiceContract1Wrapper;
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
 * 班车服务合同子表1 控制器
 *
 * @author : Wang Pengfei
 * @date : 2021-01-19 10:29:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/busServiceContract1")
@Api(value = "班车服务合同子表1", tags = "班车服务合同子表1")
public class BusServiceContract1Controller extends BladeController {

	private IBusServiceContract1Service busServiceContract1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入busServiceContract1")
	@PreAuth("hasPermission('busServiceContract1:busServiceContract1:detail')")
	public R<BusServiceContract1ResponseVO> detail(@RequestParam Long id) {
		BusServiceContract1Entity detail = busServiceContract1Service.getById(id);
		return R.data(BusServiceContract1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入busServiceContract1")
	@PreAuth("hasPermission('busServiceContract1:busServiceContract1:page')")
	public R<IPage<BusServiceContract1ResponseVO>> list(BusServiceContract1Entity busServiceContract1, Query query) {
		IPage<BusServiceContract1Entity> pages = busServiceContract1Service.pageList(Condition.getPage(query), busServiceContract1);
		return R.data(BusServiceContract1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入busServiceContract1")
	@PreAuth("hasPermission('busServiceContract1:busServiceContract1:add')")
	public R save(@Valid @RequestBody BusServiceContract1RequestVO busServiceContract1) {
		return R.status(busServiceContract1Service.save(BusServiceContract1Wrapper.build().QVEntity(busServiceContract1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入busServiceContract1")
	@PreAuth("hasPermission('busServiceContract1:busServiceContract1:update')")
	public R update(@Valid @RequestBody BusServiceContract1RequestVO busServiceContract1) {
	    if (Func.isEmpty(busServiceContract1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(busServiceContract1Service.updateById(BusServiceContract1Wrapper.build().QVEntity(busServiceContract1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('busServiceContract1:busServiceContract1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(busServiceContract1Service.deleteLogic(Func.toLongList(ids)));
	}

}
