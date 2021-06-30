package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.entity.ContractMmhtEntity;
import org.springblade.contract.service.impl.IContractMmhtService;
import org.springblade.contract.vo.ContractMmhtRequestVO;
import org.springblade.contract.vo.ContractMmhtResponseVO;
import org.springblade.contract.wrapper.ContractMmhtWrapper;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;



/**
 * 国内设备买卖合同 控制器
 *
 * @author : kx
 * @date : 2021-05-10 13:39:14
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractMmht")
@Api(value = "国内设备买卖合同", tags = "国内设备买卖合同")
public class ContractMmhtController extends BladeController {

	private IContractMmhtService contractMmhtService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractMmht")
	@PreAuth("hasPermission('contractMmht:contractMmht:detail')")
	public R<ContractMmhtResponseVO> detail(@RequestParam Long id) {
		ContractMmhtEntity detail = contractMmhtService.getById(id);
		return R.data(ContractMmhtWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractMmht")
	@PreAuth("hasPermission('contractMmht:contractMmht:page')")
	public R<IPage<ContractMmhtResponseVO>> list(ContractMmhtEntity contractMmht, Query query) {
		IPage<ContractMmhtEntity> pages = contractMmhtService.pageList(Condition.getPage(query), contractMmht);
		return R.data(ContractMmhtWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractMmht")
	@PreAuth("hasPermission('contractMmht:contractMmht:add')")
	public R save(@Valid @RequestBody ContractMmhtRequestVO contractMmht) {
		return R.status(contractMmhtService.save(ContractMmhtWrapper.build().QVEntity(contractMmht)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractMmht")
	@PreAuth("hasPermission('contractMmht:contractMmht:update')")
	public R update(@Valid @RequestBody ContractMmhtRequestVO contractMmht) {
	    if (Func.isEmpty(contractMmht.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractMmhtService.updateById(ContractMmhtWrapper.build().QVEntity(contractMmht)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractMmht:contractMmht:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractMmhtService.deleteLogic(Func.toLongList(ids)));
	}

}
