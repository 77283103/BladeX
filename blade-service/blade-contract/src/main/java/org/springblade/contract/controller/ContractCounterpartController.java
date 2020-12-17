package org.springblade.contract.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.service.IContractCounterpartService;
import org.springblade.contract.vo.ContractCounterpartRequestVO;
import org.springblade.contract.vo.ContractCounterpartResponseVO;
import org.springblade.contract.wrapper.ContractCounterpartWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * 相对方管理 控制器
 *
 * @author : XHB
 * @date : 2020-09-23 19:35:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/counterpart")
@Api(value = "相对方管理", tags = "相对方管理")
public class ContractCounterpartController extends BladeController {

	private IContractCounterpartService counterpartService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入counterpart")
	@PreAuth("hasPermission('contract:counterpart:detail')")
	public R<ContractCounterpartResponseVO> detail(@RequestParam Long id) {
		ContractCounterpartEntity detail = counterpartService.getById(id);
		return R.data(ContractCounterpartWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入counterpart")
	@PreAuth("hasPermission('contract:counterpart:list')")
	public R<IPage<ContractCounterpartResponseVO>> list(ContractCounterpartRequestVO counterpart, Query query) {
		IPage<ContractCounterpartEntity> pages = counterpartService.pageList(Condition.getPage(query), counterpart);
		return R.data(ContractCounterpartWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入counterpart")
	@PreAuth("hasPermission('contract:counterpart:add')")
	public R save(@Valid @RequestBody ContractCounterpartRequestVO counterpart) {
        ContractCounterpartEntity entity = new ContractCounterpartEntity();
        BeanUtil.copy(counterpart,entity);
		return R.status(counterpartService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入counterpart")
	@PreAuth("hasPermission('contract:counterpart:update')")
	public R update(@Valid @RequestBody ContractCounterpartRequestVO counterpart) {
	    if (Func.isEmpty(counterpart.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractCounterpartEntity entity = new ContractCounterpartEntity();
        BeanUtil.copy(counterpart,entity);
		return R.status(counterpartService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:counterpart:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(counterpartService.deleteLogic(Func.toLongList(ids)));
	}

}
