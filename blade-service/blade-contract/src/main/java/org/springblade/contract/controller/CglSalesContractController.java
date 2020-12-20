package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.CglSalesContractEntity;
import org.springblade.contract.service.ICglSalesContractService;
import org.springblade.contract.vo.CglSalesContractRequestVO;
import org.springblade.contract.vo.CglSalesContractResponseVO;
import org.springblade.contract.wrapper.CglSalesContractWrapper;
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
 * 采购类：买卖合同（国内设备购买） 控制器
 *
 * @author : 王策
 * @date : 2020-12-18 15:36:06
 */
@RestController
@AllArgsConstructor
@RequestMapping("/cglSalesContract")
@Api(value = "采购类：买卖合同（国内设备购买）", tags = "采购类：买卖合同（国内设备购买）")
public class CglSalesContractController extends BladeController {

	private ICglSalesContractService cglSalesContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入cglSalesContract")
	@PreAuth("hasPermission('cglSalesContract:cglSalesContract:detail')")
	public R<CglSalesContractResponseVO> detail(@RequestParam Long id) {
		CglSalesContractEntity detail = cglSalesContractService.getById(id);
		return R.data(CglSalesContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入cglSalesContract")
	@PreAuth("hasPermission('cglSalesContract:cglSalesContract:page')")
	public R<IPage<CglSalesContractResponseVO>> list(CglSalesContractRequestVO cglSalesContract, Query query) {
		IPage<CglSalesContractEntity> pages = cglSalesContractService.pageList(Condition.getPage(query), cglSalesContract);
		return R.data(CglSalesContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入cglSalesContract")
	@PreAuth("hasPermission('cglSalesContract:cglSalesContract:add')")
	public R save(@Valid @RequestBody CglSalesContractResponseVO cglSalesContract) {
		return R.status(cglSalesContractService.save(CglSalesContractWrapper.build().PVEntity(cglSalesContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入cglSalesContract")
	@PreAuth("hasPermission('cglSalesContract:cglSalesContract:update')")
	public R update(@Valid @RequestBody CglSalesContractResponseVO cglSalesContract) {
	    if (Func.isEmpty(cglSalesContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(cglSalesContractService.updateById(CglSalesContractWrapper.build().PVEntity(cglSalesContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('cglSalesContract:cglSalesContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(cglSalesContractService.deleteLogic(Func.toLongList(ids)));
	}

}
