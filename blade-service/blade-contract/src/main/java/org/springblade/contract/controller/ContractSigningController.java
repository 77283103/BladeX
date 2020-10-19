package org.springblade.contract.controller;

import io.swagger.annotations.Api;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.log.exception.ServiceException;
import org.springblade.contract.vo.ContractSigningRequestVO;
import org.springblade.contract.vo.ContractSigningResponseVO;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.boot.ctrl.BladeController;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springblade.contract.entity.ContractSigningEntity;
import org.springblade.contract.wrapper.ContractSigningWrapper;
import org.springblade.contract.service.IContractSigningService;


/**
 * 合同签订表 控制器
 *
 * @author : liyj
 * @date : 2020-09-23 19:27:05
 */
@RestController
@AllArgsConstructor
@RequestMapping("/signing")
@Api(value = "合同签订表", tags = "合同签订表")
public class ContractSigningController extends BladeController {

	private IContractSigningService signingService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入signing")
	@PreAuth("hasPermission('contract:signing:detail')")
	public R<ContractSigningResponseVO> detail(@RequestParam Long id) {
		ContractSigningEntity detail = signingService.getById(id);
		return R.data(ContractSigningWrapper.build().entityVO(detail));
	}

	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入signing")
	@PreAuth("hasPermission('contract:signing:list')")
	public R<IPage<ContractSigningResponseVO>> list(ContractSigningEntity signing, Query query) {
		IPage<ContractSigningEntity> pages = signingService.pageList(Condition.getPage(query), signing);
		return R.data(ContractSigningWrapper.build().pageVO(pages));
	}

	/**
	 * 新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入signing")
	@PreAuth("hasPermission('contract:signing:add')")
	public R save(@Valid @RequestBody ContractSigningRequestVO signing) {
        ContractSigningEntity entity = new ContractSigningEntity();
        BeanUtil.copy(signing,entity);
		signingService.save(entity);
		//从实体中获取签订，合同id将值赋给vo的id
		signing.setId(entity.getId());
		signingService.saveSigning(signing);
		return R.data(entity);
	}

	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入signing")
	@PreAuth("hasPermission('contract:signing:update')")
	public R update(@Valid @RequestBody ContractSigningRequestVO signing) {
	    if (Func.isEmpty(signing.getId())){
            throw new ServiceException("id不能为空");
        }
	    ContractSigningEntity entity = new ContractSigningEntity();
        BeanUtil.copy(signing,entity);
		return R.status(signingService.updateById(entity));
	}


	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contract:signing:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(signingService.deleteLogic(Func.toLongList(ids)));
	}

}
