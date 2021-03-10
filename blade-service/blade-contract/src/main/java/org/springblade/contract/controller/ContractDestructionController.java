package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractDestructionEntity;
import org.springblade.contract.service.IContractDestructionService;
import org.springblade.contract.vo.ContractDestructionRequestVO;
import org.springblade.contract.vo.ContractDestructionResponseVO;
import org.springblade.contract.wrapper.ContractDestructionWrapper;
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
 * 合同销毁 控制器
 *
 * @author : szw
 * @date : 2020-11-11 16:37:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractDestruction")
@Api(value = "合同销毁", tags = "合同销毁")
public class ContractDestructionController extends BladeController {

	private IContractDestructionService contractDestructionService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractDestruction")
	@PreAuth("hasPermission('contractDestruction:contractDestruction:detail')")
	public R<ContractDestructionResponseVO> detail(@RequestParam Long id) {
		ContractDestructionEntity detail = contractDestructionService.getById(id);
		return R.data(ContractDestructionWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractDestruction")
	@PreAuth("hasPermission('contractDestruction:contractDestruction:page')")
	public R<IPage<ContractDestructionResponseVO>> list(ContractDestructionRequestVO contractDestruction, Query query) {
		IPage<ContractDestructionEntity> pages = contractDestructionService.pageList(Condition.getPage(query), contractDestruction);
		return R.data(ContractDestructionWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增", notes = "传入contractDestruction")
	@PreAuth("hasPermission('contractDestruction:contractDestruction:add')")
	public R save(@Valid @RequestBody ContractDestructionResponseVO contractDestruction) {
		return R.status(contractDestructionService.add(contractDestruction));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改", notes = "传入contractDestruction")
	@PreAuth("hasPermission('contractDestruction:contractDestruction:update')")
	public R update(@Valid @RequestBody ContractDestructionResponseVO contractDestruction) {
	    if (Func.isEmpty(contractDestruction.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractDestructionService.updateById(ContractDestructionWrapper.build().PVEntity(contractDestruction)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractDestruction:contractDestruction:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractDestructionService.deleteLogic(Func.toLongList(ids)));
	}

}
