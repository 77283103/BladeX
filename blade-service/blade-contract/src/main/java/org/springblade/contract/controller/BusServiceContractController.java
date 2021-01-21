package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.BusServiceContractEntity;
import org.springblade.contract.service.IBusServiceContractService;
import org.springblade.contract.vo.BusServiceContractRequestVO;
import org.springblade.contract.vo.BusServiceContractResponseVO;
import org.springblade.contract.wrapper.BusServiceContractWrapper;
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
 * 班车服务合同 控制器
 *
 * @author : Wang Pengfei
 * @date : 2021-01-19 10:25:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/busServiceContract")
@Api(value = "班车服务合同", tags = "班车服务合同")
public class BusServiceContractController extends BladeController {

	private IBusServiceContractService busServiceContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入busServiceContract")
	@PreAuth("hasPermission('busServiceContract:busServiceContract:detail')")
	public R<BusServiceContractResponseVO> detail(@RequestParam Long id) {
		BusServiceContractEntity detail = busServiceContractService.getById(id);
		return R.data(BusServiceContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入busServiceContract")
	@PreAuth("hasPermission('busServiceContract:busServiceContract:page')")
	public R<IPage<BusServiceContractResponseVO>> list(BusServiceContractEntity busServiceContract, Query query) {
		IPage<BusServiceContractEntity> pages = busServiceContractService.pageList(Condition.getPage(query), busServiceContract);
		return R.data(BusServiceContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入busServiceContract")
	@PreAuth("hasPermission('busServiceContract:busServiceContract:add')")
	public R save(@Valid @RequestBody BusServiceContractRequestVO busServiceContract) {
		return R.status(busServiceContractService.save(BusServiceContractWrapper.build().QVEntity(busServiceContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入busServiceContract")
	@PreAuth("hasPermission('busServiceContract:busServiceContract:update')")
	public R update(@Valid @RequestBody BusServiceContractRequestVO busServiceContract) {
	    if (Func.isEmpty(busServiceContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(busServiceContractService.updateById(BusServiceContractWrapper.build().QVEntity(busServiceContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('busServiceContract:busServiceContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(busServiceContractService.deleteLogic(Func.toLongList(ids)));
	}

}
