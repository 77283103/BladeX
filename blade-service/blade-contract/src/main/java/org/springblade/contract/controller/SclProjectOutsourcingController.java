package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclProjectOutsourcingEntity;
import org.springblade.contract.service.ISclProjectOutsourcingService;
import org.springblade.contract.vo.SclProjectOutsourcingRequestVO;
import org.springblade.contract.vo.SclProjectOutsourcingResponseVO;
import org.springblade.contract.wrapper.SclProjectOutsourcingWrapper;
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
 * @date : 2020-12-11 11:03:44
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclProjectOutsourcing")
@Api(value = "生产类：生产项目外包服务合同", tags = "生产类：生产项目外包服务合同")
public class SclProjectOutsourcingController extends BladeController {

	private ISclProjectOutsourcingService sclProjectOutsourcingService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclProjectOutsourcing")
	@PreAuth("hasPermission('sclProjectOutsourcing:sclProjectOutsourcing:detail')")
	public R<SclProjectOutsourcingResponseVO> detail(@RequestParam Long id) {
		SclProjectOutsourcingEntity detail = sclProjectOutsourcingService.getById(id);
		return R.data(SclProjectOutsourcingWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclProjectOutsourcing")
	@PreAuth("hasPermission('sclProjectOutsourcing:sclProjectOutsourcing:page')")
	public R<IPage<SclProjectOutsourcingResponseVO>> list(SclProjectOutsourcingRequestVO sclProjectOutsourcing, Query query) {
		IPage<SclProjectOutsourcingEntity> pages = sclProjectOutsourcingService.pageList(Condition.getPage(query), sclProjectOutsourcing);
		return R.data(SclProjectOutsourcingWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclProjectOutsourcing")
	@PreAuth("hasPermission('sclProjectOutsourcing:sclProjectOutsourcing:add')")
	public R save(@Valid @RequestBody SclProjectOutsourcingResponseVO sclProjectOutsourcing) {
		return R.status(sclProjectOutsourcingService.save(SclProjectOutsourcingWrapper.build().PVEntity(sclProjectOutsourcing)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclProjectOutsourcing")
	@PreAuth("hasPermission('sclProjectOutsourcing:sclProjectOutsourcing:update')")
	public R update(@Valid @RequestBody SclProjectOutsourcingResponseVO sclProjectOutsourcing) {
	    if (Func.isEmpty(sclProjectOutsourcing.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclProjectOutsourcingService.updateById(SclProjectOutsourcingWrapper.build().PVEntity(sclProjectOutsourcing)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclProjectOutsourcing:sclProjectOutsourcing:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclProjectOutsourcingService.deleteLogic(Func.toLongList(ids)));
	}

}
