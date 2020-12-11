package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclProjectOutsourcing1Entity;
import org.springblade.contract.service.ISclProjectOutsourcing1Service;
import org.springblade.contract.vo.SclProjectOutsourcing1RequestVO;
import org.springblade.contract.vo.SclProjectOutsourcing1ResponseVO;
import org.springblade.contract.wrapper.SclProjectOutsourcing1Wrapper;
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
 * 生产类：生产项目外包服务合同 控制器
 *
 * @author : kx
 * @date : 2020-12-11 11:05:04
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclProjectOutsourcing1")
@Api(value = "生产类：生产项目外包服务合同", tags = "生产类：生产项目外包服务合同")
public class SclProjectOutsourcing1Controller extends BladeController {

	private ISclProjectOutsourcing1Service sclProjectOutsourcing1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclProjectOutsourcing1")
	@PreAuth("hasPermission('sclProjectOutsourcing1:sclProjectOutsourcing1:detail')")
	public R<SclProjectOutsourcing1ResponseVO> detail(@RequestParam Long id) {
		SclProjectOutsourcing1Entity detail = sclProjectOutsourcing1Service.getById(id);
		return R.data(SclProjectOutsourcing1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclProjectOutsourcing1")
	@PreAuth("hasPermission('sclProjectOutsourcing1:sclProjectOutsourcing1:page')")
	public R<IPage<SclProjectOutsourcing1ResponseVO>> list(SclProjectOutsourcing1RequestVO sclProjectOutsourcing1, Query query) {
		IPage<SclProjectOutsourcing1Entity> pages = sclProjectOutsourcing1Service.pageList(Condition.getPage(query), sclProjectOutsourcing1);
		return R.data(SclProjectOutsourcing1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclProjectOutsourcing1")
	@PreAuth("hasPermission('sclProjectOutsourcing1:sclProjectOutsourcing1:add')")
	public R save(@Valid @RequestBody SclProjectOutsourcing1ResponseVO sclProjectOutsourcing1) {
		return R.status(sclProjectOutsourcing1Service.save(SclProjectOutsourcing1Wrapper.build().PVEntity(sclProjectOutsourcing1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclProjectOutsourcing1")
	@PreAuth("hasPermission('sclProjectOutsourcing1:sclProjectOutsourcing1:update')")
	public R update(@Valid @RequestBody SclProjectOutsourcing1ResponseVO sclProjectOutsourcing1) {
	    if (Func.isEmpty(sclProjectOutsourcing1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclProjectOutsourcing1Service.updateById(SclProjectOutsourcing1Wrapper.build().PVEntity(sclProjectOutsourcing1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclProjectOutsourcing1:sclProjectOutsourcing1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclProjectOutsourcing1Service.deleteLogic(Func.toLongList(ids)));
	}

}
