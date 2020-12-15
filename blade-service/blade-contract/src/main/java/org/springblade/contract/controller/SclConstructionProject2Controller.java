package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclConstructionProject2Entity;
import org.springblade.contract.service.ISclConstructionProject2Service;
import org.springblade.contract.vo.SclConstructionProject2RequestVO;
import org.springblade.contract.vo.SclConstructionProject2ResponseVO;
import org.springblade.contract.wrapper.SclConstructionProject2Wrapper;
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
 * 生产类：加工承揽合同（代工合同）关联表2 控制器
 *
 * @author : 生产类：加工承揽合同（代工合同）关联表2
 * @date : 2020-12-11 10:22:06
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclConstructionProject2")
@Api(value = "生产类：加工承揽合同（代工合同）关联表2", tags = "生产类：加工承揽合同（代工合同）关联表2")
public class SclConstructionProject2Controller extends BladeController {

	private ISclConstructionProject2Service sclConstructionProject2Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclConstructionProject2")
	@PreAuth("hasPermission('sclConstructionProject2:sclConstructionProject2:detail')")
	public R<SclConstructionProject2ResponseVO> detail(@RequestParam Long id) {
		SclConstructionProject2Entity detail = sclConstructionProject2Service.getById(id);
		return R.data(SclConstructionProject2Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclConstructionProject2")
	@PreAuth("hasPermission('sclConstructionProject2:sclConstructionProject2:page')")
	public R<IPage<SclConstructionProject2ResponseVO>> list(SclConstructionProject2RequestVO sclConstructionProject2, Query query) {
		IPage<SclConstructionProject2Entity> pages = sclConstructionProject2Service.pageList(Condition.getPage(query), sclConstructionProject2);
		return R.data(SclConstructionProject2Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclConstructionProject2")
	@PreAuth("hasPermission('sclConstructionProject2:sclConstructionProject2:add')")
	public R save(@Valid @RequestBody SclConstructionProject2ResponseVO sclConstructionProject2) {
		return R.status(sclConstructionProject2Service.save(SclConstructionProject2Wrapper.build().PVEntity(sclConstructionProject2)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclConstructionProject2")
	@PreAuth("hasPermission('sclConstructionProject2:sclConstructionProject2:update')")
	public R update(@Valid @RequestBody SclConstructionProject2ResponseVO sclConstructionProject2) {
	    if (Func.isEmpty(sclConstructionProject2.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclConstructionProject2Service.updateById(SclConstructionProject2Wrapper.build().PVEntity(sclConstructionProject2)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclConstructionProject2:sclConstructionProject2:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclConstructionProject2Service.deleteLogic(Func.toLongList(ids)));
	}

}
