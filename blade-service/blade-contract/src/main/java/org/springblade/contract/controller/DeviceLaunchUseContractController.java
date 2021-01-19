package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.DeviceLaunchUseContractEntity;
import org.springblade.contract.service.IDeviceLaunchUseContractService;
import org.springblade.contract.vo.DeviceLaunchUseContractRequestVO;
import org.springblade.contract.vo.DeviceLaunchUseContractResponseVO;
import org.springblade.contract.wrapper.DeviceLaunchUseContractWrapper;
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
 * 设备投放使用协议 控制器
 *
 * @author : Wang Pengfei
 * @date : 2021-01-19 10:18:09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/deviceLaunchUseContract")
@Api(value = "设备投放使用协议", tags = "设备投放使用协议")
public class DeviceLaunchUseContractController extends BladeController {

	private IDeviceLaunchUseContractService deviceLaunchUseContractService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入deviceLaunchUseContract")
	@PreAuth("hasPermission('deviceLaunchUseContract:deviceLaunchUseContract:detail')")
	public R<DeviceLaunchUseContractResponseVO> detail(@RequestParam Long id) {
		DeviceLaunchUseContractEntity detail = deviceLaunchUseContractService.getById(id);
		return R.data(DeviceLaunchUseContractWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入deviceLaunchUseContract")
	@PreAuth("hasPermission('deviceLaunchUseContract:deviceLaunchUseContract:page')")
	public R<IPage<DeviceLaunchUseContractResponseVO>> list(DeviceLaunchUseContractEntity deviceLaunchUseContract, Query query) {
		IPage<DeviceLaunchUseContractEntity> pages = deviceLaunchUseContractService.pageList(Condition.getPage(query), deviceLaunchUseContract);
		return R.data(DeviceLaunchUseContractWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入deviceLaunchUseContract")
	@PreAuth("hasPermission('deviceLaunchUseContract:deviceLaunchUseContract:add')")
	public R save(@Valid @RequestBody DeviceLaunchUseContractRequestVO deviceLaunchUseContract) {
		return R.status(deviceLaunchUseContractService.save(DeviceLaunchUseContractWrapper.build().QVEntity(deviceLaunchUseContract)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入deviceLaunchUseContract")
	@PreAuth("hasPermission('deviceLaunchUseContract:deviceLaunchUseContract:update')")
	public R update(@Valid @RequestBody DeviceLaunchUseContractRequestVO deviceLaunchUseContract) {
	    if (Func.isEmpty(deviceLaunchUseContract.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(deviceLaunchUseContractService.updateById(DeviceLaunchUseContractWrapper.build().QVEntity(deviceLaunchUseContract)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('deviceLaunchUseContract:deviceLaunchUseContract:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(deviceLaunchUseContractService.deleteLogic(Func.toLongList(ids)));
	}

}
