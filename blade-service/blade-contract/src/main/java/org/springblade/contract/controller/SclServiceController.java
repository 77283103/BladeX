package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclServiceEntity;
import org.springblade.contract.service.ISclServiceService;
import org.springblade.contract.vo.SclServiceRequestVO;
import org.springblade.contract.vo.SclServiceResponseVO;
import org.springblade.contract.wrapper.SclServiceWrapper;
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
 * 生产类：物流服务合同（二段仓储+配送） 控制器
 *
 * @author : kx
 * @date : 2020-12-18 17:07:57
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclService")
@Api(value = "生产类：物流服务合同（二段仓储+配送）", tags = "生产类：物流服务合同（二段仓储+配送）")
public class SclServiceController extends BladeController {

	private ISclServiceService sclServiceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclService")
	@PreAuth("hasPermission('sclService:sclService:detail')")
	public R<SclServiceResponseVO> detail(@RequestParam Long id) {
		SclServiceEntity detail = sclServiceService.getById(id);
		return R.data(SclServiceWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclService")
	@PreAuth("hasPermission('sclService:sclService:page')")
	public R<IPage<SclServiceResponseVO>> list(SclServiceRequestVO sclService, Query query) {
		IPage<SclServiceEntity> pages = sclServiceService.pageList(Condition.getPage(query), sclService);
		return R.data(SclServiceWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclService")
	@PreAuth("hasPermission('sclService:sclService:add')")
	public R save(@Valid @RequestBody SclServiceResponseVO sclService) {
		return R.status(sclServiceService.save(SclServiceWrapper.build().PVEntity(sclService)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclService")
	@PreAuth("hasPermission('sclService:sclService:update')")
	public R update(@Valid @RequestBody SclServiceResponseVO sclService) {
	    if (Func.isEmpty(sclService.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclServiceService.updateById(SclServiceWrapper.build().PVEntity(sclService)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclService:sclService:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclServiceService.deleteLogic(Func.toLongList(ids)));
	}

}
