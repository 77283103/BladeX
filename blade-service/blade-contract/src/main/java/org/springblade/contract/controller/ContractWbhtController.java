package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractWbhtEntity;
import org.springblade.contract.service.IContractWbhtService;
import org.springblade.contract.vo.ContractWbhtRequestVO;
import org.springblade.contract.vo.ContractWbhtResponseVO;
import org.springblade.contract.wrapper.ContractWbhtWrapper;
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
 * 消防-维保合同 控制器
 *
 * @author : kx
 * @date : 2021-05-10 13:41:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractWbht")
@Api(value = "消防-维保合同", tags = "消防-维保合同")
public class ContractWbhtController extends BladeController {

	private IContractWbhtService contractWbhtService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractWbht")
	@PreAuth("hasPermission('contractWbht:contractWbht:detail')")
	public R<ContractWbhtResponseVO> detail(@RequestParam Long id) {
		ContractWbhtEntity detail = contractWbhtService.getById(id);
		return R.data(ContractWbhtWrapper.build().entityPV(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractWbht")
	@PreAuth("hasPermission('contractWbht:contractWbht:page')")
	public R<IPage<ContractWbhtResponseVO>> list(ContractWbhtEntity contractWbht, Query query) {
		IPage<ContractWbhtEntity> pages = contractWbhtService.pageList(Condition.getPage(query), contractWbht);
		return R.data(ContractWbhtWrapper.build().entityPVPage(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入contractWbht")
	@PreAuth("hasPermission('contractWbht:contractWbht:add')")
	public R save(@Valid @RequestBody ContractWbhtRequestVO contractWbht) {
		return R.status(contractWbhtService.save(ContractWbhtWrapper.build().QVEntity(contractWbht)));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractWbht")
	@PreAuth("hasPermission('contractWbht:contractWbht:update')")
	public R update(@Valid @RequestBody ContractWbhtRequestVO contractWbht) {
	    if (Func.isEmpty(contractWbht.getId())){
            throw new ServiceException("id不能为空");
        }
		return R.status(contractWbhtService.updateById(ContractWbhtWrapper.build().QVEntity(contractWbht)));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractWbht:contractWbht:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractWbhtService.deleteLogic(Func.toLongList(ids)));
	}

}
