package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.contract.service.IContractAccordingService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.contract.vo.ContractAccordingRequestVO;
import org.springblade.contract.vo.ContractAccordingResponseVO;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.wrapper.ContractAccordingWrapper;


/**
 * 合同依据管理 控制器
 *
 * @author : XHB
 * @date : 2020-09-24 14:20:29
 */
@RestController
@AllArgsConstructor
@RequestMapping("/according")
@Api(value = "合同依据管理", tags = "合同依据管理")
public class ContractAccordingController extends BladeController {

	private IContractAccordingService accordingService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入according")
	@PreAuth("hasPermission('contract:according:detail')")
	public R<ContractAccordingResponseVO> detail(@RequestParam Long id) {
		ContractAccordingEntity detail = accordingService.getById(id);
		return R.data(ContractAccordingWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入according")
	@PreAuth("hasPermission('contract:according:list')")
	public R<IPage<ContractAccordingResponseVO>> list(ContractAccordingEntity according, Query query) {
		IPage<ContractAccordingEntity> pages = accordingService.pageList(Condition.getPage(query), according);
		return R.data(ContractAccordingWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入according")
	@PreAuth("hasPermission('contract:according:add')")
	public R save(@Valid @RequestBody ContractAccordingRequestVO according) {
        ContractAccordingEntity entity = new ContractAccordingEntity();
        BeanUtil.copy(according,entity);
		return R.status(accordingService.save(entity));
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入according")
	@PreAuth("hasPermission('contract:according:update')")
	public R update(@Valid @RequestBody ContractAccordingRequestVO according) {
	    if (Func.isEmpty(according.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractAccordingEntity entity = new ContractAccordingEntity();
        BeanUtil.copy(according,entity);
		return R.status(accordingService.updateById(entity));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:according:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(accordingService.deleteLogic(Func.toLongList(ids)));
	}

}
