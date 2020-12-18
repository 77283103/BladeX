package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.YwbDirectSalesCustomersEntity;
import org.springblade.contract.service.IYwbDirectSalesCustomersService;
import org.springblade.contract.vo.YwbDirectSalesCustomersRequestVO;
import org.springblade.contract.vo.YwbDirectSalesCustomersResponseVO;
import org.springblade.contract.wrapper.YwbDirectSalesCustomersWrapper;
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
 * 业务类：22.直营客户促销执行通知函 控制器
 *
 * @author : 业务类：22.直营客户促销执行通知函
 * @date : 2020-12-18 16:06:12
 */
@RestController
@AllArgsConstructor
@RequestMapping("/ywbDirectSalesCustomers")
@Api(value = "业务类：22.直营客户促销执行通知函", tags = "业务类：22.直营客户促销执行通知函")
public class YwbDirectSalesCustomersController extends BladeController {

	private IYwbDirectSalesCustomersService ywbDirectSalesCustomersService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入ywbDirectSalesCustomers")
	@PreAuth("hasPermission('ywbDirectSalesCustomers:ywbDirectSalesCustomers:detail')")
	public R<YwbDirectSalesCustomersResponseVO> detail(@RequestParam Long id) {
		YwbDirectSalesCustomersEntity detail = ywbDirectSalesCustomersService.getById(id);
		return R.data(YwbDirectSalesCustomersWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入ywbDirectSalesCustomers")
	@PreAuth("hasPermission('ywbDirectSalesCustomers:ywbDirectSalesCustomers:page')")
	public R<IPage<YwbDirectSalesCustomersResponseVO>> list(YwbDirectSalesCustomersRequestVO ywbDirectSalesCustomers, Query query) {
		IPage<YwbDirectSalesCustomersEntity> pages = ywbDirectSalesCustomersService.pageList(Condition.getPage(query), ywbDirectSalesCustomers);
		return R.data(YwbDirectSalesCustomersWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入ywbDirectSalesCustomers")
	@PreAuth("hasPermission('ywbDirectSalesCustomers:ywbDirectSalesCustomers:add')")
	public R save(@Valid @RequestBody YwbDirectSalesCustomersResponseVO ywbDirectSalesCustomers) {
		return R.status(ywbDirectSalesCustomersService.save(YwbDirectSalesCustomersWrapper.build().PVEntity(ywbDirectSalesCustomers)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入ywbDirectSalesCustomers")
	@PreAuth("hasPermission('ywbDirectSalesCustomers:ywbDirectSalesCustomers:update')")
	public R update(@Valid @RequestBody YwbDirectSalesCustomersResponseVO ywbDirectSalesCustomers) {
	    if (Func.isEmpty(ywbDirectSalesCustomers.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(ywbDirectSalesCustomersService.updateById(YwbDirectSalesCustomersWrapper.build().PVEntity(ywbDirectSalesCustomers)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('ywbDirectSalesCustomers:ywbDirectSalesCustomers:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(ywbDirectSalesCustomersService.deleteLogic(Func.toLongList(ids)));
	}

}
