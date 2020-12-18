package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.SclLogisticsServiceEntity;
import org.springblade.contract.service.ISclLogisticsServiceService;
import org.springblade.contract.vo.SclLogisticsServiceRequestVO;
import org.springblade.contract.vo.SclLogisticsServiceResponseVO;
import org.springblade.contract.wrapper.SclLogisticsServiceWrapper;
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
 * 生产类：物流服务合同（二段配送） 控制器
 *
 * @author : kx
 * @date : 2020-12-18 17:17:38
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sclLogisticsService")
@Api(value = "生产类：物流服务合同（二段配送）", tags = "生产类：物流服务合同（二段配送）")
public class SclLogisticsServiceController extends BladeController {

	private ISclLogisticsServiceService sclLogisticsServiceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入sclLogisticsService")
	@PreAuth("hasPermission('sclLogisticsService:sclLogisticsService:detail')")
	public R<SclLogisticsServiceResponseVO> detail(@RequestParam Long id) {
		SclLogisticsServiceEntity detail = sclLogisticsServiceService.getById(id);
		return R.data(SclLogisticsServiceWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入sclLogisticsService")
	@PreAuth("hasPermission('sclLogisticsService:sclLogisticsService:page')")
	public R<IPage<SclLogisticsServiceResponseVO>> list(SclLogisticsServiceRequestVO sclLogisticsService, Query query) {
		IPage<SclLogisticsServiceEntity> pages = sclLogisticsServiceService.pageList(Condition.getPage(query), sclLogisticsService);
		return R.data(SclLogisticsServiceWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入sclLogisticsService")
	@PreAuth("hasPermission('sclLogisticsService:sclLogisticsService:add')")
	public R save(@Valid @RequestBody SclLogisticsServiceResponseVO sclLogisticsService) {
		return R.status(sclLogisticsServiceService.save(SclLogisticsServiceWrapper.build().PVEntity(sclLogisticsService)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入sclLogisticsService")
	@PreAuth("hasPermission('sclLogisticsService:sclLogisticsService:update')")
	public R update(@Valid @RequestBody SclLogisticsServiceResponseVO sclLogisticsService) {
	    if (Func.isEmpty(sclLogisticsService.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(sclLogisticsServiceService.updateById(SclLogisticsServiceWrapper.build().PVEntity(sclLogisticsService)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('sclLogisticsService:sclLogisticsService:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(sclLogisticsServiceService.deleteLogic(Func.toLongList(ids)));
	}

}
