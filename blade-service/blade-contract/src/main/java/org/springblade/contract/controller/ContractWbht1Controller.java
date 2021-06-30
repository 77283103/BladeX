package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractWbht1Entity;
import org.springblade.contract.service.impl.IContractWbht1Service;
import org.springblade.contract.vo.ContractWbht1RequestVO;
import org.springblade.contract.vo.ContractWbht1ResponseVO;
import org.springblade.contract.wrapper.ContractWbht1Wrapper;
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
 * 消防-维保合同子表 控制器
 *
 * @author : kx
 * @date : 2021-05-10 13:40:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractWbht1")
@Api(value = "消防-维保合同子表", tags = "消防-维保合同子表")
public class ContractWbht1Controller extends BladeController {

	private IContractWbht1Service contractWbht1Service;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractWbht1")
	@PreAuth("hasPermission('contractWbht1:contractWbht1:detail')")
	public R<ContractWbht1ResponseVO> detail(@RequestParam Long id) {
		ContractWbht1Entity detail = contractWbht1Service.getById(id);
		return R.data(ContractWbht1Wrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractWbht1")
	@PreAuth("hasPermission('contractWbht1:contractWbht1:page')")
	public R<IPage<ContractWbht1ResponseVO>> list(ContractWbht1Entity contractWbht1, Query query) {
		IPage<ContractWbht1Entity> pages = contractWbht1Service.pageList(Condition.getPage(query), contractWbht1);
		return R.data(ContractWbht1Wrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractWbht1")
	@PreAuth("hasPermission('contractWbht1:contractWbht1:add')")
	public R save(@Valid @RequestBody ContractWbht1RequestVO contractWbht1) {
		return R.status(contractWbht1Service.save(ContractWbht1Wrapper.build().QVEntity(contractWbht1)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractWbht1")
	@PreAuth("hasPermission('contractWbht1:contractWbht1:update')")
	public R update(@Valid @RequestBody ContractWbht1RequestVO contractWbht1) {
	    if (Func.isEmpty(contractWbht1.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractWbht1Service.updateById(ContractWbht1Wrapper.build().QVEntity(contractWbht1)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractWbht1:contractWbht1:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractWbht1Service.deleteLogic(Func.toLongList(ids)));
	}

}
